package com.tobiaswadseth.grocerylist;

public class Item {

    private Priority priority;
    private final String item;
    private String amount;
    private boolean bought = false;

    public Item(String item, String amount, Priority priority) {
        this.item = item;
        this.amount = amount;
        this.priority = priority;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getItem() {
        return item;
    }

    public String getAmount() {
        return amount;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }
}
