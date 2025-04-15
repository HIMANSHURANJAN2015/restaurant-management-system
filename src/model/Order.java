package model;

import java.util.Map;

public class Order extends BaseModel{

    private Customer customer;
    private Map<MenuItem, Integer> orderedItems;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Map<MenuItem, Integer> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(Map<MenuItem, Integer> orderedItems) {
        this.orderedItems = orderedItems;
    }
}
