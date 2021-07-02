package com.example.demo.problemset.tree;

import com.example.demo.problemset.linkedlist.ListNode;

import java.util.*;

public class Application {
    private int intAns;

    public static void main(String[] args) {
        //[-10,-3,0,5,9]
        getAllElements(null, null);
    }

    /**
     * 1302. 层数最深叶子节点的和
     *
     * @param root
     * @return
     */
    public int deepestLeavesSum(TreeNode root) {
        deepestLeavesSum_dst(root, 0);
        return total;
    }

    int depth = 0;

    private void deepestLeavesSum_dst(TreeNode node, int d) {
        if (node == null) {
            return;
        }

        deepestLeavesSum_dst(node.left, d + 1);

        if (d > depth) {
            depth = d;
            total = 0;
        }

        if (d == depth) {
            total += node.val;
        }

        deepestLeavesSum_dst(node.right, d + 1);
    }

    /**
     * 1305. 两棵二叉搜索树中的所有元素
     *
     * @param root1
     * @param root2
     * @return
     */
    public static List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        //inorder(root1, list1);
        //inorder(root2, list2);
        list1.add(1);
        list1.add(2);
        list1.add(4);
        list2.add(0);
        list2.add(1);
        list2.add(3);
        int i = 0;
        int j = 0;
        while (i < list1.size() || j < list2.size()) {
            if (i < list1.size() && j < list2.size()) {
                if (list1.get(i) < list2.get(j)) {
                    ans.add(list1.get(i));
                    i++;
                } else {
                    ans.add(list2.get(j));
                    j++;
                }
            } else if (i < list1.size()) {
                ans.add(list1.get(i));
                i++;
            } else {
                ans.add(list2.get(j));
                j++;
            }
        }

        return ans;
    }

    private static void inorder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }

    /**
     * 109. 有序链表转换二叉搜索树
     *
     * @param head
     * @return
     */
    public static TreeNode sortedListToBST(ListNode head) {
        return sortedListToBSTHelper(head, null);
    }

    private static TreeNode sortedListToBSTHelper(ListNode head, ListNode tail) {
        if (head == tail) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }

        TreeNode node = new TreeNode(slow.val);
        if (slow != fast) {
            node.left = sortedListToBSTHelper(head, slow);
            node.right = sortedListToBSTHelper(slow.next, tail);
        }

        return node;
    }


    public static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        flatten(root.right);
        flatten(root.left);

        root.right = pre;
        root.left = null;
        pre = root;
    }

    private static TreeNode getRightChild(TreeNode node) {
        while (node.right != null) {
            node = node.right;
        }

        return node;
    }

    public TreeNode buildTree_post(int[] inorder, int[] postorder) {
        rootIndex = inorder.length - 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return buildTree_postCore(inorder, postorder, 0, inorder.length - 1, map);
    }

    private TreeNode buildTree_postCore(int[] inorder, int[] postorder, int left, int right, HashMap<Integer, Integer> map) {
        if (rootIndex < 0 || left > right) {
            return null;
        }

        int rootVal = postorder[rootIndex];
        int rootInorderIndex = map.get(rootVal);
        if (rootInorderIndex < left || rootInorderIndex > right) {
            return null;
        }

        rootIndex--;
        TreeNode treeNode = new TreeNode(rootVal);
        treeNode.right = buildTree_postCore(inorder, postorder, rootInorderIndex + 1, right, map);
        treeNode.left = buildTree_postCore(inorder, postorder, left, rootInorderIndex - 1, map);

        return treeNode;
    }

    static int rootIndex = 0;

    public static TreeNode buildTree_pre(int[] preorder, int[] inorder) {
        return buildTreeCore(preorder, inorder, 0, inorder.length - 1);
    }

    private static TreeNode buildTreeCore(int[] preorder, int[] inorder, int left, int right) {
        if (rootIndex >= preorder.length || left > right) {
            return null;
        }

        int rootVal = preorder[rootIndex];
        int rootInorderIndex = -1;
        for (int i = left; i <= right; i++) {
            if (inorder[i] == rootVal) {
                rootInorderIndex = i;
                break;
            }
        }

        if (rootInorderIndex == -1) {
            return null;
        }

        rootIndex++;
        TreeNode treeNode = new TreeNode(rootVal);
        treeNode.left = buildTreeCore(preorder, inorder, left, rootInorderIndex - 1);
        treeNode.right = buildTreeCore(preorder, inorder, rootInorderIndex + 1, right);

        return treeNode;
    }


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }

        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        boolean direct = true;
        while (!nodes.isEmpty()) {
            LinkedList<TreeNode> nextLevelNodes = new LinkedList<>();
            for (TreeNode node : nodes) {
                if (node.left != null) {
                    nextLevelNodes.add(node.left);
                }

                if (node.right != null) {
                    nextLevelNodes.add(node.right);
                }
            }

            ArrayList<Integer> list = new ArrayList<>();
            TreeNode node;
            while (!nodes.isEmpty()) {
                node = direct ? nodes.pollFirst() : nodes.pollLast();
                list.add(node.val);
            }

            ans.add(list);
            direct = !direct;
            nodes = nextLevelNodes;
        }

        return ans;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }

        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.add(root);
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.peek();
            if (node.left != null) {
                nodeStack.add(node.left);
            }

            if (node.left == null) {
                nodeStack.pop();
                list.add(node.val);
            }

            node.left = null;
            if (node.right != null) {
                nodeStack.add(node.right);
            }

            node.right = null;
        }

        return list;
    }

    public int sumRootToLeaf(TreeNode root) {
        sumRootToLeafCore(root, "");
        return intAns;
    }

    private void sumRootToLeafCore(TreeNode root, String n) {
        if (root == null) {
            return;
        }


        String s = n + String.valueOf(root.val);
        if (root.left == null && root.right == null) {
            intAns += Integer.parseInt(s, 2);
            return;
        }

        sumRootToLeafCore(root.left, s);
        sumRootToLeafCore(root.right, s);
    }

    HashMap<Integer, Integer> depths = new HashMap<>();
    HashMap<Integer, TreeNode> parents = new HashMap<>();

    public boolean isCousins(TreeNode root, int x, int y) {
        isCousinsCore(root, null);

        if (!depths.containsKey(x) || !depths.containsKey(y)) {
            return false;
        }

        return parents.get(x) != parents.get(y) && depths.get(x).intValue() == depths.get(y).intValue();
    }

    private void isCousinsCore(TreeNode root, TreeNode parent) {
        if (root == null) {
            return;
        }

        depths.put(root.val, parent == null ? 0 : depths.get(parent.val) + 1);
        parents.put(root.val, parent);
        isCousinsCore(root.left, root);
        isCousinsCore(root.right, root);
    }

    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean l = isUnivalTree(root.left);
        if (pre != null && pre.val != root.val) {
            return false;
        }

        pre = root;
        boolean r = isUnivalTree(root.right);
        return l && r;
    }

    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }

        if (root.val > R) {
            return rangeSumBST(root.left, L, R);
        } else if (root.val < L) {
            return rangeSumBST(root.right, L, R);
        }

        rangeSumBSTCore(root, L, R);

        return intAns;
    }

    private void rangeSumBSTCore(TreeNode root, int L, int R) {
        if (root == null) {
            return;
        }

        rangeSumBSTCore(root.left, L, R);

        if (root.val >= L && root.val <= R) {
            intAns += root.val;
        }

        rangeSumBSTCore(root.right, L, R);
    }

    TreeNode newRoot = new TreeNode(0);

    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummy = newRoot;
        increasingBSTCore(root);
        return dummy.right;
    }

    private void increasingBSTCore(TreeNode root) {
        if (root == null) {
            return;
        }

        increasingBSTCore(root.left);
        newRoot.right = root;
        root.left = null;
        newRoot = newRoot.right;
        increasingBSTCore(root.right);
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> root1Leaf = new ArrayList<>();
        getLeafsVal(root1, root1Leaf);
        List<Integer> root2Leaf = new ArrayList<>();
        getLeafsVal(root2, root2Leaf);

        Vector<Integer> vector = new Vector<>();
        Vector<Integer> vector2 = new Vector<>();
        if (vector == vector2) {

        }

        if (root1Leaf.size() != root2Leaf.size()) {
            return false;
        }

        for (int i = 0; i < root1Leaf.size(); i++) {
            if (root1Leaf.get(i) != root2Leaf.get(i)) {
                return false;
            }
        }

        return true;
    }

    private void getLeafsVal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            list.add(root.val);
            return;
        }

        getLeafsVal(root.left, list);

        getLeafsVal(root.right, list);
    }

    public int minDiffInBST(TreeNode root) {
        intAns = 101;
        minDiffInBSTCore(root);
        return intAns;
    }


    private void minDiffInBSTCore(TreeNode root) {
        if (root == null) {
            return;
        }

        minDiffInBST(root.left);
        if (pre != null) {
            intAns = Math.min(intAns, root.val - pre.val);
        }

        pre = root;
        minDiffInBST(root.right);
    }

    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        postorderCore(root, list);
        return list;
    }

    public void postorderCore(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }

        for (int i = 0; i < root.children.size(); i++) {
            postorderCore(root.children.get(i), list);
        }

        list.add(root.val);
    }

    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        preorderCore(root, list);
        return list;
    }

    public void preorderCore(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }

        list.add(root.val);
        if (root.children != null) {
            for (Node node : root.children) {
                preorderCore(node, list);
            }
        }
    }

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }

        int depth = 1;
        int max = 0;
        if (root.children != null) {
            for (Node node : root.children) {
                max = Math.max(maxDepth(node), max);
            }
        }

        return depth + max;
    }

    public int longestUnivaluePath(TreeNode root) {
        longestUnivaluePathCore(root);
        return intAns;
    }

    public int longestUnivaluePathCore(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = longestUnivaluePathCore(root.left);
        int right = longestUnivaluePathCore(root.right);
        int arrowLeft = 0;
        int arrowRight = 0;
        if (root.left != null && root.left.val == root.val) {
            arrowLeft += left + 1;
        }

        if (root.right != null && root.right.val == root.val) {
            arrowRight += right + 1;
        }

        intAns = Math.max(intAns, arrowLeft + arrowRight);
        return Math.max(arrowLeft, arrowRight);
    }

    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) {
            return -1;
        }

        List<Integer> list = new ArrayList<>();
        findSecondMinimumValueCore(root, root.val, list);

        if (list.size() == 0) {
            return -1;
        }

        Collections.sort(list);
        return list.get(0);
    }

    private void findSecondMinimumValueCore(TreeNode node, int rootVal, List<Integer> list) {
        if (node == null) {
            return;
        }

        if (node.val > rootVal) {
            list.add(node.val);
            return;
        }

        findSecondMinimumValueCore(node.left, rootVal, list);
        findSecondMinimumValueCore(node.right, rootVal, list);
    }

    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }

        if (root.val > R) {
            return trimBST(root.left, L, R);
        } else if (root.val < L) {
            return trimBST(root.right, L, R);
        }

        trimBSTCore(root, L, R);
        return root;
    }

    private void trimBSTCore(TreeNode node, int L, int R) {
        if (node == null) {
            return;
        }

        if (node.left != null && node.left.val < L) {
            node.left = node.left.right;
            trimBSTCore(node, L, R);
        } else {
            trimBSTCore(node.left, L, R);
        }

        if (node.right != null && node.right.val > R) {
            node.right = node.right.left;
            trimBSTCore(node, L, R);
        } else {
            trimBSTCore(node.right, L, R);
        }
    }

    public boolean findTarget(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>();
        return findTargetCore(root, k, set);
    }

    private boolean findTargetCore(TreeNode node, int k, HashSet<Integer> set) {
        if (node == null) {
            return false;
        }

        if (set.contains(k - node.val)) {
            return true;
        }

        set.add(node.val);
        return findTargetCore(node.left, k, set) || findTargetCore(node.right, k, set);
    }


    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> doubleAns = new ArrayList<>();
        List<Long> sums = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();
        averageOfLevelsCore(root, 0, sums, counts);
        for (int i = 0; i < sums.size(); i++) {
            doubleAns.add(sums.get(i) * 1.0 / counts.get(i));
        }

        return doubleAns;
    }

    private void averageOfLevelsCore(TreeNode node, int level, List<Long> sums, List<Integer> counts) {
        if (node == null) {
            return;
        }

        if (sums.size() <= level) {
            sums.add((long) node.val);
        } else {
            sums.set(level, sums.get(level) + node.val);
        }

        if (counts.size() <= level) {
            counts.add(1);
        } else {
            counts.set(level, counts.get(level) + 1);
        }

        averageOfLevelsCore(node.left, level + 1, sums, counts);
        averageOfLevelsCore(node.right, level + 1, sums, counts);
    }

    StringBuilder ans;

    public String tree2str(TreeNode t) {
        ans = new StringBuilder();
        tree2strCore(t);
        if (ans.length() > 0) {
            return ans.substring(1, ans.length() - 1);
        }

        return "";
    }


    public void tree2strCore(TreeNode t) {
        if (t == null) {
            ans.append("()");
            return;
        }

        ans.append("(");
        ans.append(t.val);
        if (t.left != null || t.right != null) {
            tree2strCore(t.left);
        }

        if (t.right != null) {
            tree2strCore(t.right);
        }

        ans.append(")");
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        return isSubtreeCore(s, t, t);
    }

    public boolean isSubtreeCore(TreeNode s, TreeNode t, TreeNode tCopy) {
        if (s == null && t == null) {
            return true;
        }

        if (s == null || t == null) {
            return false;
        }

        return (s.val == t.val && isSubtreeCore(s.left, t.left, tCopy) && isSubtreeCore(s.right, t.right, tCopy))
                || (t == tCopy ? (isSubtreeCore(s.left, tCopy, tCopy) || isSubtreeCore(s.right, tCopy, tCopy)) : isSubtreeCore(s, tCopy, tCopy));
    }

    public int findTilt(TreeNode root) {

        getAllNodeSum(root);
        return intAns;
    }

    private int getAllNodeSum(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int l = getAllNodeSum(node.left);
        int r = getAllNodeSum(node.right);

        intAns += Math.abs(l - r);
        return l + r + node.val;
    }

    static LinkedList<Integer> list = new LinkedList<>();

    public static int[] findMode(TreeNode root) {
        if (root != null) {
            findModeCore(root, 0);
        }

        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    static int max = 1;
    static TreeNode pre;

    private static int findModeCore(TreeNode root, int times) {
        if (root.left != null) {
            times = findModeCore(root.left, times);
        }

        if (pre != null && pre.val == root.val) {
            times++;
        } else {
            times = 1;
        }

        pre = root;
        if (times >= max) {
            if (times > max) {
                list.clear();
            }

            if (list.size() == 0 || list.peekLast() != root.val) {
                list.add(root.val);
            }
        }

        max = Math.max(times, max);

        if (root.right != null) {
            times = findModeCore(root.right, times);
        }

        return times;
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }

        if (t2 == null) {
            return t1;
        }

        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);

        return t1;
    }

    public static TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }

        convertBST(root.right);
        root.val += total;
        total = root.val;
        convertBST(root.left);

        return root;
    }

    private static int total = 0;

    private static TreeNode convertBSTCore(TreeNode root) {
        if (root == null) {
            return null;
        }

        convertBST(root.right);
        root.val += total;
        total = root.val;
        convertBST(root.left);

        return root.left == null ? root : root.left;
    }

    public static int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        return getPathSum(root, sum)
                + pathSum(root.left, sum)
                + pathSum(root.right, sum);
    }

    private static int getPathSum(TreeNode node, int sum) {
        if (node == null) {
            return 0;
        }

        return (sum == node.val ? 1 : 0) + getPathSum(node.left, sum - node.val) + getPathSum(node.right, sum - node.val);
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return Math.abs(getMaxDepth(root.left) - getMaxDepth(root.right)) < 2
                && isBalanced(root.left) && isBalanced(root.right);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        intAns = 1;
        getDiameter(root);
        return intAns - 1;
    }

    private int getDiameter(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int L = getDiameter(node.left);
        int R = getDiameter(node.right);
        intAns = Math.max(intAns, L + R + 1);
        return Math.max(L, R) + 1;
    }

    private static int getMaxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return 1 + Math.max(getMaxDepth(node.left), getMaxDepth(node.right));
    }

    public int getMinimumDifference(TreeNode root) {
        return getMinimumDifferenceCore(root, null, Integer.MAX_VALUE);
    }

    private int getMinimumDifferenceCore(TreeNode root, TreeNode pre, int min) {
        if (root == null) {
            return min;
        }

        min = Math.min(getMinimumDifferenceCore(root.left, pre, min), min);
        if (pre != null) {
            min = Math.min(Math.abs(root.val - pre.val), min);
        }

        pre = root;
        min = Math.min(getMinimumDifferenceCore(root.right, pre, min), min);

        return min;
    }

    private int leftSum = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return leftSum;
        }


        if (root.left != null && root.left.left == null && root.left.right == null) {
            leftSum += root.left.val;
        }

        sumOfLeftLeaves(root.left);
        sumOfLeftLeaves(root.right);

        return leftSum;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root.val > p.val && q.val > root.val) {
            return root;
        }

        if (root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }

        if (p.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        return null;
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        getTreePaths(result, root, "");
        return result;
    }

    private void getTreePaths(List<String> result, TreeNode node, String path) {
        if (node == null) {
            return;
        }

        path += String.valueOf(node.val);
        if (node.left == null && node.right == null) {
            result.add(path);
            return;
        }

        path += "->";
        getTreePaths(result, node.left, path);
        getTreePaths(result, node.right, path);
    }

    public static void test(StringBuilder sb) {
        sb.append(11);
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return root.val == sum;
        }

        if (root.left != null) {
            root.left.val += root.val;
        }

        if (root.right != null) {
            root.right.val += root.val;
        }

        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }

    private static TreeNode sortedArrayToBSTHelper(int[] nums, int min, int max) {
        if (min > max) {
            return null;
        }

        int midIndex = (min + max) / 2;
        TreeNode treeNode = new TreeNode(nums[midIndex]);
        treeNode.left = sortedArrayToBSTHelper(nums, min, midIndex - 1);
        treeNode.right = sortedArrayToBSTHelper(nums, midIndex + 1, max);

        return treeNode;
    }
}
