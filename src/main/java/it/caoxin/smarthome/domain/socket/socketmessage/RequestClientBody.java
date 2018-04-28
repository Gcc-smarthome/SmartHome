package it.caoxin.smarthome.domain.socket.socketmessage;

public class RequestClientBody extends MsgBody {
    private String familyId;
    private String operatorInstruction;

    public RequestClientBody(String familyId, String operatorInstruction) {
        this.familyId = familyId;
        this.operatorInstruction = operatorInstruction;
    }

    public RequestClientBody(String familyId) {

    }

    public String getFamilyId() {
        return familyId;
    }

    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }

    public String getOperatorInstruction() {
        return operatorInstruction;
    }

    public void setOperatorInstruction(String operatorInstruction) {
        this.operatorInstruction = operatorInstruction;
    }
}
