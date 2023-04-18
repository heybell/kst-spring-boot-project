package com.bell.kst.service;

import com.bell.kst.dto.JwtUserInfoDTO;
import com.bell.kst.entity.User;
import com.bell.kst.repository.UserRepository;
import com.bell.kst.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public JwtUserInfoDTO fetchUserInfo(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user == null) return null;
        if (!passwordEncoder.matches(password, user.getPassword())) return null;

        return new JwtUserInfoDTO(user.getSeq(), user.getName(), user.getRole().getSeq());
    }
}
