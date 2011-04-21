package CPSC507DemoAppJava;

import java.util.Date;

public class TestInputGenerator {
    @Override
    public boolean equals(Object target) {
        return target != null && target.getClass() == this.getClass();
    }

    @Override
    public int hashCode() {
        return 31;
    }

    public ItemCategory createBookCategory() {
        return ItemCategory.Book;
    }
    
    public ItemCategory createApparelCategory() {
        return ItemCategory.Apparel;
    }
    
    public ItemCategory createElectronicsCategory() {
        return ItemCategory.Electronics;
    }

    public Date createDate(long date) {
        return new Date(date);
    }
    
    public Promotion createPromotion(String id, ItemCategory category, String[] descriptionKeywords,
            Date startTime, Date endTime, double discount) {
        return new Promotion(id, category, descriptionKeywords, startTime, endTime, discount);
    }
    
    public OrderItem createOrderItem(String id, ItemCategory category, String description, double price) {
        return new OrderItem(id, category, description, price);
    }
    
    public Order createOrder(Date creationTime) {
        Order order = new Order(creationTime);
        return order;
    }
    
    public void addItemsToOrder(Order order, OrderItem item, int quantity) {
        order.addItems(item, quantity);
    }
    
}
