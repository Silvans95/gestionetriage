package it.prova.gestionetriage.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionetriage.model.User;


public interface UserService {
	
	public User inserisciNuovo(User transientInstance);

	public List<User> listAll();

	public User cariscaSingoloElemento(Long id);

	public Page<User> searchAndPaginate(User automobileExample, Integer pageNo, Integer pageSize, String sortBy);

	public User get(Long idInput);

	public User save(User input);

	public void delete(User input);
	
}
