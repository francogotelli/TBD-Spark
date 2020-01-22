package controllers;

import models.Reunion;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class CtrUser {

    private Sql2o sql2o[];
    public CtrUser(Sql2o[] sql2o) {
        this.sql2o = sql2o;
    }

    public User creaUser(User user){
        int db = user.getId() % sql2o.length;
        try(Connection conn = sql2o[db].open()){
            conn.createQuery(
                    "insert into " +
                            "user(id, name, password, rut, phone, email) " +
                            "values (:id, :name, :password, :rut, :phone, :email)")
                    .addParameter("id", user.getId())
                    .addParameter("name", user.getName())
                    .addParameter("password", user.getPassword())
                    .addParameter("rut", user.getRut())
                    .addParameter("phone", user.getPhone())
                    .addParameter("email", user.getEmail())
                    .executeUpdate();
            return user;
        }
    }
}
