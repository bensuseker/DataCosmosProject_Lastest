package com.datacosmos.datacosmosproject.repository;

import com.datacosmos.datacosmosproject.entities.Rating;
import com.datacosmos.datacosmosproject.entities.RatingS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RatingRepository extends JpaRepository<Rating, RatingS> {
    /**
     * Finds a Rating from the rating table by user id and dataset id,
     * we need the underscore notation because the user id and dataset id are
     * contained within the RatingS embedded key field.
     *
     * @param userId the user's id
     * @param datasetsId the datasets' id
     */
    Rating findById_User_IdAndId_Datasets_Id(Long userId, Long datasetsId);

    /**
     * Calculates the average rating for a particular dataset by running a query on the rating table
     * where the dataset_id matches.
     *
     * @param datasetsId the dataset id for which we want to calculate average rating
     */
    @Query("SELECT AVG(r.value) FROM Rating r WHERE r.id.datasets.id = :datasetsId")
    Double calculateAverageValue(@Param("datasetsId") Long datasetsId);
}
