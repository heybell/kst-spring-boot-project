package com.bell.kst.controller;

import com.bell.kst.constants.StatusCode;
import com.bell.kst.dto.AuthDTO;
import com.bell.kst.dto.JwtUserInfoDTO;
import com.bell.kst.dto.StatusCodeDTO;
import com.bell.kst.dto.UserDTO;
import com.bell.kst.security.JwtTokenProvider;
import com.bell.kst.service.AuthService;
import com.bell.kst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "auth")
public class AuthController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping(value = "signin")
    public ResponseEntity<?> signin(HttpServletResponse response, @RequestBody UserDTO param) {
        try {
            String email = param.getEmail();
            String password = param.getPassword();

            JwtUserInfoDTO user = userService.fetchUserInfo(email, password);
            if (user == null)
                return ResponseEntity.ok(new StatusCodeDTO(StatusCode.SIGNIN_FAIL.getCode(), StatusCode.SIGNIN_FAIL.getMessage()));

            String token = jwtTokenProvider.generateToken(email, user);
            response.setHeader("Authorization", "Bearer " + token);
            
            List<AuthDTO> auth = authService.fetchAuthInfo(user.getRole());

            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("user", user);
            responseMap.put("auth", auth);
            responseMap.put("status", new StatusCodeDTO(StatusCode.SIGNIN_SUCCESS.getCode(), StatusCode.SIGNIN_SUCCESS.getMessage()));

            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            throw new RuntimeException("An exception occurred while processing the signin endpoint.", e);
        }
    }
}
