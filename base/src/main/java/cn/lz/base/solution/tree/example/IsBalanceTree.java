package cn.lz.base.solution.tree.example;

import cn.lz.base.solution.tree.TreeNode;
import cn.lz.base.solution.tree.util.TreeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 判断是否是二叉搜索树
 */
public class IsBalanceTree {

    public static void main(String[] args) {
        // false
        TreeNode falseTree = TreeUtil.createTreeByArray(new Integer[]{5, 2, 6, 1, 9});
        System.out.println(isBalance(falseTree));

        pre = null;
        TreeNode trueTree = TreeUtil.createTreeByArray(new Integer[]{5, 2, 6, 1, 3});
        System.out.println(isBalance(trueTree));
    }

    // 这里pre需要是全局变量，只有这样才能保证pre被访问的顺序
    static Integer pre = null;

    /**
     * 使用递归的方式
     * 力扣上一种不错的解法
     */
    public static boolean isBalance(TreeNode node) {
        if (node == null) {
            return true;
        }
        if (!isBalance(node.left)) {
            return false;
        }
        if (pre != null && node.val < pre) {
            return false;
        }
        pre = node.val;
        return isBalance(node.right);
    }

    private static boolean isBalance2(TreeNode node) {
        List<Integer> res = new ArrayList<>();
        iterator(node, res);
        final Integer[] max = {null};
        return res.stream().noneMatch(i -> {
            if (max[0] == null) {
                max[0] = i;
                return false;
            } else {
                if (i < max[0]) {
                    return true;
                } else {
                    max[0] = i;
                    return false;
                }
            }
        });

    }

    /**
     * 中序遍历
     */
    private static Integer[] iterator(TreeNode node, List<Integer> res) {
        if (node == null) {
            return res.toArray(new Integer[0]);
        }
        iterator(node.left, res);
        res.add(node.val);
        iterator(node.right, res);
        return res.toArray(new Integer[0]);
    }


}
