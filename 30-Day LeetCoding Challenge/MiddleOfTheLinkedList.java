/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode middleNode(ListNode head) {
        int count = getCount(head);
        int mid = (count/2)+1;
        return getMiddleNode(head, mid);
    }
    
    public ListNode getMiddleNode(ListNode head, int index) {
        int count = 1;
        if(count == index)
            return head;
        return getMiddleNode(head.next, index -1);
    }
    
    public int getCount(ListNode head) {
        if (head == null)
            return 0;
        return 1 + getCount(head.next);
    }
}