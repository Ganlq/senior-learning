package learning.simpleRpc.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.lang.reflect.Proxy;

import learning.simpleRpc.client.ClientConfig;
import learning.simpleRpc.client.RPCProxyHandler;
import learning.simpleRpc.client.RPCRequest;
import learning.simpleRpc.server.RPCResponse;
import learning.simpleRpc.server.ServerConfig;

public class RPC {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static ApplicationContext serverContext;

    public static ApplicationContext clientContext;


    /**
     * 暴露调用端使用的静态方法，为抽象接口生成动态代理类
     * TODO 为考虑后面优化时  不再使用时仍需强转
     * @param cls
     * @return
     */
    public static Object call(Class cls){
        RPCProxyHandler rpcProxyHandler = new RPCProxyHandler();
        Object proxyInstance = Proxy.newProxyInstance(cls.getClassLoader(), new Class<?>[]{cls}, rpcProxyHandler);
        return proxyInstance;
    }


    public static void start(){
        System.out.println("welcome ,simple RPC ");
       // RPCResponseNetty.connet();
    }

    public static String requestEncode(RPCRequest request)throws Exception{
        return objectMapper.writeValueAsString(request)+System.getProperty("line.separator");
    }

    public static RPCRequest requestDecode(String json) throws IOException {
        return objectMapper.readValue(json,RPCRequest.class);
    }

    public static String responseEncode(RPCResponse response) throws JsonProcessingException {
        return objectMapper.writeValueAsString(response)+System.getProperty("line.separator");
    }

    public static Object  responseDecode(String responseJson) throws IOException {
        return objectMapper.readValue(responseJson,RPCResponse.class);

    }
    public static ServerConfig getServerConfig(){
        return serverContext.getBean(ServerConfig.class);
    }

    public static ClientConfig getClientCofig(){
        return clientContext.getBean(ClientConfig.class);
    }
}
