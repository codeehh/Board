package com.codehh.board.api.service;

import com.codehh.board.api.dto.user.request.JoinReq;
import com.codehh.board.api.dto.user.request.LoginReq;
import com.codehh.board.api.dto.user.response.LoginRes;
import com.codehh.board.common.exception.JoinFailureException;
import com.codehh.board.common.exception.LoginFailureException;
import com.codehh.board.db.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {

    public boolean idCheck(String id);

    public boolean nicknameCheck(String nickname);

    public boolean passwordCheck(String password);

    public String sendEmail(String email);

    public void join(JoinReq joinReq) throws NoSuchAlgorithmException, JoinFailureException;

    public LoginRes login(LoginReq loginReq, HttpServletRequest req) throws NoSuchAlgorithmException, LoginFailureException;

    public void logout(HttpServletRequest req);

    public List<String> findId(String email);

    public User findPassword(String id, String email);

    public boolean resetPassword(String id, String password) throws NoSuchAlgorithmException;
}
