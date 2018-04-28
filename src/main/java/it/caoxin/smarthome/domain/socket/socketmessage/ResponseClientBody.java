package it.caoxin.smarthome.domain.socket.socketmessage;

/**
 *
 */
public class ResponseClientBody extends MsgBody {
    private String clientInfo;

    public ResponseClientBody(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }
}
