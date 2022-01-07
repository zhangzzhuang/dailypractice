package com.example.sortagorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangzhuang
 * @since 2021-02-25
 */
public class TreeSerialize {

	//[1,2,3,null,null,4,5]

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {



		if (root == null) {
			return "{}";
		}

		List<TreeNode> list = new ArrayList<>();
		list.add(root);
		for (int i = 0; i < list.size(); i++) {
			TreeNode node = list.get(i);
			if (node == null) {
				continue;
			}
			list.add(node.left);
			list.add(node.right);
		}

		while (list.get(list.size() - 1) == null) {
			list.remove(list.size() - 1);
		}
		StringBuilder sb = new StringBuilder("{");
		sb.append(list.get(0).val);
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i) == null) {
				sb.append(",#");
			} else {
				sb.append("," + list.get(i).val);
			}
		}
		sb.append("}");
		return sb.toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if (data == null || data.equals("{}")) {
			return null;
		}
		String[] d = data.substring(1, data.length() - 1).split(",");
		boolean isLeft = true;

		List<TreeNode> queue = new ArrayList<>();
		TreeNode node = new TreeNode(Integer.parseInt(d[0]));
		queue.add(node);

		int index = 0;

		for (int i = 1; i < d.length; i++) {
			if (!d[i].equals("#")) {
				TreeNode treeNode = new TreeNode(Integer.parseInt(d[i]));
				if (isLeft) {
					queue.get(index).left = treeNode;
				} else {
					queue.get(index).right = treeNode;
				}
				queue.add(treeNode);
			}
			if (!isLeft) {
				index++;
			}
			isLeft = !isLeft;
		}
		return queue.get(0);
	}


	 class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

}
