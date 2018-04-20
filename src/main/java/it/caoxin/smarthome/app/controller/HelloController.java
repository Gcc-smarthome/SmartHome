package it.caoxin.smarthome.app.controller;


import it.caoxin.smarthome.domain.common.ClientIpPool;
import it.caoxin.smarthome.domain.model.User;
import it.caoxin.smarthome.domain.service.sensor.SensorService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class HelloController {
    public static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    SensorService sensorService;

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        User user = new User();
        user.setId(1);
        user.setUsername("test");
        user.setPassword("test");
        JSONObject jsonObject = JSONObject.fromObject(user);
        return jsonObject.toString();
    }

    @RequestMapping("/toConnectServer")
    @ResponseBody
    public String toConnectServer() throws InterruptedException {
        String host = (String) ClientIpPool.getClientIpPoolMap().get(1);
        int port = 50001;
//        EchoClient echoClient = new EchoClient(host, port,1,null);
//        echoClient.start();


        return "test...";
    }


    @RequestMapping("/testLogInfo")
    @ResponseBody
    public String testLogInfo() throws InterruptedException {
        String res = "6 8 9 open";

        String[] fields = res.split(" ");
        Integer deviceId = Integer.parseInt(fields[0]);


//        logger.info();
        return "test...";
    }
}
