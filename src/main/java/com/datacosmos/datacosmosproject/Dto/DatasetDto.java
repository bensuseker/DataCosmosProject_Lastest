package com.datacosmos.datacosmosproject.Dto;

import com.datacosmos.datacosmosproject.entities.Datasets;
import jakarta.annotation.Nullable;

public class DatasetDto {

    private Integer id;

    private @Nullable String Dataset_name;

    private @Nullable String url;
    private Double ratingAverage;

    private @Nullable String keyword;

    public DatasetDto(@Nullable Integer id, @Nullable String dataset_name, String url, Double ratingAverage, String keyword) {
        this.id = id;
        this.Dataset_name = dataset_name;
        this.url = url;
        this.ratingAverage = ratingAverage;
        this.keyword = keyword;
    }

    public DatasetDto(Datasets datasets){
        this.setId(Math.toIntExact(datasets.getId()));
    }

    public DatasetDto() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataset_name() {
        return Dataset_name;
    }

    public void setDataset_name(String dataset_name) { Dataset_name = dataset_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getRatingAverage() {
        return ratingAverage;
    }

    public void setRatingAverage(Double ratingAverage) {
        this.ratingAverage = ratingAverage;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
