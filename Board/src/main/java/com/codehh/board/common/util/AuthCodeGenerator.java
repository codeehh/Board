package com.codehh.board.common.util;

import java.util.Random;

public class AuthCodeGenerator {

    public static String getCode(int length) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int rand = new Random().nextInt(36);
            if (0 <= rand && rand < 10) {
                stringBuffer.append((char) (rand + '0'));
            } else if (10 <= rand && rand < 36) {
                stringBuffer.append((char) (rand - 10 + 'A'));
            }
        }
        return stringBuffer.toString();
    }
}
