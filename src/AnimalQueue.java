import java.util.LinkedList;
public class AnimalQueue {
    private int order; //timestamp
    LinkedList<Dog> dogs = new LinkedList<>();
    LinkedList<Cat> cats = new LinkedList<>();

    public void enqueue(Animal a){
        a.setOrder(order);
        order++;
        if(a instanceof Dog){
            dogs.addLast((Dog) a);
        }else{
            cats.addLast((Cat) a);
        }
    }
    public Animal dequeAny(){
        if(dogs.size() == 0)
            return dequeueCats();
        else if(cats.size() ==0)
            return dequeueDogs();
        Dog dog = dogs.peek();
        Cat cat = cats.peek();
        if(dog.isOlder(cat)){
            return dequeueDogs();
        }else{
            return dequeueCats();
        }
    }
    public Dog dequeueDogs(){
        return dogs.poll();
    }
    public Cat dequeueCats(){
        return cats.poll();
    }
}
