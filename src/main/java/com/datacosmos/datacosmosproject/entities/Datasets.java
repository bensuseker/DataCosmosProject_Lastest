package com.datacosmos.datacosmosproject.entities;

import com.datacosmos.datacosmosproject.Dto.DatasetDto;
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
    private Long id;

    @Column(name = "Dataset_name", nullable = false)
    private String Dataset_name;

    @Column(name = "keyword", nullable = false)
    private String keyword;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "ratingAverage")
    private Double ratingAverage;

    @Column(name = "rating")
    private int rating;

    public Datasets(Long id, String dataset_name, String keyword, String url, Double ratingAverage, int rating) {
        this.id = id;
        this.Dataset_name = dataset_name;
        this.keyword = keyword;
        this.url = url;
        this.ratingAverage = ratingAverage;
        this.rating = rating;
    }

    public Datasets() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void rating(int stars) {
        if (stars < 1 || stars > 5) {
            throw new IllegalArgumentException("Rating should be between 1 and 5 stars.");
        }
        this.rating = stars;
    }
}