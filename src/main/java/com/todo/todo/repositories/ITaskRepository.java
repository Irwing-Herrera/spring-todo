package com.todo.todo.repositories;

import java.util.List;

import com.todo.todo.entity.Task;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITaskRepository extends CrudRepository<Task, Integer> {
    public List<Task> findAll();
}