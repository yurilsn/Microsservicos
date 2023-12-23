package br.com.microsservicos.demo.service;

import br.com.microsservicos.demo.domain.Produto;
import br.com.microsservicos.demo.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProdutoService {
    private ProdutoRepository produtoRepository;

    public Produto update(Produto produtoAtualizado, Long id){
        return produtoRepository.findById(id).map(produto -> {
            produto.setNome(produtoAtualizado.getNome());
            produto.setQuantidade(produtoAtualizado.getQuantidade());
            produto.setValor(produtoAtualizado.getValor());
            produto.setObservacao(produtoAtualizado.getObservacao());
            return produtoRepository.save(produto);
        }).orElseGet(() -> {return produtoRepository.save(produtoAtualizado);});
    }
}
