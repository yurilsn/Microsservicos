package br.com.microsservicos.demo.service;

import br.com.microsservicos.demo.domain.Produto;
import br.com.microsservicos.demo.domain.exception.ResourceNotFoundException;
import br.com.microsservicos.demo.repository.ProdutoRepository;
import br.com.microsservicos.demo.shared.ProdutoDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ProdutoService {
    private ProdutoRepository produtoRepository;

    public List<ProdutoDTO> findAll(){
        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream().map(produto -> new ModelMapper().map(produto, ProdutoDTO.class)).collect(Collectors.toList());

    }

    public Optional<ProdutoDTO> findById(Long id){
        Optional<Produto> produto =  produtoRepository.findById(id);
        if (produto.isEmpty()){
            throw new ResourceNotFoundException("Produto com ID: " + id + ", não foi encontrado");
        }

        ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);
        return Optional.of(dto);
    }

    public ProdutoDTO save(ProdutoDTO produtoDTO){
        ModelMapper mapper = new ModelMapper();

        Produto produto = produtoRepository.save(mapper.map(produtoDTO, Produto.class));

        produtoDTO.setId(produto.getId());

        return produtoDTO;
    }

    public ProdutoDTO update(ProdutoDTO produtoDTOAtualizado, Long id){
//        return produtoRepository.findById(id).map(produto -> {
//            produto.setNome(produtoDTOAtualizado.getNome());
//            produto.setQuantidade(produtoDTOAtualizado.getQuantidade());
//            produto.setValor(produtoDTOAtualizado.getValor());
//            produto.setObservacao(produtoDTOAtualizado.getObservacao());
//            return produtoRepository.save(produto);
//        }).orElseGet(() -> {return produtoRepository.save(produtoDTOAtualizado);});


//        produtoDTOAtualizado.setId(id);
//        return produtoRepository.save(produtoDTOAtualizado);
        produtoDTOAtualizado.setId(id);

        ModelMapper mapper = new ModelMapper();

        Produto produto = mapper.map(produtoDTOAtualizado, Produto.class);

        produtoRepository.save(produto);

        return produtoDTOAtualizado;
    }

    public void delete(Long id){
        produtoRepository.deleteById(id);
    }
}
