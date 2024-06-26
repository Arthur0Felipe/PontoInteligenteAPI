package com.arthur.api.PontoInteligenteApi.dtos;

public class EmpresaDto {
    private Long id;
    private String razaoSocial;
    private String cnpj;

    public EmpresaDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String stringfy() {
        return "EmpresaDto [id: " + id +
                ", razaoSocial: " + razaoSocial +
                ", cnpj: " + cnpj + "]";
    }
}