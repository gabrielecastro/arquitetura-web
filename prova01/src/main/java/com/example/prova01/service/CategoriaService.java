package com.example.prova01.service;

import com.example.prova01.model.Categoria;
import com.example.prova01.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

  @Autowired
  private CategoriaRepository categoriaRepository;

  // buscar todas as categorias
  public List<Categoria> findAll() {
    return categoriaRepository.findAll();
  }

  // buscar uma categoria pelo ID
  public Optional<Categoria> findById(Long id) {
    return categoriaRepository.findById(id);
  }

  // salvar uma nova categoria ou atualizar uma existente
  public Categoria save(Categoria categoria) {
    return categoriaRepository.save(categoria);
  }

  // deletar uma categoria pelo ID
  public void deleteById(Long id) {
    categoriaRepository.deleteById(id);
  }
}
