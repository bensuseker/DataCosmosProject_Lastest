package com.datacosmos.datacosmosproject.repository;

import com.datacosmos.datacosmosproject.entities.AuthenticationToken;
import com.datacosmos.datacosmosproject.entities.User;
import org.apache.catalina.authenticator.SpnegoAuthenticator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {

    AuthenticationToken findTokenByUser(User user);
    AuthenticationToken findTokenByToken(String token);

}
