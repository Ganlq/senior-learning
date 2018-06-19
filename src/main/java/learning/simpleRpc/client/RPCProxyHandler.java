package learning.simpleRpc.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicLong;

public class RPCProxyHandler implements InvocationHandler{

    //TODO 目前有问题，每次创建代理会被重新赋值
    private static AtomicLong requestTimes = new AtomicLong(0);//记录调用次数，也作id标识



    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RPCRequest request = new RPCRequest();


        return null;
    }
}
