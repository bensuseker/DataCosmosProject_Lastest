package com.datacosmos.datacosmosproject.repository;

import com.datacosmos.datacosmosproject.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);


}
