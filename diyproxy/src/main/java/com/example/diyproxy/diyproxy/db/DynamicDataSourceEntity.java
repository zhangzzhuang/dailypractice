package com.example.diyproxy.diyproxy.db;

/**
 * @author zhangzhuang
 * @since 2020-10-19
 */
public class DynamicDataSourceEntity {


	public final static String DEFAULET_SOURCE = null;

	private final static  ThreadLocal<String> local = new ThreadLocal<String>();

	public DynamicDataSourceEntity() {
	}

	public static String get(){
		return local.get();
	}

	public static void restore(){
		local.set(DEFAULET_SOURCE);
	}

	public static void set(String source){
		local.set(source);
	}

	public static void set(int year){
		local.set("DB_"+year);
	}
}
