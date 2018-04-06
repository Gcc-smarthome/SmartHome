package it.caoxin.smarthome.domain.model;

public class UserFamily {
    private Integer id;
    private Integer familyId;
    private Integer userId;
    private String familyRole;
    private String status;

    public UserFamily() {
    }

    public UserFamily(Integer id, Integer familyId, Integer userId, String familyRole, String status) {
        this.id = id;
        this.familyId = familyId;
        this.userId = userId;
        this.familyRole = familyRole;
        this.status = status;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFamilyRole() {
        return familyRole;
    }

    public void setFamilyRole(String familyRole) {
        this.familyRole = familyRole;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserFamily{" +
                "id=" + id +
                ", familyId=" + familyId +
                ", userId=" + userId +
                ", familyRole='" + familyRole + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
