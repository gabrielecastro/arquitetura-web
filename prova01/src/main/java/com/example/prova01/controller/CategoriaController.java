package com.example.prova01.controller;

import com.example.prova01.model.Categoria;
import com.example.prova01.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

  @Autowired
  private CategoriaService categoriaService;

  // buscar todas as categorias
  @GetMapping
  public List<Categoria> getAllCategorias() {
    return categoriaService.findAll();
  }

  // buscar uma categoria pelo ID
  @GetMapping("/{id}")
  public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
    Optional<Categoria> categoria = categoriaService.findById(id);
    return categoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  // criar uma nova categoria
  @PostMapping
  public Categoria createCategoria(@RequestBody Categoria categoria) {
    return categoriaService.save(categoria);
  }

  // atualizar uma categoria existente
  @PutMapping("/{id}")
  public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @RequestBody Categoria categoriaDetails) {
    Optional<Categoria> categoriaOptional = categoriaService.findById(id);
    if (categoriaOptional.isPresent()) {
      Categoria categoria = categoriaOptional.get();
      categoria.setNome(categoriaDetails.getNome());
      categoria.setDescricao(categoriaDetails.getDescricao());

      return ResponseEntity.ok(categoriaService.save(categoria));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // deletar uma categoria
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
    categoriaService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}