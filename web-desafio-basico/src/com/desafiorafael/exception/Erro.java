package com.desafiorafael.exception;

public class Erro {
    private int status;
    private String menssagem;
    private String detalhe;

    public Erro() {
    }

    public Erro(int status, String menssagem, String detalhe) {
        this.status = status;
        this.menssagem = menssagem;
        this.detalhe = detalhe;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMenssagem() {
        return menssagem;
    }

    public void setMenssagem(String menssagem) {
        this.menssagem = menssagem;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }
}
