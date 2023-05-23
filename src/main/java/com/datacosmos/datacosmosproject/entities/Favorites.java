package com.datacosmos.datacosmosproject.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "Favorites")
@Data
public class Favorites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "dataset_id")
    private Datasets datasets;

    public Favorites(){
    }

    public Favorites(User user, Datasets datasets) {
        this.user = user;
        this.datasets = datasets;
        this.createdDate = new Date();
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Datasets getDatasets() {
        return datasets;
    }

    public void setDatasets(Datasets datasets) {
        this.datasets = datasets;
    }
}
