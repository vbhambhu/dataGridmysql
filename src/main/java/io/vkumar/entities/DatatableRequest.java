package io.vkumar.entities;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatatableRequest {

    private int draw;
    private int start;
    private int length;

    private HashMap<String, String> search = new HashMap<>();
    private List<HashMap<String, String>> order;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public HashMap<String, String> getSearch() {
        return search;
    }

    public void setSearch(HashMap<String, String> search) {
        this.search = search;
    }

    public List<HashMap<String, String>> getOrder() {
        return order;
    }

    public void setOrder(List<HashMap<String, String>> order) {
        this.order = order;
    }
}
