package com.codehh.board.common.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthCodeGeneratorTest {

    @Test
    void getCodeTest() {
        for (int i = 0; i < 10; i++) {
            System.out.println(AuthCodeGenerator.getCode(12));
        }
    }
}