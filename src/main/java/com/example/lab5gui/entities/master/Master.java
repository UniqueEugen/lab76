package com.example.lab5gui.entities.master;

import com.example.lab5gui.entities.FerretDB;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Master extends FerretDB {
    private IntegerProperty age;
    private StringProperty sex;
    public Master(String name, int age, String sex, int id){
        super(id, name);
        this.age=new SimpleIntegerProperty(age);
        this.sex=new SimpleStringProperty(sex);
    }
    public Master() {
        super(null, new SimpleStringProperty());
        sex = new SimpleStringProperty();
        age = new SimpleIntegerProperty();
    }
    public Master(MasterEntity master){
        super(master.getIndex(), master.getName());
        this.age=new SimpleIntegerProperty(master.getAge());
        this.sex=new SimpleStringProperty(master.getSex());
    }
}
