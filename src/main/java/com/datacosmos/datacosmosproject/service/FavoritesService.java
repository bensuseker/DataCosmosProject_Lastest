package com.datacosmos.datacosmosproject.service;

import com.datacosmos.datacosmosproject.entities.Favorites;
import com.datacosmos.datacosmosproject.repository.favoritesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FavoritesService {

  private final favoritesRepository favoritesRepo;


    public FavoritesService(favoritesRepository favoritesRepo) {
        this.favoritesRepo = favoritesRepo;
    }

    //create wishlist
    public void createFavorites(Favorites favorites) {
        favoritesRepo.save(favorites);
    }
    public List<Favorites> readFavorites(Long userId){
    return favoritesRepo.findAllByUserIdOrderByCreatedDateDesc(userId);
    }
}
