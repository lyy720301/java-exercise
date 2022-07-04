package cn.lz.base.solution.tree.example;

import cn.lz.base.solution.tree.TreeNode;
import cn.lz.base.solution.tree.util.TreeUtil;

/**
 * [1,2,2,3,4,4,3] true
 * <p>
 * [1,2,2,null,3,null,3] false
 * 对称二叉树
 */
public class SymmetricTree {

    public static void main(String[] args) {
        System.out.println(isSymmetric(TreeUtil.createTreeByString("[1,2,2,null,3,null,3]")));
        System.out.println(isSymmetric(TreeUtil.createTreeByString("[1,2,2,3,4,4,3]")));
    }

    public static boolean isSymmetric(TreeNode node) {
        if (node == null) {
            return true;
        }
        return check(node.left, node.right);
    }

    private static boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }

        return check(left.right, right.left) && check(left.left, right.right);
    }
}
