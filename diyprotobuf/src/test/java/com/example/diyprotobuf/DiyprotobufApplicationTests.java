package com.example.diyprotobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DiyprotobufApplicationTests {

	@Test
	void contextLoads() {
	}


	@Test
	public void TestPersonBuf() throws InvalidProtocolBufferException {

		PersonBuf.Person.Builder personBuilder = PersonBuf.Person.newBuilder();
		personBuilder.setName("Bob");
		personBuilder.setId(10001);
		personBuilder.setEmail("12@qq.com");

		PersonBuf.Person person = personBuilder.build();
		System.out.println(person.toString());

		System.out.println("===== person Byte 开始=====");
		for (byte b: person.toByteArray()) {
			System.out.print(b);
		}

		System.out.println("\n" + "bytes长度" + person.toByteString().size());
		System.out.println("===== person Byte 结束=====");


		System.out.println("\n" + "===== 使用person 反序列化生成对象开始 =====");

		PersonBuf.Person p =PersonBuf.Person.parseFrom(person.toByteArray());

		System.out.println(p.toString());
		System.out.println("===== 使用person 反序列化生成对象结束 =====");
	}
}
