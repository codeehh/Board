package com.codehh.board.api.service;

import com.codehh.board.api.dto.user.request.JoinReq;
import com.codehh.board.api.dto.user.response.JoinRes;
import com.codehh.board.common.exception.JoinFailureException;

import java.security.NoSuchAlgorithmException;

public interface UserService {

    public boolean idCheck(String id);

    public boolean nicknameCheck(String nickname);

    public String sendEmail(String email);

    public JoinRes join(JoinReq joinReq) throws NoSuchAlgorithmException, JoinFailureException;
}
