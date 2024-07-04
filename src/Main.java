import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        LinkedList l1 = new LinkedList();
        l1.addNodeAtEnd(1);
        l1.addNodeAtEnd(2);
        l1.addNodeAtEnd(3);
        l1.addNodeAtEnd(2);
        l1.addNodeAtEnd(1);
        l1.printLinkedList();
        System.out.print(l1.isPalindrome(l1));
    }
}