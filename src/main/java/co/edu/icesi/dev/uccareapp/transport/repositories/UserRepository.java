package co.edu.icesi.dev.uccareapp.transport.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.dev.uccareapp.transport.model.system.UserType;
import co.edu.icesi.dev.uccareapp.transport.model.system.Users;

public interface UserRepository extends CrudRepository<Users, Long> {
	
	Users findByUsername(String username);
	
	List<Users> findByType(UserType type);

}
