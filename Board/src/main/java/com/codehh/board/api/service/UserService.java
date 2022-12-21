package com.codehh.board.api.service;

import com.codehh.board.api.dto.user.request.JoinReq;
import com.codehh.board.api.dto.user.response.JoinRes;
import com.codehh.board.common.exception.JoinFailureException;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public interface UserService {

    public boolean idCheck(String id);

    public boolean nicknameCheck(String nickname);

    public String sendEmail(String email);

    public JoinRes join(HashMap<String, Object> payload) throws NoSuchAlgorithmException, JoinFailureException;
}
