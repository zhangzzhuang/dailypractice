package com.example.sortagorithm.tree;

import java.util.*;

/**
 * @author zhangzhuang
 * @since 2021-02-25
 */
public class InorderTraversal {


	public List<Integer> inOrderTraversal(TreeNode root) {

		List<Integer> result = new ArrayList<>();
		if (root == null) { return result; }

		Deque<TreeNode> stack = new LinkedList<>();
		TreeNode current = root;
		while (current != null || !stack.isEmpty()) {

			while (current != null) {
				stack.push(current);
				current = current.left;
			}
			current = stack.pop();
			result.add(current.val);
			current = current.right;
		}

		return result;
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
}
