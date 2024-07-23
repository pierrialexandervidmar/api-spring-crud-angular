package br.com.projeto.api.resources;

import java.net.URI;
import java.util.Optional;

import br.com.projeto.api.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.projeto.api.entity.Client;
import br.com.projeto.api.service.ClientService;

@RestController
@CrossOrigin(origins = "*")
public class ClientResource {

  @Autowired
  private ClientService service;

  @PostMapping("cliente")
  public ResponseEntity<Client> create(@RequestBody Client cliente) {
    Client obj = service.save(cliente);
    URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest().path("/{id}")
        .buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(uri).body(obj);
  }

  @GetMapping("clientes")
  public ResponseEntity<Iterable<Client>> findAll() {
    Iterable<Client> list = service.findAll();
    return ResponseEntity.ok().body(list);
  }

  @PutMapping("cliente/{id}")
  public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client cliente) {
    try {
      Client updatedClient = service.update(id, cliente);
      return ResponseEntity.ok().body(updatedClient);
    } catch (ResourceNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("cliente/{id}")
  public ResponseEntity<Optional<Client>> findById(@PathVariable Long id) {
    Optional<Client> client = service.findById(id);
    return client != null ? ResponseEntity.ok().body(client) : ResponseEntity.notFound().build();
  }

  @DeleteMapping("cliente/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    try {
      Optional<Client> client = service.findById(id);
      if (client != null) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
      }
      return ResponseEntity.notFound().build();
    } catch (ResourceNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
