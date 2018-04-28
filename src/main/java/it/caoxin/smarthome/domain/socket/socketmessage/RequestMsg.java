package it.caoxin.smarthome.domain.socket.socketmessage;

public class RequestMsg extends BaseMsg {
    private MsgBody body;
    public RequestMsg() {
        super();
        setType(MsgType.RESPONSE);
    }

    public MsgBody getBody() {
        return body;
    }

    public void setBody(MsgBody body) {
        this.body = body;
    }
}
