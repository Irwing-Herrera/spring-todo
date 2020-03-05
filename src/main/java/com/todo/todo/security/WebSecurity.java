package com.todo.todo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.todo.todo.security.SecurityConstants.SIGN_UP_URL;

import com.todo.todo.services.UserDetailsServiceImplen;

// Configurar Filtros
// aprovechar la configuración de seguridad web predeterminada proporcionada por Spring Security 
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    
    private UserDetailsServiceImplen userDetailsServiceImplen;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(UserDetailsServiceImplen userDetailsServiceImplen, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsServiceImplen = userDetailsServiceImplen;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // definir qué recursos son públicos y cuáles están asegurados
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .anyRequest().authenticated().and().addFilter(new JWTAuthenticationFilter(authenticationManager(), userDetailsServiceImplen))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    // implementación personalizada UserDetailsServicepara cargar datos específicos
    // del usuario en el marco de seguridad
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImplen).passwordEncoder(bCryptPasswordEncoder);
    }

    // permitir solicitudes de cualquier fuente
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}