package entity;


public class IP {
    public  String  ip;
    public  String port;
    public  Enumhttp http;

    public Enumhttp getHttp() {
        return http;
    }

    public void setHttp(Enumhttp http) {
        this.http = http;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
