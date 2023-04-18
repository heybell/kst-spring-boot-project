package com.bell.kst.service;

import com.bell.kst.entity.User;
import com.bell.kst.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // User 엔티티를 조회하기 위한 UserRepository

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username); // 이 부분은 User 엔티티에서 사용자를 조회하는 방식에 따라 변경될 수 있습니다.
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // User 엔티티를 기반으로 UserDetails 객체 생성 및 반환
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword()) // 패스워드 암호화를 위한 처리가 필요할 수 있습니다.
                .roles(user.getRole().getRole()) // 사용자 권한 정보
                .build();
    }
}
