package it.caoxin.smarthome.domain.model;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable {
    private Integer id;

    private Integer familyId;

    private String type;

    private String info;

    private String status;

    private Date generateTime;

    private static final long serialVersionUID = 1L;

    public Log() {
    }

    public Log(Integer id, Integer familyId, String type, String info, String status, Date generateTime) {
        this.id = id;
        this.familyId = familyId;
        this.type = type;
        this.info = info;
        this.status = status;
        this.generateTime = generateTime;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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

    public Date getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(Date generateTime) {
        this.generateTime = generateTime;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", familyId=" + familyId +
                ", type='" + type + '\'' +
                ", info='" + info + '\'' +
                ", status='" + status + '\'' +
                ", generateTime=" + generateTime +
                '}';
    }
}