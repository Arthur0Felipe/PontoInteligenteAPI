package com.arthur.api.PontoInteligenteApi.utils;

import org.junit.jupiter.api.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaUtilTest {

    private static final String SENHA = "123456";
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Test
    public void testSenhaNula () throws Exception {
        Assertions.assertNull(SenhaUtil.gerarBCrypt(null));
    }

    @Test
    public void testSenhaInvalida () throws Exception {
        String hash = SenhaUtil.gerarBCrypt(SENHA);
        Assertions.assertTrue(encoder.matches(SENHA, hash));
    }
}
