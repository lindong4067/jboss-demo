package com.example.demo.jgroups.counter;

import org.jgroups.*;
import org.jgroups.blocks.atomic.Counter;
import org.jgroups.blocks.atomic.CounterService;
import org.jgroups.util.Util;

public class CounterServiceDemo {

    protected JChannel channel;

    /**
     * add <COUNTER bypass_bundling="true" timeout="5000" /> in udp.xml
     */
    protected void start(String props, String channel_name) throws Exception {

        this.channel = new JChannel("udp.xml");
        this.channel.setName(channel_name);
        this.channel.setReceiver(new ReceiverAdapter() {
            @Override
            public void viewAccepted(View view) {
                System.out.println("-- view: " + view);
            }
        });
        loop();
    }

    public void start(JChannel channel) throws Exception {
        this.channel = channel;
        this.channel.setReceiver(new ReceiverAdapter() {
            @Override
            public void viewAccepted(View view) {
                System.out.println("-- view: " + view);
            }
        });
        loop();
    }

    private void loop() throws Exception {
        CounterService counterService = new CounterService(channel);
        channel.connect("counter-cluster");
        Counter counter = counterService.getOrCreateCounter("mycounter", 1);

        boolean looping = true;
        while (looping) {
            try {
                int key = Util.keyPress(
                        "[1] Increment\n" +
                        "[2] Decrement \n" +
                        "[3] Compare and set\n" +
                        "[4] Create counter \n" +
                        "[5] Delete counter\n" +
                        "[6] Print counters \n" +
                        "[7] Get counter\n" +
                        "[8] Increment 1M times \n" +
                        "[9] Dump pending requests \n" +
                        "[0] Select counter \n" +
                        "[x] Exit\n");
                switch (key) {
                    case '0':
                        String selectCounter = Util.readStringFromStdin("-- input select counter: ");
                        counter = counterService.getOrCreateCounter(selectCounter, 1);
                    case '1':
                        long val = counter.incrementAndGet();
                        System.out.println("counter: " + val);
                        break;
                    case '2':
                        val = counter.decrementAndGet();
                        System.out.println("counter: " + val);
                        break;
                    case '3':
                        long expect = Util.readLongFromStdin("expected value: ");
                        long update = Util.readLongFromStdin("update: ");
                        if (counter.compareAndSet(expect, update)) {
                            System.out.println("-- set counter \"" + counter.getName() + "\" to " + update + "\n");
                        } else {
                            System.err.println("failed setting counter \"" + counter.getName() + "\" from " + expect +
                                    " to " + update + ", current value is " + counter.get() + "\n");
                        }
                        break;
                    case '4':
                        String counter_name = Util.readStringFromStdin("counter name: ");
                        counter = counterService.getOrCreateCounter(counter_name, 1);
                        break;
                    case '5':
                        counter_name=Util.readStringFromStdin("counter name: ");
                        counterService.deleteCounter(counter_name);
                        break;
                    case '6':
                        System.out.println("Counters (current=" + counter.getName() + "):\n\n" + counterService.printCounters());
                        break;
                    case '7':
                        long l = counter.get();
                        System.out.println(l);
                        break;
                    case '8':
                        int NUM = Util.readIntFromStdin("num: ");
                        System.out.println("");
                        int print = NUM / 10;
                        long retval = 0;
                        long start = System.currentTimeMillis();
                        for(int i=0; i < NUM; i++) {
                            retval = counter.incrementAndGet();
                            if(i > 0 && i % print == 0)
                                System.out.println("-- count=" + retval);
                        }
                        long diff = System.currentTimeMillis() - start;
                        System.out.println("\n" + NUM + " incrs took " + diff + " ms; " + (NUM / (diff / 1000.0)) + " ops /sec\n");
                        break;
                    case '9':
                        System.out.println("Pending requests:\n" + counterService.dumpPendingRequests());
                        break;
                    case 'x':
                    case -1:
                        looping = false;
                        break;
                }
            } catch (Throwable t) {
                System.out.println(t);
            }
        }
        Util.close(channel);
    }

    public static void main(final String[] args) throws Exception {
        String properties = null;
        String name = null;
        for(int i = 0; i < args.length; i++) {
            if(args[i].equals("-props")) {
                properties=args[++i];
                continue;
            }
            if(args[i].equals("-name")) {
                name=args[++i];
                continue;
            }
            help();
            return;
        }

        new CounterServiceDemo().start(properties, name);

    }

    private static void help() {
        System.out.println("CounterServiceDemo [-props props] [-name name]");
    }
}
