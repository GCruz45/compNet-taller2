package co.edu.icesi.dev.uccareapp.transport.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.model.system.UserType;
import co.edu.icesi.dev.uccareapp.transport.model.system.Users;
import co.edu.icesi.dev.uccareapp.transport.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void save(Users user) {
		userRepository.save(user);

	}

	public Optional<Users> findById(long id) {

		return userRepository.findById(id);
	}

	public Iterable<Users> findAll() {
		return userRepository.findAll();
	}
	
	public Iterable<Users> findAllOperators() {
		return userRepository.findByType(UserType.operator);
	}


	public void delete(Users user) {
		userRepository.delete(user);

	}

	public UserType[] getTypes() {
		// TODO Auto-generated method stub
		return UserType.values();
	}
}
