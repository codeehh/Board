package com.codehh.board.api.service;

import com.codehh.board.api.dto.user.request.JoinReq;
import com.codehh.board.common.exception.JoinFailureException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

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

        JoinReq joinReq = new JoinReq();
        joinReq.setId(id1);
        joinReq.setNickname("중복");
        joinReq.setPassword("!@#asd323");
        joinReq.setEmail("wndqhr@wndqhr.com");
        userService.join(joinReq);
        boolean result1 = userService.idCheck(id1);
        boolean result2 = userService.idCheck(id2);

        assertThat(result1).isEqualTo(false);
        assertThat(result2).isEqualTo(true);
    }

    @Test
    void nicknameCheck() throws NoSuchAlgorithmException, JoinFailureException {
        String nickname1 = "중복된닉네임";
        String nickname2 = "중복되지않은닉네임";

        JoinReq joinReq = new JoinReq();
        joinReq.setId("asdhjdhsadg");
        joinReq.setNickname(nickname1);
        joinReq.setPassword("!@#asd123");
        joinReq.setEmail("wndqhr@wndqhr.com");
        userService.join(joinReq);
        boolean result1 = userService.nicknameCheck(nickname1);
        boolean result2 = userService.nicknameCheck(nickname2);

        assertThat(result1).isEqualTo(false);
        assertThat(result2).isEqualTo(true);
    }

    @Test
    void join() throws NoSuchAlgorithmException, JoinFailureException {
        JoinReq[] joinReqs = new JoinReq[6];
        for (int i = 0; i < joinReqs.length; i++) {
            joinReqs[i] = new JoinReq();
        }
        //정상 가입요청
        joinReqs[0].setId("shghwns");
        joinReqs[0].setNickname("노호준");
        joinReqs[0].setPassword("!@#asd123");
        joinReqs[0].setEmail("shghwns@ghwns.com");
        //중복된 아이디 가입요청
        joinReqs[1].setId("shghwns");
        joinReqs[1].setNickname("노호준준");
        joinReqs[1].setPassword("!@#asd123");
        joinReqs[1].setEmail("shghwns@ghwns.com");
        //중복된 닉네임 가입요청
        joinReqs[2].setId("shghwnss");
        joinReqs[2].setNickname("노호준");
        joinReqs[2].setPassword("!@#asd123");
        joinReqs[2].setEmail("shghwns@ghwns.com");
        //잘못된 아이디 가입요청
        joinReqs[3].setId("s");
        joinReqs[3].setNickname("노호준준");
        joinReqs[3].setPassword("!@#asd123");
        joinReqs[3].setEmail("shghwns@ghwns.com");
        //잘못된 닉네임 가입요청
        joinReqs[4].setId("shghwnss");
        joinReqs[4].setNickname("노");
        joinReqs[4].setPassword("!@#asd123");
        joinReqs[4].setEmail("shghwns@ghwns.com");
        //잘못된 비밀번호 가입요청
        joinReqs[5].setId("shghwnss");
        joinReqs[5].setNickname("노호준준");
        joinReqs[5].setPassword("123456");
        joinReqs[5].setEmail("shghwns@ghwns.com");

        assertThatNoException().isThrownBy(() -> userService.join(joinReqs[0]));
        for (int i = 1; i < joinReqs.length; i++) {
            int finalI = i;
            assertThatExceptionOfType(JoinFailureException.class).isThrownBy(() -> userService.join(joinReqs[finalI]));
        }
    }
}