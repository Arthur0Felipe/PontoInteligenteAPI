package com.arthur.api.PontoInteligenteApi.repositories;

import com.arthur.api.PontoInteligenteApi.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    @Transactional(readOnly = true)
    Empresa findByCnpj(String cnpj);
}
