package learning.simpleRpc.server;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

import learning.simpleRpc.core.RPC;

public class ServerConfig implements ApplicationContextAware{

    private int port;

    private Map<String ,String> serverImplMap;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Map<String, String> getServerImplMap() {
        return serverImplMap;
    }

    public void setServerImplMap(Map<String, String> serverImplMap) {
        this.serverImplMap = serverImplMap;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RPC.serverContext=applicationContext;
    }
}
