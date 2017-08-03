package io.vkumar.entities;


public class MetaData {

    private int id;
    private int sheet_id;
    private String col_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSheet_id() {
        return sheet_id;
    }

    public void setSheet_id(int sheet_id) {
        this.sheet_id = sheet_id;
    }

    public String getCol_name() {
        return col_name;
    }

    public void setCol_name(String col_name) {
        this.col_name = col_name;
    }
}
