package bg.softUni.Countries.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests(
                        authorizeRequest -> authorizeRequest
                                .requestMatchers("/", "/users/login","/users/login-error", "/users/register","/about").permitAll()
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .anyRequest().authenticated()
                ).formLogin(
                        formLogin -> {
                            formLogin.loginPage("/users/login");
                            formLogin.usernameParameter("username");
                            formLogin.passwordParameter("password");
                            formLogin.defaultSuccessUrl("/", true);
                            formLogin.failureForwardUrl("/users/login-error");
                        }
                )
                .logout(
                        logout -> {
                            logout.logoutUrl("/users/logout");
                            logout.logoutSuccessUrl("/");
                            logout.invalidateHttpSession(true);
                        }
                )

                .build();

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }
}
