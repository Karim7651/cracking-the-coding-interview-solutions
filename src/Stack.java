import java.util.EmptyStackException;
//questions => 109
//answers => 239
public class Stack<T> {
    StackNode<T> top;
    private static class StackNode<T>{
        T data;
        StackNode<T> next;
        public StackNode(T data){
            this.data = data;
        }
    }
    public T pop(){
        if(top == null)
            throw new EmptyStackException();
        T item = top.data;
        top = top.next;
        return item;
    }
    public void push(T item){
        StackNode<T> t = new StackNode<>(item);
        t.next=top;
        top = t;
    }
    public T peek(){
        if(top == null)
            throw new EmptyStackException();
        return top.data;
    }
    public boolean isEmpty(){
        return top== null;
    }
//155. Min Stack
//    class MinStack {
//        Stack<Integer> stack = new Stack<>();
//        Stack<Integer> minStack = new Stack<>();
//        public void push(int val) {
                                    //what is there's a duplicated minimum answer below in if statement
//            if(stack.isEmpty() || val<=minStack.peek()){
//                minStack.push(val);
//            }
//            stack.push(val);
//        }
//
//        public int pop() {
//            int value = stack.pop();
//            if(value == minStack.peek()){
//                minStack.pop();
//            }
//            return value;
//        }
//
//        public int top() {
//            return stack.peek();
//        }
//
//        public int getMin() {
//            return minStack.peek();
//        }
//    }
// approach 2 Method 2 (By making deQueue operation costly):
// In this method, in en-queue operation,
// the new element is entered at the top of stack1.
// In de-queue operation, if stack2 is empty then all the elements are moved to stack2 and finally top of stack2 is returned.

    //Time Complexity:
    //Push operation: O(1).
    //Same as pop operation in stack.
    //Pop operation: O(N) in general and O(1) amortized time complexity.
    //In the worst case we have to empty the whole of stack 1 into stack 2 so its O(N).
    // Amortized time is the way to express the time complexity when an algorithm has the
    // very bad time complexity only once in a while besides the time complexity
    // that happens most of time.
    // So its O(1) amortized time complexity, since we have to empty whole of stack 1 only when stack 2 is empty, rest of the times the pop operation takes O(1) time.
//class MyQueue {
//
//    Stack<Integer> s1 = new Stack<>();
//    Stack<Integer> s2 = new Stack<>();
//    public void push(int x) {
//        s1.push(x);
//    }
//
//    public int pop() {
//        if(s2.isEmpty()){
//            while(!s1.isEmpty()){
//                s2.push(s1.pop());
//            }
//        }
//        return s2.pop();
//    }
//
//    public int peek() {
//        if(s2.isEmpty()){
//            while(!s1.isEmpty()){
//                s2.push(s1.pop());
//            }
//        }
//        return s2.peek();
//    }
//
//    public boolean empty() {
//        return s1.isEmpty() && s2.isEmpty();
//    }
//}

    //Sort Stack: Write a program to sort a stack such that the smallest items are on the top. You can use
    //an additional temporary stack, but you may not copy the elements into any other data structure
    //(such as an array). The stack supports the following operations: push, pop, peek, and is Empty.
    //Approach 1 : using givenStack & bufferStack & finalSortedStack
    //search the given stack for minimum element then push it into the buffer stack (repeat N times)
    //at the end pop all bufferStack into finalSortedStack O(N^2) O(N)
    //Approach 2 : using s1 and s2 (s2 elements are sorted)
    //we pop and element from s1 and store it in a temporary variable
    //then we insert it in the right place in s2 my popping as many elements from s2 to s1
    //then inserting the temporary variable back into s2
    //notice that elements that were popped from s2 are still in s1 then we repeat as many times as needed
    //O(N^2) O(N)
    public void sortStack(Stack<Integer> s1){
        Stack<Integer> s2 = new Stack<>();
        while(!s1.isEmpty()){
            int temp = s1.pop();
            while(!s2.isEmpty() && s2.peek() > temp){
                s1.push(s2.peek());
            }
            s2.push(temp);
        }
    }
    //with the mergesort solution, we would create two extra stacks and divide the stack into two parts. We
    //would recursively sort each stack, and then merge them back together in sorted order into the original
    //stack. Note that this would require the creation of two additional stacks per level of recursion.
    //With the quicksort solution, we would create two additional stacks and divide the stack into the two stacks
    //based on a pivot element. The two stacks would be recursively sorted, and then merged back together
    //into the original stack. Like the earlier solution, this one involves creating two additional stacks per level of
    //recursion
}


