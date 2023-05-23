/* package com.datacosmos.datacosmosproject.service;

import com.datacosmos.datacosmosproject.entities.AuthenticationToken;
import com.datacosmos.datacosmosproject.entities.User;
import com.datacosmos.datacosmosproject.exceptions.AuthenticationFailException;
import com.datacosmos.datacosmosproject.repository.tokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {

  //  @Autowired
   // tokenRepository tokenRepo;

   // public void saveConfirmationToken(AuthenticationToken authenticationToken){
        tokenRepo.save(authenticationToken); }

    public AuthenticationToken getToken(User user) {
       return tokenRepo.findByUser(user);
    }

    public User getUser(String token){
        final AuthenticationToken authenticationToken = tokenRepo.findByToken(token);
        if (Objects.isNull(token)){
            return null;
        }
        //authenticationToken is not null
        return authenticationToken.getUser();
    }

    public void authenticate(String token) throws AuthenticationFailException {
    if (Objects.isNull(token)){
    throw new AuthenticationFailException("token not present");
    }  if (Objects.isNull(getUser(token))) {
            throw new AuthenticationFailException("token not valid");
        }


    }





    /*public void authenticate(String token) throws AuthenticationFailException {
        if (Objects.isNull(token)){
            //throw an exception
        throw new AuthenticationFailException("token not present");
        }
        if (Objects.isNull(getUser(token))){
            throw new AuthenticationFailException("token not valid");
}
    }*/


