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
        // Find or create the user
        User user = userRepo.findByEmail(dto.getEmail());
        if (user == null) {
            user = new User();
            user.setEmail(dto.getEmail());
            user = userRepo.saveAndFlush(user);
        }
        // Find the dataset
        Datasets datasets = datasetsRepo.findById(dto.getDatasetId()).get();

        // Create a new rating and associate it with the user and dataset
        Rating rating = new Rating();
        rating.setDatasets(datasets);
        rating.setUser(user);
        rating.setValue(dto.getRatingAverage());

        // Save the rating
        rating = ratingRepo.saveAndFlush(rating);


        // Calculate the new average rating for the dataset
        double sum = 0.0;
        for (Rating s : datasets.getRatings()) {
            sum = sum + s.getValue();
        }

        double avg = sum / datasets.getRatings().size();

        // Update the dataset with the new average rating and the total number of ratings
        datasets.setRatingAverage(avg);
        datasets.setRating(datasets.getRatings().size());

        // Save the updated dataset
        datasets = datasetsRepo.save(datasets);

        return new DatasetDto(datasets);
    }
}
