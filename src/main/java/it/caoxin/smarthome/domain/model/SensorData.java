package it.caoxin.smarthome.domain.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class SensorData implements Serializable {
    public static final String STATUS_NORMAL = "正常";

    private Integer id;

    private Integer sensorId;

    private String dataName;

    private String dataValue;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date collectionTime;

    private String info;

    private String status;

    private static final long serialVersionUID = 1L;

    public SensorData() {
    }

    public SensorData(Integer id, Integer sensorId, String dataName, String dataValue, Date collectionTime, String info, String status) {
        this.id = id;
        this.sensorId = sensorId;
        this.dataName = dataName;
        this.dataValue = dataValue;
        this.collectionTime = collectionTime;
        this.info = info;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName == null ? null : dataName.trim();
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue == null ? null : dataValue.trim();
    }

    public Date getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Date collectionTime) {
        this.collectionTime = collectionTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Override
    public String toString() {
        return "SensorData{" +
                "id=" + id +
                ", sensorId=" + sensorId +
                ", dataName='" + dataName + '\'' +
                ", dataValue='" + dataValue + '\'' +
                ", collectionTime=" + collectionTime +
                ", info='" + info + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}