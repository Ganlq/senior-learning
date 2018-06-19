package learning.simpleRpc.client;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import learning.simpleRpc.core.RPC;
import learning.simpleRpc.server.RPCResponse;

public class RPCRequestHandler extends ChannelHandlerAdapter {

    public static ChannelHandlerContext channelCtx;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception{
        channelCtx=ctx;
        //需要在lock和unlock的包裹下
/*
        RPCRequestNetty.connectlock.lock();
        RPCRequestNetty.connectCondition.signalAll();
        RPCRequestNetty.connectlock.unlock();
*/

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String responseJson = (String)msg;
        RPCResponse response= (RPCResponse)RPC.responseDecode(responseJson);
        synchronized (RPCRequestNetty.requestLockMap.get(response.getRequestID())){
            //唤醒在该对象锁上wait的线程
           /* RPCRequest request = RPCRequestNetty.requestLockMap.get(response.getRequestID());
            request.setResult(response.getResult());
            request.notifyAll();*/
        }

    }
}
