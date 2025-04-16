    package com.secureCrud.secureCrud.config;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    import com.secureCrud.secureCrud.filter.JwtAuthFilter;
    import com.secureCrud.secureCrud.service.CustomUserDetailsService;

    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {
        private final JwtAuthFilter jwtAuthFilter;



    @Autowired
        private CustomUserDetailsService customUserDetailsService;




        // Constructor injection of JwtAuthFilter
        public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
            this.jwtAuthFilter = jwtAuthFilter;
        }
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                .csrf(csrf -> csrf.disable())  // Disable CSRF for APIs
                .authorizeHttpRequests(auth -> auth
                    //.requestMatchers("/auth/**").permitAll() // Allow authentication endpoints
                    .requestMatchers("/api/auth/register","/api/auth/login").permitAll()
                    .requestMatchers("/api/hello").hasAnyRole("ADMIN","MANAGER")
                    .anyRequest().authenticated() // Secure all other endpoints
                ).userDetailsService(customUserDetailsService)
                .httpBasic(httpBasic -> httpBasic.disable()) // Disable HTTP Basic Authentication
                .formLogin(form -> form.disable()) // Disable Form Login
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
        
    }
