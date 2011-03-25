package CPSC507DemoAppJava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
    private Map<String, OrderItemAndQuantity> lineItems = new HashMap<String, OrderItemAndQuantity>();
    private Date creationTime;

    public Order(Date creationTime) {
        this.creationTime = creationTime;
    }
    public Date getCreationTime() {
        return creationTime;
    }
    
    public void addItems(OrderItem item, int quantity) {
        OrderItemAndQuantity lineItem = lineItems.get(item.getId());
        if (lineItem == null) {
            lineItem = new OrderItemAndQuantity(item, quantity);
            lineItems.put(item.getId(), lineItem);
        } else {
            lineItem.setQuantity(lineItem.getQuantity() + quantity);
        }
    }
    
    public void removeItems(OrderItem item, int quantity) {
        OrderItemAndQuantity lineItem = lineItems.get(item.getId());
        if (lineItem != null) {
            if (lineItem.getQuantity() > quantity) {
                lineItem.setQuantity(lineItem.getQuantity() - quantity);
            } else {
                lineItems.remove(item.getId());
            }
        }
    }

    public CostAndApplicablePromotions calculateCost(List<Promotion> promotionList, 
            int allowablePromotions) {
        double totalCost = calculateCostBeforePromotions();
        
        List<PromotionSavings> promoSavingsList = new ArrayList<PromotionSavings>();
        
        if (allowablePromotions > 0) {
            promoSavingsList = calculateAllPromotionSavings(promotionList);
            
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
        return new CostAndApplicablePromotions(totalCost, promoSavingsList);
    }
    
    private List<PromotionSavings> calculateAllPromotionSavings(List<Promotion> promotionList) {
        List<PromotionSavings> result = new ArrayList<PromotionSavings>();
        for (Promotion promo: promotionList) {
            result.add(calculatePromotionSavings(promo));
        }
        return result;
    }
    
    private PromotionSavings calculatePromotionSavings(Promotion promotion) {
        if (creationTime.after(promotion.getStartTime()) && 
                creationTime.before(promotion.getEndTime())) {
            for (OrderItemAndQuantity lineItem: lineItems.values()) {
                OrderItem orderItem = lineItem.getItem();
                if (orderItem.getCategory().equals(promotion.getCategory())) {
                    boolean descriptionMatch = false;
                    if (promotion.getDescriptionKeywords() == null || 
                            promotion.getDescriptionKeywords().isEmpty()) {
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
        for (OrderItemAndQuantity lineItem: lineItems.values()){
            result += lineItem.getQuantity() * lineItem.getItem().getPrice();
        }
        return result;
    }

}
