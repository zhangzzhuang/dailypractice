package com.example.diyredpacket.config;


import com.example.diyredpacket.common.FSTSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;



import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author zhangzhuang
 * @since 2021-01-20
 */
@Configuration
@ConditionalOnClass({RedisOperations.class})
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {

	@Bean
	@ConditionalOnMissingBean(name = "redisTemplate")
	public RedisTemplate<Object, Object> redisTemplate(@Qualifier("jedisConnectionFactory") RedisConnectionFactory connectionFactory) {


		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);

		//value值的序列化使用fst序列化
		FSTSerializer fstSerializer = new FSTSerializer();
		template.setValueSerializer(fstSerializer);
		template.setHashValueSerializer(fstSerializer);

		// key的序列化采用StringRedisSerializer
		RedisSerializer stringRedisSerializer = new StringRedisSerializer();
		template.setKeySerializer(stringRedisSerializer);
		template.setHashKeySerializer(stringRedisSerializer);

		template.afterPropertiesSet();
		return template;
	}

	@Bean
	@ConditionalOnMissingBean(name = "stringRedisTemplate")
	public StringRedisTemplate stringRedisTemplate(@Qualifier("jedisConnectionFactory") RedisConnectionFactory connectionFactory) {

		StringRedisTemplate redisTemplate = new StringRedisTemplate();
		redisTemplate.setConnectionFactory(connectionFactory);

		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		// key采用String的序列化方式
		redisTemplate.setKeySerializer(stringRedisSerializer);
		// hash的key也采用String的序列化方式
		redisTemplate.setHashKeySerializer(stringRedisSerializer);
		// value序列化方式采用String的序列化方式
		redisTemplate.setValueSerializer(stringRedisSerializer);
		// hash的value序列化方式采用String的序列化方式
		redisTemplate.setHashValueSerializer(stringRedisSerializer);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

	/**
	 * redis哨兵配置
	 *
	 * @return
	 */
	@Bean
	public RedisSentinelConfiguration redisSentinelConfiguration(RedisProperties redisProperties) {
		RedisSentinelConfiguration configuration = new RedisSentinelConfiguration();
		//切割集群节点
		List<String> sNodes = redisProperties.getSentinel().getNodes();
		Set<RedisNode> nodes = new HashSet<RedisNode>();
		sNodes.forEach(address -> nodes.add(new RedisNode(address.split(":")[0].trim(), Integer.parseInt(address.split(":")[1]))));
		configuration.setSentinels(nodes);
		configuration.setMaster(redisProperties.getSentinel().getMaster());
		return configuration;
	}


	/**
	 * 连接redis的工厂类
	 *
	 * @return
	 */
	@Bean
	public JedisConnectionFactory jedisConnectionFactory(RedisProperties redisProperties) {

		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMinIdle(redisProperties.getJedis().getPool().getMinIdle());
		poolConfig.setMaxIdle(redisProperties.getJedis().getPool().getMaxIdle());
		poolConfig.setMaxTotal(redisProperties.getJedis().getPool().getMaxActive());
		poolConfig.setMaxWaitMillis(redisProperties.getJedis().getPool().getMaxWait().toMillis());
		poolConfig.setTestOnBorrow(true);
		return new JedisConnectionFactory(redisSentinelConfiguration(redisProperties), poolConfig);
	}



}