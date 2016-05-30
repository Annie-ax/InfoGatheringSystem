package com.hbut.info.igsServer.rabbitmq;

import com.hbut.info.commons.model.LogInfo;
import com.hbut.info.commons.model.Source;
import com.hbut.info.commons.service.LogInfoService;
import com.hbut.info.commons.service.SourceService;
import com.hbut.info.igsServer.config.QueueConfig;
import com.rabbitmq.client.QueueingConsumer;
import org.apache.commons.lang.SerializationUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.rabbitmq.client.Channel;

/**
 * Created by admin on 2015/12/17.
 */
@Service
public class QueueConsumer {
    private static final Logger logger = LoggerFactory.getLogger(QueueConsumer.class);
    @Autowired
    private LogInfoService logInfoService;
    @Autowired
    private RabbitMqFactory rabbitMqFactory;
    @Autowired
    private SourceService sourceService;

    public void listen(){
        Thread consumerThread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                start();
            }
        });
        consumerThread1.start();
    }

    public void start() {
        Channel channel = rabbitMqFactory.getChannel();
        String queueName = QueueConfig.QUEUE_NAME();
        try {
            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(queueName, true, consumer);
            logger.info("mqComsumer 启动成功！");
            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String str = (String) SerializationUtils.deserialize(delivery.getBody());
                JSONObject jsonObject = null;
                System.out.println("mq read:" + str);

                try {
                    jsonObject = new JSONObject(str);
                } catch (Exception e) {
                    System.out.println("不是json格式:" + e.getMessage());
                }

                if (jsonObject != null) {
                    //String time = jsonObject.getString("time");
                    long time = jsonObject.getLong("time");
                    String timeStr = (new Timestamp(time)).toString();
                    Integer timeSecond = new Integer(timeStr.substring(20, timeStr.length()));
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    LogInfo log = new LogInfo();
                    log.setSource(jsonObject.getString("source"));
                    log.setCreateTime(new Date(System.currentTimeMillis()));
                    try {
                        log.setTimeDate(df.parse(timeStr.substring(0, 19)));
                    } catch (ParseException e) {
                        System.out.println("字符串转化成日期错误" + e.getMessage());
                    }
                    log.setTimeSecond(timeSecond);
                    log.setPid(jsonObject.getInt("pid"));
                    log.setLevel(jsonObject.getInt("level"));
                    log.setMsg(jsonObject.getString("msg"));

                    HashMap<String, Object> hashMap = new HashMap<>();
                    String tableName = jsonObject.getString("source") + "_log";
                    hashMap.put("tableName", tableName);
                    //创建表
                    if (!hasTable(jsonObject.getString("source"))) {
                        logInfoService.createLogTable(jsonObject.getString("source"));
                    }
                    //将数据存入数据库
                    hashMap.put("log", log);
                    logInfoService.insert(hashMap);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断数据库中是否存在该信息源所对应的表
     *
     * @param source
     * @return 若存在，则返回true
     */
    public Boolean hasTable(String source) {
        List<Source> sourceList = new ArrayList<>();
        sourceList = sourceService.findAll();
        for (Source item : sourceList) {
            if (source.equals(item.getSource()))
                return true;
        }
        return false;
    }
}
