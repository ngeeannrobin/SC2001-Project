public class PriorityQueueHeap implements PriorityQueue {
    private int size;
    private int MAX_SIZE;

    private Item[] heapArray;

    public PriorityQueueHeap(int maxSize){
        this.MAX_SIZE = maxSize;
        this.size = 0;

        this.heapArray = new Item[maxSize];
        // reducing Instantiation overhead by filling up array with Item objects, then only changing their values;
        for (int i=0; i<maxSize; i++){
            this.heapArray[i] = new Item();
        }
    }

    public boolean IsEmpty(){
        return this.size==0;
    }

    public void Enqueue(int value, int priority){
        this.heapArray[this.size].value = value;
        this.heapArray[this.size].priority = priority;
        this.size -=- 1;
        this.FixUp(this.size-1);
    }

    public int Dequeue() {
        if (this.IsEmpty()) return -1;

        int returnValue = this.heapArray[0].value;
        this.heapArray[0] = this.heapArray[this.size-1];
        this.size +=- 1;
        this.FixDown(0);

        return returnValue;
    }

    public void Remove(int value){
        int removeIndex = -1;
        for (int i=0; i<this.size; i++){
            if (this.heapArray[i].value == value){
                removeIndex = i;
                break;
            }
        }
        if (removeIndex == -1) return; // item not found

        this.heapArray[removeIndex] = this.heapArray[this.size-1];
        this.size +=- 1;

        int parentIndex = (int) Math.floor((removeIndex-1)/2);

        if (parentIndex >= 0 && 
            this.heapArray[removeIndex].priority < this.heapArray[parentIndex].priority)
            this.FixUp(removeIndex);
        else 
            this.FixDown(removeIndex);

    }

    private void FixUp(int index){
        while (index>0){
            int parentIndex = (int) Math.floor((index-1)/2);
            if (this.heapArray[index].priority < this.heapArray[parentIndex].priority){
                this.Swap(index,parentIndex);
                index = parentIndex;
            } 
            else
                break;
        }
    }

    private void FixDown(int index){
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;
        int minIndex = index;
        if (leftIndex < this.size &&
            this.heapArray[leftIndex].priority < this.heapArray[minIndex].priority) {
            minIndex = leftIndex;
        }

        if (rightIndex < this.size &&
            this.heapArray[rightIndex].priority < this.heapArray[minIndex].priority) {
            minIndex = rightIndex;
        }

        if (minIndex != index) {
            this.Swap(index, minIndex);
            this.FixDown(minIndex);
        }
    }

    private void Swap(int i, int j){
        int tempValue = this.heapArray[i].value;
        int tempPriority = this.heapArray[i].priority;

        this.heapArray[i].value = this.heapArray[j].value;
        this.heapArray[i].priority = this.heapArray[j].priority;

        this.heapArray[j].value = tempValue;
        this.heapArray[j].priority = tempPriority;
    }

   
    
}