package com.arthur.api.PontoInteligenteApi.repositories;

import com.arthur.api.PontoInteligenteApi.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Funcionario findByCpf(String cpf);
    Funcionario findByEmail(String email);
    Funcionario findByCpfOrEmail(String cpf, String email);
}
