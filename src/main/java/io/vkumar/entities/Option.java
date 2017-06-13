package io.vkumar.entities;

import javax.persistence.*;


//TODO: change okey to name and ovalue to value


@Entity
@Table(name = "field_options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    public String okey;
    public String ovalue;
    public int position;
    public Long fieldId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOkey() {
        return okey;
    }

    public void setOkey(String okey) {
        this.okey = okey;
    }

    public String getOvalue() {
        return ovalue;
    }

    public void setOvalue(String ovalue) {
        this.ovalue = ovalue;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }
}
