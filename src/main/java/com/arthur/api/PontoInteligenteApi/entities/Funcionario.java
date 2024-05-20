package com.arthur.api.PontoInteligenteApi.entities;

import com.arthur.api.PontoInteligenteApi.enums.PerfilEnum;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable {
    private static final long serialVersionUID = -5754246207015712518L;

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private BigDecimal valorHora;
    private Float qtdHorasTrabalhoDia;
    private Float qtdHorasAlmoco;
    private PerfilEnum perfil;
    private Date dataCriacao;
    private Date dataAtualizacao;
    private Empresa empresa;
    private List<Lancamento> lancamentos;

    public Funcionario() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "nome", nullable = false)
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "senha", nullable = false)
    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Column(name = "cpf", nullable = false)
    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Column(name = "valor_hora", nullable = true)
    public BigDecimal getValorHora() {
        return this.valorHora;
    }

    @Transient
    public Optional<BigDecimal> getValorHoraOpt() {
        return Optional.ofNullable(this.valorHora);
    }

    public void setValorHora(BigDecimal valorHora) {
        this.valorHora = valorHora;
    }

    @Column(name = "qtd_horas_trabalho_dia", nullable = true)
    public Float getQtdHorasTrabalhoDia() {
        return this.qtdHorasTrabalhoDia;
    }

    @Transient
    public Optional<Float> getQtdHorasTrabalhoDiaOpt() {
        return Optional.ofNullable(this.qtdHorasTrabalhoDia);
    }

    public void setQtdHorasTrabalhoDia(Float qtdHorasTrabalhoDia) {
        this.qtdHorasTrabalhoDia = qtdHorasTrabalhoDia;
    }

    @Column(name = "qtd_horas_almoco", nullable = true)
    public Float getQtdHorasAlmoco() {
        return this.qtdHorasAlmoco;
    }

    @Transient
    public Optional<Float> getQtdHorasAlmocoOpt() {
        return Optional.ofNullable(qtdHorasAlmoco);
    }

    public void setQtdHorasAlmoco(Float qtdHorasAlmoco) {
        this.qtdHorasAlmoco = qtdHorasAlmoco;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false)
    public PerfilEnum getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilEnum perfil) {
        this.perfil = perfil;
    }

    @Column(name = "data_criacao", nullable = false)
    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Column(name = "data_atualizacao", nullable = false)
    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }

    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = new Date();
    }

    @PrePersist
    public void prePersist() {
        final Date dataAtual = new Date();
        this.dataCriacao = dataAtual;
        this.dataAtualizacao = dataAtual;
    }

    public String stringfy() {
        return "Funcionario [id: " + this.id +
                ", nome: " + this.nome +
                ", email: " + this.email +
                ", senha: " + this.senha +
                ", dataCriacao: " + this.dataCriacao +
                ", dataAtualizacao: " + this.dataAtualizacao +
                ", cpf: " + this.cpf +
                ", valorHora: " + this.valorHora +
                ", qtdHorasTrabalhoDia: " + this.qtdHorasTrabalhoDia +
                ", perfil: " + this.perfil +
                ", empresa: " + this.empresa +
                ", qtdHorasAlmoco: " + this.qtdHorasAlmoco + "]";
    }
}