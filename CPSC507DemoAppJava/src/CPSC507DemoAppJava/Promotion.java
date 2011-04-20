package CPSC507DemoAppJava;

import java.util.Date;

public class Promotion {
    private String id;
    private ItemCategory category;
    private String[] descriptionKeywords;
    private Date startTime;
    private Date endTime;
    private double discount;
    
    public Promotion(String id, ItemCategory category, String[] descriptionKeywords,
            Date startTime, Date endTime, double discount) {
        if (discount < 0.0) {
            throw new IllegalArgumentException("discount < 0.0");
        }
        if (discount > 1.0) {
            throw new IllegalArgumentException("discount > 1.0");
        }
        this.id = id;
        this.category = category;
        this.descriptionKeywords = descriptionKeywords;
        this.startTime = startTime;
        this.endTime = endTime;
        this.discount = discount;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((category == null) ? 0 : category.hashCode());
        result = prime
                * result
                + ((descriptionKeywords == null) ? 0 : descriptionKeywords
                        .hashCode());
        long temp;
        temp = Double.doubleToLongBits(discount);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((startTime == null) ? 0 : startTime.hashCode());
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
        Promotion other = (Promotion) obj;
        if (category != other.category)
            return false;
        if (descriptionKeywords == null) {
            if (other.descriptionKeywords != null)
                return false;
        } else if (!descriptionKeywords.equals(other.descriptionKeywords))
            return false;
        if (Double.doubleToLongBits(discount) != Double
                .doubleToLongBits(other.discount))
            return false;
        if (endTime == null) {
            if (other.endTime != null)
                return false;
        } else if (!endTime.equals(other.endTime))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (startTime == null) {
            if (other.startTime != null)
                return false;
        } else if (!startTime.equals(other.startTime))
            return false;
        return true;
    }

    public String getId() {
        return id;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public String[] getDescriptionKeywords() {
        return descriptionKeywords;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public double getDiscount() {
        return discount;
    }
    
}
