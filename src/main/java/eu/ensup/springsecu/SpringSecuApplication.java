package eu.ensup.springsecu;

import eu.ensup.springsecu.config.PasswordConfig;
import eu.ensup.springsecu.domaine.User;
import eu.ensup.springsecu.model.Roles;
import eu.ensup.springsecu.repository.UserRepository;
import eu.ensup.springsecu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecuApplication {



    public static void main(String[] args) {
        SpringApplication.run(SpringSecuApplication.class, args
        );
    }

}
