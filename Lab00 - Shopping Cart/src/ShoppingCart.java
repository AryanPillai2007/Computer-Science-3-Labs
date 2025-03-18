//By Aryan Pillai - aryan.pillai.152@k12.friscoisd.org

import java.util.List;
import java.util.ArrayList;

public class ShoppingCart

{
    public ShoppingCart() {
        orders = new ArrayList<>();
    }
    private List<ItemOrder> orders;
    public void add(ItemOrder newOrder) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).equals(newOrder)) {
                orders.set(i,newOrder);
                return;
            }
        }
        orders.add(newOrder);
    }
    public double getTotal() {
        double total= 0.0;
        for (ItemOrder order:orders) {
            total+= order.getPrice();
        }
        return total;
    }
}