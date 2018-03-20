package it.caoxin.smarthome.domain.model;

import java.io.Serializable;

public class SensorType implements Serializable {
    private Integer id;

    private String name;

    private String info;

    private String description;

    private String status;

    private static final long serialVersionUID = 1L;

    public SensorType() {
    }

    public SensorType(Integer id, String name, String info, String description, String status) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.description = description;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "SensorType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}