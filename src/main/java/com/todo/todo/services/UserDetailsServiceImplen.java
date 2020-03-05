package com.todo.todo.services;

import com.todo.todo.entity.Usuario;
import com.todo.todo.repositories.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImplen implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    // Cuando un usuario intenta autenticarse, este método recibe el nombre de usuario, 
    // busca en la base de datos un registro que lo contenga y (si se encuentra) devuelve una 
    // instancia de User. 
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new User(user.getEmail(), user.getPassword(), emptyList());
    }

    public void setToken(String email, String token) {
        Usuario user = userRepository.findByUsername(email);
        user.setToken(token);
        userRepository.save(user);
    }
}