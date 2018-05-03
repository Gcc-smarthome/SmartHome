package it.caoxin.smarthome.domain.model;

public class Scene {
    private Integer id;
    private Integer familyId;
    private Integer deviceId;
    private Integer userId;
    private String sceneName;
    private String pattern;
    private String status;
    private String desc;

    public Scene() {
    }

    public Scene(Integer id, Integer familyId, Integer deviceId, Integer userId, String sceneName, String pattern, String status, String desc) {
        this.id = id;
        this.familyId = familyId;
        this.deviceId = deviceId;
        this.userId = userId;
        this.sceneName = sceneName;
        this.pattern = pattern;
        this.status = status;
        this.desc = desc;
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

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Scene{" +
                "id=" + id +
                ", familyId=" + familyId +
                ", deviceId=" + deviceId +
                ", userId=" + userId +
                ", sceneName='" + sceneName + '\'' +
                ", pattern='" + pattern + '\'' +
                ", status='" + status + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }


}
