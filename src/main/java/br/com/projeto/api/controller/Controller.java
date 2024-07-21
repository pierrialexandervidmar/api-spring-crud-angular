package br.com.projeto.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.repository.Repository;

@RestController
public class Controller {

  @Autowired
  private Repository repository;  
  
  @GetMapping("/teste")
  public String teste() {
    return "Hello World";
  }
}
