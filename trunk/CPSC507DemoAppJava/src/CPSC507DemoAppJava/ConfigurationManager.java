package CPSC507DemoAppJava;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationManager {
    private Serializer serializer;
    private StorageManager storageManager;
    
    private final static String PROMO_CATEGORY = "Promotion";
    
    public ConfigurationManager(Serializer serializer, StorageManager storageManager) {
        this.serializer = serializer;
        this.storageManager = storageManager;
    }
    
    public void storePromotion(Promotion promo) throws SerializerException, StorageException {
        String serialized = serializer.serialize(promo);
        storageManager.storeObject(PROMO_CATEGORY, promo.getId(), serialized);
    }
    
    public void deletePromotion(String id) throws StorageException {
        storageManager.deleteObject(PROMO_CATEGORY, id);
    }
    
    public List<Promotion> loadPromotions() throws SerializerException, StorageException {
        List<String> promoData = storageManager.loadObjects(PROMO_CATEGORY);
        List<Promotion> result = new ArrayList<Promotion>();
        for (String data: promoData) {
            result.add(serializer.deserialize(Promotion.class, data));
        }
        return result;
    }
}
