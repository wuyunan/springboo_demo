package com.qingteng.demo.respository;

import com.qingteng.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RoleRepository
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String string);
}