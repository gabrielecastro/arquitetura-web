package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Usuario;
import com.example.demo.Service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")

public class Usuariocontroller {
 
    private final UsuarioService service;
   
    public Usuariocontroller(UsuarioService service) {
        this.service = service;
    }
        @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }
        @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                     .map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
        @PostMapping
    public ResponseEntity<Usuario> salvar(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.ok(service.salvar(usuario));
    }
        @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}



