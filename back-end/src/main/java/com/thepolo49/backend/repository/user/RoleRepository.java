package com.thepolo49.backend.repository.user;

import java.util.Optional;

import com.thepolo49.backend.model.user.ERole;
import com.thepolo49.backend.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
