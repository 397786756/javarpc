package com.zsj.rpcgame.dao.sql;

import com.zsj.rpcgame.entity.GamePlayer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: 玩家dao
 * @Author: zhengsongjie
 * @File: GamePlayerDao
 * @Version: 1.0.0
 * @Time: 2020-11-03 下午 05:42
 * @Project: rpcgame
 * @Package: com.zsj.rpcgame.dao.sql
 * @Software: IntelliJ IDEA
 */
@Mapper
public interface GamePlayerDao {
    @Select("select * from game_player")
    List<GamePlayer> selectAll();
}
