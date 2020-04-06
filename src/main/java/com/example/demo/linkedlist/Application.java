package com.example.demo.linkedlist;

import com.example.demo.tree.TreeNode;

import java.util.LinkedList;

public class Application {
    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(0);
        linkedList.addAtIndex(1, 4);
        linkedList.addAtTail(8);
        linkedList.addAtHead(5);
        linkedList.addAtIndex(4, 3);
        linkedList.addAtTail(0);
        linkedList.addAtTail(5);
        linkedList.addAtIndex(6, 3);
        linkedList.deleteAtIndex(7);
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
