package com.arthur.api.PontoInteligenteApi.services.implementations;


import com.arthur.api.PontoInteligenteApi.entities.Empresa;
import com.arthur.api.PontoInteligenteApi.repositories.EmpresaRepository;
import com.arthur.api.PontoInteligenteApi.services.EmpresaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpresaImplementation implements EmpresaService {
    private static final Logger log = LoggerFactory.getLogger(EmpresaImplementation.class);

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public Optional<Empresa> buscarPorCnpj(String cnpj) {
        log.info("Buscando uma empresa para o CNPJ {}", cnpj);
        return Optional.ofNullable(empresaRepository.findByCnpj(cnpj));
    }

    @Override
    public Empresa persistir(Empresa empresa) {
        log.info("Persistindo empresa: {}", empresa);
        return this.empresaRepository.save(empresa);
    }
}
