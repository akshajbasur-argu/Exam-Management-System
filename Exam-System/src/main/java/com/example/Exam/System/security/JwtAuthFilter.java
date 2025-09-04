package com.example.Exam.System.security;

import com.example.Exam.System.entity.User;
import com.example.Exam.System.repository.UserRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderThreadLocalAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserRepo userRepo;
    private final AuthUtil authUtil;
    private final HandlerExceptionResolver handlerExceptionResolver;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try{
            log.info("incoming request: {}", request.getRequestURI());
            final String requestTokenheader = request.getHeader("Authorization");
            if(requestTokenheader==null || !requestTokenheader.startsWith("Bearer"))
            {
                filterChain.doFilter(request,response);
                return;
            }

            String token = requestTokenheader.split(" ")[1];
            String username= authUtil.getUsernameFromToken(token);

            if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
                User user=userRepo.findByUsername(username).orElseThrow();
                UsernamePasswordAuthenticationToken token1 = new UsernamePasswordAuthenticationToken(
                        user,null,user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(token1);

            }
            filterChain.doFilter(request,response);
        }
        catch (NoHandlerFoundException e){
            handlerExceptionResolver.resolveException(request,response,null,e);
        }
        catch (Exception exception) {
            handlerExceptionResolver.resolveException(request,response,null,exception);
        }
    }
}
