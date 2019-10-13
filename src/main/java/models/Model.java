package models;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Model<T>{
    public Model(){ }

    public Map<String, Object> getFieldsAndValues(Class<? extends Object> objClass) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Field[] fields = objClass.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            field.setAccessible(true);
            Object value = field.get(this);
            map.put(name, value);
        }
        return map;
    }
}
