package com.zsj.rpcgame.entity;

import com.zsj.rpcgame.mybatis.hanlder.MySqlJsonObjectHandler;
import lombok.Data;


public class GamePlayer extends AbstractSimpleJsonData {

    public GamePlayer(int userId) {
        super(userId);
    }
}
