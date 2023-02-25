package basic.vaja;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

// below line is for setting table name.
@Entity(tableName = "user_data")
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String surname;
    private String dateOfBirth;
    private String heartRate;
    private String so2;
    private String bodyTemperature;

    public UserEntity (String name, String surname, String dateOfBirth, String heartRate,
                     String so2, String bodyTemperature) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.heartRate = heartRate;
        this.so2 = so2;
        this.bodyTemperature = bodyTemperature;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getSo2() {
        return so2;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }

    public String getBodyTemperature() {
        return bodyTemperature;
    }

    public void setBodyTemperature(String bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
