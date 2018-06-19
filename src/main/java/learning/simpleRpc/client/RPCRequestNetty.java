package learning.simpleRpc.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class RPCRequestNetty {

    //全局map 每个请求对应的锁，用于同步等待每个异步的rpc请求
    public static Map requestLockMap = new ConcurrentHashMap<String,RPCRequest>();

    public static Lock connectLock = new ReentrantLock(); //阻塞等待连接成功的锁

    public static Condition connectCondition = connectLock.newCondition();

    private static volatile RPCRequestNetty instance;




    private RPCRequestNetty(){

        //netty 线程组
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new LineBasedFrameDecoder(2048));//以换行符分包，最大2048
                        socketChannel.pipeline().addLast(new StringDecoder());//将接收到的对象转换为字符串
                        //添加相应回调处理器和编码器
                        socketChannel.pipeline().addLast(new RPCRequestHandler());
                    }
                });


    }

    public static RPCRequestNetty connect(){
        if (instance ==null){
            synchronized (RPCRequestNetty.class){
                if (instance ==null){
                    instance = new RPCRequestNetty();
                }
            }
        }
        return instance;
    }





}
