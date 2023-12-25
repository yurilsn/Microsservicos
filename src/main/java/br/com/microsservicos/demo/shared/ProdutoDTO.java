package br.com.microsservicos.demo.shared;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {

    private Long id;
    private String nome;

    private Integer quantidade;

    private Double valor;

    private String observacao;

}
