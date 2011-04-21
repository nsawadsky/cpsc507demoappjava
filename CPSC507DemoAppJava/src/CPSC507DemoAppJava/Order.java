package CPSC507DemoAppJava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Order {
    private List<OrderItemAndQuantity> lineItems = new ArrayList<OrderItemAndQuantity>();
    private Date creationTime;

    public Order(Date creationTime) {
        this.creationTime = creationTime;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((creationTime == null) ? 0 : creationTime.hashCode());
        result = prime * result
                + ((lineItems == null) ? 0 : lineItems.hashCode());
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
        Order other = (Order) obj;
        if (creationTime == null) {
            if (other.creationTime != null)
                return false;
        } else if (!creationTime.equals(other.creationTime))
            return false;
        if (lineItems == null) {
            if (other.lineItems != null)
                return false;
        } else if (!lineItems.equals(other.lineItems))
            return false;
        return true;
    }
    
    public Date getCreationTime() {
        return creationTime;
    }
    
    public List<OrderItemAndQuantity> getLineItems() {
        return lineItems;
    }
    
    public void addItems(OrderItem item, int quantity)
    {
        if (quantity < 0)
        {
            throw new IllegalArgumentException("quantity < 0");
        }
        lineItems.add(new OrderItemAndQuantity(item, quantity));
    }

    public CostAndApplicablePromotions calculateCost(Promotion[] promotions, 
            int allowablePromotions) {
        if (allowablePromotions < 0) {
            throw new IllegalArgumentException("allowablePromotions < 0");
        }
        double totalCost = calculateCostBeforePromotions();
        
        List<PromotionSavings> promoSavingsList = new ArrayList<PromotionSavings>();
        
        if (allowablePromotions > 0) {
            promoSavingsList = calculateAllPromotionSavings(promotions);
            
            if (promoSavingsList.size() > allowablePromotions) {
                // Sort the savings list in descending order according to savings value.
                Collections.sort(promoSavingsList, new Comparator<PromotionSavings>() {
        
                    public int compare(PromotionSavings arg0, PromotionSavings arg1) {
                        // Deliberately swapping the arguments: we want descending order.
                        return Double.compare(arg1.getSavings(), arg0.getSavings());
                    }
                    
                });
                promoSavingsList = promoSavingsList.subList(0, allowablePromotions-1);
            }
            
            for (PromotionSavings promoSavings: promoSavingsList) {
                totalCost -= promoSavings.getSavings();
            }
            
        }
        return new CostAndApplicablePromotions(totalCost, promoSavingsList.toArray(new PromotionSavings[0]));
    }
    
    private List<PromotionSavings> calculateAllPromotionSavings(Promotion[] promotions) {
        List<PromotionSavings> result = new ArrayList<PromotionSavings>();
        for (Promotion promo: promotions) {
            PromotionSavings savings = calculatePromotionSavings(promo);
            if (savings != null) {
                result.add(savings);
            }
        }
        return result;
    }
    
    private PromotionSavings calculatePromotionSavings(Promotion promotion) {
        if (creationTime.compareTo(promotion.getStartTime()) >= 0 && 
                creationTime.compareTo(promotion.getEndTime()) <= 0) {
            for (OrderItemAndQuantity lineItem: lineItems) {
                OrderItem orderItem = lineItem.getItem();
                if (orderItem.getCategory().equals(promotion.getCategory())) {
                    boolean descriptionMatch = false;
                    if (promotion.getDescriptionKeywords() == null || 
                            promotion.getDescriptionKeywords().length == 0) {
                        descriptionMatch = true;
                    } else {
                        for (String keyword: promotion.getDescriptionKeywords()) {
                            if (orderItem.getDescription().contains(keyword)) {
                                descriptionMatch = true;
                            }
                        }
                    }
                    if (descriptionMatch) {
                        double savings = promotion.getDiscount() * 
                            (lineItem.getQuantity() * orderItem.getPrice());
                        return new PromotionSavings(promotion, savings);
                    }
                }
            }
        }
        return null;
    }
    
    private double calculateCostBeforePromotions() {
        double result = 0.0;
        for (OrderItemAndQuantity lineItem: lineItems){
            result += lineItem.getQuantity() * lineItem.getItem().getPrice();
        }
        return result;
    }

}
