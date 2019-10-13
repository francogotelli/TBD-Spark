package controllers;

import models.Characteristic;
import org.sql2o.Sql2o;

public class CharacteristicController extends Controller<Characteristic, Long> {
    public CharacteristicController() {
    }

    public CharacteristicController(String tableName, Sql2o sql2o) {
        super(tableName, sql2o);
    }


}
