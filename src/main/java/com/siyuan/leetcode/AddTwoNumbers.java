package com.siyuan.leetcode;

public class AddTwoNumbers {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode = null;
        boolean next = false;
        while (l1.next != null && l2.next != null){
            int temp1 = l1.val;
            int temp2 = l2.val;
            int add ;
            if (next){
                add= temp1 + temp2 + 1 >= 10? (temp1+temp2 +1 )-10 : temp1 + temp2 + 1;
            }else{
                add= temp1 + temp2>= 10? (temp1+temp2)-10 : temp1 + temp2;
            }

            if (temp1 + temp2 >=10){
                next = true;
            }


            if (listNode == null){
                listNode = new ListNode(add);
            }else{
                listNode.next = new ListNode(add);
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null){
            listNode.next = l1.next;
            l1 = l1.next;
        }

        while (l2 != null){
            listNode.next = l2.next;
            l2 = l2.next;
        }


        return listNode;
    }




}


class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
}