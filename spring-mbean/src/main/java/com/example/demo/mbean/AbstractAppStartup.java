package com.example.demo.mbean;

public abstract class AbstractAppStartup {
    private String serviceName;

    public AbstractAppStartup() {
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void start() throws Exception {
        this.startService();
    }

    public void stop() throws Exception {
        this.stopService();
    }

    protected abstract void startService();

    protected abstract void stopService();
}
