package com.vilgodskaia.movieplatformpetproject.config.security;

import com.vilgodskaia.movieplatformpetproject.model.Role;
import com.vilgodskaia.movieplatformpetproject.model.User;
import com.vilgodskaia.movieplatformpetproject.repository.UserRestRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(@Autowired UserRestRepository userRestRepository) {
        return login -> userRestRepository.findByLoginIgnoreCase(login)
                .map(this::convertCustomUserToSpringSecurityUser)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by login: " + login));
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(@Autowired UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(encoder());
        return authenticationProvider;
    }

    @SneakyThrows
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) {
        httpSecurity.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(auth -> auth
                        .antMatchers(
                                "/v2/api-docs",
                                "/configuration/ui",
                                "/swagger-resources",
                                "/configuration/security",
                                "/swagger-ui.html",
                                "/webjars/**",
                                "/swagger-resources/**",
                                "/api/discovery/oauth-server").permitAll()
                        .antMatchers("/**").authenticated())
                .httpBasic();
        return httpSecurity.build();
    }

    private org.springframework.security.core.userdetails.User convertCustomUserToSpringSecurityUser(User user) {
        String[] roles = user
                .getRoles()
                .stream()
                .map(Role::name)
                .toArray(String[]::new);
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                AuthorityUtils.createAuthorityList(roles));
    }
}
