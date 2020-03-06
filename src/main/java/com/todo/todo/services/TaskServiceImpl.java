package com.todo.todo.services;

import java.util.List;

import com.todo.todo.entity.Task;
import com.todo.todo.entity.Usuario;
import com.todo.todo.repositories.ITaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private ITaskRepository taskServiceImpl;

    @Autowired
    private IUserService userService;

    @Override
    public List<Task> findAllById(int idUser) {
        return taskServiceImpl.findAll();
    }

    @Override
    public void save(Task task) {
        taskServiceImpl.save(task);
    }

    // private int getIdUsuario() {
    //     String username = (SecurityContextHolder.getContext().getAuthentication().getPrincipal()).toString();
    //     Usuario usuario = userService.findByUsername(username);
    //     return usuario.getId();
    // }
    
}