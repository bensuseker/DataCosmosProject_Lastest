package com.datacosmos.datacosmosproject.repository;

import com.datacosmos.datacosmosproject.entities.Rating;
import com.datacosmos.datacosmosproject.entities.RatingS;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, RatingS> {


}
