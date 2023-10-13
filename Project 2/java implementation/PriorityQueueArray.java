public class PriorityQueueArray implements PriorityQueue {
    private int size;
    private int MAX_SIZE;

    private int[] queueArray;

    // O(V)
    public PriorityQueueArray(int maxSize){
        this.MAX_SIZE = maxSize; // O(1)
        this.size = 0; // O(1)
        this.queueArray = new int[maxSize]; // O(1)

        for (int i=0; i<maxSize; i++){ // O(V)
            this.queueArray[i] = Integer.MAX_VALUE; // O(1)
        }
        
    }

    // O(1)
    public boolean IsEmpty(){
        return this.size==0; // O(1)
    }

    // O(1)
    public void Enqueue(int value, int priority){
        // priority already assigned 
        if (this.queueArray[value] != Integer.MAX_VALUE) return; // O(1)

        this.queueArray[value] = priority; // O(1)
        this.size -=- 1; // O(1)
    }

    // O(V)
    public int Dequeue() {

        if (this.IsEmpty()) return -1; // O(1)

        int lowestValue = this.Peek(); // O(V)

        // reset priority
        this.queueArray[lowestValue] = Integer.MAX_VALUE; // O(1)

        this.size +=- 1; // O(1)
        return lowestValue; // O(1)
    }

    // doesnt check for if value's value is unassigned, not necessary if carefully used.
    // O(1)
    public void Remove(int value){
        this.queueArray[value] = Integer.MAX_VALUE;
        this.size +=- 1;

    }

    // O(V)
    private int Peek(){
        int lowestPriority = this.queueArray[0]; // O(1)
        int lowestValue = 0; // O(1)

        for (int i=1; i<this.MAX_SIZE; i++){ // O(V)
            if (this.queueArray[i] < lowestPriority){ // O(1)
                lowestPriority = this.queueArray[i]; // O(1)
                lowestValue = i; // O(1)
            }
        }
        return lowestValue; // O(1)
    }
    
}