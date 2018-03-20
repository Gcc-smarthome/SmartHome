package it.caoxin.smarthome.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
*@Author:Caoxin
*@Description
*@Date:20:17 2018/1/31
*@param
*@return
*/
public class Family implements Serializable {

    public static final String MANAGER_UNIQUE_CODE = "manager";
    //家庭id
    private Integer id;

    //家庭识别码
    private String familyUniqueCode;

    //家庭名称
    private String familyName;

    //家庭地址
    private String familyAddress;

    //家庭维度
    private String lat;

    //家庭经度
    private String lon;

    //家庭正常温度
    private String temperature;

    //家庭正常湿度
    private String humidity;

    //家庭正常红外
    private String infrared;

    //家庭正常烟雾
    private String smoke;

    //家庭正常光强
    private String lightIntensity;

    //家庭控制模式
    private String controlMode;

    //家庭状态
    private String status;

    //家庭修改日期
    private Date registerTime;

    private List<Device> deviceList;

    private static final long serialVersionUID = 1L;

    public Family() {
    }

    public Family(Integer id, String familyUniqueCode, String familyName, String familyAddress, String lat, String lon, String temperature, String humidity, String infrared, String smoke, String lightIntensity, String controlMode, String status, Date registerTime, List<Device> deviceList) {
        this.id = id;
        this.familyUniqueCode = familyUniqueCode;
        this.familyName = familyName;
        this.familyAddress = familyAddress;
        this.lat = lat;
        this.lon = lon;
        this.temperature = temperature;
        this.humidity = humidity;
        this.infrared = infrared;
        this.smoke = smoke;
        this.lightIntensity = lightIntensity;
        this.controlMode = controlMode;
        this.status = status;
        this.registerTime = registerTime;
        this.deviceList = deviceList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFamilyUniqueCode() {
        return familyUniqueCode;
    }

    public void setFamilyUniqueCode(String familyUniqueCode) {
        this.familyUniqueCode = familyUniqueCode == null ? null : familyUniqueCode.trim();
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName == null ? null : familyName.trim();
    }

    public String getFamilyAddress() {
        return familyAddress;
    }

    public void setFamilyAddress(String familyAddress) {
        this.familyAddress = familyAddress == null ? null : familyAddress.trim();
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon == null ? null : lon.trim();
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature == null ? null : temperature.trim();
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity == null ? null : humidity.trim();
    }

    public String getInfrared() {
        return infrared;
    }

    public void setInfrared(String infrared) {
        this.infrared = infrared == null ? null : infrared.trim();
    }

    public String getSmoke() {
        return smoke;
    }

    public void setSmoke(String smoke) {
        this.smoke = smoke == null ? null : smoke.trim();
    }

    public String getLightIntensity() {
        return lightIntensity;
    }

    public void setLightIntensity(String lightIntensity) {
        this.lightIntensity = lightIntensity == null ? null : lightIntensity.trim();
    }

    public String getControlMode() {
        return controlMode;
    }

    public void setControlMode(String controlMode) {
        this.controlMode = controlMode == null ? null : controlMode.trim();
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

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    @Override
    public String toString() {
        return "Family{" +
                "id=" + id +
                ", familyUniqueCode='" + familyUniqueCode + '\'' +
                ", familyName='" + familyName + '\'' +
                ", familyAddress='" + familyAddress + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", temperature='" + temperature + '\'' +
                ", humidity='" + humidity + '\'' +
                ", infrared='" + infrared + '\'' +
                ", smoke='" + smoke + '\'' +
                ", lightIntensity='" + lightIntensity + '\'' +
                ", controlMode='" + controlMode + '\'' +
                ", status='" + status + '\'' +
                ", registerTime=" + registerTime +
                ", deviceList=" + deviceList +
                '}';
    }
}