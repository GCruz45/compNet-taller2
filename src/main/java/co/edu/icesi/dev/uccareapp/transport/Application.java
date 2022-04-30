package co.edu.icesi.dev.uccareapp.transport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import co.edu.icesi.dev.uccareapp.transport.model.system.UserType;
import co.edu.icesi.dev.uccareapp.transport.model.system.Users;
import co.edu.icesi.dev.uccareapp.transport.repositories.UserRepository;

@SpringBootApplication
//@ComponentScan(basePackages = {"main"})
public class Application implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
    @Override
    public void run(String... args) throws Exception {
    	Users newUser = new Users();
    	newUser.setUsername("adamNoop");
    	newUser.setPassword("{noop}12341234");
    	newUser.setType(UserType.ADMIN);;
    	
    	userRepo.save(newUser);
    	
    	Users newUser2 = new Users();
    	newUser2.setUsername("abelNoop");
    	newUser2.setPassword("{noop}12341234");
    	newUser2.setType(UserType.operator);;
    	
    	userRepo.save(newUser2);
    }
}
