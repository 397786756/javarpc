package com.zsj.rpcgame.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 统一日志输出工具类
 *
 * @author zhengsongjie
 */
public class Log {
    private Log() {
    }

    private static Logger COMMON_LOGGER = LoggerFactory.getLogger(Log.class);


    /**
     * 记录错误信息（默认标签）
     *
     * @param format    日志输出格式
     * @param arguments 填入参数
     */
    public static void error(String format, Object... arguments) {
        COMMON_LOGGER.error(format, arguments);
    }


    /**
     * INFO级别日志（默认标签）
     *
     * @param format    日志输出格式
     * @param arguments 填入参数
     */
    public static void info(String format, Object... arguments) {
        COMMON_LOGGER.info(format, arguments);
    }

}
