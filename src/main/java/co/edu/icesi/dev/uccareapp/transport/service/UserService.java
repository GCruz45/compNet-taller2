package co.edu.icesi.dev.uccareapp.transport.service;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.model.system.UserType;
import co.edu.icesi.dev.uccareapp.transport.model.system.Users;

public interface UserService {
	public void save(Users user);

	public Optional<Users> findById(long id);

	public Iterable<Users> findAll();

	public Iterable<Users> findAllOperators();


	public void delete(Users user);


	public UserType[] getTypes();
}
