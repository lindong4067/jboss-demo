package com.example.demo.jgroups.counter;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.blocks.MessageDispatcher;
import org.jgroups.blocks.RequestHandler;
import org.jgroups.blocks.RequestOptions;
import org.jgroups.util.RspList;
import org.jgroups.util.Util;

public class MessageDispatcherTest implements RequestHandler {

    JChannel channel;
    MessageDispatcher dispatcher;
    RspList<?> rspList;
    String props;

    public void start() throws Exception {
        channel = new JChannel(props);
        dispatcher = new MessageDispatcher(channel, this);
        channel.connect("MessageDispatcherTestGroup");

        for (int i = 0; i < 10; i++) {
            Util.sleep(100);
            System.out.println("Casting message #" + i);
            byte[] payload=("Number #" + i).getBytes();
            rspList = dispatcher.castMessage(null, payload, 0, payload.length, RequestOptions.SYNC());
            System.out.println("Responses:\n" + rspList);
        }

        Util.close(dispatcher, channel);
    }

    public static void main(String[] args) {
        try {
            new MessageDispatcherTest().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object handle(Message msg) throws Exception {
        System.out.println("handle(): " + msg);
        return "Success!";
    }
}
