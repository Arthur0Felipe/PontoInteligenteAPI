package com.arthur.api.PontoInteligenteApi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaUtil {

    private static final Logger log = LoggerFactory.getLogger(SenhaUtil.class);

    public SenhaUtil() {
    }

    public static String gerarBCrypt(String senha) {
        if (senha == null) {
            return senha;
        }

        log.info("Gerando hash com o BCrypt.");
        BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        return bCryptEncoder.encode(senha);
    }

}
