package controllers;

import java.util.ArrayList;

public class QueryHelper {
    private ArrayList<String> columnNames;
    private ArrayList<String> columnValues;

    public QueryHelper(){
        columnNames = new ArrayList<>();
        columnValues = new ArrayList<>();
    }

    public String getColumnNames(){
        StringBuilder names = new StringBuilder("(");
        int i = 0;
        while (i < columnNames.size()){
            if (i != columnNames.size()-1){
                names.append(columnNames.get(i)).append(", ");
            } else {
                names.append(columnNames.get(i)).append(")");
            }
            i++;
        }
        return names.toString();
    }

    public String getColumnValues(){
        StringBuilder values = new StringBuilder("(");
        int i = 0;
        while (i < columnValues.size()){
            if (i != columnValues.size()-1){
                values.append(":").append(columnValues.get(i)).append(", ");
            } else {
                values.append(":").append(columnValues.get(i)).append(")");
            }
            i++;
        }
        return values.toString();
    }

    public QueryHelper addName(String name){
        columnNames.add(name);
        return this;
    }

    public QueryHelper addValue(String value){
        columnValues.add(value);
        return this;
    }
}
