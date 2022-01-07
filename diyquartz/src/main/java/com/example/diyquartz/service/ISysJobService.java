package com.example.diyquartz.service;

import com.example.diyquartz.entity.SysJob;

import java.util.HashMap;
import java.util.List;

/**
 * @author zhangzhuang
 * @since 2020-10-21
 */
public interface ISysJobService {

	/**
	 * 查询定时任务列表
	 * @param map
	 * @return
	 */
	List<SysJob> querySysJobList(HashMap<String, String> map);

	/**
	 * 新增定时任务
	 * @param record
	 * @return
	 */
	int insertSelective(SysJob record);

	/**
	 * 删除定时任务
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 根据主键查询定时任务详情
	 * @param id
	 * @return
	 */
	SysJob selectByPrimaryKey(Integer id);

	/**
	 * 根据bean查询定时任务详情
	 * @param
	 * @return
	 */
	SysJob selectByBean(SysJob bean);

	/**
	 * 更新定时任务详情
	 * @param
	 * @return
	 */
	int updateByPrimaryKeySelective(SysJob bean);
}
