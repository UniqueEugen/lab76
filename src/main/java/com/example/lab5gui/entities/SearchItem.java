package com.example.lab5gui.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchItem {
    private FerretDB ferret;
    private String field;
    private int startPos;
    private int length;
}
