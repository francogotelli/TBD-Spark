package controllers;

import models.Emergency;
import org.sql2o.Sql2o;

public class EmergencyController extends Controller<Long, Emergency>{

    public EmergencyController() { }

    public EmergencyController(String tableName, Sql2o sql2o) {
        super(tableName, sql2o);
    }
}
