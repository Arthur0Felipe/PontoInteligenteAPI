package com.arthur.api.PontoInteligenteApi.repositories;

import com.arthur.api.PontoInteligenteApi.entities.Empresa;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class EmpresaRepositoryTest {

    @Autowired
    private EmpresaRepository empresaRepository;

    private static final String CNPJ = "51463645000100";

    @BeforeEach
    public void setUp() throws Exception {
        Empresa empresa = new Empresa();
        empresa.setRazaoSocial("Empresa de exemplo");
        empresa.setCnpj(CNPJ);
        this.empresaRepository.save(empresa);
    }

    @AfterEach
    public final void tearDown() {
        this.empresaRepository.deleteAll();
    }

    @Test
    public void testBuscarPorCnpj() {
        Empresa empresa = this.empresaRepository.findByCnpj(CNPJ);
        Assertions.assertEquals(CNPJ, empresa.getCnpj());
    }
}
