package CPSC507DemoAppJava;

public class OrderItemAndQuantity {
    private int quantity;
    private OrderItem item;
    
    public OrderItemAndQuantity(OrderItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
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
