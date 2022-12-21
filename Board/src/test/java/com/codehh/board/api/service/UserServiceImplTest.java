package com.codehh.board.api.service;

import com.codehh.board.common.exception.JoinFailureException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Test
    void idCheck() throws NoSuchAlgorithmException, JoinFailureException {
        String id1 = "duplid36721";
        String id2 = "noduplid2132";

        HashMap<String, Object> payload = new HashMap<>();
        payload.put("id", id1);
        payload.put("nickname", "중복");
        payload.put("password", "!@#asd323");
        payload.put("email", "wndqhr@wndqhr.com");
        userService.join(payload);
        boolean result1 = userService.idCheck(id1);
        boolean result2 = userService.idCheck(id2);

        assertThat(result1).isEqualTo(false);
        assertThat(result2).isEqualTo(true);
    }

    @Test
    void sendEmail() {
        userService.sendEmail("hh9501@naver.com");
    }

    @Test
    void nicknameCheck() throws NoSuchAlgorithmException, JoinFailureException {
        String nickname1 = "중복된닉네임";
        String nickname2 = "중복되지않은닉네임";

        HashMap<String, Object> payload = new HashMap<>();
        payload.put("id", "asdhjdhsadg");
        payload.put("nickname", nickname1);
        payload.put("password", "!@#asd123");
        payload.put("email", "wndqhr@wndqhr.com");
        userService.join(payload);
        boolean result1 = userService.nicknameCheck(nickname1);
        boolean result2 = userService.nicknameCheck(nickname2);

        assertThat(result1).isEqualTo(false);
        assertThat(result2).isEqualTo(true);
    }

    @Test
    void join() throws NoSuchAlgorithmException, JoinFailureException {
        HashMap<String, Object>[] payloads = new HashMap[6];
        for (int i = 0; i < payloads.length; i++) {
            payloads[i] = new HashMap<>();
        }
        //정상 가입요청
        payloads[0].put("id", "shghwns");
        payloads[0].put("nickname", "노호준");
        payloads[0].put("password", "!@#asd123");
        payloads[0].put("email", "shghwns@ghwns.com");
        //중복된 아이디 가입요청
        payloads[1].put("id", "shghwns");
        payloads[1].put("nickname", "노호준준");
        payloads[1].put("password", "!@#asd123");
        payloads[1].put("email", "shghwns@ghwns.com");
        //중복된 닉네임 가입요청
        payloads[2].put("id", "shghwnss");
        payloads[2].put("nickname", "노호준");
        payloads[2].put("password", "!@#asd123");
        payloads[2].put("email", "shghwns@ghwns.com");
        //잘못된 아이디 가입요청
        payloads[3].put("id", "s");
        payloads[3].put("nickname", "노호준준");
        payloads[3].put("password", "!@#asd123");
        payloads[3].put("email", "shghwns@ghwns.com");
        //잘못된 닉네임 가입요청
        payloads[4].put("id", "shghwnss");
        payloads[4].put("nickname", "노");
        payloads[4].put("password", "!@#asd123");
        payloads[4].put("email", "shghwns@ghwns.com");
        //잘못된 비밀번호 가입요청
        payloads[5].put("id", "shghwnss");
        payloads[5].put("nickname", "노호준준");
        payloads[5].put("password", "123456");
        payloads[5].put("email", "shghwns@ghwns.com");


        assertThatNoException().isThrownBy(() -> userService.join(payloads[0]));
        for (int i = 1; i < payloads.length; i++) {
            int finalI = i;
            assertThatExceptionOfType(JoinFailureException.class).isThrownBy(() -> userService.join(payloads[finalI]));
        }
    }
}