package Mservlet;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by ff on 16-4-1.
 */
@ServerEndpoint("/getmessage")
public class Getmessage {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    public String userid=null;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<Getmessage> webSocketSet = new CopyOnWriteArraySet<Getmessage>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        //对客户端来的消息进行解析.
        String[] messageall = message.split(";");
        this.userid = messageall[0];
        String friendid = messageall[1];
        String sendmessage = messageall[2];

        //群发消息
        for(Getmessage item: webSocketSet){
            try {
                //发送消息给指定好友的websocket
                if(item.userid.equals(friendid)){
                    item.sendMessage(sendmessage);
                }
//                item.sendMessage(sendmessage);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    @OnMessage
//    public void onMessage(byte[] message,Session session){
//        //前40个字节用于保存用户id+好友ｉｄ
//        byte[] buff=new byte[20];
//        for(int i=0;i<20;i++){
//            buff[i]=message[i];
//        }
//        String friendid = buff.toString();
//
//        //群发消息
//        for(Getmessage item: webSocketSet){
//            try {
//                //发送消息给指定好友的websocket
//                if(item.userid.equals(friendid)){
//                    item.sendMessage(sendmessage);
//                }
////                item.sendMessage(sendmessage);
//            } catch (IOException e) {
//                e.printStackTrace();
//                continue;
//            }
//        }
//    }
    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
        //得到一个输出流
//        OutputStream pw =this.session.getBasicRemote().getSendStream();
        //this.session.getAsyncRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        Getmessage.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        Getmessage.onlineCount--;
    }
}
