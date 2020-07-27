package com.example.demo.mbean;

public class AppClientImpl implements AppClient {

    private AppService appServiceLocal;

    private AppService appServiceRemote;

    private String requestType;

    public AppClientImpl() {
    }

    public AppClientImpl(AppService appServiceLocal, AppService appServiceRemote, String requestType) {
        this.appServiceLocal = appServiceLocal;
        this.appServiceRemote = appServiceRemote;
        this.requestType = requestType;
    }

    public AppService getAppServiceLocal() {
        return appServiceLocal;
    }

    public void setAppServiceLocal(AppService appServiceLocal) {
        this.appServiceLocal = appServiceLocal;
    }

    public AppService getAppServiceRemote() {
        return appServiceRemote;
    }

    public void setAppServiceRemote(AppService appServiceRemote) {
        this.appServiceRemote = appServiceRemote;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    @Override
    public String invokeLocal(String requestType) {
        System.out.println("Invoke local App Service.");
        return appServiceLocal.sendRequest(requestType);
    }

    @Override
    public String invokeRemote(String requestType) {
        System.out.println("Invoke remote App Service");
        return appServiceRemote.sendRequest(requestType);
    }
}
