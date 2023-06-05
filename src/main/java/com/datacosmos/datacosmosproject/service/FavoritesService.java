package com.datacosmos.datacosmosproject.service;

import com.datacosmos.datacosmosproject.Dto.FavoritesDto;
import com.datacosmos.datacosmosproject.entities.Datasets;
import com.datacosmos.datacosmosproject.entities.Favorites;
import com.datacosmos.datacosmosproject.entities.Rating;
import com.datacosmos.datacosmosproject.repository.RatingRepository;
import com.datacosmos.datacosmosproject.repository.favoritesRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FavoritesService {

    private final favoritesRepository favoritesRepo;
    @Autowired
    private RatingRepository ratingRepo;

    public FavoritesService(favoritesRepository favoritesRepo) {
        this.favoritesRepo = favoritesRepo;
    }

    public Favorites createFavorites(Favorites favorites) throws DataIntegrityViolationException {
        return favoritesRepo.save(favorites);
    }

    public List<FavoritesDto> readFavorites(Long userId) {
        List<Favorites> favoritesList = favoritesRepo.findAllByUserIdOrderByCreatedDateDesc(userId);
        System.out.println("favoritesList: " + favoritesList);
        List<FavoritesDto> dtoList = new ArrayList<>();

        for (Favorites favorites : favoritesList) {
            FavoritesDto dto = new FavoritesDto();
            dto.setDatasetId(favorites.getDatasetId());
            System.out.println("favorites.getDatasets: " + favorites.getDatasets());
            dto.setDatasetName(favorites.getDatasets().getName());
            dto.setDatasetLink(favorites.getDatasets().getUrl());
            dto.setKeyword(favorites.getDatasets().getKeyword());
            dto.setRatingAverage(favorites.getDatasets().getRatingAverage());

            // This makes it so that each user only sees the rating that they set for a particular dataset.
            // Find the rating row from the rating table in the database, based on current user id and datasaet id
            Rating ratingRow = ratingRepo.findById_User_IdAndId_Datasets_Id(userId, favorites.getDatasetId());

            // Set the rating value into our RatingDto object
            if (ratingRow != null) {
                Integer ratingForCurrentUser = ratingRow.getValue();
                dto.setRating(ratingForCurrentUser);
            }
            else
            {
                dto.setRating(null);
            }

            // Add this dto object to our final list (which we will return)
            dtoList.add(dto);
        }

        return dtoList;
    }

    public boolean deleteFavorites(Long userId, Long datasetId) {
        Favorites favorite = favoritesRepo.findByUserIdAndDatasetId(userId, datasetId);
        if (favorite != null)
        {
            favoritesRepo.delete(favorite);
            return true;
        }
        else
        {
            return false;
        }
    }
}
