package com.wp.myboot.baseEntity;

public class NetRequest<ParaType> {

    private NetProtocol protocol;//固定协议参数
    private ParaType params;//自定义参数

    public NetRequest() {
    }

    public NetProtocol getProtocol() {
        return protocol;
    }

    public void setProtocol(NetProtocol protocol) {
        this.protocol = protocol;
    }

    public ParaType getParams() {
        return params;
    }

    public void setParams(ParaType params) {
        this.params = params;
    }
}
