package com.arthur.api.PontoInteligenteApi.services;

import com.arthur.api.PontoInteligenteApi.entities.Empresa;

import java.util.Optional;

public interface EmpresaService {

    Optional<Empresa> buscarPorCnpj(String cnpj);

    Empresa persistir(Empresa empresa);
}
