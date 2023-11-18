package com.example.lab5gui.entities.ferret;

import com.example.lab5gui.entities.FerretDB;
import com.example.lab5gui.entities.master.MasterEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@Data
public class Ferret  extends FerretDB {
    private StringProperty type;
    private StringProperty breed;
    private IntegerProperty age;
    private StringProperty city;
    private IntegerProperty levelOfTraining;
    private MasterEntity master;

    public Ferret(String name, String breed, int age, String city, int levelOfTraining, String type, int id) {
        super(id, name);
        this.breed = new SimpleStringProperty(breed);
        this.age = new SimpleIntegerProperty(age);
        this.city = new SimpleStringProperty(city);
        this.levelOfTraining = new SimpleIntegerProperty(levelOfTraining);
        this.type = new SimpleStringProperty(type);
    }

    public Ferret() {
        super(null, new SimpleStringProperty());
        breed = new SimpleStringProperty();
        age = new SimpleIntegerProperty();
        city = new SimpleStringProperty();
        levelOfTraining = new SimpleIntegerProperty();
        type=new SimpleStringProperty();
        master = new MasterEntity();
    }
    public Ferret(FerretEntity ferret){
        super(ferret.index, ferret.name);
        this.breed = new SimpleStringProperty(ferret.getBreed());
        this.age = new SimpleIntegerProperty(ferret.getAge());
        this.city = new SimpleStringProperty(ferret.getCity());
        this.levelOfTraining = new SimpleIntegerProperty(ferret.getLevelOfTraining());
        this.type = new SimpleStringProperty(ferret.getType());
        this.master = ferret.getMasterEntity();
    }

    public HashMap<String, String> getMap(){
        StringFerret stringFerret = new StringFerret();
        ObjectMapper mapObject = new ObjectMapper();
        Map < String, String > mapObj = mapObject.convertValue(stringFerret, Map.class);
        return new HashMap<>(mapObj);
    }
    @Data
    public class StringFerret{
        private String type0=(type.isNull().get())? "-":type.getValue();
        private String name0=(name.isNull().get())? "-":name.getValue();
        private String breed0=(breed.isNull().get())? "-":breed.getValue();
        private String age0=(age==null)? "-":age.getValue().toString();
        private String city0=(city.isNull().get())? "-":city.getValue();
        private String levelOfTraining0=(levelOfTraining==null)? "-":levelOfTraining.getValue().toString();
    }
}
