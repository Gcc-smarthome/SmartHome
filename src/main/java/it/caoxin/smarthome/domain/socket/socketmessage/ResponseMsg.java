package it.caoxin.smarthome.domain.socket.socketmessage;

/**
 *
 */
public class ResponseMsg extends BaseMsg {
    public ResponseMsg() {
        super();
        setType(MsgType.RESPONSE);
    }
    private MsgBody body;

    public MsgBody getBody() {
        return body;
    }

    public void setBody(MsgBody body) {
        this.body = body;
    }
}
