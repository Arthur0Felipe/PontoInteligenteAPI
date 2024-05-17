package com.arthur.api.PontoInteligenteApi.entities;

import com.arthur.api.PontoInteligenteApi.enums.TipoEnum;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "lancamento")
public class Lancamento implements Serializable {

    private static final long serialVersionUID = 6524560251526772839L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    private String descricao;
    private String localizacao;
    private Date dataCriacao;
    private Date dataAtualizacao;
    @Enumerated(EnumType.STRING)
    private TipoEnum tipo;
    @ManyToOne(fetch = FetchType.EAGER)
    private Funcionario funcionario;

    public Lancamento() {
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
        return "Lancamento [id=" + id +
                ", data = " + data +
                ", descricao = " + descricao +
                ", localizacao = " + localizacao +
                ", dataCriacao = " + dataCriacao +
                ", dataAtualizacao = " + dataAtualizacao +
                ", tipo = " + tipo +
                ", funcionario = " + funcionario + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
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

    public TipoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoEnum tipo) {
        this.tipo = tipo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
