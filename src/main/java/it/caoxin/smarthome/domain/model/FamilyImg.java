package it.caoxin.smarthome.domain.model;

public class FamilyImg {
    private Integer id;
    private Integer familyId;
    private String imgUrl;
    private String status;

    public FamilyImg() {
    }

    public FamilyImg(Integer id, Integer familyId, String imgUrl, String status) {
        this.id = id;
        this.familyId = familyId;
        this.imgUrl = imgUrl;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FamilyImg{" +
                "id=" + id +
                ", familyId=" + familyId +
                ", imgUrl='" + imgUrl + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
