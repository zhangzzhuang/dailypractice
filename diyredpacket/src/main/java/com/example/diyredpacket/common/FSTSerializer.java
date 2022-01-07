package com.example.diyredpacket.common;

import com.example.diyredpacket.util.ObjectUtils;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;

/**
 * @author zhangzhuang
 * @since 2021-03-09
 */
@Component("springSessionDefaultRedisSerializer")
public class FSTSerializer implements RedisSerializer<Object> {

	public FSTSerializer(){}

	// 为了方便进行对象与字节数组的转换，所以应该首先准备出两个转换器
	private static final byte[] EMPTY_BYTE_ARRAY = new byte[0]; // 做一个空数组，不是null


	@Override
	public byte[] serialize(Object o) throws SerializationException {
		if (o == null){
			return EMPTY_BYTE_ARRAY;
		}
		// 将对象变为字节数组
		try {
			return ObjectUtils.serializeFst(o);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			throw new SerializationException(e.getMessage(), e);
		}
	}

	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {
		if (bytes == null || bytes.length == 0) { // 此时没有对象的内容信息
			return null;
		}
		try {
			return ObjectUtils.unSerializeFst(bytes);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			throw new SerializationException(e.getMessage(), e);
		}
	}
}
