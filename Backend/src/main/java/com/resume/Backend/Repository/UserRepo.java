package com.resume.Backend.Repository;

import com.resume.Backend.Model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UsersModel, Long> {
}
