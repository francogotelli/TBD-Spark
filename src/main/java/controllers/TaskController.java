package controllers;

import models.Task;
import org.sql2o.Sql2o;

public class TaskController extends Controller<Long, Task> {
    public TaskController() { }

    public TaskController(String tableName, Sql2o sql2o) {
        super(tableName, sql2o);
    }
}
