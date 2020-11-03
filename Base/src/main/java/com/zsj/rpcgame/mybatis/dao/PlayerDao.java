package com.zsj.rpcgame.mybatis.dao;

import com.zsj.rpcgame.entity.GamePlayer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Description: 用户dao
 * @Author: zhengsongjie
 * @File: PlayerDao
 * @Version: 1.0.0
 * @Time: 2020-11-03 下午 03:10
 * @Project: rpcgame
 * @Package: com.zsj.rpcgame.mybatis.dao
 * @Software: IntelliJ IDEA
 */
@Mapper
public interface PlayerDao {
    @Select("select * from game_player where userId =#{userId}")
    public GamePlayer selectGamePlayer(int userId);
}
