package com.datacosmos.datacosmosproject.Dto;

public class FavoritesDto {
    private Long datasetId;
    private String datasetName;
    private String datasetLink;
    private String keyword;
    private Integer rating;
    private Double ratingAverage;

    public Long getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(Long datasetId) {
        this.datasetId = datasetId;
    }

    public String getDatasetName() {
        return datasetName;
    }

    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }

    public String getDatasetLink() {
        return datasetLink;
    }

    public void setDatasetLink(String datasetLink) {
        this.datasetLink = datasetLink;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Double getRatingAverage() {
        return ratingAverage;
    }

    public void setRatingAverage(Double ratingAverage) {
        this.ratingAverage = ratingAverage;
    }
}
