package io.vkumar.entities;


import java.util.ArrayList;
import java.util.List;

public class SelectResponse {

    private int page;
    private int total;
    private List<?> items = new ArrayList();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getItems() {
        return items;
    }

    public void setItems(List<?> items) {
        this.items = items;
    }
}
