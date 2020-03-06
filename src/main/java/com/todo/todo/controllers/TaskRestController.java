package com.todo.todo.controllers;

import com.todo.todo.entity.Task;
import com.todo.todo.entity.Usuario;
import com.todo.todo.services.ITaskService;
import com.todo.todo.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskRestController {

    @Autowired
    private ITaskService taskService;

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        int idUser = getUsuario();
        return new ResponseEntity<>(taskService.findAllById(idUser),HttpStatus.ACCEPTED);
    }

    @PostMapping()
	public ResponseEntity<?> addTask(@RequestBody Task task){
		taskService.save(task);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

    private int getUsuario() {
        String username = (SecurityContextHolder.getContext().getAuthentication().getPrincipal()).toString();
        Usuario usuario = userService.findByUsername(username);
        return usuario.getId();
    }
}