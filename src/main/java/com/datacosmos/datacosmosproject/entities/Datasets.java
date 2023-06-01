package com.datacosmos.datacosmosproject.entities;

import com.datacosmos.datacosmosproject.Dto.DatasetDto;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/* Datasets class is annotated with @Entity to indicate that
*  it is a JPA entity mapped to a database table named "Datasets".
*  It contains various attributes such as id,
* Dataset_name, keyword, url, ratingAverage, rating, and image representing the
*/

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

    @Column(name = "ratingAvarage")
    private Double ratingAverage;
    @Column(nullable = false)
    private int rating;

    private String image;

    @OneToMany(mappedBy = "dataset", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Rating> ratings = new HashSet<>();


    public Datasets() {

    }

    /**
     * Parameterized constructor for the Datasets entity class.
     * Initializes the Datasets object with the provided values.
     *
     * @param id             The ID of the dataset.
     * @param dataset_name   The name of the dataset.
     * @param keyword        The keyword associated with the dataset.
     * @param url            The URL of the dataset.
     * @param ratingAverage  The average rating of the dataset.
     * @param rating         The rating of the dataset.
     * @param image          The image associated with the dataset.
     */

    public Datasets(Long id, String dataset_name, String keyword, String url, Double ratingAverage,
                    int rating, String image) {
        this.id = id;
        this.Dataset_name = dataset_name;
        this.keyword = keyword;
        this.url = url;
        this.ratingAverage = ratingAverage;
        this.rating = rating;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }
}