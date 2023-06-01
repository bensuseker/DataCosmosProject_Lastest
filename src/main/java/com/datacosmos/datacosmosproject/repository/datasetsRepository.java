package com.datacosmos.datacosmosproject.repository;

import com.datacosmos.datacosmosproject.entities.Datasets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface datasetsRepository extends JpaRepository<Datasets, Long> {
    Datasets findByNameAndUrlAndKeyword(String Dataset_name, String url, String keyword);

//    @Query(value = "SELECT AVG(rating_value) FROM rating WHERE dataset_id = :datasetId", nativeQuery = true)
//    Double calculateAverageRating(@Param("datasetId") Long datasetId);
}
