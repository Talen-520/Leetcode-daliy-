// https://leetcode.com/problems/add-two-numbers/discuss/126659/My-Java-Solution

class Solution {
    /**https://github.com/Talen-520/Leetcode-daliy-/blob/main/Add%20Two%20Numbers.java
     * This list node uses for optimize memory usage
     */
    private static final ListNode ZERO_NODE = new ListNode(0);
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return recursiveSum(l1, l2, 0);
    }

    /**
     * Calculate sum of node values recursively
     *
     * @param a     first node
     * @param b     second node
     * @param carry the number from the last addition
     * @return calculated list node
     */
    private ListNode recursiveSum(ListNode a, ListNode b, int carry) {
        int sum = (a.val + b.val) + carry;
        int newCarry = sum / 10;

        ListNode r = new ListNode(sum % 10);
        if (a.next == null && b.next == null) {
            if (newCarry > 0) {
                r.next = new ListNode(newCarry);
            }
        } else {
            r.next = recursiveSum(
                    (a.next == null) ? ZERO_NODE : a.next,
                    (b.next == null) ? ZERO_NODE : b.next, newCarry);
        }

        return r;
    }    
}
 

//solution 2
//https://leetcode.com/problems/add-two-numbers/discuss/1044/Java-concise-solution.
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    int carry = 0;
    ListNode p, head = new ListNode(0);
    p = head;
    while (l1 != null || l2 != null || carry != 0) {
        if (l1 != null) {
            carry += l1.val;
            l1 = l1.next;
        }
        if (l2 != null) {
            carry += l2.val;
            l2 = l2.next;
        }
        p.next = new ListNode(carry%10);
        carry /= 10;
        p = p.next;
    }
    return head.next;
}
