/*package com.datacosmos.datacosmosproject.controller;

import com.datacosmos.datacosmosproject.Dto.DatasetDto;
import com.datacosmos.datacosmosproject.entities.Datasets;
import com.datacosmos.datacosmosproject.entities.Favorites;
import com.datacosmos.datacosmosproject.entities.User;
import com.datacosmos.datacosmosproject.response.ApiResponse;
import com.datacosmos.datacosmosproject.service.AuthenticationService;
import com.datacosmos.datacosmosproject.service.FavoritesService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

*/

/*
@RestController
@RequestMapping("/favorites")
public class FavoritesController {

    @Autowired
    FavoritesService favoritesService;
    @Autowired
    AuthenticationService authenticationService;*/
    //save website links as favorites item

/*
    @SneakyThrows
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToFavorites(@RequestParam Datasets datasets,
                                                      @RequestParam("token") String token) {

            //authenticate the token
        authenticationService.authenticate(token);

        User user = authenticationService.getUser(token);

        Favorites favorites = new Favorites(user, datasets);

        favoritesService.createFavorites(favorites);

        ApiResponse apiResponse = new ApiResponse(true, "Added to favorites");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        //find the user
        //save the websitelink in favorites
    }

    //get all favorites item for user
    @SneakyThrows
    @GetMapping("/{token}")
    public ResponseEntity<List<DatasetDto>> getFavorites(@PathVariable("token") String token) {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        List<DatasetDto> favoritesForUser = favoritesService.getFavoritesForUser(user);
        return new ResponseEntity<>(favoritesForUser, HttpStatus.OK);
    }

}
*/