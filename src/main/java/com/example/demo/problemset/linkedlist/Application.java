package com.example.demo.problemset.linkedlist;

import com.example.demo.problemset.tree.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.LockSupport;

public class Application {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(4);
        listNode.next.next = new ListNode(5);


        ListNode listNode2 = new ListNode(1);
        listNode2.next = new ListNode(3);
        listNode2.next.next = new ListNode(4);


        ListNode listNode3 = new ListNode(2);
        listNode3.next = new ListNode(6);
        listNode3.next.next = new ListNode(30);

        ListNode listNode4 = new ListNode(2);
        listNode4.next = new ListNode(3);
        listNode4.next.next = new ListNode(4);
        listNode4.next.next.next = new ListNode(6);
        listNode4.next.next.next.next = new ListNode(9);
        listNode4.next.next.next.next.next = new ListNode(14);
        listNode4.next.next.next.next.next.next = new ListNode(18);
        listNode4.next.next.next.next.next.next.next = new ListNode(19);
        listNode4.next.next.next.next.next.next.next.next = new ListNode(22);

        ListNode[] lists = {listNode, listNode2, listNode3, listNode4};

        Application application = new Application();
        application.mergeKLists(lists);


    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode parent = dummy;
        //移动空链表到数组末尾
        int size = lists.length - 1;
        int left = 0;
        int right = size;
        while (left < right) {
            while (left < lists.length && lists[left] != null) {
                left++;
            }

            while (right >= 0 && lists[right] == null) {
                right--;
            }

            if (left < right) {
                lists[left] = lists[right];
                lists[right] = null;
                size--;
            }
        }

        size = right;

        //构建小顶堆
        while (size >= 0) {
            up(lists, size);
            parent.next = lists[0];
            parent = parent.next;

            if (lists[0] != null) {
                lists[0] = lists[0].next;
                parent.next = null;
            }

            if (lists[0] == null) {
                lists[0] = lists[size];
                lists[size] = null;
                size--;
            }
        }

        return dummy.next;
    }

    /**
     * 上浮头部元素小的链表
     *
     * @param lists
     * @param size
     */
    private void up(ListNode[] lists, int size) {
        int lastParentNode = size / 2;
        for (int i = lastParentNode; i >= 0; i--) {
            int minChildIndex = i * 2 + 1;
            if (size >= minChildIndex + 1 && lists[minChildIndex + 1].val < lists[minChildIndex].val) {
                minChildIndex++;
            }

            if (size >= minChildIndex && lists[minChildIndex].val < lists[i].val) {
                ListNode temp = lists[minChildIndex];
                lists[minChildIndex] = lists[i];
                lists[i] = temp;
            }
        }
    }

    public static ListNode sortList(ListNode head) {
        return sortListCore(head, null);
    }

    static ListNode sortListCore(ListNode head, ListNode tail) {
        if (head != tail) {
            ListNode mid = getMidNode(head, tail);

            ListNode left = sortListCore(head, mid);
            ListNode right = sortListCore(mid.next, tail);

            return mergeListNode(left, right, tail == null ? null : tail.next);
        }

        return head;
    }

    static ListNode mergeListNode(ListNode left, ListNode right, ListNode tail) {
        ListNode res = new ListNode(0);
        ListNode dummy = res;
        ListNode rightCopy = right;
        while (left != null && right != null && left != rightCopy && right != tail) {
            if (left.val > right.val) {
                res.next = right;
                right = right.next;
            } else {
                res.next = left;
                left = left.next;
            }

            res = res.next;
        }

        while (right != null && right != tail) {
            res.next = right;
            right = right.next;
            res = res.next;
        }

        while (left != null && left != rightCopy) {
            res.next = left;
            left = left.next;
            res = res.next;
        }

        return dummy.next;
    }

    public static ListNode getMidNode(ListNode head, ListNode tail) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode res = slow.next;
        slow.next = null;
        return res;
    }

    public static Node flatten(Node head) {
        if (head == null) {
            return null;
        }

        recursion(head);
        return head;
    }

    private static Node recursion(Node head) {
        Node headCopy = head;

        while (headCopy.next != null && headCopy.child == null) {
            headCopy = headCopy.next;
        }

        if (headCopy.next == null && headCopy.child == null) {
            return headCopy;
        }

        Node next = headCopy.next;
        headCopy.next = headCopy.child;
        headCopy.next.prev = headCopy;
        headCopy.child = null;
        headCopy = recursion(headCopy.next);
        headCopy.next = next;
        next.prev = headCopy;

        while (headCopy.next != null) {
            headCopy = headCopy.next;
        }

        return headCopy;
    }

    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        ListNode oddNode = head;
        ListNode evenNode = head.next;
        ListNode evenNodeHead = evenNode;
        while (evenNode != null && evenNode.next != null) {
            oddNode.next = oddNode.next.next;
            evenNode.next = evenNode.next.next;

            oddNode = oddNode.next;
            evenNode = evenNode.next;
        }

        //join
        oddNode.next = evenNodeHead;

        return head;
    }

    public static ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] result = new ListNode[k];
        if (root == null || k == 0) {
            return result;
        }

        if (k == 1) {
            result[0] = root;
            return result;
        }

        for (int i = 0; i < k; i++) {
            result[i] = root;
        }

        int n = 0;
        ListNode head = root;
        while (head != null) {
            n++;
            head = head.next;
        }

        head = root;
        int nodeCount;
        int countCopy = n;
        ListNode temp;
        for (int i = 0; i < k; i++) {
            nodeCount = (int) Math.ceil(n / (k - i * 1.0));
            n -= nodeCount;
            result[i] = head;
            while (nodeCount > 1 && head != null) {
                nodeCount--;
                head = head.next;
            }

            if (head == null) {
                break;
            }

            temp = head.next;
            head.next = null;
            head = temp;
        }

        return result;
    }

    public static void reorderList(ListNode head) {
        if (head == null) {
            return;
        }

        //找到中间节点
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        //反转后面的节点
        ListNode list2 = null;
        ListNode temp;
        ListNode breakNode = slow;
        while (slow != null) {
            temp = slow.next;
            slow.next = list2;
            list2 = slow;
            slow = temp;
        }

        //重新组装前后两段节点
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        ListNode list1 = head;

        while (head != breakNode) {
            prev.next = head;
            head = head.next;
            prev = prev.next;

            prev.next = list2;
            list2 = list2.next;
            prev = prev.next;
        }

        prev.next = list2;
        head = dummy.next;
    }

    /**
     * [[7,null],[13,0],[11,4],[10,2],[1,0]]
     *
     * @param head
     * @return
     */
    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Node headCopy = head;
        while (headCopy != null) {
            Node newNode = new Node(headCopy.val);
            newNode.next = headCopy.next;
            headCopy.next = newNode;

            headCopy = newNode.next;
        }

        headCopy = head;
        while (headCopy != null) {
            headCopy.next.random = headCopy.random == null ? null : headCopy.random.next;

            headCopy = headCopy.next.next;
        }

        Node oldList = head;
        Node newList = head.next;
        Node newHead = head.next;
        while (oldList != null) {
            oldList.next = oldList.next.next;
            newList.next = newList.next == null ? null : newList.next.next;
            newList = newList.next;
            oldList = oldList.next;
        }

        return newList;
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        TreeNode root = new TreeNode(head.val);
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.add(root);

        while (linkedList.size() > 0) {
            TreeNode parent = linkedList.pop();
            head = head.next;
            if (head == null) {
                break;
            }
            parent.left = new TreeNode(head.val);
            head = head.next;
            if (head == null) {
                break;
            }
            parent.right = new TreeNode(head.val);

            linkedList.add(parent.left);
            linkedList.add(parent.right);
        }

        return root;
    }

    public static ListNode partition(ListNode head, int x) {
        ListNode smallDummy = new ListNode(0);
        ListNode smallPrev = smallDummy;
        ListNode bigDummy = new ListNode(0);
        ListNode bigPrev = bigDummy;
        while (head != null) {
            if (head.val < x) {
                smallPrev.next = head;
                smallPrev = head;
            } else {
                bigPrev.next = head;
                bigPrev = head;
            }
            head = head.next;
        }

        bigPrev.next = null;
        smallPrev.next = bigDummy.next;
        return smallDummy.next;
    }

    public static ListNode rotateRight(ListNode head, int k) {
        ListNode node = head;
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }

        while (k > count) {
            k -= count;
        }

        ListNode fast = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        ListNode slow = head;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        fast.next = head;
        ListNode newHead = slow.next;
        slow.next = null;
        return newHead;
    }

    /**
     * 给定 1->2->3->4, 你应该返回 2->1->4->3.
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode t = null;
        ListNode result = head.next;
        ListNode prev = new ListNode(0);
        prev.next = head;
        while (head != null && head.next != null) {
            t = head.next;
            prev.next = t;
            head.next = t.next;
            t.next = head;
            prev = head;
            head = head.next;
        }

        return result;
    }

    /**
     * [1,2]
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = new ListNode(0);
        prev.next = slow;
        for (int i = n; i > 0; i--) {
            fast = fast.next;
        }

        while (fast != null & fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        if (slow == head) {
            return prev.next.next;
        } else {
            slow.next = slow.next.next;
        }

        return prev.next;
    }
}
