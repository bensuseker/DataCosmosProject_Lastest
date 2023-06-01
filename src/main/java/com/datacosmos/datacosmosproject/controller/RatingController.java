package com.datacosmos.datacosmosproject.controller;

import com.datacosmos.datacosmosproject.Dto.DatasetDto;
import com.datacosmos.datacosmosproject.Dto.RatingDto;
import com.datacosmos.datacosmosproject.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/scores", method = { RequestMethod.GET, RequestMethod.POST })
public class RatingController {

    @Autowired
    private RatingService ratingService;

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
    @PutMapping
    public DatasetDto saveRating(@RequestBody RatingDto dto){

        // Save the rating and get the updated dataset DTO
        DatasetDto datasetDto = ratingService.saveRating(dto);

        //Return the updated dataset DTO
       return datasetDto;
    }
}
