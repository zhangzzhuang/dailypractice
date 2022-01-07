package com.example.diythread.PriorityQueueDemo;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @author zhangzhuang
 * @since 2021-04-06
 */
public class Customer implements Externalizable {

	private int id;

	private String name;

	public Customer(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
			out.writeUTF(name);
			out.writeInt(id);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
			this.id = in.readInt();
			this.name = in.readUTF();
	}
}
