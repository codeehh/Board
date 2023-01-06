package com.codehh.board.api.service;

import com.codehh.board.api.dto.user.request.JoinReq;
import com.codehh.board.api.dto.user.request.LoginReq;
import com.codehh.board.api.dto.user.response.LoginRes;
import com.codehh.board.common.exception.JoinFailureException;
import com.codehh.board.common.exception.LoginFailureException;
import com.codehh.board.common.util.AuthCodeGenerator;
import com.codehh.board.common.util.Checker;
import com.codehh.board.common.util.HashGenerator;
import com.codehh.board.db.entity.User;
import com.codehh.board.db.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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
    public boolean passwordCheck(String password) {
        //조건 검사
        return Checker.passwordChecker(password);
    }

    @Override
    public String sendEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Board 인증 메일입니다.");
        String authCode = AuthCodeGenerator.getCode(12);
        message.setText("인증코드 : " + authCode);

        javaMailSender.send(message);

        return authCode;
    }

    @Override
    public void join(JoinReq joinReq) throws NoSuchAlgorithmException, JoinFailureException {
        //id 검사
        if (!Checker.idChecker(joinReq.getId()))
            throw new JoinFailureException();
        if (userRepository.findById(joinReq.getId()) != null)
            throw new JoinFailureException();
        //닉네임 검사
        if (!Checker.nicknameChecker(joinReq.getNickname()))
            throw new JoinFailureException();
        if (userRepository.findByNickname(joinReq.getNickname()) != null)
            throw new JoinFailureException();
        //비밀번호 검사
        if (!Checker.passwordChecker(joinReq.getPassword()))
            throw new JoinFailureException();

        //검사 전부 통과했으면 가입
        User user = new User();

        user.setId(joinReq.getId());
        user.setNickname(joinReq.getNickname());
        user.setHashingPassword(HashGenerator.getHash(joinReq.getPassword()));
        user.setEmail(joinReq.getEmail());

        userRepository.save(user);
    }

    @Override
    public LoginRes login(LoginReq loginReq, HttpServletRequest req) throws NoSuchAlgorithmException, LoginFailureException {
        User user = userRepository.findByIdAndHashingPassword(loginReq.getId(), HashGenerator.getHash(loginReq.getPassword()));

        if (user == null)
            throw new LoginFailureException();

        HttpSession session = req.getSession(true);

        if (loginReq.isAutoLogin()) {
            //30일
            session.setMaxInactiveInterval(2592000);
        } else {
            //30분
            session.setMaxInactiveInterval(1800);
        }

        session.setAttribute("userId", user.getUserId());

        LoginRes loginRes = new LoginRes();
        loginRes.setNickname(user.getNickname());

        return loginRes;
    }

    @Override
    public void logout(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    @Override
    public List<String> findId(String email) {
        List<User> users = userRepository.findByEmail(email);
        if (users == null) {
            return null;
        }

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            ids.add(users.get(i).getId());
        }

        return ids;
    }

    @Override
    public User findPassword(String id, String email) {
        User user = userRepository.findByIdAndEmail(id, email);

        return user;
    }

    @Override
    public boolean resetPassword(String id, String password) throws NoSuchAlgorithmException {
        User user = userRepository.findById(id);
        if (user == null) {
            return false;
        }
        user.setHashingPassword(HashGenerator.getHash(password));
        userRepository.save(user);

        return true;
    }
}
