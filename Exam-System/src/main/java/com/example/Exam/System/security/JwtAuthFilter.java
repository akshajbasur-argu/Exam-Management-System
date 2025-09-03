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
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserRepo userRepo;
    private final AuthUtil authUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("incoming request: {}", request.getRequestURI());
        final String requestTokenheader = request.getHeader("Authorization");
        if(requestTokenheader==null || !requestTokenheader.startsWith("Bearer"))
        {
            filterChain.doFilter(request,response);
            return;
        }

        String token = requestTokenheader.split("Bearer")[1];
        String username= authUtil.getUsernameFromToken(token);

        if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
            User user=userRepo.findByUsername(username).orElseThrow();
            UsernamePasswordAuthenticationToken token1 = new UsernamePasswordAuthenticationToken(
                    user,null,user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(token1);

        }
        filterChain.doFilter(request,response);
    }
}
