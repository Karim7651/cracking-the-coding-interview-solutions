
import java.util.EmptyStackException;

public class FixedMultiStack  {
    int numberOfStacks = 3;
    int[] values;
    int[] sizes;
    int stackCapacity;
    public FixedMultiStack(int stackSize){
        stackCapacity = stackSize;
        values = new int[stackSize * numberOfStacks];
        sizes = new int[numberOfStacks];
    }
    public void push(int stackNumber, int value){
        if(isFull(stackNumber)){
            System.out.print("This stack is full");
        }
        int targetIndex = sizes[stackNumber]++;
        values[targetIndex] = value;
    }
    public int pop(int stackNumber){
        if(isEmpty(stackNumber)){
            throw new EmptyStackException();
        }
        int targetIndex = sizes[stackNumber];
        int value = values[targetIndex];
        values[targetIndex] = 0;
        sizes[stackNumber]--;
        return value;
    }
    public int peek(int stackNumber){
        if(isEmpty(stackNumber)){
            throw new EmptyStackException();
        }
        int targetIndex = sizes[stackNumber];
        return values[targetIndex];
    }
    public boolean isFull(int stackNumber){
        return sizes[stackNumber] == stackCapacity;
    }
    public boolean isEmpty(int stackNumber){
        return sizes[stackNumber] == 0;
    }
}
