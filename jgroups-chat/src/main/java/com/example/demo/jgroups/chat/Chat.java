package com.example.demo.jgroups.chat;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.Receiver;
import org.jgroups.View;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Chat implements Receiver {
    protected JChannel channel;
    protected static final String CLUSTER="chat";

    @Override
    public void viewAccepted(View new_view) {
        System.out.println("Print view: " + new_view);
    }

    @Override
    public void receive(Message msg) {
        System.out.println("[" + msg.getSrc() + "]: " + msg.getObject());
    }

    public void start(JChannel channel) throws Exception {
        this.channel = channel;
        this.channel.setReceiver(this);
        this.channel.connect(CLUSTER);
        eventLoop();
        this.channel.close();
    }

    private void start(String props, String name, boolean nohup) throws Exception {
        this.channel = new JChannel(props);
        if (name != null) {
            this.channel.name(name);
        }
        this.channel.setReceiver(this);
        this.channel.connect(CLUSTER);
        if (!nohup) {
            eventLoop();
            this.channel.close();
        }
    }

    private void eventLoop() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.println("> ");
                System.out.flush();
                String line = in.readLine().toLowerCase();
                if (line.startsWith("quit") || line.startsWith("exit")) {
                    break;
                }
                Message msg = new Message(null, line);
                this.channel.send(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String props = "udp.xml";
        String name = null;
        boolean nohup = false;

        for (int i = 0; i < args.length; i++) {
            if(args[i].equals("-props")) {
                props=args[++i];
                continue;
            }
            if(args[i].equals("-name")) {
                name=args[++i];
                continue;
            }
            if(args[i].equals("-nohup")) {
                nohup=true;
                continue;
            }
            help();
            return;
        }

        new Chat().start(props, name, nohup);
    }

    private static void help() {
        System.out.println("Chat [-props XML config] [-name name] [-nohup]");
    }
}
