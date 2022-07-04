package cn.lz.base.solution.tree.example;

import cn.lz.base.solution.tree.TreeNode;
import cn.lz.base.solution.tree.util.TreeUtil;

import java.util.*;

public class TraverseTree {

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
        TreeNode root = TreeUtil.createTreeByArray(arr);
        if (Arrays.equals(arr, solution(root)))
            System.out.println(true);
    }

    /**
     * 层序遍历二叉树
     */
    private static Integer[] solution(TreeNode root) {
        if (root == null) {
            return new Integer[0];
        } else {
            Queue<TreeNode> queue = new LinkedList<>();
            List<Integer> res = new ArrayList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                res.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            return res.toArray(new Integer[0]);
        }
    }
}
