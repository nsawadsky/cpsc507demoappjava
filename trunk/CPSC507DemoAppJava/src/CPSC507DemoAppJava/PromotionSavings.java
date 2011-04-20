package CPSC507DemoAppJava;

public class PromotionSavings {
    private Promotion promotion;
    private double savings;

    public Promotion getPromotion() {
        return promotion;
    }

    public double getSavings() {
        return savings;
    }

    public PromotionSavings(Promotion promotion, double savings) {
        if (savings < 0.0) {
            throw new IllegalArgumentException("savings < 0.0");
        }
        this.promotion = promotion;
        this.savings = savings;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((promotion == null) ? 0 : promotion.hashCode());
        long temp;
        temp = Double.doubleToLongBits(savings);
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
        PromotionSavings other = (PromotionSavings) obj;
        if (promotion == null) {
            if (other.promotion != null)
                return false;
        } else if (!promotion.equals(other.promotion))
            return false;
        if (Double.doubleToLongBits(savings) != Double
                .doubleToLongBits(other.savings))
            return false;
        return true;
    }

}