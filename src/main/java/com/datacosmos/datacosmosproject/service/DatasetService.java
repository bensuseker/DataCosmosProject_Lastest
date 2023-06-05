package com.datacosmos.datacosmosproject.service;

import com.datacosmos.datacosmosproject.Dto.DatasetDto;
import com.datacosmos.datacosmosproject.entities.Datasets;
import com.datacosmos.datacosmosproject.repository.datasetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The DatasetService class handles the business logic for managing datasets.
 * It interacts with the datasetsRepository to perform CRUD operations on datasets.
 * Both methods are annotated with @Transactional(readOnly = true) to indicate
 * that they are read-only transactions and do not modify the data.
 * The DatasetService class encapsulates the interaction with the repository
 * and provides a higher-level interface for working with datasets, returning DTO objects instead of entity objects.
 */
@Service
public class DatasetService {

    @Autowired
    private datasetsRepository datasetsRepo;

    /**
     * Retrieves all datasets from the repository and returns a paginated result of DatasetDto objects.
     *
     * @param pageable The pagination information.
     * @return A paginated result of DatasetDto objects.
     */
    @Transactional(readOnly = true)
    public Page<DatasetDto> findAll(Pageable pageable) {
        Page<Datasets> result = datasetsRepo.findAll(pageable);
        Page<DatasetDto> page = result.map(x -> new DatasetDto(x));
        return page;
    }

    /**
     * Finds a dataset by its ID and returns a DatasetDto object.
     *
     * @param id The ID of the dataset to find.
     * @return The DatasetDto object representing the found dataset.
     */
    @Transactional(readOnly = true)
    public DatasetDto findById(Long id) {
        Datasets result = datasetsRepo.findById(id).get();
        DatasetDto dto = new DatasetDto(result);
        return dto;
    }

    /**
     * Creates a dataset and inserts it into the database.
     *
     * @param datasetDto the DatasetDto object
     * @return The Datasets entity object from the database.
     */
    public Datasets createDataset(DatasetDto datasetDto) throws DataIntegrityViolationException {
        Datasets dataset = new Datasets();
        dataset.setName(datasetDto.getDatasetName());
        dataset.setKeyword(datasetDto.getKeyword());
        dataset.setUrl(datasetDto.getUrl());
        dataset.setRatingAverage(datasetDto.getRatingAverage());
        return datasetsRepo.save(dataset);
    }

    /**
     * Creates a dataset and inserts it into the database.
     *
     * @param dataset the dataset object we want a Dto object for
     * @return the datasetDto object associated with the given dataset.
     */
    public DatasetDto getDatasetDto(Datasets dataset) {
        DatasetDto datasetDto = new DatasetDto();
        dataset.setName(dataset.getName());
        dataset.setKeyword(dataset.getKeyword());
        dataset.setUrl(dataset.getUrl());
        dataset.setRatingAverage(dataset.getRatingAverage());
        return datasetDto;
    }

    /**
     * Finds a dataset from the database based on name, url and keyword.
     *
     * @param name the name of the dataset we want
     * @param url the url of the dataset we want
     * @param keyword the keyword of the dataset we want
     * @return the datasetDto object associated with the given dataset.
     */
    public Datasets getDataset(String name, String url, String keyword) {
        return datasetsRepo.findByNameAndUrlAndKeyword(name, url, keyword);
    }

}
