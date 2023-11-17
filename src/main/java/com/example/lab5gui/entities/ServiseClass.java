package com.example.lab5gui.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name="servise")
public class ServiseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serviseID")
    private int id;
    @Column
    private String serviseStr;
}
