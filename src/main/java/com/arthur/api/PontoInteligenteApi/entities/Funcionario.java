package com.arthur.api.PontoInteligenteApi.entities;

import com.arthur.api.PontoInteligenteApi.enums.PerfilEnum;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable {

    private static final long serialVersionUID = -5754246207015712518L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private BigDecimal valorHora;
    private Float horasTrabalhadas;
    private Float horasAlmoco;
    @Enumerated(EnumType.STRING)
    private PerfilEnum perfil;
    private Date dataCriacao;
    private Date dataAtualizacao;
    @ManyToOne(fetch = FetchType.EAGER)
    private Empresa empresa;
    @OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Lancamento> lancamentos;

    public Funcionario() {
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
        return "Funcionario [id = " + id +
                ", nome = " + nome +
                ", email = " + email +
                ", senha = " + senha +
                ", cpf = " + cpf +
                ", valorHora = " + valorHora +
                ", horasTrabalhadas = " + horasTrabalhadas +
                ", horasAlmoco = " + horasAlmoco +
                ", perfil = " + perfil +
                ", dataCriacao = " + dataCriacao +
                ", dataAtualizacao = " + dataAtualizacao +
                ", empresa = " + empresa + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BigDecimal getValorHora() {
        return valorHora;
    }

    public void setValorHora(BigDecimal valorHora) {
        this.valorHora = valorHora;
    }

    public Float getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(Float horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public Float getHorasAlmoco() {
        return horasAlmoco;
    }

    public void setHorasAlmoco(Float horasAlmoco) {
        this.horasAlmoco = horasAlmoco;
    }

    public PerfilEnum getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilEnum perfil) {
        this.perfil = perfil;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }
}
