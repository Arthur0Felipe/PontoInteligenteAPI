package com.arthur.api.PontoInteligenteApi.controllers;

import com.arthur.api.PontoInteligenteApi.dtos.CadastroPFDto;
import com.arthur.api.PontoInteligenteApi.entities.Empresa;
import com.arthur.api.PontoInteligenteApi.entities.Funcionario;
import com.arthur.api.PontoInteligenteApi.enums.PerfilEnum;
import com.arthur.api.PontoInteligenteApi.responses.Response;
import com.arthur.api.PontoInteligenteApi.services.EmpresaService;
import com.arthur.api.PontoInteligenteApi.services.FuncionarioService;
import com.arthur.api.PontoInteligenteApi.utils.SenhaUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@RestController
@RequestMapping("api/cadastrar-pf")
@CrossOrigin(origins = "*")
public class CadastroPFController {
    private static final Logger log = LoggerFactory.getLogger(CadastroPFController.class);

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private FuncionarioService funcionarioService;

    public CadastroPFController() {
    }

    @PostMapping
    public ResponseEntity<Response<CadastroPFDto>> cadastrar(@Valid @RequestBody CadastroPFDto cadastroPFDto, BindingResult result) throws NoSuchAlgorithmException {

        log.info("Cadastrando PF: {}", cadastroPFDto.stringfy());
        Response<CadastroPFDto> response = new Response<CadastroPFDto>();
        validarDadosExistentes(cadastroPFDto, result);
        Funcionario funcionario = this.converterDtoParaFuncionario(cadastroPFDto, result);

        if (result.hasErrors()) {
            log.error("Erro validando dados de cadastro PF: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        Optional<Empresa> empresa = this.empresaService.buscarPorCnpj(cadastroPFDto.getCnpj());
        Funcionario finalFuncionario = funcionario;
        empresa.ifPresent(finalFuncionario::setEmpresa);
        funcionario = this.funcionarioService.persistir(funcionario);
        response.setData(this.converterCadastroPFDto(funcionario));
        return ResponseEntity.ok(response);
    }

    private void validarDadosExistentes(CadastroPFDto cadastroPFDto, BindingResult result) {

        Optional<Empresa> empresa = this.empresaService.buscarPorCnpj(cadastroPFDto.getCnpj());

        if (!empresa.isPresent()) {
            result.addError(new ObjectError("empresa", "Empresa não cadastrada."));
        }

        this.funcionarioService.buscarPorCpf(cadastroPFDto.getCpf()).ifPresent(func -> result.addError(new ObjectError("funcionario", "CPF já existente.")));
        this.funcionarioService.buscarPorEmail(cadastroPFDto.getEmail()).ifPresent(func -> result.addError(new ObjectError("funcionario", "Email já existente.")));
    }

    private Funcionario converterDtoParaFuncionario(CadastroPFDto cadastroPFDto, BindingResult result) throws NoSuchAlgorithmException {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(cadastroPFDto.getNome());
        funcionario.setEmail(cadastroPFDto.getEmail());
        funcionario.setCpf(cadastroPFDto.getCpf());
        funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
        funcionario.setSenha(SenhaUtil.gerarBCrypt(cadastroPFDto.getSenha()));
        cadastroPFDto.getQtdHorasAlmoco().ifPresent(qtdHorasAlmoco -> funcionario.setQtdHorasAlmoco(Float.valueOf(qtdHorasAlmoco)));
        cadastroPFDto.getQtdHorasTrabalhoDia().ifPresent(qtdHorasTrabDia -> funcionario.setQtdHorasTrabalhoDia(Float.valueOf(qtdHorasTrabDia)));
        cadastroPFDto.getValorHora().ifPresent(valorHora -> funcionario.setValorHora(new BigDecimal(valorHora)));
        return funcionario;
    }

    private CadastroPFDto converterCadastroPFDto(Funcionario funcionario) {
        CadastroPFDto cadastroPFDto = new CadastroPFDto();
        cadastroPFDto.setId(funcionario.getId());
        cadastroPFDto.setNome(funcionario.getNome());
        cadastroPFDto.setEmail(funcionario.getEmail());
        cadastroPFDto.setCpf(funcionario.getCpf());
        cadastroPFDto.setCnpj(funcionario.getEmpresa().getCnpj());
        funcionario.getQtdHorasAlmocoOpt().ifPresent(qtdHorasAlmoco -> cadastroPFDto.setQtdHorasAlmoco(Optional.of(Float.toString(qtdHorasAlmoco))));
        funcionario.getQtdHorasTrabalhoDiaOpt().ifPresent(qtdHorasTrabDia -> cadastroPFDto.setQtdHorasTrabalhoDia(Optional.of(Float.toString(qtdHorasTrabDia))));
        funcionario.getValorHoraOpt().ifPresent(valorHora -> cadastroPFDto.setValorHora(Optional.of(valorHora.toString())));
        return cadastroPFDto;
    }
}