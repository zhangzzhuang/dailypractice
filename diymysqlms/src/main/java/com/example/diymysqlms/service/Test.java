package com.example.diymysqlms.service;

import com.example.diymysqlms.controller.HealthCheckController;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.diymysqlms.controller.HealthCheckController.abc;

/**
 * @author zhangzhuang
 * @since 2021-04-26
 */
public class Test {


	public static void main(String[] args) {
		Test test = new Test();
		System.out.println(HealthCheckController.abc);
		System.out.println(abc);
	}




		public Test(){//构造函数
			System.out.println("A的构造函数");
		}
		{//构造代码块
			System.out.println("A的构造代码块");
		}
		static {//静态代码块
			System.out.println("A的静态代码块");
		}

}
