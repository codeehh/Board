package com.codehh.board.api.service;

import com.codehh.board.api.dto.user.request.JoinReq;
import com.codehh.board.api.dto.user.request.LoginReq;
import com.codehh.board.api.dto.user.response.JoinRes;
import com.codehh.board.common.exception.JoinFailureException;
import com.codehh.board.common.exception.LoginFailureException;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public interface UserService {

    public boolean idCheck(String id);

    public boolean nicknameCheck(String nickname);

    public String sendEmail(String email);

    public JoinRes join(JoinReq joinReq) throws NoSuchAlgorithmException, JoinFailureException;

    public void login(LoginReq loginReq, HttpServletRequest req) throws NoSuchAlgorithmException, LoginFailureException;
}
