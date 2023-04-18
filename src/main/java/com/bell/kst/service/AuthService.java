package com.bell.kst.service;

import com.bell.kst.dto.AuthDTO;
import com.bell.kst.entity.Role;
import com.bell.kst.repository.AuthRepository;
import com.bell.kst.repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<AuthDTO> fetchAuthInfo(Integer seq) {
        Role role = roleRepository.findBySeq(seq);
        List<AuthDTO> auth = authRepository.findByRole(role).stream().map(a -> {
                    AuthDTO authDTO = new AuthDTO();
                    BeanUtils.copyProperties(a, authDTO);
                    authDTO.setMenu(a.getMenu().getPath());
                    return authDTO;
                })
                .collect(Collectors.toList());
        return auth;
    }
}
