package com.example.diyredpacket.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author zhangzhuang
 * @since 2021-03-11
 */
@Data
public class RedisDTO {

	@NotBlank(message = "key不为空")
	private String key;
	@NotEmpty(message = "value不能为空")
	private String value;
}
