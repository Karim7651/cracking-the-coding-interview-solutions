import java.util.ArrayList;
import java.util.Stack;
//Stack of Plates: Imagine a (literal) stack of plates. If the stack gets too high, it might topple.
//Therefore, in real life, we would likely start a new stack when the previous stack exceeds some
//threshold. Implement a data structure SetOfStacks that mimics this. SetO-fStacks should be
//composed of several stacks and should create a new stack once the previous one exceeds capacity.
//SetOfStacks. push() and SetOfStacks. pop() should behave identically to a single stack
//(that is, pop () should return the same values as it would if there were just a single stack).
//FOLLOW UP
//Implement a function popAt ( int index) which performs a pop operation on a specific sub-stack.
//Hints:#64, #87
public class StackOfPlates {
    ArrayList<java.util.Stack<Integer>> arrayStacks ;
    //to keep track of which stack we're using
    int arrayIndex;
    //to keep track of which top of current stack
    int stackPosition;
    int maxStackCapacity = 10;
    public StackOfPlates(){
        arrayStacks = new ArrayList<>();
        arrayIndex = -1;
        stackPosition = -1;
    }
    public void push(int value){
        //if our stack of plates isn't initiated yet or our current stack is at maxCapacity
        if(arrayIndex == -1 || arrayStacks.get(arrayIndex).size() == maxStackCapacity){
            java.util.Stack<Integer> stack = new java.util.Stack<>();
            stack.push(value);
            arrayIndex++;
            stackPosition=0;
            arrayStacks.add(stack);
        }else{
            //otherwise
            java.util.Stack<Integer> stack = arrayStacks.get(arrayIndex);
            stack.push(value);
        }
    }
    public int pop(){
        if(isEmpty()){
            System.out.print("Stack is empty");
            return Integer.MIN_VALUE;
        }
        java.util.Stack<Integer> stack = arrayStacks.getLast();
        int value = stack.pop();
        stackPosition--;
        //sub stack is empty
        if(stackPosition == -1){
            arrayStacks.removeLast();
            arrayIndex--;
        }
        return value;

    }
    public int popAt(int index){
        int arrayOffset = index/10;
        int stackOffset = index - arrayOffset*10;
        if(arrayOffset > arrayIndex )
            return Integer.MIN_VALUE; //err value
        java.util.Stack<Integer> stack = arrayStacks.get(arrayOffset);
        if(stackOffset > stack.size()-1)
            return Integer.MIN_VALUE; //err value

        int value = stack.get(stackOffset);
        stack.remove(stackOffset);
        stackPosition--;
        return value;
    }
    //Queue via Stacks: Implement a MyQueue class which implements a queue using two stacks
    //232. Implement Queue using Stacks
//    class MyQueue {
//        Stack<Integer> s1 = new Stack<>();
//        Stack<Integer> s2 = new Stack<>();
//
//        public void push(int x) {
//            if(s1.isEmpty())
//                s1.push(x);
//            else{
//                while(!s1.isEmpty()){
//                    s2.push(s1.pop());
//                }
//                s1.push(x);
//                while(!s2.isEmpty()){
//                    s1.push(s2.pop());
//                }
//            }
//
//        }
//
//        public int pop() {
//            return s1.pop();
//        }
//
//        public int peek() {
//            return s1.peek();
//        }
//
//        public boolean empty() {
//            return s1.isEmpty();
//        }
//    }
    public boolean isEmpty(){
        if(arrayStacks.isEmpty() || arrayStacks.get(0).isEmpty()){
            return true;
        }
        return false;
    }
}
