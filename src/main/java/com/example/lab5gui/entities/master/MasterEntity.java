package com.example.lab5gui.entities.master;

import com.example.lab5gui.entities.ferret.FerretEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "masters")
public class MasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMaster")
    private int index;
    @Column(name = "name")
    private String name;
    @Column(name="age")
    private int age;
    @Column(name="sex")
    private String sex;
    @OneToMany(mappedBy = "masterEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<FerretEntity> ferretEntities;
    public MasterEntity(Master master){
        this.index= master.getIndex();
        this.name=master.getName().getValue();
        this.age=master.getAge().getValue();
        this.sex=master.getSex().getValue();
    }
    public MasterEntity(String name){
        this.name=name;
    }
    public void addFerret(FerretEntity ferret) {
        ferret.setMasterEntity(this);
        this.ferretEntities.add(ferret);
    }
}
