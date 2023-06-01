package com.datacosmos.datacosmosproject.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serial;
import java.io.Serializable;

/**
 * The RatingS class represents a composite key for the Rating entity.
 * It establishes a many-to-one relationship between the Rating entity, Datasets entity, and User entity.
 * It contains the associated Datasets object and User object for a rating.
 */
@Embeddable
public class RatingS implements Serializable {

    // The serialVersionUID is used for version control during serialization and deserialization
    @Serial
    private static final long serialVersionUID= 1L;

    // The Datasets object associated with the rating
    @ManyToOne
    @JoinColumn(name = "datasets_id")
    private Datasets datasets;

    // The User object associated with the rating
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public RatingS(){ }

    public Datasets getDatasets() {
        return datasets;
    }

    /**
     * Sets the Datasets object associated with this RatingS.
     *
     * @param datasets The Datasets object to set.
     */
    public void setDatasets(Datasets datasets) {
        this.datasets = datasets;
    }


    /**
     * Gets the User object associated with this RatingS.
     *
     * @return The User object.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the User object associated with this RatingS.
     *
     * @param user The User object to set.
     */
    public void setUser(User user) {
        this.user = user;
    }
}
