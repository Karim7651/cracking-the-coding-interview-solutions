import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        StackOfPlates stackOfPlates = new StackOfPlates();
        stackOfPlates.push(1);
        stackOfPlates.push(1);
        stackOfPlates.push(1);
        stackOfPlates.push(1);
        stackOfPlates.push(1);
        stackOfPlates.push(1);
        stackOfPlates.push(1);
        stackOfPlates.push(1);
        stackOfPlates.push(1);
        stackOfPlates.push(1);
        stackOfPlates.push(6);
        System.out.print(stackOfPlates.pop() + " " + stackOfPlates.pop());
        stackOfPlates.push(1);
        stackOfPlates.push(1);

    }
}