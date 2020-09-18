package com.tobiaswadseth.grocerylist;

import java.util.ArrayList;
import java.util.List;

public class GroceryList {

    private List<Item> itemList;

    public GroceryList() {
        this.itemList = new ArrayList<>();
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public boolean addItem(Item item) {
        return itemList.add(item);
    }

    public boolean removeItem(Item item) {
        return itemList.remove(item);
    }

    public boolean removeItem(int index) {
        return itemList.remove(index) != null;
    }
}
