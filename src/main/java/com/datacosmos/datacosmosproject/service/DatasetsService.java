package com.datacosmos.datacosmosproject.service;

import com.datacosmos.datacosmosproject.Dto.DatasetDto;
import com.datacosmos.datacosmosproject.entities.Datasets;
import com.datacosmos.datacosmosproject.repository.datasetsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DatasetsService {

    @Autowired
    datasetsRepository datasetsRepo;


    public Datasets createDataset(DatasetDto datasetDto) {
        Datasets dataset = new Datasets();
        dataset.setDataset_name(datasetDto.getDataset_name());
        dataset.setKeyword(datasetDto.getKeyword());
        dataset.setUrl(datasetDto.getUrl());
        dataset.setRatingAverage(datasetDto.getRatingAverage());
        return datasetsRepo.save(dataset);
    }

    public DatasetDto getDatasetDto(Datasets datasets) {
        DatasetDto datasetDto = new DatasetDto();
        datasets.setDataset_name(datasets.getDataset_name());
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

}
