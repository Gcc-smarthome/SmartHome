package it.caoxin.smarthome.domain.model;

import java.io.Serializable;
import java.util.Date;

public class DeviceOperator implements Serializable {
    private Integer id;

    private Integer deviceId;

    private String operatorName;

    private String operatorCode;

    private String description;

    private Date registerTime;

    private String status;

    private static final long serialVersionUID = 1L;

    public DeviceOperator() {
    }

    public DeviceOperator(Integer id, Integer deviceId, String operatorName, String operatorCode, String description, Date registerTime, String status) {
        this.id = id;
        this.deviceId = deviceId;
        this.operatorName = operatorName;
        this.operatorCode = operatorCode;
        this.description = description;
        this.registerTime = registerTime;
        this.status = status;
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

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode == null ? null : operatorCode.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Override
    public String toString() {
        return "DeviceOperator{" +
                "id=" + id +
                ", deviceId=" + deviceId +
                ", operatorName='" + operatorName + '\'' +
                ", operatorCode='" + operatorCode + '\'' +
                ", description='" + description + '\'' +
                ", registerTime=" + registerTime +
                ", status='" + status + '\'' +
                '}';
    }
}