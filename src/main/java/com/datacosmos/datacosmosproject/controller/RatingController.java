package com.datacosmos.datacosmosproject.controller;

import com.datacosmos.datacosmosproject.Dto.RatingDto;
import com.datacosmos.datacosmosproject.entities.User;
import com.datacosmos.datacosmosproject.response.ApiResponse;
import com.datacosmos.datacosmosproject.service.IUserService;
import com.datacosmos.datacosmosproject.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private IUserService userService;

    /**
     * Saves a rating for a dataset.
     * Accepts a RatingDto object in the request body, representing the rating information.
     * Calls the RatingService to save the rating and update the dataset information.
     * Returns the updated DatasetDto object containing the updated dataset information.
     *
     * dto The RatingDto object containing the rating information.
     * The DatasetDto object representing the updated dataset information.
     */
    // Handler method to save a rating
    @PostMapping("")
    public ResponseEntity<ApiResponse> saveRating(@RequestBody RatingDto dto){
        System.out.println("Trying to save the rating!");
        if (dto.getRating() < 0 || dto.getRating() > 5) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Rating must be in the range 0 to 5"), HttpStatus.BAD_REQUEST);
        }

        // Get currently logged in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(authentication.getName());
        dto.setEmail(user.getEmail());

        // Save the rating
        ratingService.saveRating(dto);

        //Return the updated dataset DTO
       return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Rating saved"), HttpStatus.OK);
    }
}
