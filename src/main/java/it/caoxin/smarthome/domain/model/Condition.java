package it.caoxin.smarthome.domain.model;

import java.io.Serializable;

public class Condition implements Serializable{
    private Integer id;
    private Integer sceneId;
    private String conditionValue;
    private String desc;
    private String status;

    public Condition() {
    }

    public Condition(Integer id, Integer sceneId, String conditionValue, String desc, String status) {
        this.id = id;
        this.sceneId = sceneId;
        this.conditionValue = conditionValue;
        this.desc = desc;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSceneId() {
        return sceneId;
    }

    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }

    public String getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(String conditionValue) {
        this.conditionValue = conditionValue;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "id=" + id +
                ", sceneId=" + sceneId +
                ", conditionValue='" + conditionValue + '\'' +
                ", desc='" + desc + '\'' +
                ", status='" + status + '\'' +
                '}';
    }


}
