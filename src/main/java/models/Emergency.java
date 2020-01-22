package models;

import java.util.Date;

public class Emergency extends Model implements IModel<Long>{
    private Long id;
    private String description;
    private String last_updated_at;
    private String location;
    private Date created_at;
    private Boolean status;
    private float altitude;
    private float latitude;
    private float longitude;
    private String title;
    private String type;
    private Long user_id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }


    public float getLatitude() { return latitude; }

    public float getLongitude() { return longitude; }

    public void setLatitude(float latitude) { this.latitude = latitude; }

    public void setLongitude(float longitude) {this.longitude = longitude; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
