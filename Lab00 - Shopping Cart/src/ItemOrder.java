//By Aryan Pillai - aryan.pillai.152@k12.friscoisd.org

public class ItemOrder {
    private Item item;
    private int quantity;

    public ItemOrder(Item item, int qty) {
        this.item = item;
        this.quantity = qty;
    }
    public double getPrice() {
        return item.priceFor(quantity);
    }

//    Overrides
//    Public Boolean Equals (Object obj)
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    ItemOrder other = (ItemOrder) obj;
    return item.equals(other.item);
    }
//   Public String toString
@Override
public String toString() {
    return quantity + " x " + item.toString();
}
}