package com.codehh.board.api.controller;

import com.codehh.board.api.dto.user.request.JoinReq;
import com.codehh.board.api.dto.user.response.JoinRes;
import com.codehh.board.api.service.UserService;
import com.codehh.board.common.exception.JoinFailureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    private HashMap<String, String> emailToAuthCode = new HashMap<>();

    @PostMapping("/id-check")
    public ResponseEntity<Object> idCheck(@RequestBody HashMap<String, Object> payload) {
        String id = (String) payload.get("id");

        HttpStatus status = HttpStatus.OK;
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("canUse", userService.idCheck(id));

        return ResponseEntity.status(status).body(result);
    }

    @PostMapping("/nickname-check")
    public ResponseEntity<Object> nicknameCheck(@RequestBody HashMap<String, Object> payload) {
        String nickname = (String) payload.get("nickname");

        HttpStatus status = HttpStatus.OK;
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("canUse", userService.nicknameCheck(nickname));

        return ResponseEntity.status(status).body(result);
    }

    @PostMapping("/email-check")
    public ResponseEntity<Object> emailCheck(@RequestBody HashMap<String, Object> payload) {
        String email = (String) payload.get("email");

        HttpStatus status = HttpStatus.OK;
        //이메일 보내는 로직
        String authCode = userService.sendEmail(email);

        //key: 보낸 이메일, value: 인증코드 저장
        emailToAuthCode.put(email, authCode);

        return ResponseEntity.status(status).build();
    }

    @PostMapping("/auth-code-check")
    public ResponseEntity<Object> authCodeCheck(@RequestBody HashMap<String, Object> payload) {
        String email = (String) payload.get("email");
        String authCode = (String) payload.get("auth_code");

        HttpStatus status = HttpStatus.OK;
        HashMap<String, Object> result = new HashMap<String, Object>();
        //인증코드 매칭 확인
        boolean isMatch = (emailToAuthCode.get(email).equals(authCode));
        result.put("isMatch", isMatch);

        return ResponseEntity.status(status).body(result);
    }


    @PostMapping("/users")
    public ResponseEntity<Object> join(@RequestBody JoinReq joinReq) {
        HttpStatus status = HttpStatus.OK;
        JoinRes result = null;
        try {
            result = userService.join(joinReq);
        } catch (NoSuchAlgorithmException e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } catch (JoinFailureException e) {
            status = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(status).body(result);
    }
}
