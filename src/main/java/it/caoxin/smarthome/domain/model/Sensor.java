package it.caoxin.smarthome.domain.model;

import java.io.Serializable;
import java.util.Date;

public class Sensor implements Serializable {
    private Integer id;

    private Integer deviceId;

    private Integer sensorTypeId;

    private String name;

    private String info;

    private String description;

    private String status;

    private Date registerTime;

    private static final long serialVersionUID = 1L;

    private SensorType sensorType;
    public Sensor() {
    }

    public Sensor(Integer id, Integer deviceId, Integer sensorTypeId, String name, String info, String description, String status, Date registerTime, SensorType sensorType) {
        this.id = id;
        this.deviceId = deviceId;
        this.sensorTypeId = sensorTypeId;
        this.name = name;
        this.info = info;
        this.description = description;
        this.status = status;
        this.registerTime = registerTime;
        this.sensorType = sensorType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getSensorTypeId() {
        return sensorTypeId;
    }

    public void setSensorTypeId(Integer sensorTypeId) {
        this.sensorTypeId = sensorTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", deviceId=" + deviceId +
                ", sensorTypeId=" + sensorTypeId +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", registerTime=" + registerTime +
                ", sensorType=" + sensorType +
                '}';
    }
}