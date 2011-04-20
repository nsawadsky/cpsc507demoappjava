package CPSC507DemoAppJava;

public class OrderItemAndQuantity {
    private int quantity;
    private OrderItem item;
    
    public OrderItemAndQuantity(OrderItem item, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("quantity < 0");
        }
        this.item = item;
        this.quantity = quantity;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + quantity;
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
        OrderItemAndQuantity other = (OrderItemAndQuantity) obj;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (quantity != other.quantity)
            return false;
        return true;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public OrderItem getItem() {
        return item;
    }
   
}
