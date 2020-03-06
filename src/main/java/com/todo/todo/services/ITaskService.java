package com.todo.todo.services;

import java.util.List;

import com.todo.todo.entity.Task;

public interface ITaskService {
    public List<Task> findAllById(int idUser);
    public void save(Task task);
}