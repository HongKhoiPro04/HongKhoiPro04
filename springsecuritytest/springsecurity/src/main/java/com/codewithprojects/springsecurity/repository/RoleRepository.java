package com.codewithprojects.springsecurity.repository;

import com.codewithprojects.springsecurity.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

}
