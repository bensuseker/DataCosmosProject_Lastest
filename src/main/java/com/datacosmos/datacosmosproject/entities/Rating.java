package com.datacosmos.datacosmosproject.entities;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//This class define rating for dataset weblinks

@Getter
@Setter
@Entity
@Table(name = "Rating")
public class Rating {

    /* The RatingS class is an embedded composite primary key class containing the User and Datasets attributes. */
    @EmbeddedId
    private RatingS id = new RatingS();

    @ManyToOne
    private Datasets dataset;

    private Double value;

    public Rating(){}

    /**
     * Sets the Datasets object associated with this Rating.
     *
     * @param datasets The Datasets object to set.
     */
    public void setDatasets(Datasets datasets){
        id.setDatasets(datasets);
    }

    /**
     * Sets the User object associated with this Rating.
     *
     * @param user The User object to set.
     */
    public void setUser(User user){
        id.setUser(user);
    }

    public RatingS getId() {
        return id;
    }

    public void setId(RatingS id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
