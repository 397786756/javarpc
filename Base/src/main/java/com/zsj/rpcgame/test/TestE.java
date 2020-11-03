package com.zsj.rpcgame.test;

import com.zsj.rpcgame.entity.GamePlayer;
import com.zsj.rpcgame.mybatis.dao.PlayerDao;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * @Description:
 * @Author: zhengsongjie
 * @File: TestE
 * @Version: 1.0.0
 * @Time: 2020-11-03 下午 03:16
 * @Project: rpcgame
 * @Package: com.zsj.rpcgame.test
 * @Software: IntelliJ IDEA
 */
@Component
public class TestE implements CommandLineRunner, DisposableBean {
    @Autowired
    private PlayerDao playerDao;
    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void run(String... args) throws Exception {
        GamePlayer gamePlayer = playerDao.selectGamePlayer(1);
        System.out.println(gamePlayer);

    }
}
