package br.com.microsservicos.demo.view.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoRequest {


    private String nome;

    private Integer quantidade;

    private Double valor;

    private String observacao;


}

