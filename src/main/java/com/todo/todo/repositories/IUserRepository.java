package com.todo.todo.repositories;

import com.todo.todo.entity.Usuario;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<Usuario, Integer> {
    public Usuario findByEmail(String email);
    public Usuario findByUsername(String username);
}