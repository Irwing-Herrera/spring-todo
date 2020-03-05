package com.todo.todo.services;

import com.todo.todo.entity.Usuario;

public interface IUserService {
    
    public void save(Usuario user);
    public Usuario findUser(Usuario user);
    public void setToken(String email, String token);
}