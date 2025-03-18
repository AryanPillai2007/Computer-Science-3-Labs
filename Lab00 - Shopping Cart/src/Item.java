//By Aryan Pillai - aryan.pillai.152@k12.friscoisd.org
public class Item {
    private String name;
    private double price;
    private int bulkQty;
    private double bulkPrice;


    public Item(String name, double price) {
        this(name, price, 0, 0.0);
    }


    public Item(String name, double price, int bulkQty, double bulkPrice) {
        if (price < 0 || bulkPrice < 0 || bulkQty < 0) {
            throw new IllegalArgumentException("Error. Any number is negative. Fix and Reload program");
        }
        this.name = name;
        this.price = price;
        this.bulkQty = bulkQty;
        this.bulkPrice = bulkPrice;
    }

    public double priceFor(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cant be negative.");
        }
        if (bulkQty > 0 && quantity >= bulkQty) {
            return quantity * bulkPrice;
        } else {
            return quantity * price;

        }
    }

    //   /???/ <overrides>
//   Overriding Boolean Equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Item other = (Item) obj;
        return this.name.equals(other.name);
    }
    //     Overriding toString method
    @Override
    public String toString() {
        String result = name+",$"+price;
        if (bulkQty > 0) {
            result += " ("+bulkQty+" for $"+bulkPrice+")";
        }
        return result;
    }
}
