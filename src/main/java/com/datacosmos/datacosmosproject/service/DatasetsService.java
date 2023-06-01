package com.datacosmos.datacosmosproject.service;

import com.datacosmos.datacosmosproject.Dto.DatasetDto;
import com.datacosmos.datacosmosproject.entities.Datasets;
import com.datacosmos.datacosmosproject.repository.datasetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DatasetsService {

    @Autowired
    datasetsRepository datasetsRepo;


    public Datasets createDataset(DatasetDto datasetDto) throws DataIntegrityViolationException {
        Datasets dataset = new Datasets();
        dataset.setName(datasetDto.getDatasetName());
        dataset.setKeyword(datasetDto.getKeyword());
        dataset.setUrl(datasetDto.getUrl());
        dataset.setRatingAverage(datasetDto.getRatingAverage());
        return datasetsRepo.save(dataset);
    }

    public DatasetDto getDatasetDto(Datasets datasets) {
        DatasetDto datasetDto = new DatasetDto();
        datasets.setName(datasets.getName());
        datasets.setKeyword(datasets.getKeyword());
        datasets.setUrl(datasets.getUrl());
        datasets.setRatingAverage(datasets.getRatingAverage());
        return datasetDto;
    }

    public List<DatasetDto> getAllDatasets() {
        List<Datasets> allProducts = datasetsRepo.findAll();

        List<DatasetDto> datasetDtos = new ArrayList<>();
        for (Datasets dataset : allProducts) {
            datasetDtos.add(getDatasetDto(dataset));
        }
        return datasetDtos;

    }

    public static DatasetDto getDtoFromDataset(Datasets datasets) {
        DatasetDto datasetDto = new DatasetDto(datasets);
        return datasetDto;
    }

//    public void calculateRatingsAverage() {
//        datasetsRepo.calculateAverageRating()
//    }

    public Datasets getDataset(String name, String url, String keyword) {
        return datasetsRepo.findByNameAndUrlAndKeyword(name, url, keyword);
    }

}
