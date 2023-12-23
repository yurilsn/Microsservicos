package br.com.microsservicos.demo.domain.error;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
     private String titulo;
     private Integer status;
     private String mensagem;
}
