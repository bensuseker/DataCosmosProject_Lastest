package com.datacosmos.datacosmosproject.repository;

import com.datacosmos.datacosmosproject.entities.Datasets;
import com.datacosmos.datacosmosproject.entities.Favorites;
import com.datacosmos.datacosmosproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface favoritesRepository extends JpaRepository<Favorites, Integer> {


    List<Favorites> findAllByUserIdOrderByCreatedDateDesc(Long userId);



}
