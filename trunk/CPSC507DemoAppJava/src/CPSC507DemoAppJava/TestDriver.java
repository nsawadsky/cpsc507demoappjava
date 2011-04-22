package CPSC507DemoAppJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;

public class TestDriver {
    @Override
    public boolean equals(Object target) {
        return target != null && target.getClass() == this.getClass();
    }
    
    @Override
    public int hashCode() {
        return 31;
    }
     
    public void testCalculateCost(
            Order target,
            Promotion[] promotions,
            int allowablePromotions)
    {
        CostAndApplicablePromotions result = target.calculateCost(promotions, 
                allowablePromotions);

        CostAndApplicablePromotions expectedResult = calculateCostAndApplicablePromotions(
                target, promotions, allowablePromotions);

        Assert.assertEquals(expectedResult.getCost(), result.getCost(), 0.0);

        assertPromotionSavingsListsMatch(Arrays.asList(expectedResult.getApplicablePromotions()),
                Arrays.asList(result.getApplicablePromotions()));
    }

    private void assertPromotionSavingsListsMatch(List<PromotionSavings> expected,
            List<PromotionSavings> actual)
    {
        if (actual.size() != expected.size())
        {
            Assert.fail("expected.size() = " + expected.size() + ", actual.size() = " + actual.size());
        }
        for (PromotionSavings savings: expected)
        {
            if (savings == null)
            {
                Assert.fail("null reference in expected promotion savings list");
            }
            if (!actual.contains(savings))
            {
                Assert.fail("Actual is missing savings object for promotion '"
                        + savings.getPromotion().getId() + "'");
            }
        }
        for (PromotionSavings savings: actual)
        {
            if (savings == null)
            {
                Assert.fail("null reference in actual promotion savings list");
            }
            if (!expected.contains(savings))
            {
                Assert.fail("Actual contains unexpected savings object for promotion '"
                        + savings.getPromotion().getId() + "'");
            }
        }
    }

    private CostAndApplicablePromotions calculateCostAndApplicablePromotions(
            Order target, Promotion promotions[], int allowablePromotions)
    {
        double cost = 0.0;
        for (OrderItemAndQuantity itemAndQuantity: target.getLineItems())
        {
            cost += itemAndQuantity.getQuantity() * itemAndQuantity.getItem().getPrice();
        }

        List<PromotionSavings> applicablePromotions = new ArrayList<PromotionSavings>();
        for (Promotion promo: promotions)
        {
            double savings = 0.0;
            boolean promoMatched = false;
            for (OrderItemAndQuantity itemAndQuantity: target.getLineItems())
            {
                if (itemAndQuantity.getItem().getCategory() == promo.getCategory())
                {
                    if (target.getCreationTime().compareTo(promo.getStartTime()) >= 0 &&
                            target.getCreationTime().compareTo(promo.getEndTime()) <= 0) 
                    {
                        boolean descriptionKeywordMatch = false;
                        if (promo.getDescriptionKeywords() == null || promo.getDescriptionKeywords().length == 0)
                        {
                            descriptionKeywordMatch = true;
                        }
                        else
                        {
                            List<String> tokens = Arrays.asList(itemAndQuantity.getItem().getDescription().split(" "));
                            for (String keyword: promo.getDescriptionKeywords()) {
                                if (tokens.contains(keyword)) {
                                    descriptionKeywordMatch = true;
                                    break;
                                }
                            }
                        }
                        if (descriptionKeywordMatch)
                        {
                            promoMatched = true;
                            savings += promo.getDiscount() * itemAndQuantity.getItem().getPrice() * itemAndQuantity.getQuantity();
                        }
                    }
                }
            }
            if (promoMatched)
            {
                applicablePromotions.add(new PromotionSavings(promo, savings));
            }
        }
        if (applicablePromotions.size() > allowablePromotions)
        {
            Collections.sort(applicablePromotions, new Comparator<PromotionSavings>() {

                public int compare(PromotionSavings arg0, PromotionSavings arg1) {
                    // Deliberately swapping the arguments: we want descending order.
                    return Double.compare(arg1.getSavings(), arg0.getSavings());
                }

            });
            applicablePromotions = applicablePromotions.subList(0, allowablePromotions-1);
        }
        for (PromotionSavings savings: applicablePromotions)
        {
            cost -= savings.getSavings();
        }
        return new CostAndApplicablePromotions(cost, applicablePromotions.toArray(new PromotionSavings[0]));
    }
 
}
