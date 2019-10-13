package controllers;

import models.IModel;
import models.Model;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Controller<T, K> {
    private String tableName;
    private Sql2o sql2o;

    public Controller () { }

    public Controller(String tableName, Sql2o sql2o){
        this.tableName = tableName;
        this.sql2o = sql2o;
    }

    public String generateAllColumnNames(ArrayList<String> fields){
        QueryHelper queryHelper = new QueryHelper();
        for (String field : fields){
            queryHelper.addName(field);
        }
        return queryHelper.getColumnNames();
    }

    public String generateAllColumnValues(ArrayList<String> fields){
        QueryHelper queryHelper = new QueryHelper();
        for (String field : fields){
            queryHelper.addValue(field);
        }
        return queryHelper.getColumnValues();
    }

    public T insert(Map<String, Object> fieldsAndValues){
        Connection conn = sql2o.open();
        ArrayList<String> columnNames =  new ArrayList<>(fieldsAndValues.keySet());
        columnNames.removeIf(c -> c.equals("id"));
        Query query = conn.createQuery("insert into " + tableName + " " + generateAllColumnNames(columnNames) + " values " + generateAllColumnValues(columnNames), true);
        for (Map.Entry<String, Object> field: fieldsAndValues.entrySet()){
            if (!field.getKey().equals("id"))
                query.addParameter(field.getKey(), field.getValue());
        }
        System.out.println(query.toString());
        T response = (T)query.executeUpdate().getKey();
        conn.close();
        return response;
    }

    public IModel insertAllFields(IModel model, Class<? extends Object> objClass) throws IllegalAccessException {
        Map<String, Object> fieldsAndValues = model.getFieldsAndValues(objClass);
        model.setId(insert(fieldsAndValues));
        return model;
    }

    public Model insertAllFieldsExcept(Model model, Class<? extends Object> objClass, ArrayList<String> fields) throws IllegalAccessException {
        Map<String, Object> fieldsAndValues = model.getFieldsAndValues(objClass);
        fieldsAndValues.keySet().removeIf(fields::contains);
        insert(fieldsAndValues);
        return model;
    }

    public Model insertFields(Model model, Class<? extends Object> objClass, ArrayList<String> fields) throws IllegalAccessException{
        Map<String, Object> fieldsAndValues = model.getFieldsAndValues(objClass);
        fieldsAndValues.keySet().removeIf(key -> !fields.contains(key));
        insert(fieldsAndValues);
        return model;
    }

    public List<K> getAll(Class<? extends Object> objClass){
        Connection conn = sql2o.open();
        String query = "select * from " + tableName;
        System.out.println(query);
        return (ArrayList<K>)conn.createQuery(query).executeAndFetch(objClass);
    }
}
