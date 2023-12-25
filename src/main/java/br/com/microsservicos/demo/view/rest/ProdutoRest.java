package br.com.microsservicos.demo.view.rest;

import br.com.microsservicos.demo.domain.Produto;
import br.com.microsservicos.demo.repository.ProdutoRepository;
import br.com.microsservicos.demo.service.ProdutoService;
import br.com.microsservicos.demo.shared.ProdutoDTO;
import br.com.microsservicos.demo.view.domain.ProdutoResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/Produto")
@AllArgsConstructor
public class ProdutoRest {
    private ProdutoRepository produtoRepository;
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> read(){
        List<ProdutoDTO> produtos =  produtoService.findAll();

        ModelMapper mapper = new ModelMapper();

        List<ProdutoResponse> responses = produtos.stream().map(produtoDTO -> mapper.map(produtoDTO, ProdutoResponse.class)).collect(Collectors.toList());

        return new ResponseEntity<>(responses, HttpStatus.OK);
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

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        produtoRepository.deleteById(id);
    }
}
