package com.datacosmos.datacosmosproject.entities;
import jakarta.persistence.*;
import lombok.Data;
//This class define rating for dataset weblinks

@Entity
@Table(name = "Rating")
@Data
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating_value", nullable = false, unique = true)
    //from 1 to 5
    private int value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dataset_id", nullable = false)
    private Datasets datasets;

    public Rating() {}
    public Rating(Long id, int value, Datasets datasets) {
        this.id = id;
        this.value = value;
        this.datasets = datasets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Datasets getDatasets() {
        return datasets;
    }

    public void setDatasets(Datasets datasets) {
        this.datasets = datasets;
    }
}
