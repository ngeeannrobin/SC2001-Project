class Item {
    public int value;
    public int priority;

    Item(){
        this.value = 0;
        this.priority = 0;
    }
}

public interface PriorityQueue {
    public boolean IsEmpty();
    public void Enqueue(int value, int priority);
    public int Dequeue();
    public void Remove(int value);
}