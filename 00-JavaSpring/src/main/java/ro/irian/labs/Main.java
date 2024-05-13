package ro.irian.labs;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Main {
    public static void main(String[] args){
        System.out.println("Hello world!");

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.setBaseDir("temp"); // Set a base directory
        tomcat.getConnector(); // Ensure the connector is initialized

        // Add webapp and set the context path to root
        tomcat.addWebapp("", new File("src/main/webapp").getAbsolutePath());

        try {
            tomcat.start();
            tomcat.getServer().await(); // Use this to keep Tomcat running
        } catch (LifecycleException e) {
            e.printStackTrace();
            System.exit(1); // Non-zero status indicates abnormal termination
        }
    }
}