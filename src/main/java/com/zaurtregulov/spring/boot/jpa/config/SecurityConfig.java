package com.zaurtregulov.spring.boot.jpa.config;

import com.zaurtregulov.spring.boot.jpa.service.MyUserService;
import com.zaurtregulov.spring.boot.jpa.service.MyUserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //аннотация, которая указывает, что данная конфигурация предназначена для настройки Spring
@EnableWebSecurity //аннотация, которая указывает, что данная конфигурация предназначена для настройки Spring Security
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(){ //UserDetailsService - интерфейс, который предоставляет способ получения пользователя по имени
//        UserDetails admin = User.builder().username("admin").password(encoder.encode("admin")).roles("ADMIN").build();
//        UserDetails user = User.builder().username("user").password(encoder.encode("user")).roles("USER").build();
//        UserDetails peter = User.builder().username("peter").password(encoder.encode("peter")).roles("ADMIN","USER").build();

//       return new InMemoryUserDetailsManager(admin, user, peter); //InMemoryUserDetailsManager - интерфейс, который предоставляет способ хранения пользователей в памяти
        return new MyUserServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){ //PasswordEncoder - интерфейс, который предоставляет способ шифрования пароля
        return new BCryptPasswordEncoder(); //библиотека для шифрования пароля
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers("/app/welcome", "/app/add-user","/api/**").permitAll() //permitAll() - разрешает всем пользователям
                .requestMatchers("/app/**").authenticated()) //authenticated() - разрешает только аутентифицированным пользователям
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
