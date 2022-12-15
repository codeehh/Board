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

    @PostMapping("/id-check")
    public ResponseEntity<Object> idCheck(@RequestParam String id) {
        HttpStatus status = HttpStatus.OK;
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("canUse", userService.idCheck(id));

        return ResponseEntity.status(status).body(result);
    }

    @PostMapping("/nickname-check")
    public ResponseEntity<Object> nicknameCheck(@RequestParam String nickname) {
        HttpStatus status = HttpStatus.OK;
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("canUse", userService.nicknameCheck(nickname));

        return ResponseEntity.status(status).body(result);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> join(@ModelAttribute JoinReq joinReq) {
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
