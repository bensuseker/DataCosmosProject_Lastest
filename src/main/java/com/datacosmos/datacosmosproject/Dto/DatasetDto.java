package com.datacosmos.datacosmosproject.Dto;

import com.datacosmos.datacosmosproject.entities.Datasets;
import jakarta.annotation.Nullable;



public class DatasetDto {

    private Long id;
    private String datasetName;
    private String keyword;
    private String url;
    private Double RatingAverage;
    private Integer rating;
    private String image;

    // Default constructor
    public DatasetDto(){}

    // Parameterized constructor


    /**
     * Parameterized constructor for the DatasetDto class.
     * Initializes the DatasetDto object with the provided values.
     *
     * @param id             The ID of the dataset.
     * @param url            The URL of the dataset.
     * @param ratingAverage  The average rating of the dataset.
     * @param rating         The rating of the dataset.
     * @param image          The image associated with the dataset.
     */
    public DatasetDto(Long id, String datasetName, String keyword, String url, Double ratingAverage, Integer rating, String image) {
        this.id = id;
        this.datasetName = datasetName;
        this.keyword = keyword;
        this.url = url;
        this.RatingAverage = ratingAverage;
        this.rating = rating;
        this.image = image;
    }

    /**
     * Constructor for the DatasetDto class that takes a Datasets entity object.
     * Initializes the DatasetDto object with the values from the Datasets entity.
     *
     * @param datasets The Datasets entity object.
     */
    public DatasetDto(Datasets datasets) {
        this.id = datasets.getId();
        this.datasetName = datasets.getName();
        this.keyword = datasets.getKeyword();
        this.url = datasets.getUrl();
        this.RatingAverage = datasets.getRatingAverage();
    }

    public String getDatasetName() {
        return datasetName;
    }

    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getRatingAverage() {
        return RatingAverage;
    }

    public void setRatingAverage(Double ratingAverage) {
        RatingAverage = ratingAverage;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /**
     * Retrieves the image associated with the dataset.
     *
     * @return The image associated with the dataset.
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the image associated with the dataset.
     *
     * @param image The image associated with the dataset.
     */
    public void setImage(String image) {
        this.image = image;
    }
}
