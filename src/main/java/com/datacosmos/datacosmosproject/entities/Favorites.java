package com.datacosmos.datacosmosproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

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
