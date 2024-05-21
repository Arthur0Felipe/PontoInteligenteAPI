package com.arthur.api.PontoInteligenteApi.security;


import java.util.ArrayList;
import java.util.List;

import com.arthur.api.PontoInteligenteApi.entities.Funcionario;
import com.arthur.api.PontoInteligenteApi.enums.PerfilEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class JwtUserFactory {

    private JwtUserFactory() {

    }

    public static JwtUser create(Funcionario funcionario) {
        return new JwtUser(funcionario.getId(), funcionario.getEmail(),
                funcionario.getSenha(), mapToGrantedAuthorities(funcionario.getPerfil()));
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(PerfilEnum perfilEnum) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
        return authorities;
    }
}
