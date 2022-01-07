package com.example.diythread.AtomicDemo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzhuang
 * @since 2021-03-30
 */
public class LruCache {


	private class CacheNode {
		private CacheNode prev;
		private CacheNode next;
		private int key;
		private int value;

		public CacheNode(int key, int value) {
			this.key = key;
			this.value = value;
			this.prev = null;
			this.next = null;
		}
	}

	private int capacity;
	private Map<Integer, CacheNode> valMap  = new HashMap<>();
	private CacheNode head = new CacheNode(-1, -1);
	private CacheNode tail = new CacheNode(-1, -1);

	public LruCache(int capacity) {
		this.capacity = capacity;
		this.head.next = this.tail;
		this.tail.prev = this.head;
	}

	public int get(int key) {
		if (!valMap.containsKey(key)) {
			return -1;
		}
		CacheNode current = valMap.get(key);
		current.next.prev = current.prev;
		current.prev.next = current.next;
		moveToTail(current);
		return current.value;
	}

	public void put(int key, int value) {
		if (get(key) != -1){
			valMap.get(key).value = value;
			return;
		}
		if (valMap.size() == capacity){
			valMap.remove(head.next.key);
			head.next = head.next.next;
			head.next.prev = head;
		}
		CacheNode insert = new CacheNode(key, value);
		valMap.put(key,insert);
		moveToTail(insert);
	}

	private void moveToTail(CacheNode node){
		node.prev = tail.prev;
		node.prev.next = node;
		tail.prev = node;
		node.next = tail;
	}

	public static void main(String[] args) {
		LruCache cache = new LruCache(3);
		cache.put(3,3);
		cache.put(3,4);
		cache.put(5,5);
		cache.put(6,6);
		cache.put(7,7);
		System.out.println(cache.valMap.values().size());
	}
}
