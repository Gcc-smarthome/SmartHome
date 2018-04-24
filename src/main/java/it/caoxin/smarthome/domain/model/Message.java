package it.caoxin.smarthome.domain.model;

/*
服务器将消息推送到对应的客户端
 */
public class Message {
    private SensorData sensorData;

    public Message() {
    }

    public Message(SensorData sensorData) {
        this.sensorData = sensorData;
    }

    public SensorData getSensorData() {
        return sensorData;
    }

    public void setSensorData(SensorData sensorData) {
        this.sensorData = sensorData;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sensorData=" + sensorData +
                '}';
    }
}
