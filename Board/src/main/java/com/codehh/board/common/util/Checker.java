package com.codehh.board.common.util;

public class Checker {

    /**
     * 아이디 조건
     * 글자 수 2이상 12이하
     * 알파벳 소문자, 숫자로만 이뤄짐
     */
    public static boolean idChecker(String id) {
        if (!(2 <= id.length() && id.length() <= 12))
            return false;

        for (int i = 0; i < id.length(); i++) {
            char c = id.charAt(i);
            if (!('a' <= c && c <= 'z' ||
                    '0' <= c && c <= '9')) {
                return false;
            }
        }

        return true;
    }

    /**
     * 닉네임 조건
     * 글자 수 2이상 12이하
     * 알파벳, 한글, 숫자로만 이뤄짐
     */
    public static boolean nicknameChecker(String nickname) {
        if (!(2 <= nickname.length() && nickname.length() <= 12))
            return false;

        for (int i = 0; i < nickname.length(); i++) {
            char c = nickname.charAt(i);
            if (!('a' <= c && c <= 'z' ||
                    'A' <= c && c <= 'Z' ||
                    '0' <= c && c <= '9' ||
                    '가' <= c && c <= '힣')) {
                return false;
            }
        }

        return true;
    }

    /**
     * 비밀번호 조건
     * 길이 8이상 15 이하
     * 특수문자, 알파벳, 숫자로만 이뤄지고 각각 1개 이상 포함
     */
    public static boolean passwordChecker(String password) {
        if (!(8 <= password.length() && password.length() <= 15))
            return false;

        boolean special = false;
        boolean alphabet = false;
        boolean numeric = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (!(33 <= c && c <= 126))
                return false;
            if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z')) {
                alphabet = true;
            } else if ('0' <= c && c <= '9') {
                numeric = true;
            } else {
                special = true;
            }
        }

        return special && alphabet && numeric;
    }
}
