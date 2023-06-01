
package com.datacosmos.datacosmosproject.controller;


import com.datacosmos.datacosmosproject.Dto.DatasetDto;
import com.datacosmos.datacosmosproject.entities.Datasets;
import com.datacosmos.datacosmosproject.entities.Favorites;
import com.datacosmos.datacosmosproject.entities.User;
import com.datacosmos.datacosmosproject.response.ApiResponse;
import com.datacosmos.datacosmosproject.service.DatasetsService;
import com.datacosmos.datacosmosproject.service.FavoritesService;
import com.datacosmos.datacosmosproject.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoritesController {

    @Autowired
    FavoritesService favoritesService;

    @Autowired
    private IUserService userService;

    @Autowired
    private DatasetsService datasetsService;

    @GetMapping("")
    public List<Favorites> getFavorites() {
        // Get currently logged in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(authentication.getName());

        // Get favorites
        return favoritesService.readFavorites(user.getId());
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addFavorites(String datasetName, String datasetLink, String keyword) {
        // Retrieve the authentication object from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Create a datasetDTO object and populate it with form fields
        DatasetDto datasetDto = new DatasetDto();
        datasetDto.setDataset_name(datasetName);
        datasetDto.setUrl(datasetLink);
        datasetDto.setKeyword(keyword);
//        datasetDto.calculateRatingAverage();

        Datasets dataset;

        // Create a dataset entry in the database or get already existing one
        try {
            dataset = datasetsService.createDataset(datasetDto);
        }
        // This exception will be raised if this dataset already exists
        catch (DataIntegrityViolationException e) {
//            System.out.println("EXCEPTION CAUGHT!!! " + e);
            // In this case we can just get that database object from the database
            dataset = datasetsService.getDataset(datasetName, datasetLink, keyword);
            System.out.println("Duplicate dataset exception raised, retrieved already existing dataset: " + dataset);
        }

        System.out.println("dataset object: " + dataset);

        // Get currently logged in user
        User user = userService.findByEmail(authentication.getName());
//        System.out.println("user object: " + user);
//        System.out.println("user.getId(): " + user.getId());
//        System.out.println("dataset.getId(): " + dataset.getId());

        // Create a favorites entry in the database if it doesn't already exist
        Favorites favorite = new Favorites(user.getId(), dataset.getId());
        System.out.println("favorites object: " + favorite);
        try {
            favoritesService.createFavorites(favorite);
        }
        // This exception will be raised if this favorite already exists
        catch (DataIntegrityViolationException e) {
            System.out.println("Duplicate favorite exception raised: " + e);
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, "This dataset is already added as a favorite for this user"), HttpStatus.CREATED);
        }

        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to favorites"), HttpStatus.CREATED);
    }
}
