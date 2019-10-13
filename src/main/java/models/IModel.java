package models;

import java.util.Map;

public interface IModel<T> {
    T getId();
    void setId(T id);
    Map<String, Object> getFieldsAndValues(Class<? extends Object> objClass) throws IllegalAccessException;
}
