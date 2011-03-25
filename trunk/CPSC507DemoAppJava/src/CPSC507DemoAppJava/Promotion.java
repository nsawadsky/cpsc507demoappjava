package CPSC507DemoAppJava;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Promotion {
    private String id;
    private ItemCategory category;
    private List<String> descriptionKeywords;
    private Date startTime;
    private Date endTime;
    private double discount;
    
    public Promotion(String id, ItemCategory category, List<String> descriptionKeywords,
            Date startTime, Date endTime, double discount) {
        this.id = id;
        this.category = category;
        this.descriptionKeywords = new ArrayList<String>(descriptionKeywords);
        this.startTime = startTime;
        this.endTime = endTime;
        this.discount = discount;
    }
    
    public String getId() {
        return id;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public List<String> getDescriptionKeywords() {
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
