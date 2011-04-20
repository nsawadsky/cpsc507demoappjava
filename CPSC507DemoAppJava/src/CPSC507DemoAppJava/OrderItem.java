package CPSC507DemoAppJava;

public class OrderItem {
    private String id;
    private ItemCategory category;
    private String description;
    private double price;
    
    public OrderItem(String id, ItemCategory category, String description, double price) {
        if (price < 0.0) {
            throw new IllegalArgumentException("price < 0.0");
        }
        this.id = id;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((category == null) ? 0 : category.hashCode());
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        long temp;
        temp = Double.doubleToLongBits(price);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderItem other = (OrderItem) obj;
        if (category != other.category)
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (Double.doubleToLongBits(price) != Double
                .doubleToLongBits(other.price))
            return false;
        return true;
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
