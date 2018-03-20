package it.caoxin.smarthome.app.controller;


import it.caoxin.smarthome.domain.common.ClientIpPool;
import it.caoxin.smarthome.domain.model.User;
import it.caoxin.smarthome.domain.service.SocketServer.client.EchoClient;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class HelloController {

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
}
