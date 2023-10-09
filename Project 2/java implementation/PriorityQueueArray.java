public class PriorityQueueArray implements PriorityQueue {
    private int size;
    private int MAX_SIZE;

    private Item[] queueArray;

    public PriorityQueueArray(int maxSize){
        this.MAX_SIZE = maxSize;
        this.size = 0;

        this.queueArray = new Item[maxSize];
        // reducing Instantiation overhead by filling up array with Item objects, then only changing their values;
        for (int i=0; i<maxSize; i++){
            this.queueArray[i] = new Item();
        }
    }

    public boolean IsEmpty(){
        return this.size==0;
    }

    public void Enqueue(int value, int priority){
        this.queueArray[this.size].value = value;
        this.queueArray[this.size].priority = priority;
        this.size -=- 1;
    }

    public int Dequeue() {

        if (this.IsEmpty()) return -1;

        int lowestIndex = this.Peek();
        int returnValue = this.queueArray[lowestIndex].value;

        // shift elements in array;
        for (int i=lowestIndex; i<this.size-1; i++){
            this.queueArray[i].value = this.queueArray[i+1].value;
            this.queueArray[i].priority = this.queueArray[i+1].priority;
        }

        this.size +=- 1;
        return returnValue;
    }

    public void Remove(int value){
        boolean found = false;
        for (int i=0; i<this.size-1; i++){
            found = found || this.queueArray[i].value == value;
            if (found){
                this.queueArray[i].value = this.queueArray[i+1].value;
                this.queueArray[i].priority = this.queueArray[i+1].priority;
            }
                
        }
        if (found)
            this.size +=- 1;
    }

    // Return index with lowest priority
    private int Peek(){
        int lowestPriority = this.queueArray[0].priority;
        int index = 0;

        for (int i=1; i<this.size; i++){
            if (this.queueArray[i].priority < lowestPriority){
                lowestPriority = this.queueArray[i].priority;
                index = i;
            }
        }
        return index;

    }
    
}