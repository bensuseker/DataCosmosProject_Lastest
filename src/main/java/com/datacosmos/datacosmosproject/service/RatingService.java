package com.datacosmos.datacosmosproject.service;


import com.datacosmos.datacosmosproject.Dto.DatasetDto;
import com.datacosmos.datacosmosproject.Dto.RatingDto;
import com.datacosmos.datacosmosproject.entities.Datasets;
import com.datacosmos.datacosmosproject.entities.Rating;
import com.datacosmos.datacosmosproject.entities.User;
import com.datacosmos.datacosmosproject.repository.RatingRepository;
import com.datacosmos.datacosmosproject.repository.datasetsRepository;
import com.datacosmos.datacosmosproject.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The RatingService class handles the business logic for managing ratings.
 * It interacts with the repositories to perform operations on ratings, datasets, and users.
 */
@Service
public class RatingService {

    @Autowired
    private datasetsRepository datasetsRepo;

    @Autowired
    private userRepository userRepo;


    @Autowired
    private RatingRepository ratingRepo;

    /**
     * Saves a rating for a dataset.
     * If the user does not exist, a new user is created.
     * The rating is associated with the user and dataset, and the average rating for the dataset is updated.
     *
     * @param dto The RatingDto object containing the rating details.
     * @return The DatasetDto object representing the updated dataset.
     */
    @Transactional
    public DatasetDto saveRating(RatingDto dto) {
        System.out.println("Got the rating dto " + dto);
        // Get the user object
        User user = userRepo.findByEmail(dto.getEmail());
        System.out.println("Got the user " + user);

        // Find the dataset
        Datasets datasets = datasetsRepo.findById(dto.getDatasetId()).get();
        System.out.println("Got the dataset " + datasets);

        // Create a new rating and associate it with the user and dataset
        Rating rating = new Rating();
        rating.setDatasets(datasets);
        rating.setUser(user);
        rating.setValue(dto.getRating());

//        System.out.println("Set rating value to Rating object: " + rating);

        // Save the rating
        rating = ratingRepo.saveAndFlush(rating);

        System.out.println("After saveandflush, rating: " + rating);

        System.out.println("user.getId(): " + user.getId());
        System.out.println("datasets.getId(): " + datasets.getId());

        // Calculate new average rating for this dataset
        double avg = getAverageRating(datasets.getId());

        System.out.println("Average calc avg: " + avg);

        // Update the dataset with the new average rating and set the rating value set by this user
        datasets.setRatingAverage(avg);
        datasets.setRating(rating.getValue());

        // Save the updated dataset
        datasets = datasetsRepo.save(datasets);

        return new DatasetDto(datasets);
    }

    public Double getAverageRating(Long datasetsId) {
        return ratingRepo.calculateAverageValue(datasetsId);
    }
}
