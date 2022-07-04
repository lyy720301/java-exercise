package cn.lz.base.solution.tree.util;

import cn.lz.base.solution.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class TreeUtil {

    public static void main(String[] args) {
        TreeNode treeByArray = createTreeByArray(new Integer[]{1, null, 2, 3, 4});
        assert treeByArray.val == 1;
        assert treeByArray.left == null;
        assert treeByArray.right != null && treeByArray.right.val == 2;
        assert treeByArray.right.left != null && treeByArray.right.left.val == 3;
        assert treeByArray.right.right != null && treeByArray.right.right.val == 4;
    }

    /**
     * @param arr 层序遍历得到的数组
     * @return 重建的二叉树
     */
    public static TreeNode createTreeByArray(Integer[] arr) {
        if (arr == null) {
            return null;
        } else {
            Queue<TreeNode> queue = new LinkedList<>();
            TreeNode root;
            if (arr[0] != null) {
                root = new TreeNode(arr[0]);
                queue.offer(root);
            } else {
                return null;
            }
            int index = 1;
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (index < arr.length) {
                    node.left = arr[index] == null ? null : new TreeNode(arr[index]);
                    index++;
                    if (node.left != null)
                        queue.offer(node.left);
                }
                if (index < arr.length) {
                    node.right = arr[index] == null ? null : new TreeNode(arr[index]);
                    index++;
                    if (node.right != null)
                        queue.offer(node.right);
                }
            }
            return root;
        }
    }
}
