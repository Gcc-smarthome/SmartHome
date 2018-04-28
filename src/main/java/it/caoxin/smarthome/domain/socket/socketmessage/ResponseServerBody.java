package it.caoxin.smarthome.domain.socket.socketmessage;

/**
 *
 */
public class ResponseServerBody extends MsgBody {
    private String serverInfo;
    public ResponseServerBody(String serverInfo) {
        this.serverInfo = serverInfo;
    }
    public String getServerInfo() {
        return serverInfo;
    }
    public void setServerInfo(String serverInfo) {
        this.serverInfo = serverInfo;
    }
}
