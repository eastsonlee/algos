package algos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class TreeTraversal {
    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    /***/
    public List<Integer> inorderRecursion(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    /***/
    public List<Integer> preorderRecursion(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

    private void preorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preorder(root.left, list);
        preorder(root.right, list);
    }

    /***/
    public List<Integer> postorderRecursion(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorder(root, list);
        return list;
    }

    private void postorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postorder(root.left, list);
        postorder(root.right, list);
        list.add(root.val);
    }

    /***/
    public List<Integer> inorderIteration(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }

        while (!stack.isEmpty()) {
            curr = stack.pop();
            list.add(curr.val); // add val
            curr = curr.right;
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
        }
        return list;
    }

    /***/
    public List<Integer> preorderIteration(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (curr != null) {
            list.add(curr.val); // add val
            stack.push(curr);
            curr = curr.left;
        }

        while (!stack.isEmpty()) {
            curr = stack.pop();
            curr = curr.right;
            while (curr != null) {
                list.add(curr.val); // add val
                stack.push(curr);
                curr = curr.left;
            }
        }
        return list;
    }

    /***/
    public List<Integer> postorderIteration(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> stackReverse = new Stack<>();
        TreeNode curr = root;

        while (curr != null) {
            stack.push(curr);
            stackReverse.push(curr);
            curr = curr.right;
        }

        while (!stack.isEmpty()) {
            curr = stack.pop();
            curr = curr.left;
            while (curr != null) {
                stack.push(curr);
                stackReverse.push(curr);
                curr = curr.right;
            }
        }

        // reverse root-right-left
        while (!stackReverse.isEmpty()) {
            list.add(stackReverse.pop().val);
        }
        return list;
    }

    /***/
    public List<Integer> levelorder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr != null) {
                list.add(curr.val);
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
        }

        return list;
    }

    /***/
    public List<List<Integer>> levelorderBottomUp(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) return lists;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            List<Integer> list = new ArrayList<>();
            while (curr != null) {
                list.add(curr.val);
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
                curr = queue.poll();
            }
            if (!queue.isEmpty()) queue.add(null);
            lists.add(list);
        }

        Collections.reverse(lists);
        return lists;
    }

    /***/
    public List<List<Integer>> levelOrderZigzag(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) return lists;
        Stack<TreeNode> currStack = new Stack<>();
        currStack.add(root);
        Boolean oddLevel = false;

        while (!currStack.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            Stack<TreeNode> nextStack = new Stack<>();
            while (!currStack.isEmpty()) {
                TreeNode curr = currStack.pop();
                list.add(curr.val);
                if (oddLevel) {
                    if (curr.right != null) nextStack.add(curr.right);
                    if (curr.left != null) nextStack.add(curr.left);
                } else {
                    if (curr.left != null) nextStack.add(curr.left);
                    if (curr.right != null) nextStack.add(curr.right);
                }
            }
            lists.add(list);
            currStack = nextStack;
            oddLevel = !oddLevel;
        }

        return lists;
    }

    /***/
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        TreeTraversal traversal = new TreeTraversal();
        System.out.println("inorder (recursion): " + traversal.inorderRecursion(root));
        System.out.println("inorder (iteration): " + traversal.inorderIteration(root));
        System.out.println("preorder (recursion): " + traversal.preorderRecursion(root));
        System.out.println("preorder (iteration): " + traversal.preorderIteration(root));
        System.out.println("postorder (recursion): " + traversal.postorderRecursion(root));
        System.out.println("postorder (iteration): " + traversal.postorderIteration(root));
        System.out.println("levelorder: " + traversal.levelorder(root));
        System.out.println("levelorderBottomUp: " + traversal.levelorderBottomUp(root));
        System.out.println("levelorderZigzag: " + traversal.levelOrderZigzag(root));
    }
}
