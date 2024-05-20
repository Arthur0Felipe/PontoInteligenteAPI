package com.arthur.api.PontoInteligenteApi.repositories;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import com.arthur.api.PontoInteligenteApi.entities.Empresa;
import com.arthur.api.PontoInteligenteApi.entities.Funcionario;
import com.arthur.api.PontoInteligenteApi.entities.Lancamento;
import com.arthur.api.PontoInteligenteApi.enums.PerfilEnum;
import com.arthur.api.PontoInteligenteApi.enums.TipoEnum;
import com.arthur.api.PontoInteligenteApi.utils.SenhaUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
public class LancamentoRepositoryTest {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    private Long funcionarioId;

    @BeforeEach
    public void setUp() throws Exception {
        Empresa empresa = this.empresaRepository.save(obterDadosEmpresa());
        Funcionario funcionario = this.funcionarioRepository.save(obterDadosFuncionario(empresa));
        this.funcionarioId = funcionario.getId();
        this.lancamentoRepository.save(obterDadosLancamentos(funcionario));
        this.lancamentoRepository.save(obterDadosLancamentos(funcionario));
    }

    @AfterEach
    public void tearDown() throws Exception {
        this.empresaRepository.deleteAll();
    }

    @Test
    public void testBuscarLancamentosPorFuncionarioId() {
        List<Lancamento> lancamentos = this.lancamentoRepository.findByFuncionarioId(funcionarioId);
        Assertions.assertEquals(2, lancamentos.size());
    }

    @Test
    public void testBuscarLancamentosPorFuncionarioIdPaginado() {
        PageRequest page = PageRequest.of(0, 10);
        Page<Lancamento> lancamentos = this.lancamentoRepository.findByFuncionarioId(funcionarioId, page);
        Assertions.assertEquals(2, lancamentos.getTotalElements());
    }

    private Lancamento obterDadosLancamentos(Funcionario funcionario) {
        Lancamento lancamento = new Lancamento();
        lancamento.setData(new Date());
        lancamento.setTipo(TipoEnum.INICIO_ALMOCO);
        lancamento.setFuncionario(funcionario);
        return lancamento;
    }

    private Funcionario obterDadosFuncionario(Empresa empresa) throws NoSuchAlgorithmException {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Fulano de Tal");
        funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
        funcionario.setSenha(SenhaUtil.gerarBCrypt("123456"));
        funcionario.setCpf("24291173474");
        funcionario.setEmail("email@email.com");
        funcionario.setEmpresa(empresa);
        return funcionario;
    }

    private Empresa obterDadosEmpresa() {
        Empresa empresa = new Empresa();
        empresa.setRazaoSocial("Empresa de exemplo");
        empresa.setCnpj("51463645000100");
        return empresa;
    }
}