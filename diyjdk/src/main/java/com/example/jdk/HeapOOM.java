package com.example.jdk;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangzhuang
 * @since 2021-09-27
 */
public class HeapOOM {


	static class OOMObjec{

	}

	public static void main(String[] args) {
		List<OOMObjec> list = new ArrayList<>();

		while (true){
			list.add(new OOMObjec());
		}
	}
}
