package com.codehh.board.common.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CheckerTest {

    @Test
    void idCheckerTest() {
        String[] ids = new String[7];
        //올바른 id
        ids[0] = "correctid123";
        //글자 수가 2미만
        ids[1] = "s";
        //글자 수가 12초과
        ids[2] = "longlong12345";
        //알파벳 대문자 포함
        ids[3] = "dhSjd23";
        //한글 포함
        ids[4] = "asd한글dk23";
        //특수문자 포함
        ids[5] = "!@#$asd";
        //한자 포함
        ids[6] = "漢字包含";

        boolean[] results = new boolean[7];
        for (int i = 0; i < ids.length; i++) {
            results[i] = Checker.idChecker(ids[i]);
        }

        assertThat(results[0]).isEqualTo(true);
        for (int i = 1; i < results.length; i++) {
            assertThat(results[i]).isEqualTo(false);
        }
    }

    @Test
    void nicknameCheckerTest() {
        String[] nicknames = new String[5];
        //올바른 닉네임
        nicknames[0] = "abcABC가나다123";
        //글자 수가 2미만
        nicknames[1] = "짧";
        //글자 수가 12초과
        nicknames[2] = "longLong초과123";
        //특수문자 포함
        nicknames[3] = "contain!@#";
        //한자 포함
        nicknames[4] = "漢字包含";

        boolean[] results = new boolean[5];
        for (int i = 0; i < nicknames.length; i++) {
            results[i] = Checker.nicknameChecker(nicknames[i]);
        }

        assertThat(results[0]).isEqualTo(true);
        for (int i = 1; i < results.length; i++) {
            assertThat(results[i]).isEqualTo(false);
        }
    }

    @Test
    void passwordCheckerTest() {
        String[] passwords = new String[6];
        //올바른 비밀번호
        passwords[0] = "!@#abcABC321";
        //길이가 8미만
        passwords[1] = "short!2";
        //길이가 15초과
        passwords[2] = "longLong!@#$4321";
        //특수문자 미포함
        passwords[3] = "noSpecial123";
        //알파벳 미포함
        passwords[4] = "!@#$%^123456";
        //숫자 미포함
        passwords[5] = "noNumeric!@#";

        boolean[] results = new boolean[6];
        for (int i = 0; i < passwords.length; i++) {
            results[i] = Checker.passwordChecker(passwords[i]);
        }

        assertThat(results[0]).isEqualTo(true);
        for (int i = 1; i < results.length; i++) {
            assertThat(results[i]).isEqualTo(false);
        }
    }
}