package com.datacosmos.datacosmosproject.entities;

import jakarta.persistence.*;


/*This class define This class represents the website links that your website lists for the users.
It will have properties such as title, description, URL, and so on.
You might also want to include a method for scoring the links. */
@Entity
@Table(
        name = "Datasets",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "keyword", "url"})
        })
public class Datasets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "keyword", nullable = false)
    private String keyword;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "ratingAverage")
    private Double ratingAverage;

    @Column(name = "rating")
    private int rating;

    public Datasets(Long id, String name, String keyword, String url, Double ratingAverage, int rating) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String datasetName) {
        this.name = datasetName;
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