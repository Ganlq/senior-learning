package learning.algorithm.leetcode.LinkedList;


/**
 * 206. 反转链表  easy
 *
 *
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class ReverseList {


    /**
     * 原地逆序
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head){
        ListNode newHead = null;
        while (head!=null){
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }


    /**
     * 头插法
        设置一个临时的头结点tempHead，利用head指针遍历链表，每遍历一个节点就将该节点插入到tempHead后
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head){
        ListNode tempHead = new ListNode(0);
        while (head!=null){
            ListNode next = head.next;
            head.next = tempHead.next;
            tempHead.next = head;
            head = next;
        }
        return tempHead.next;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }


}


