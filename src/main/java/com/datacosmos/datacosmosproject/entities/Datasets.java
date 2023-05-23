package com.datacosmos.datacosmosproject.entities;

import jakarta.persistence.*;

import java.util.List;



/*This class define This class represents the website links that your website lists for the users.
It will have properties such as title, description, URL, and so on.
You might also want to include a method for scoring the links. */
@Entity
@Table(name = "Datasets")
public class Datasets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "Dataset_name", nullable = false)
    private String Dataset_name;

    @Column(name = "keyword", nullable = false)
    private String keyword;


    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "ratingAvarage", nullable = false)
    private Double ratingAverage;

    @OneToMany
    private List<Rating> ratings;

    public Datasets(Integer id, String dataset_name, String keyword, String url, Double ratingAverage, List<Rating> ratings) {
        this.id = id;
        this.Dataset_name = dataset_name;
        this.keyword = keyword;
        this.url = url;
        this.ratingAverage = ratingAverage;
        this.ratings = ratings;
    }

    public Datasets() {

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

    public void setDataset_name(String dataset_name) {
        Dataset_name = dataset_name;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}