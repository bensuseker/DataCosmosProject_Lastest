package com.datacosmos.datacosmosproject.entities;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//This class define rating for dataset weblinks

@Getter
@Setter
@NoArgsConstructor
public class Rating {

    private float stars;
}
