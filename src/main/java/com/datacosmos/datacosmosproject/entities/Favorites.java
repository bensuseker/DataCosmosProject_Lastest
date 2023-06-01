package com.datacosmos.datacosmosproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

/*

* The Favorites class represents the favorites functionality in your application.
* The @Entity annotation indicates that this class is a JPA entity mapped to a database table named "Favorites".
* The @Table annotation specifies the name of the table as "Favorites".
* The @Data annotation is provided by Lombok and automatically generates getter and setter methods, as well as other common methods such as equals, hashCode, and toString.
* The class has attributes such as id, userId, datasetId, and createdDate representing the favorite dataset.
* The @Id annotation indicates that the id attribute is the primary key of the entity.
* The @GeneratedValue annotation specifies the strategy for generating the primary key values, in this case, using an identity strategy.
* The @Column annotation is used to specify the column details for the respective attributes, such as column names and constraints.
* The @NotBlank annotation is a validation constraint that ensures the userId and datasetId attributes are not blank (empty or null).
* The @ManyToOne annotation establishes a many-to-one relationship between Favorites and Datasets entities.
* The @JoinColumn annotation specifies the join column details, including the column name, referenced column name, and insertable/updatable properties.
* The class provides a parameterized constructor to initialize the userId, datasetId, and createdDate attributes.
* It also provides default constructors and getter/setter methods for all the attributes.

*/

@Entity
@Table(
        name = "Favorites",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "dataset_id"})
        }
)
@Data
public class Favorites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private @NotNull Long userId;

    @Column(name = "dataset_id")
    private @NotNull Long datasetId;


    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dataset_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Datasets datasets;

    /**
     * Constructs a new Favorites object with the provided userId and datasetId,
     * and sets the createdDate to the current date and time.
     *
     * @param userId     The ID of the user who favorited the dataset.
     * @param datasetId  The ID of the dataset that was favorited.
     */
    public Favorites(Long userId, Long datasetId) {
        this.datasetId = datasetId;
        this.userId = userId;
        this.createdDate = new Date();
    }

    public Favorites() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(Long datasetId) {
        this.datasetId = datasetId;
    }

    public Datasets getDatasets() {
        return datasets;
    }

    public void setDatasets(Datasets datasets) {
        this.datasets = datasets;
    }
}
