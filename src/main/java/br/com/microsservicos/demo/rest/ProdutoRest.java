package br.com.microsservicos.demo.rest;

import br.com.microsservicos.demo.domain.Produto;
import br.com.microsservicos.demo.repository.ProdutoRepository;
import br.com.microsservicos.demo.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Produto")
@AllArgsConstructor
public class ProdutoRest {
    private ProdutoRepository produtoRepository;
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> read(){
        return produtoRepository.findAll();
    }
    @GetMapping("/{id}")
    public Optional<Produto> readById(@PathVariable("id") Long id){
        return produtoRepository.findById(id);
    }

    @PostMapping
    public Produto create(@RequestBody Produto produto){
        return produtoRepository.save(produto);
    }

    @PutMapping("/{id}")
    public Produto update(@RequestBody Produto produto, @PathVariable("id") Long id){
        return produtoService.update(produto, id);
    }
}
