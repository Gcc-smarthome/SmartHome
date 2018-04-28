package it.caoxin.smarthome.app.listener;

import it.caoxin.smarthome.domain.service.SocketServer.server.EchoServer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SeverTestListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        new ServerStart().start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    class ServerStart extends Thread{
        @Override
        public void run() {
            System.out.println("启动测试服务器服务器....");
//            new EchoServer(20000).start();
        }
    }


}
