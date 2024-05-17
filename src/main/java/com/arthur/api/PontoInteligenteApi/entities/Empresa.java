package com.arthur.api.PontoInteligenteApi.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {

    private static final long serialVersionUID = 3960436649365666213L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String razaoSocial;
    private String cnpj;
    private Date dataCriacao;
    private Date dataAtualizacao;
    @OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Funcionario> funcionarios;

    public Empresa() {
    }

    @PrePersist
    public void prePersist(){
        dataAtualizacao = new Date();
    }

    @PreUpdate
    public void preUpdate(){
        final Date atual = new Date();
        dataCriacao = atual;
        dataAtualizacao = atual;
    }

    @Override
    public String toString() {
        return "Empresa [id = " + id +
                ", razaoSocial = " + razaoSocial +
                ", cnpj = " + cnpj +
                ", dataCriacao = " + dataCriacao +
                ", dataAtualizacao = " + dataAtualizacao + "]";
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

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
