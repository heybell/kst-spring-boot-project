package com.bell.kst.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Value("${unauthenticated.urls}")
    private String unauthenticatedUrls;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String[] unauthUrls = unauthenticatedUrls.split(",");
        boolean isUnauthenticatedURL = Arrays.stream(unauthUrls).anyMatch(url -> new AntPathRequestMatcher(url).matches(request));
        if (isUnauthenticatedURL) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("Authorization");
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        token = token.substring(7);
        if (!jwtTokenProvider.validateToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰 유효성 검사를 완료한 후, 인증된 사용자로 설정
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}

