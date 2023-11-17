package com.example.lab5gui.entities.ferret;

import com.example.lab5gui.entities.master.MasterEntity;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ferrets")
public class FerretEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "ferretID")
    protected Integer index;
    @Column(name = "name")
    protected String name;
    @Column(name="type")
    private String type;
    @Column(name = "breed")
    private String breed;
    @Column(name = "age")
    private int age;
    @Column(name = "city")
    private String city;
    @Column(name="levelOfTraining")
    private int levelOfTraining;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "masterID")
    private MasterEntity masterEntity;

}
