package CPSC507DemoAppJava;

public interface Serializer {
    String serialize(Object object) throws SerializerException;
    
    <T> T deserialize(Class<T> expectedClass, String serialized) throws SerializerException;
}
