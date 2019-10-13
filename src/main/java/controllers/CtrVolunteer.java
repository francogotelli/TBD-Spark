package controllers;

import models.Volunteer;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class CtrVolunteer extends Controller{

    private String tableName;
    //private String[] fields;
    private Sql2o sql2o;

    public CtrVolunteer() {}

    public CtrVolunteer(String tableName, Sql2o sql2o){
        this.tableName = tableName;
        this.sql2o = sql2o;
    }

}
