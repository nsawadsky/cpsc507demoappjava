package CPSC507DemoAppJava;

public class OrderItem {
    private int id;
    private ItemCategory category;
    private String description;
    
    public enum ItemCategory {
        Book,
        Electronics,
        Apparel
    };
    
    public OrderItem(int id, ItemCategory category, String description) {
        this.id = id;
        this.category = category;
        this.description = description;
    }

    public int getId() {
        return id;
    }
    
    public ItemCategory getCategory() {
        return category;
    }

    public String getItemDescription() {
        return description;
    }

}
