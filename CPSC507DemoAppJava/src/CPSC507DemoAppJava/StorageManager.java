package CPSC507DemoAppJava;

import java.util.List;

public interface StorageManager {
    void storeObject(String category, String id, String data) throws StorageException;
    
    void deleteObject(String category, String id) throws StorageException;
    
    List<String> loadObjects(String category) throws StorageException;
}
