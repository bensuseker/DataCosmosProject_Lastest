package com.datacosmos.datacosmosproject.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Data Transfer Object (DTO) class representing a rating.
 * The RatingDto class encapsulates information related to a rating,
 * such as the dataset ID, email of the user giving the rating, and the rating average.
 *
 * The class uses Lombok annotations to automatically generate getter and setter methods,
 * as well as constructors, reducing boilerplate code.
 *
 * Class properties:
 * - datasetId: The ID of the dataset being rated.
 * - email: The email of the user giving the rating.
 * - ratingAverage: The rating average for the dataset.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingDto {

    private Long datasetId;
    private String email;
    private Integer rating;

    @Override
    public String toString() {
        return "RatingDto{" +
                "datasetId=" + datasetId +
                ", email='" + email + '\'' +
                ", rating=" + rating +
                '}';
    }
}
