package CPSC507DemoAppJava;

import java.util.List;

public class CostAndApplicablePromotions {
    private double cost;
    private List<PromotionSavings> applicablePromotions;
    
    public CostAndApplicablePromotions(double cost,
            List<PromotionSavings> applicablePromotions) {
        this.cost = cost;
        this.applicablePromotions = applicablePromotions;
    }
    public double getCost() {
        return cost;
    }
    public List<PromotionSavings> getApplicablePromotions() {
        return applicablePromotions;
    }
    
}
