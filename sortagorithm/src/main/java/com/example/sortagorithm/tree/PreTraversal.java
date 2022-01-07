package com.example.sortagorithm.tree;

import java.util.*;

/**
 * @author zhangzhuang
 * @since 2021-02-25
 */
public class PreTraversal {


	/**
	 *	递归
	 */
	public List<Integer> preTraversal(TreeNode root) {

		List<Integer> result = new ArrayList<>();
		if (root == null){
			return result;
		}

		List<Integer> left = preTraversal(root.left);
		List<Integer> right = preTraversal(root.right);

		result.add(root.val);
		result.addAll(left);
		result.addAll(right);
		return result;

	}

	/**
	 *	非递归
	 */



	public List<Integer> preOrderTraversal(TreeNode root) {

		List<Integer> result = new ArrayList<>();
		if (root == null){ return result; }
		Deque<TreeNode> stack = new LinkedList<>();
		stack.push(root);
		while (!stack.isEmpty()){
			TreeNode node = stack.pop();
			result.add(node.val);
			if (node.right != null){
				stack.push(node.right);
			}
			if (node.left != null){
				stack.push(node.left);
			}
		}
		return  result;

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





