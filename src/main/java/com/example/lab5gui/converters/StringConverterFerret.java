package com.example.lab5gui.converters;

import com.example.lab5gui.entities.FerretDB;
import javafx.util.StringConverter;

public class StringConverterFerret extends StringConverter<FerretDB> {

    @Override
    public String toString(FerretDB ferret) {
        return ferret == null ? null : ferret.getName().getValue();
    }

    @Override
    public FerretDB fromString(String string) {
        return null;
    }

}