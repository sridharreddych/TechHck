package com.hcl.myhotel.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.myhotel.domain.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
