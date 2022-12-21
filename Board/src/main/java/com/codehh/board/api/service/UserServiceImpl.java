package com.codehh.board.api.service;

import com.codehh.board.api.dto.user.request.JoinReq;
import com.codehh.board.api.dto.user.response.JoinRes;
import com.codehh.board.common.exception.JoinFailureException;
import com.codehh.board.common.util.AuthCodeGenerator;
import com.codehh.board.common.util.Checker;
import com.codehh.board.common.util.HashGenerator;
import com.codehh.board.db.entity.User;
import com.codehh.board.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public boolean idCheck(String id) {
        //조건 검사
        if (!Checker.idChecker(id))
            return false;

        //중복 검사
        User user = userRepository.findById(id);
        return user == null;
    }

    @Override
    public boolean nicknameCheck(String nickname) {
        //조건 검사
        if (!Checker.nicknameChecker(nickname))
            return false;

        //중복 검사
        User user = userRepository.findByNickname(nickname);
        return user == null;
    }

    @Override
    public String sendEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Board 가입 인증 메일입니다.");
        String authCode = AuthCodeGenerator.getCode(12);
        message.setText(authCode);

        javaMailSender.send(message);

        return authCode;
    }

    @Override
    public JoinRes join(HashMap<String, Object> payload) throws NoSuchAlgorithmException, JoinFailureException {
        String id = (String) payload.get("id");
        String nickname = (String) payload.get("nickname");
        String password = (String) payload.get("password");
        String email = (String) payload.get("email");
        //id 검사
        if (!Checker.idChecker(id))
            throw new JoinFailureException();
        if (userRepository.findById(id) != null)
            throw new JoinFailureException();
        //닉네임 검사
        if (!Checker.nicknameChecker(nickname))
            throw new JoinFailureException();
        if (userRepository.findByNickname(nickname) != null)
            throw new JoinFailureException();
        //비밀번호 검사
        if (!Checker.passwordChecker(password))
            throw new JoinFailureException();

        //검사 전부 통과했으면 가입
        User user = new User();

        user.setId(id);
        user.setNickname(nickname);
        user.setHashingPassword(HashGenerator.getHash(password));
        user.setEmail(email);

        User saveUser = userRepository.save(user);

        JoinRes joinRes = new JoinRes();
        joinRes.setUserId(saveUser.getUserId());
        joinRes.setNickname(saveUser.getNickname());

        return joinRes;
    }
}
