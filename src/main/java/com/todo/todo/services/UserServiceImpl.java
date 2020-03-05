package com.todo.todo.services;
import com.todo.todo.entity.Usuario;
import com.todo.todo.repositories.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    
    @Override
    public void save(Usuario user) {
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)  //Manejar transacción en este caso solo lectura
    public Usuario findUser(Usuario user) {
        return (Usuario) userRepository.findByEmail(user.getEmail());
    }

    @Override
    public void setToken(String email, String token) {
        Usuario user = userRepository.findByEmail(email);
        user.setToken(token);
        userRepository.save(user);
    }
    
}