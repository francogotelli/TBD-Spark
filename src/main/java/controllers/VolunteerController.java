package controllers;

import models.Volunteer;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class VolunteerController extends Controller<Long, Volunteer>{

    public VolunteerController() {}

    public VolunteerController(String tableName, Sql2o sql2o) {
        super(tableName, sql2o);
    }
}
