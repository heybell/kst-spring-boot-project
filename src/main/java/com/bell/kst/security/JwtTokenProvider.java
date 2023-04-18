package com.bell.kst.security;

import com.bell.kst.dto.JwtUserInfoDTO;
import com.bell.kst.dto.UserDTO;
import com.bell.kst.entity.User;
import com.bell.kst.service.CustomUserDetailsService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret-key}")
    private String SECRET_KEY;

    @Value("${jwt.expiration-time}")
    private long EXPIRATION_TIME;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // JWT 토큰 발급
    public String generateToken(String email, JwtUserInfoDTO dto) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME);

        String token = Jwts.builder()
                .setSubject(email)
                .claim("user", dto)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        return token;
    }

    // JWT 토큰 검증
    public boolean validateToken(String token) {
        try {
            // 토큰을 비밀 키를 사용하여 검증
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | SignatureException e) {
            throw new RuntimeException("An exception occurred while validating the token.", e);
        }
    }

    // JWT 토큰에서 사용자 정보 추출
    public String getUsernameFromToken(String token) {
        try {
            // 토큰에서 클레임(Claims) 추출하여 사용자 이름 반환
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            return claims.getSubject();
        } catch (ExpiredJwtException | SignatureException e) {
            throw new RuntimeException("An exception occurred while retrieving the username from the token.", e);
        }
    }

    public Authentication getAuthentication(String token) {
        String username = getUsernameFromToken(token); // 토큰에서 사용자 이름 추출
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username); // 사용자 이름을 이용하여 UserDetails 객체 조회

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}