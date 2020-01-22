package models;

import java.util.Date;
import java.util.Objects;

public class Volunteer extends Model implements IModel<Long>{
    private Long id;

    private String name;

    private String last_name;

    private String user_name;

    private String password;

    private String rut;

    private String sex;

    private String phone;

    private String email;

    private String address;

    private float latitude;

    private float longitude;

    private String created_at;

    private String last_updated_at;


    public Volunteer() {
    }

    public Volunteer(Long id, String name, String password) {

        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id;}

    //Name
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    //Last Name
    public String getLast_name() { return last_name; }

    public void setLast_name(String last_name) { this.last_name = last_name; }

    //User Name
    public String getUser_name() { return user_name; }

    public void setUser_name(String user_name) { this.user_name = user_name; }

    //Password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
    public void addCharacteristic(Characteristic characteristic, int score){
        CharacteristicVolunteerId cvid = new CharacteristicVolunteerId(characteristic.getId(), this.id);
        CharacteristicVolunteer cv = new CharacteristicVolunteer(this, characteristic, score);
        cv.setId(cvid);
        //characteristics.add(cv);
    }*/

    //Rut
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    //Sex
    public String getSex() { return sex; }

    public void setSex(String sex) { this.sex = sex; }

    //Telephone
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //Address
    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    //Latitude
    public float getLatitude() { return latitude; }

    public void setLatitude(float latitude) { this.latitude = latitude; }

    //Longitude
    public float getLongitude() { return longitude; }

    public void setLongitude(float longitude) { this.longitude = longitude; }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLast_name(), getUser_name(), getPassword(), getRut(), getSex(), getPhone(), getEmail(), getAddress(), getLatitude(), getLongitude());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Volunteer)) return false;
        Volunteer volunteer = (Volunteer) o;
        return getSex().equals(volunteer.getSex()) &&
                Float.compare(volunteer.getLatitude(), getLatitude()) == 0 &&
                Float.compare(volunteer.getLongitude(), getLongitude()) == 0 &&
                getId().equals(volunteer.getId()) &&
                getName().equals(volunteer.getName()) &&
                getLast_name().equals(volunteer.getLast_name()) &&
                getUser_name().equals(volunteer.getUser_name()) &&
                getPassword().equals(volunteer.getPassword()) &&
                getRut().equals(volunteer.getRut()) &&
                getPhone().equals(volunteer.getPhone()) &&
                getEmail().equals(volunteer.getEmail()) &&
                getAddress().equals(volunteer.getAddress());
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", rut='" + rut + '\'' +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", created_at=" + created_at +
                ", last_updated_at=" + last_updated_at +
                '}';
    }
}
