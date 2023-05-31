
package com.datacosmos.datacosmosproject.controller;


import com.datacosmos.datacosmosproject.Dto.DatasetDto;
import com.datacosmos.datacosmosproject.entities.Datasets;
import com.datacosmos.datacosmosproject.entities.Favorites;
import com.datacosmos.datacosmosproject.entities.User;
import com.datacosmos.datacosmosproject.response.ApiResponse;
import com.datacosmos.datacosmosproject.service.AuthenticationService;
import com.datacosmos.datacosmosproject.service.DatasetsService;
import com.datacosmos.datacosmosproject.service.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.net.Authenticator;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoritesController {

    @Autowired
    FavoritesService favoritesService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/{token}")
    public ResponseEntity<List<DatasetDto>> getFavorites(@PathVariable("token") String token) {

        int user_id = Math.toIntExact(authenticationService.getUser(token).getId());

        //get wishlist
        List<Favorites> body = favoritesService.readFavorites((long) user_id);
        List<DatasetDto> datasets = new ArrayList<DatasetDto>();
        for (Favorites favorites : body) {
            datasets.add(DatasetsService.getDtoFromDataset(favorites.getDatasets()));
        }
        return new ResponseEntity<List<DatasetDto>>(datasets, HttpStatus.OK);
    }

    @PostMapping("/fav")
    public ResponseEntity<ApiResponse> addFavorites(@RequestBody Datasets datasets, @RequestParam("token") String token){
    authenticationService.authenticate(token);
    User user = authenticationService.getUser(token);
    Favorites favorites = new Favorites(user.getId(), datasets.getId());
    favoritesService.createFavorites(favorites);
    return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Add to favorites"), HttpStatus.CREATED);
    }


}
