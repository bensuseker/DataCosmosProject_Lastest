
package com.datacosmos.datacosmosproject.controller;


import com.datacosmos.datacosmosproject.Dto.DatasetDto;
import com.datacosmos.datacosmosproject.entities.Datasets;
import com.datacosmos.datacosmosproject.entities.Favorites;
import com.datacosmos.datacosmosproject.entities.User;
import com.datacosmos.datacosmosproject.response.ApiResponse;
import com.datacosmos.datacosmosproject.service.AuthenticationService;
import com.datacosmos.datacosmosproject.service.DatasetsService;
import com.datacosmos.datacosmosproject.service.FavoritesService;
import com.datacosmos.datacosmosproject.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private IUserService userService;

    @Autowired
    private DatasetsService datasetsService;

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
//    public ResponseEntity<ApiResponse> addFavorites(@RequestBody Datasets datasets, @RequestParam("token") String token) {
    public ResponseEntity<ApiResponse> addFavorites(String datasetName, String datasetLink, String keyword) {
//        authenticationService.authenticate(token);
//        System.out.println("token from addFavorites: " + token);
//        User user = authenticationService.getUser(token);
//        System.out.println("current user: " + user);

        // Retrieve the authentication object from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authentication object: " + authentication);
        String userEmail = authentication.getName();
        System.out.println("user email: " + userEmail);

        // Create a datasetDTO object and populate it with form fields
        DatasetDto datasetDto = new DatasetDto();
        datasetDto.setDataset_name(datasetName);
        datasetDto.setUrl(datasetLink);
        datasetDto.setKeyword(keyword);
//        datasetDto.calculateRatingAverage();

        // Create a dataset entry in the database
        Datasets dataset = datasetsService.createDataset(datasetDto);
        System.out.println("dataset object: " + dataset);

        // Get currently logged in user
        User user = userService.findByEmail(authentication.getName());
        System.out.println("user object: " + user);
        System.out.println("user.getId(): " + user.getId());
        System.out.println("dataset.getId(): " + dataset.getId());

        // Create a favorites entry in the database
        Favorites favorites = new Favorites(user.getId(), dataset.getId());
        System.out.println("favorites object: " + favorites);
        favoritesService.createFavorites(favorites);

        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to favorites"), HttpStatus.CREATED);
    }


}
