package it.caoxin.SmartHomeServer.common;

public class DataOfCSResult {
    //需要操作的家庭id
    private Integer familyId;
    //需要操作的家庭用电器指令
    private Integer deviceId;
    private String operatorInstruction;
    //终端返回结果
    private String returnData;

    public DataOfCSResult() {
    }

    public DataOfCSResult(Integer familyId, Integer deviceId, String operatorInstruction, String returnData) {
        this.familyId = familyId;
        this.deviceId = deviceId;
        this.operatorInstruction = operatorInstruction;
        this.returnData = returnData;
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

    public String getOperatorInstruction() {
        return operatorInstruction;
    }

    public void setOperatorInstruction(String operatorInstruction) {
        this.operatorInstruction = operatorInstruction;
    }

    public String getReturnData() {
        return returnData;
    }

    public void setReturnData(String returnData) {
        this.returnData = returnData;
    }
}
