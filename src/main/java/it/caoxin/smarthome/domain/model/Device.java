package it.caoxin.smarthome.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Device implements Serializable {
    private Integer id;

    private Integer familyId;

    private String name;

    private String description;

    private String location;

    private String info;

    private Date registertTime;

    private String status;

    private String img;

    private String controlMode;

    private static final long serialVersionUID = 1L;

    private List<DeviceOperator> operatorList;

    private List<Sensor> sensorList;

    public Device() {
    }

    public Device(Integer id, Integer familyId, String name, String description, String location, String info, Date registertTime, String status, String img, String controlMode) {
        this.id = id;
        this.familyId = familyId;
        this.name = name;
        this.description = description;
        this.location = location;
        this.info = info;
        this.registertTime = registertTime;
        this.status = status;
        this.img = img;
        this.controlMode = controlMode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public Date getRegistertTime() {
        return registertTime;
    }

    public void setRegistertTime(Date registertTime) {
        this.registertTime = registertTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getControlMode() {
        return controlMode;
    }

    public void setControlMode(String controlMode) {
        this.controlMode = controlMode == null ? null : controlMode.trim();
    }

    public List<DeviceOperator> getOperatorList() {
        return operatorList;
    }

    public void setOperatorList(List<DeviceOperator> operatorList) {
        this.operatorList = operatorList;
    }

    public List<Sensor> getSensorList() {
        return sensorList;
    }

    public void setSensorList(List<Sensor> sensorList) {
        this.sensorList = sensorList;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", familyId=" + familyId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", info='" + info + '\'' +
                ", registertTime=" + registertTime +
                ", status='" + status + '\'' +
                ", img='" + img + '\'' +
                ", controlMode='" + controlMode + '\'' +
                ", operatorList=" + operatorList +
                ", sensorList=" + sensorList +
                '}';
    }
}