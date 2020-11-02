package com.zsj.rpcgame.entity;

import com.alibaba.fastjson.JSONObject;
import com.zsj.rpcgame.mybatis.hanlder.MySqlJsonObjectHandler;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author songjie.zheng
 * @Description: 基础JSON
 * @date 2020/9/2218:17
 */

public abstract class AbstractSimpleJsonData extends MySqlJsonObjectHandler {
	/** 玩家userId */
	protected int UserId;
	/** 玩家内容 */
	protected JSONObject Content;
	/** 修改情况 */
	protected AtomicInteger option = new AtomicInteger();

	public AbstractSimpleJsonData(int userId) {
		UserId = userId;
	}

	public void initData() {
		if (Content == null) {
			Content = new JSONObject();
		}
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public JSONObject getContent() {
		return Content;
	}

	public void setContent(JSONObject content) {
		Content = content;
	}

	public AtomicInteger getOption() {
		return option;
	}

	public void setOption(AtomicInteger option) {
		this.option = option;
	}
}
