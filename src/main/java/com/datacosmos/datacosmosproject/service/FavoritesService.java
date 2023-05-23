package com.datacosmos.datacosmosproject.service;

import com.datacosmos.datacosmosproject.Dto.DatasetDto;
import com.datacosmos.datacosmosproject.entities.Favorites;
import com.datacosmos.datacosmosproject.entities.User;
import com.datacosmos.datacosmosproject.repository.favoritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoritesService {

    // use the correct type name and field name
    @Autowired
    favoritesRepository favoritesRepo;

    @Autowired
    DatasetsService datasetsService;

    public FavoritesService(favoritesRepository favoritesrepository) {
        // assign the parameter to the field
        this.favoritesRepo = favoritesrepository;
    }
    //CreateFavorites
    public void createFavorites(Favorites favorites) {
        favoritesRepo.save(favorites);
    }


    //readFavorites
    public List<Favorites> readFavorites(User user){
        return favoritesRepo.findAllByUserId(user);
    }


    public List<DatasetDto> getFavoritesForUser(User user) {
       final List<Favorites> fav = favoritesRepo.findAllByUserOrderByCreatedDateDesc(user);
       List<DatasetDto> datasetsDtos = new ArrayList<>();
       for (Favorites favorites: fav){
           datasetsDtos.add(datasetsService.getDatasetDto(favorites.getDatasets()));
       }
       return datasetsDtos;
    }
}
