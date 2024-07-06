import java.util.HashSet;
import java.util.Hashtable;
import java.util.Stack;

// problems => 105
// solutions => 219
//hints page 664
public class LinkedList {
    Node head;

    public LinkedList() {

    }

    public void addNodeAtStart(int value) {
        Node newNode = new Node(value);
        if (head == null)
            this.head = newNode;
        else {
            newNode.next = head;
            head = newNode;
        }


    }

    public void addNodeAtEnd(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            return;
        }
        Node traverseNode = head;
        while (traverseNode.next != null) {
            traverseNode = traverseNode.next;
        }
        traverseNode.next = newNode;
    }

    public void printLinkedList() {
        Node traverseNode = head;
        while (traverseNode != null) {
            System.out.print(traverseNode.value + " ");
            traverseNode = traverseNode.next;
        }
    }

    //O(N) O(N)
    public void removeDupsWithBuffer(LinkedList linkedList) {
        if (linkedList.head == null)
            return;
        HashSet<Integer> set = new HashSet<>();
        Node traverseNode = head;
        Node prevNode = null;
        while (traverseNode != null) {
            if (set.contains(traverseNode.value)) {
                prevNode.next = prevNode.next.next;
                prevNode = traverseNode;
            } else {
                set.add(traverseNode.value);
                prevNode = traverseNode;
            }
            traverseNode = traverseNode.next;
        }
    }

    //O(N^2) O(1)
    public void removeDupsWithoutBuffer(LinkedList linkedList) {
        if (linkedList.head == null)
            return;
        Node i = head;
        Node j;
        Node prev;
        while (i != null) {
            prev = i;
            j = i.next;
            while (j != null) {
                if (i.value == j.value) {
                    prev.next = prev.next.next;
                    prev = j;
                }
                prev = j;
                j = j.next;
            }
            i = i.next;
        }
    }

    // Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.
    //Hints:#8, #25, #41, #67, #126
    //O(N) O(1)
    public void deleteKthToLast(LinkedList linkedList, int index) {
        //assume given index 0 delete last node
        //assume given index 1 delete the node right before last Node

        //fast pointer is k nodes ahead of slow pointer
        Node fastPointer = head;
        Node slowPointer = head;
        while (index > 0) {
            if (fastPointer == null) {
                System.out.print("linked list too short for this");
                return;
            }
            fastPointer = fastPointer.next;
            index--;
        }
        //if we'll delete the first node
        if (fastPointer.next == null) {
            head = head.next;
            return;
        }

        while (fastPointer.next.next != null) {
            if (fastPointer == null) {
                System.out.print("linked list too short for this");
                return;
            }
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next;
        }
        if (slowPointer == head) {

        }
        slowPointer.next = slowPointer.next.next;


    }

    //printing kth to last element
    //O(N) O(N)
    //HARD REVISE
    private int returnKthToLastRecursive(Node head, int k) {
        if (head == null)
            return 0;
        int index = returnKthToLastRecursive(head.next, k) + 1;
        if (index == k) {
            System.out.print(k + "th to last node is " + head.value);
        }
        return index;
    }

    public int returnKthToLastRecursive(LinkedList linkedList, int index) {
        return returnKthToLastRecursive(linkedList.head, index);
    }

    //2.3 Delete Middle Node: Implement an algorithm to delete a node in the middle (i.e., any node but
    //the first and last node, not necessarily the exact middle) of a singly linked list, given only access to
    //that node.
    //EXAMPLE
    //input:the node c from the linked list a->b->c->d->e->f
    //Result: nothing is returned, but the new linked list looks like a->b->d->e->f
    //Hints:#72
    //In this problem, you are not given access to the head of the linked list. You only have access to that node.
    //The solution is simply to copy the data from the next node over to the current node, and then to delete the
    //next node.
    //this solution delete a node in the middle not the given node
    public void deleteMiddleNode(LinkedList linkedList) {
        Node head = linkedList.head;
        //fast moves 2 nodes at a time and one bonus at the start
        //slow moves one node at a time
        Node fast = head.next.next;
        Node slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
    }

    //86. Partition List REVISE
//    public ListNode partition(ListNode head, int x) {
//        ListNode beforeHead = new ListNode(0);
//        ListNode beforeTraverse = beforeHead;
//        ListNode afterHead = new ListNode(0);
//        ListNode afterTraverse = afterHead;
//
//        while(head != null){
//            if(head.val < x){
//                beforeTraverse.next = head;
//                beforeTraverse = beforeTraverse.next;
//            }else{
//                afterTraverse.next = head;
//                afterTraverse = afterTraverse.next;
//            }
//            head = head.next;
//        }
//        afterTraverse.next = null;
//        beforeTraverse.next = afterHead.next;
//        return beforeHead.next;
//    }
    //Sum Lists: You have two numbers represented by a linked list, where each node contains a single
    //digit. The digits are stored in reverse order, such that the 1 's digit is at the head of the list. Write a
    //function that adds the two numbers and returns the sum as a linked list.
    //EXAMPLE
    //Input: (7-> 1 -> 6) + (5 -> 9 -> 2).That is,617 + 295.
    //Output: 2 -> 1 -> 9. That is, 912.
    //7 + 5 = 12 : sum = 12 % 10 = 2, carry = 12/10 = 1
    //carry is appended to the next operation
    //FOLLOW UP
    //Suppose the digits are stored in forward order. Repeat the above problem.
    //EXAMPLE
    //lnput:(6 -> 1 -> 7) + (2 -> 9 -> 5).That is,617 + 295.
    //Output: 9 -> 1 -> 2. That is, 912
    //2. Add Two Numbers
    //O(N+M) O(N) =>
    //
//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        ListNode result = new ListNode();
//        ListNode traverse = result;
//        int carry= 0 ;
//        while(l1 != null || l2!=null){
//            int digit1 = l1 == null ? 0 : l1.val;
//            int digit2 = l2 == null ? 0 : l2.val;
//            int addition = digit1 + digit2 + carry;
//
//            int sum = addition % 10;
//            carry = addition / 10;
//
//            ListNode newNode = new ListNode(sum);
//            traverse.next = newNode;
//            traverse = traverse.next;
//
//            if(l1 != null){
//                l1 = l1.next;
//            }
//
//            if(l2 != null){
//                l2=l2.next;
//            }
//
//        }
//        if(carry != 0){
//            ListNode newNode = new ListNode(carry);
//            traverse.next = newNode;
//        }
//        return result.next;
//
//    }
    //when lists are in order
    //check lengths pad zeros if needed
    public Node addTwoLists(Node l1, Node l2) {
        return addTwoListsHelper(l1, l2, 0);
    }

    private Node addTwoListsHelper(Node l1, Node l2, int carry) {
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }
        int d1 = (l1 != null) ? l1.value : 0;
        int d2 = (l2 != null) ? l2.value : 0;

        int addition = d1 + d2 + carry;
        int sum = addition % 10;
        carry = addition / 10;
        Node result = new Node(sum);
        if (l1 != null || l2 != null) {
            Node next = addTwoListsHelper(l1 != null ? l1.next : null, l2 != null ? l2.next : null, carry);
            result.next = result;
        }
        return result;
    }

    public int length(LinkedList linkedList) {
        Node current = head;
        int counter = 0;
        while (current != null) {
            current = current.next;
            counter++;
        }
        return counter;
    }

    public void padListWithZeros(LinkedList linkedList, int numberOfZeros) {
        if (numberOfZeros < 1) {
            return;
        }
        Node first = head;
        for (int i = 0; i < numberOfZeros; i++) {
            Node newNode = new Node(0);
            newNode.next = first;
            first = newNode;
        }
        head = first;
    }

    public void reverseLinkedList(LinkedList linkedList) {
        Node prev = null;
        Node current = head;
        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    //2.6 Palindrome: Implement a function to check if a linked list is a palindrome
    //approach 1 : reverse linked list and compare half of the elements
    //approach 2 : push half of the elements into a stack then compare those elements to the rest
    //if we don't know the length of the linked list we can use a slow fast pointer approach
    //where if the fast pointer is at the end we know that slow is at the middle of the linkedList
    //1 2 3 2 1      L = 5
    //1 2 2 1        L = 4

    //O(N) O(N)
    public boolean isPalindrome(LinkedList l1) {
        if (l1.head == null)
            return false;
        Stack<Integer> stack = new Stack<>();
        Node slowPointer = l1.head;
        Node fastPointer = l1.head;
        while (fastPointer != null && fastPointer.next != null) {
            //slow pointer didn't reach the middle yet keep pushing into stack
            stack.push(slowPointer.value);
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }
        //now slow is already in the middle we need to decide weather to ignore middle or take it
        //if oddLength(fastPointer != null) => ignore middle => slow = slow.next
        if (fastPointer != null)
            slowPointer = slowPointer.next;
        while (slowPointer != null) {
            int value = stack.pop();
            if (value != slowPointer.value)
                return false;
            slowPointer = slowPointer.next;
        }
        return true;
    }

    //2.7 Intersection: Given two (singly) linked lists, determine if the two lists intersect. Return the intersecting node.
    // Note that the intersection is defined based on reference, not value. That is, if the kth
    //node of the first linked list is the exact same node (by reference) as the jth node of the second
    //linked list, then they are intersecting.

    //intersecting linked Lists will always have the same last node(memory location)
    //algorithm : run through both linked lists to get the lengths and get the tails
    //approach one : use hashset traverse one of the lists and add all nodes
    //then traverse the second and see if this key exists in hashset

    //if tails aren't the same we know they don't intersect
    //if they have the same tail now we have to chop off the first k nodes from the longer list (make it traversing pointer k nodes ahead)
    //where k is the difference between the lengths of the lists
    //then we'd just check if two pointers are equal
    //O(N) O(1) // where n is input size
    public Node returnIntersection(LinkedList l1, LinkedList l2) {
        if (l1.head == null || l2.head == null)
            return null;
        int length1 = length(l1);
        int length2 = length(l2);
        Node lastInl1 = l1.head;
        Node lastInl2 = l2.head;

        while (lastInl1.next != null)
            lastInl1 = lastInl1.next;

        while (lastInl2.next != null)
            lastInl2 = lastInl2.next;

        if (lastInl1 != lastInl2)
            return null;

        Node shorter = length1 > length2 ? l2.head : l1.head;
        Node longer = length1 > length2 ? l1.head : l2.head;
        for(int i = 0 ; i < Math.abs(length1 - length2) ; i++){
            longer = longer.next;
        }
        while(shorter != null){
            if(longer == shorter)
                return longer;
            shorter = shorter.next;
            longer = longer.next;
        }
        return null;
    }
    //Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the
    //beginning of the loop.
    //DEFINITION
    //Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so
    //as to make a loop in the linked list.
    //EXAMPLE
    //Input: A -> B -> C -> D -> E -> C [the same C as earlier]
    //Output: C
    //Hints: #50, #69, #83, #90
    public Node returnLoopStartNode(LinkedList l1){
        Node slow = l1.head;
        Node fast = l1.head;
        while(fast!= null && fast.next != null){
            if(fast == slow)
                return slow; //or fast
            slow = slow.next;
            fast = fast.next.next;
        }
        return null;
    }
    //1. Create two pointers, FastPointer and SlowPointer.
    //2. Move FastPointer at a rate of 2 steps and SlowPointer at a rate of 1 step.
    //3. When they collide, move SlowPointer to LinkedListHead. Keep FastPointer where it is.
    //4. Move SlowPointer and FastPointer at a rate of one step. Return the new collision point

    public Node returnLoopStartNode1(LinkedList l1){
        Node slow = head;
        Node fast = head;
        while(fast != null && fast.next!= null){
            slow = slow.next;
            fast=fast.next.next;
            if(slow == fast){
                break;
            }
        }
        //reached end ?
        if(fast == null || fast.next == null){
            return null; // no collision
        }
        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }
    public class Node {

        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node(int value) {
            this.value = value;
        }

        public Node() {
        }
    }
}
