public class PriorityQueueHeap implements PriorityQueue {
    private int size;
    private int MAX_SIZE;

    private Item[] heapArray;

    // O(V)
    public PriorityQueueHeap(int maxSize){
        this.MAX_SIZE = maxSize; // O(1)
        this.size = 0; // O(1)

        this.heapArray = new Item[maxSize]; // O(1)
        // reducing Instantiation overhead by filling up array with Item objects, then only changing their values;
        for (int i=0; i<maxSize; i++){ // O(V)
            this.heapArray[i] = new Item();  // O(1)
        }
    }

    // O(1)
    public boolean IsEmpty(){
        return this.size==0; // O(1)
    }

    // O(logV)
    public void Enqueue(int value, int priority){
        this.heapArray[this.size].value = value; // O(1)
        this.heapArray[this.size].priority = priority; // O(1)
        this.size -=- 1; // O(1)
        this.FixUp(this.size-1); // O(logV)
    }

    // O(logV)
    public int Dequeue() {
        if (this.IsEmpty()) return -1; // O(1)

        int returnValue = this.heapArray[0].value; // O(1)
        this.heapArray[0] = this.heapArray[this.size-1]; // O(1)
        this.size +=- 1; // O(1)
        this.FixDown(0); // O(logV)

        return returnValue; // O(1)
    }

    // O(V)
    public void Remove(int value){
        int removeIndex = -1; // O(1)
        for (int i=0; i<this.size; i++){ // O(V)
            if (this.heapArray[i].value == value){ // O(1)
                removeIndex = i; // O(1)
                break; // O(1)
            }
        }
        if (removeIndex == -1) return;

        this.Swap(removeIndex,this.size-1); // O(1)
        this.size +=- 1; // O(1)

        int parentIndex = (int) Math.floor((removeIndex-1)/2); // O(1)
        
        if (parentIndex >= 0 && 
            this.heapArray[removeIndex].priority < this.heapArray[parentIndex].priority)
            this.FixUp(removeIndex); // O(logV)
        else 
            this.FixDown(removeIndex); // O(logV)

    }

    // O(logV)
    private void FixUp(int index){
        while (index>0){ // O(logV)
            int parentIndex = (int) Math.floor((index-1)/2); // O(1)
            if (this.heapArray[index].priority < this.heapArray[parentIndex].priority){ // O(1)
                this.Swap(index,parentIndex); // O(1)
                index = parentIndex; // O(1)
            } 
            else
                break; // O(1)
        }
    }

    // O(logV)
    private void FixDown(int index){
        int leftIndex = 2 * index + 1; // O(1)
        int rightIndex = 2 * index + 2; // O(1)
        int minIndex = index; // O(1)
        if (leftIndex < this.size &&
            this.heapArray[leftIndex].priority < this.heapArray[minIndex].priority) {
            minIndex = leftIndex; // O(1)
        }

        if (rightIndex < this.size &&
            this.heapArray[rightIndex].priority < this.heapArray[minIndex].priority) {
            minIndex = rightIndex; // O(1)
        }

        if (minIndex != index) {
            this.Swap(index, minIndex); // O(1)
            this.FixDown(minIndex); // O(logV)
        }
    }

    // O(1)
    private void Swap(int i, int j){
        int tempValue = this.heapArray[i].value; // O(1)
        int tempPriority = this.heapArray[i].priority; // O(1)

        this.heapArray[i].value = this.heapArray[j].value; // O(1)
        this.heapArray[i].priority = this.heapArray[j].priority; // O(1)

        this.heapArray[j].value = tempValue; // O(1)
        this.heapArray[j].priority = tempPriority; // O(1)
    }

   
    
}