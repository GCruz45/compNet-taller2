package co.edu.icesi.dev.uccareapp.transport.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.model.system.Users;
import co.edu.icesi.dev.uccareapp.transport.repositories.UserRepository;


@Service
public class MyCustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepo.findByUsername(username);
		if (user != null) {
			User.UserBuilder builder = User
					.withUsername(username)
					.password(user.getPassword())
					.roles(user.getType().toString());
			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}
}