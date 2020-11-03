package com.zsj.rpcgame.netty;

/**
 * @Description: 服务器枚举类
 * @Author: zhengsongjie
 * @File: ServerType
 * @Version: 1.0.0
 * @Time: 2020-11-03 下午 03:03
 * @Project: rpcgame
 * @Package: com.zsj.rpcgame.netty
 * @Software: IntelliJ IDEA
 */
public enum ServerType {
    //socket服务器
    SOCKET("socket服务器"),
    //webSocket服务器
    WEBSCOKET("webSocket服务器"),
    //http服务器
    HTTP("http服务器");
    private String serverName;

    ServerType(String serverName) {
        this.serverName = serverName;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
