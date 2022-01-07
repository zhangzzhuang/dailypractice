package com.example.diyquartz.dao;

import com.example.diyquartz.entity.SysJob;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

@Repository
public interface SysJobMapper extends Mapper<SysJob> {
	List<SysJob> querySysJobList(HashMap<String, String> map);

	SysJob selectByBean(SysJob bean);
}