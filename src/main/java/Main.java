import static spark.Spark.*;

import com.google.gson.GsonBuilder;

import com.google.gson.Gson;
import controllers.*;
import models.*;
import org.sql2o.Sql2o;

public class Main {

    // Enables CORS on requests. This method is an initialization method and should be called once.
    private static void enableCORS(final String origin, final String methods, final String headers) {

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
            response.header("Access-Control-Expose-Headers", "Pagination-Count, Pagination-Limit, Pagination-Count, Pagination-Page");
            // Note: this may or may not be necessary in your particular application
            response.type("application/json");
        });
    }


    public static void main(String[] args) {
        enableCORS("*","*", "*");
        Sql2o sql2o[] = new Sql2o[2];
        sql2o[0] = new Sql2o("jdbc:postgresql://localhost:5432/tbd","postgres","secret");
        sql2o[1] = new Sql2o("jdbc:postgresql://localhost:5432/tbd2","postgres","secret");

        /* CONTROLLERS */
        CtrEmpleado ctrEmpleado = new CtrEmpleado(sql2o);
        CtrReunion ctrReunion = new CtrReunion(sql2o);
        CtrParticipantes ctrParticipantes = new CtrParticipantes(sql2o);

        VolunteerController volunteerController = new VolunteerController("volunteers", sql2o[0]);
        EmergencyController emergencyController = new EmergencyController("emergencies", sql2o[0]);
        TaskController taskController = new TaskController("tasks", sql2o[0]);
        CharacteristicController characteristicController = new CharacteristicController("characteristics", sql2o[0]);

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        get("/", (req, res) -> "{\"mensaje\":\"Corriendo\"}");


        /* EMERGENCY ROUTES */
        post("/emergencies/post", (req, res) -> {
            Emergency emergency = gson.fromJson(req.body(), Emergency.class);
            Emergency insertedEmergency = (Emergency) volunteerController.insertAllFields(emergency, Emergency.class);
            res.status(201);
            return gson.toJson(insertedEmergency);
        });

        get("/emergencies", (req, res) -> {
            return gson.toJson(emergencyController.getAll(Emergency.class));
        });

        get("volunteers", (req, res) -> {
            return gson.toJson(volunteerController.getAll(Volunteer.class));
        });

        /* VOLUNTEER ROUTES */
        post("/volunteers", (req, res) -> {
            Volunteer volunteer = gson.fromJson(req.body(), Volunteer.class);
            Volunteer insertedVolunteer = (Volunteer) volunteerController.insertAllFields(volunteer, Volunteer.class);
            res.status(201);
            return gson.toJson(insertedVolunteer);
        });

        /* TASK ROUTES */
        post("tasks/post", (req, res) -> {
           Task task = gson.fromJson(req.body(), Task.class);
           Task insertedTask = (Task) taskController.insertAllFields(task, Task.class);
           res.status(201);
           return gson.toJson(insertedTask);
        });

        /* CHARACTERISTIC ROUTES */
        get("characteristics", (req, res) -> {
           return gson.toJson(characteristicController.getAll(Characteristic.class));
        });

        post("/empleados", (req, res) -> {
            Empleado emp = gson.fromJson(req.body(), Empleado.class);
            Empleado insertado = ctrEmpleado.creaEmpleado(emp);
            res.status(201);
            return gson.toJson(insertado);
        });

        get("/empleados", (req, res) -> {
            return gson.toJson(ctrEmpleado.getEmpleados());
        });

        post("/reunion", (req, res) -> {
            Reunion reu = gson.fromJson(req.body(), Reunion.class);
            Reunion insertado = ctrReunion.creaReunion(reu);
            res.status(201);
            return gson.toJson(insertado);
        });

        get("/reunion", (req, res) -> {
            return gson.toJson(ctrReunion.getReuniones());
        });

        post("/participantes",(req, res)->{
            Participante p = gson.fromJson(req.body(), Participante.class);
            Participante insertado = ctrParticipantes.agregaParticipante(p);
            res.status(201);
            return gson.toJson(insertado);
        });

        get("/empleados/reunion/date", (req, res) -> {
            String year = req.queryParams("year");
            String month = req.queryParams("month");
            String day= req.queryParams("day");
            String date = year+"-"+month+"-"+day;
            return gson.toJson(ctrParticipantes.getFilledParticipanteByDate(date));
        });

        after((req, res) -> {
            res.type("application/json");
        });
    }


}
