package com.example.springbootbackend.security.config;


import com.example.springbootbackend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/v1/login/**").permitAll()
                .antMatchers("/api/v1/registration/**").permitAll()
                .antMatchers("/api/products/**").permitAll()
                .antMatchers("/api/products-search/**").permitAll()
                .antMatchers("/api/user/**").permitAll()
                .antMatchers("/api/shoppingCartProduct/product/**").permitAll()
                .antMatchers("/api/shoppingCartProduct/**").permitAll()
                .antMatchers("/api/billData/**").permitAll()
                .antMatchers("/api/creditCard/**").permitAll()
                .antMatchers("/api/order/**").permitAll()
                .antMatchers("/api/genpdf/**").permitAll()
                .antMatchers("/api/bill/**").permitAll()
                .anyRequest().authenticated();
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
}
