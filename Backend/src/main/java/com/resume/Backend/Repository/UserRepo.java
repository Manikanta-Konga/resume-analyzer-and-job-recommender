package com.resume.Backend.repository;

import com.resume.Backend.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UsersModel, Long> {
}
