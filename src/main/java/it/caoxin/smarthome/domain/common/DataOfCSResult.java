package it.caoxin.smarthome.domain.common;

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

    public DataOfCSResult(Integer familyId, String operatorInstruction,Integer deviceId,String returnData) {
        this.familyId = familyId;
        this.operatorInstruction = operatorInstruction;
        this.deviceId = deviceId;
        this.returnData = returnData;
    }

    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
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

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "DataOfCSResult{" +
                "familyId=" + familyId +
                ", deviceId=" + deviceId +
                ", operatorInstruction='" + operatorInstruction + '\'' +
                ", returnData='" + returnData + '\'' +
                '}';
    }
}
