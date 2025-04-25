package com.example.prova01.controller;

import com.example.prova01.model.Categoria;
import com.example.prova01.model.Produto;
import com.example.prova01.repository.CategoriaRepository;
import com.example.prova01.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

  @Autowired
  private ProdutoService produtoService;

  @Autowired
  private CategoriaRepository categoriaRepository;

  @GetMapping
  public List<Produto> getAllProdutos() {
    return produtoService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
    Optional<Produto> produto = produtoService.findById(id);
    return produto.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Produto> save(@RequestBody Produto produto) {
    if (produto.getCategoria() != null && produto.getCategoria().getId() != null) {
      Categoria categoria = categoriaRepository.findById(produto.getCategoria().getId())
          .orElseThrow(
              () -> new RuntimeException("Categoria não encontrada com ID: " + produto.getCategoria().getId()));
      produto.setCategoria(categoria);
    } else {
      return ResponseEntity.badRequest().body(null);
    }

    Produto produtoSalvo = produtoService.save(produto);
    return ResponseEntity.ok(produtoSalvo);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody Produto produtoDetails) {
    Optional<Produto> produtoOptional = produtoService.findById(id);

    if (produtoOptional.isPresent()) {
      Produto produto = produtoOptional.get();
      produto.setNome(produtoDetails.getNome());
      produto.setDescricao(produtoDetails.getDescricao());
      produto.setQuantidade(produtoDetails.getQuantidade());

      if (produtoDetails.getCategoria() != null && produtoDetails.getCategoria().getId() != null) {
        Categoria categoria = categoriaRepository.findById(produtoDetails.getCategoria().getId())
            .orElseThrow(() -> new RuntimeException(
                "Categoria não encontrada com ID: " + produtoDetails.getCategoria().getId()));
        produto.setCategoria(categoria);
      }

      return ResponseEntity.ok(produtoService.save(produto));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
    produtoService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}