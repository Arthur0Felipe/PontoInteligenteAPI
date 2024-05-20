package com.arthur.api.PontoInteligenteApi.services.implementations;

import com.arthur.api.PontoInteligenteApi.entities.Lancamento;
import com.arthur.api.PontoInteligenteApi.repositories.LancamentoRepository;
import com.arthur.api.PontoInteligenteApi.services.LancamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;

import java.util.Optional;


@Service
public class LancamentoImplementation implements LancamentoService {
    private static final Logger log = LoggerFactory.getLogger(LancamentoImplementation.class);

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest) {
        log.info("Buscando lançamentos para o funcionario ID {}", funcionarioId);
        return this.lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest);
    }

    @Cacheable("lancamentoPorId")
    public Optional<Lancamento> buscarPorId(Long id) {
        log.info("Buscando um lançamento pelo ID {}",id);
        return this.lancamentoRepository.findById(id);
    }

    @CachePut("lancamentoPorId")
    public Lancamento persistir(Lancamento lancamento) {
        log.info("Persistindo pelo lançamento {}", lancamento);
        return this.lancamentoRepository.save(lancamento);
    }

    public void remover(Long id) {
        log.info("Removendo o Lancamento ID {}",id);
        this.lancamentoRepository.deleteById(id);
    }
}