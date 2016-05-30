package com.hbut.info.igsServer.test;

import com.hbut.info.igsServer.netty.Client;
import org.json.JSONObject;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2016/4/13.
 */
public class TestClient{
    public static void main(String[] args) {
        //模拟客户端1
        Client client1 = new Client("127.0.0.1",8889);
        client1.init();
        client1.conn();
        JSONObject jsonStr1 = new JSONObject();
        jsonStr1.put("source","virtualClient1");
        jsonStr1.put("time",System.currentTimeMillis());
        jsonStr1.put("pid",7771);
        jsonStr1.put("level", 1);
        jsonStr1.put("msg", "this is a test message");
        String msg1 = jsonStr1.toString();
        System.out.println("start send message..");
        client1.sendMsg(msg1);

        //模拟客户端2
        Client client2 = new Client("127.0.0.1",8889);
        client2.init();
        client2.conn();
        JSONObject jsonStr2 = new JSONObject();
        jsonStr2.put("source","virtualClient2");
        jsonStr2.put("time",System.currentTimeMillis());
        jsonStr2.put("pid",7772);
        jsonStr2.put("level", 2);
        jsonStr2.put("msg", "this is a test message");
        String msg2 = jsonStr2.toString();
        System.out.println("start send message..");
        client1.sendMsg(msg2);

        //模拟客户端3
        Client client3 = new Client("127.0.0.1",8889);
        client3.init();
        client3.conn();
        JSONObject jsonStr3 = new JSONObject();
        jsonStr3.put("source","virtualClient3");
        jsonStr3.put("time",System.currentTimeMillis());
        jsonStr3.put("pid",7773);
        jsonStr3.put("level", 3);
        jsonStr3.put("msg", "this is a test message");
        String msg3 = jsonStr2.toString();
        System.out.println("start send message..");
        client1.sendMsg(msg3);
    }
}
