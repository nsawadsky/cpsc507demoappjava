package CPSC507DemoAppJava;

import java.util.Date;
import java.util.Map;

public class Order {
    private Map<Integer, OrderLineItem> orderLineItems;
    private Date orderCreationTime;

    public Date getOrderCreationTime() {
        return orderCreationTime;
    }
    
    public void setOrderCreationTime(Date time) {
        orderCreationTime = time;
    }
    
    public void addOrderItems(OrderItem item, int quantity) {
        
    }
    
    public void removeOrderItems(OrderItem item, int quantity) {
        
    }
    
    public Map<Integer, OrderLineItem> getOrderLineItems() {
        return orderLineItems;
    }
    

}
