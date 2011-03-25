package CPSC507DemoAppJava;

public class OrderItem {
    private String id;
    private ItemCategory category;
    private String description;
    private double price;
    
    public OrderItem(String id, ItemCategory category, String description, double price) {
        this.id = id;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }
    
    public ItemCategory getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
    
    public double getPrice() {
        return price;
    }

}
