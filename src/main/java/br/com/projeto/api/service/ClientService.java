package br.com.projeto.api.service;

import java.util.Optional;

import br.com.projeto.api.service.exceptions.DatabaseException;
import br.com.projeto.api.service.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.projeto.api.entity.Client;
import br.com.projeto.api.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {

  @Autowired
  private ClientRepository repository;

  @Transactional
  public Client save(Client cliente) {
    Client entity = repository.save(cliente);
    return entity;
  }

  @Transactional
  public Client update(Long id, Client cliente) {
    try {
      Client entity = repository.getReferenceById(id);

      // Atualize os campos do entity com os novos dados do cliente
      entity.setNome(cliente.getNome());
      entity.setIdade(cliente.getIdade());
      entity.setCidade(cliente.getCidade());

      entity = repository.save(entity);
      return entity;
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException("Id " + id + " não encontrado!");
    }
  }

  public Iterable<Client> findAll() {
    return repository.findAll();
  }

  public Optional<Client> findById(Long id) {
    Optional<Client> obj = repository.findById(id);
    Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada!"));
    return Optional.ofNullable(entity);
  }

  public void deleteById(Long id) {
    try {
      repository.deleteById(id);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException("Id " + id + " não encontrado!");
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException("Integrity Violation");
    }
  }
}