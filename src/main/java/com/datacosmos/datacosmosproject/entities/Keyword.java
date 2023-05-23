package com.datacosmos.datacosmosproject.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Keywords")
@Data
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}
