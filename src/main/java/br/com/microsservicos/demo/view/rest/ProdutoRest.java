package br.com.microsservicos.demo.view.rest;

import br.com.microsservicos.demo.domain.Produto;
import br.com.microsservicos.demo.repository.ProdutoRepository;
import br.com.microsservicos.demo.service.ProdutoService;
import br.com.microsservicos.demo.shared.ProdutoDTO;
import br.com.microsservicos.demo.view.domain.ProdutoRequest;
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
    public ResponseEntity<Optional<ProdutoResponse>> readById(@PathVariable("id") Long id){


        try {

            Optional<ProdutoDTO> produtoDTO = produtoService.findById(id);
            ProdutoResponse response = new ModelMapper().map(produtoDTO.get(), ProdutoResponse.class);
            return new ResponseEntity<>(Optional.of(response), HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> create(@RequestBody ProdutoRequest produtoRequest){
        ModelMapper mapper = new ModelMapper();
        ProdutoDTO produtoDTO = produtoService.save(mapper.map(produtoRequest, ProdutoDTO.class));
        
        return new ResponseEntity<>(mapper.map(produtoDTO, ProdutoResponse.class), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> update(@RequestBody ProdutoRequest produtoRequest, @PathVariable("id") Long id){
        ModelMapper mapper = new ModelMapper();
        ProdutoDTO produtoDTO = produtoService.update(mapper.map(produtoRequest, ProdutoDTO.class), id);

        return new ResponseEntity<>(
                mapper.map(produtoDTO, ProdutoResponse.class),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        produtoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
