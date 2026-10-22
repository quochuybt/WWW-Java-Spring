package iuh.fit.springjdbc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    // Sử dụng NoOpPasswordEncoder để đọc mật khẩu dạng thô (không băm hash)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()

                        // Phân quyền theo ma trận
                        .requestMatchers(HttpMethod.GET, "/employees", "/employees/search", "/employees/{id}").hasAnyRole("GUEST", "USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/employees/new").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/employees").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/employees/{id}/edit").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/employees/{id}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/employees/{id}/delete").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/employees", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }
}