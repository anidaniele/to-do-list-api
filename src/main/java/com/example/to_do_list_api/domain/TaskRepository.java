package com.example.to_do_list_api.domain;

import com.example.to_do_list_api.persistence.Task;
import com.example.to_do_list_api.persistence.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findAllByUser(User user);

    Page<Task> findAllByUser(User user, Pageable pageable);

    Optional<Task> findByIdAndUser(int id, User user);
}
