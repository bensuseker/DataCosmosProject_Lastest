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
        this.name = name;
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