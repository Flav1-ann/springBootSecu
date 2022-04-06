package eu.ensup.springsecu.config;
import eu.ensup.springsecu.domaine.User;
import eu.ensup.springsecu.model.Roles;
import eu.ensup.springsecu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import eu.ensup.springsecu.service.UserService;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordConfig passwordConfig;

    @Autowired
    private UserService userService;

//    @Autowired
//    private UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/","/login").permitAll()
            .antMatchers("/etudiant/**").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
//            .httpBasic() //Pop up
            .formLogin()
//            .loginPage("/login")
//            .defaultSuccessUrl("/", true)
//            .failureUrl("/login?error=true")
//            .permitAll()
//            .and()
//            .logout()
//            .logoutSuccessUrl("/")
//            .permitAll()
            .and()
            .csrf().disable();
        ;
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
//        userRepository.save(new User(1l,"test", Roles.USER,passwordConfig.passwordEncoder().encode("test")));

        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordConfig.passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("flavien")
//                .password(passwordConfig.passwordEncoder().encode("flavien"))
//                .roles("ADMIN")
//                    .and()
//                .withUser("test")
//                .password(passwordConfig.passwordEncoder().encode("test"))
//                .roles("USER")
//        ;
//    }

//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService(){
//
//        UserDetails user = User.builder()
//                .username("flavien")
//                .password(passwordConfig.passwordEncoder().encode("flavien"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }

}

