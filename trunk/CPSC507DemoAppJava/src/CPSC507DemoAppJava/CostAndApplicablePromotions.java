package CPSC507DemoAppJava;

public class CostAndApplicablePromotions {
    private double cost;
    private PromotionSavings[] applicablePromotions;
    
    public CostAndApplicablePromotions(double cost,
            PromotionSavings[] applicablePromotions) {
        if (cost < 0.0) {
            throw new IllegalArgumentException("cost < 0.0");
        }
        this.cost = cost;
        this.applicablePromotions = applicablePromotions;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime
                * result
                + ((applicablePromotions == null) ? 0 : applicablePromotions
                        .hashCode());
        long temp;
        temp = Double.doubleToLongBits(cost);
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
        CostAndApplicablePromotions other = (CostAndApplicablePromotions) obj;
        if (applicablePromotions == null) {
            if (other.applicablePromotions != null)
                return false;
        } else if (!applicablePromotions.equals(other.applicablePromotions))
            return false;
        if (Double.doubleToLongBits(cost) != Double
                .doubleToLongBits(other.cost))
            return false;
        return true;
    }
    
    public double getCost() {
        return cost;
    }
    
    public PromotionSavings[] getApplicablePromotions() {
        return applicablePromotions;
    }
    
}
