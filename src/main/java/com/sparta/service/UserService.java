package com.sparta.service;


import com.sparta.domain.UserRepository;
import com.sparta.domain.Users;
import com.sparta.domain.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(SignupRequestDto requestDto) {
// 회원 ID 중복 확인
        String username = requestDto.getUsername();
        Optional<Users> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }
        String id = "^[a-zA-Z0-9]{3,}$";
        if(Pattern.matches(id,username)==false){
            throw new IllegalArgumentException("최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 입력해주세요");
        }
// 패스워드 암호화
        if(Pattern.matches(username,requestDto.getPassword()) || requestDto.getPassword().length() < 4 ){
            throw new IllegalArgumentException("최소 4자 이상, username와 다르게 해주세요");
        }
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();

// 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        Users user = new Users(username, password, email, role);
        userRepository.save(user);
    }
}