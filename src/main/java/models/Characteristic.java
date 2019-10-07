package models;

import java.util.Set;

public class Characteristic {

        private Long id;
private String name;

        private String description;



    public Characteristic() {
    }

    public Characteristic(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }



}
