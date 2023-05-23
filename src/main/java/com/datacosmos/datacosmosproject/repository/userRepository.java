package com.datacosmos.datacosmosproject.repository;

import com.datacosmos.datacosmosproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<User, Long> {

   User findByEmail(String email);


}
