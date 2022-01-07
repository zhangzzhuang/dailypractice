package com.example.diyredpacket.dao;

import com.example.diyredpacket.entity.TUserRedPacket;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface TUserRedPacketMapper extends Mapper<TUserRedPacket> {

	int decreaseRedPacketForVersion(@Param("id") Long id,@Param("version") Integer version);

}