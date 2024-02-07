package com.example.taskManagement;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
 
@Configuration
@EnableWebSecurity
public class SecurityConfig {
// 
//    @Bean
//    public UserDetailsService userDetailsService() {
//       
//    }
 
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    .authorizeRequests()
//    .antMatchers("/api/register").permitAll()
//    .antMatchers("/api/**").authenticated()
//.and()
//.formLogin()
//    .loginPage("/login")
//    .permitAll()
//.and()
//.logout()
//    .permitAll();
//
//// Configure session management
//http.sessionManagement()
//.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//.maximumSessions(1)
//.maxSessionsPreventsLogin(true)
//.expiredUrl("/login?expired");
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     
        http.authorizeHttpRequests(
                auth -> auth.requestMatchers("/api/user/").permitAll()
//                .requestMatchers("/users/**", "/apps/**").hasAuthority("ADMIN")
//                .requestMatchers("/myapps/**").hasAuthority("CLIENT")
                .requestMatchers("/api/**").hasAnyAuthority("user")
                .anyRequest().authenticated()
               )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
//                        .usernameParameter("email")
//                        .defaultSuccessUrl("/", true)
                        .permitAll()   
                        
                )
                .logout(logout -> logout.permitAll());
//                .rememberMe(rememberMe -> rememberMe.key("AbcdEfghIjkl..."))
//                .logout(logout -> logout.logoutUrl("/signout").permitAll());
        http
        .sessionManagement(session -> session
            .maximumSessions(1)
            .maxSessionsPreventsLogin(true)
            .expiredUrl("/login?expired")
        );
     
     
        return http.build();
    }
 
    
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
            .build();
    }

//    @Bean
//    public UserDetailsManager users(DataSource dataSource) {
//        UserDetails user = User.withDefaultPasswordEncoder()
//            .username("user")
//            .password("password")
//            .roles("USER")
//            .build();
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        users.createUser(user);
//        return users;
//    }
 
}