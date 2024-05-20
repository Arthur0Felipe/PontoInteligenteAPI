package com.arthur.api.PontoInteligenteApi.repositories;

import com.arthur.api.PontoInteligenteApi.entities.Empresa;
import com.arthur.api.PontoInteligenteApi.entities.Funcionario;
import com.arthur.api.PontoInteligenteApi.enums.PerfilEnum;
import com.arthur.api.PontoInteligenteApi.utils.SenhaUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.security.NoSuchAlgorithmException;

@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioRepositoryTest {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    private static final String EMAIL = "email@email.com";
    private static final String CPF = "45634547825";

    @BeforeEach
    public void setUp() throws Exception {
        Empresa empresa = this.empresaRepository.save(obterDadosEmpresa());
        this.funcionarioRepository.save(obterDadosFuncionario(empresa));
    }

    @AfterEach
    public final void tearDown() {
        this.empresaRepository.deleteAll();
    }

    @Test
    public void testBuscarFuncionarioPorEmail() {
        Funcionario funcionario = this.funcionarioRepository.findByEmail(EMAIL);
        Assertions.assertEquals(EMAIL, funcionario.getEmail());
    }

    @Test
    public void testBuscarFuncionarioPorCpf() {
        Funcionario funcionario = this.funcionarioRepository.findByCpf(CPF);
        Assertions.assertEquals(CPF, funcionario.getCpf());
    }

    @Test
    public void testBuscarFuncionarioPorEmailECpf() {
        Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail(CPF, EMAIL);
        Assertions.assertNotNull(funcionario);
    }

    @Test
    public void testBuscarFuncionarioPorEmailOuCpfParaEmailInvalido() {
        Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail(CPF, "email@invalido.com");
        Assertions.assertNotNull(funcionario);
    }

    @Test
    public void testBuscarFuncionarioPorEmailECpfParaCpfInvalido() {
        Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail("12345678901", EMAIL);
        Assertions.assertNotNull(funcionario);
    }

    private Funcionario obterDadosFuncionario(Empresa empresa) throws NoSuchAlgorithmException {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Fulano de Tal");
        funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
        funcionario.setSenha(SenhaUtil.gerarBCrypt("123456"));
        funcionario.setCpf(CPF);
        funcionario.setEmail(EMAIL);
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