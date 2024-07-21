package br.com.projeto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.api.entity.Client;

public interface Repository extends JpaRepository<Client, Long> {
  
}
