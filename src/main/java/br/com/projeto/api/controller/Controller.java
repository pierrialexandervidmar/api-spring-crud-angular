package br.com.projeto.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.entity.Client;
import br.com.projeto.api.repository.Repository;

@RestController
public class Controller {

  @Autowired
  private Repository repository;

  @PostMapping("/cadastrar")
  public Client create(@RequestBody Client cliente) {
    return repository.save(cliente);
  }
  
  @GetMapping("/listar")
  public Iterable<Client> findAll() {
    return repository.findAll();
  }

  @PutMapping("/editar")
  public Client update(@RequestBody Client cliente) {
    return repository.save(cliente);
  }
}
