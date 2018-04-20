package log4j;

import it.caoxin.smarthome.domain.mapper.sensor.SensorMapper;
import it.caoxin.smarthome.domain.model.Sensor;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestLog4j {
    public static final Logger logger = LoggerFactory.getLogger(TestLog4j.class);

    @Autowired
    private SensorMapper sensorMapper;
    @Test
    public void testLog4j(){
        String res = "6 8 9 open";

        String[] fields = res.split(" ");
        Integer deviceId = Integer.parseInt(fields[0]);

        List<Sensor> sensors = sensorMapper.selectSensorByDeviceId(deviceId);

//        logger.info();
    }
}
