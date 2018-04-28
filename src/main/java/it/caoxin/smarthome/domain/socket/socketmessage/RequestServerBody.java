package it.caoxin.smarthome.domain.socket.socketmessage;

public class RequestServerBody {
    private String serverInfo;
    public RequestServerBody(String serverInfo) {
        this.serverInfo = serverInfo;
    }
    public String getServerInfo() {
        return serverInfo;
    }
    public void setServerInfo(String serverInfo) {
        this.serverInfo = serverInfo;
    }
}
