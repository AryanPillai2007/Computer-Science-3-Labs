//By Aryan Pillai - aryan.pillai.152@k12.friscoisd.org

import java.util.List;
import java.util.ArrayList;

public class Catalog {
    private List<Item> items;
    private String name;
    public Catalog(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }
    public void add(Item item) {
        items.add(item);
    }
    public int size() {
        return items.size();
    }
    public Item get(int index) {
        return items.get(index);
    }
    public String getName() {
        return name;
    }
}
