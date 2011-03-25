package CPSC507DemoAppJava;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TestInputGenerator {
    private static Random random = new Random();
    
    public static ItemCategory createBookCategory() {
        return ItemCategory.Book;
    }
    
    public static ItemCategory createApparelCategory() {
        return ItemCategory.Apparel;
    }
    
    public static ItemCategory createElectronicsCategory() {
        return ItemCategory.Electronics;
    }
    
    public static Date createRandomDate() {
        return new Date(random.nextLong());
    }
    
    public List<String> createStringList(String[] input) {
        List<String> result = new ArrayList<String>();
        for (String str: input) {
            result.add(str);
        }
        return result;
    }
    
    public List<Promotion> createPromotionList(Promotion[] input) {
        List<Promotion> result = new ArrayList<Promotion>();
        for (Promotion promo: input) {
            result.add(promo);
        }
        return result;
    }
    
    public List<PromotionSavings> createPromotionSavingsList(PromotionSavings[] input) {
        List<PromotionSavings> result = new ArrayList<PromotionSavings>();
        for (PromotionSavings promo: input) {
            result.add(promo);
        }
        return result;
    }
}
