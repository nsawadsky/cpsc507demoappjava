package CPSC507DemoAppJava;

public class OrderLineItem {
    private int quantity;
    private OrderItem item;
    
    public OrderLineItem(OrderItem item) {
        this.item = item;
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
