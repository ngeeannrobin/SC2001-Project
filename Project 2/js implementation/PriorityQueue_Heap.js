// Priority queue implementation using minimising heap
// Lower priority value = higher priority
class HeapPriorityQueue{
  constructor(){
    this.heap = [];
    this.size = 0;
  }
  
  IsEmpty(){
    return this.size === 0;
  }
  
  Enqueue(value,priority){
    this.heap[this.size++] = {value:value, priority:priority};
    this.FixUp(this.size-1);
  }
  
  Dequeue(){
    let returnValue = this.heap[0].value;
    // this.Swap(0, this.size-1);
    this.heap[0] = this.heap[--this.size];
    this.FixDown(0);
    return returnValue;
  }
  // remove value from heap
  Remove(value){
    let removeIndex = -1;
    for (let i=0; i<this.size; i++){
      if (this.heap[i].value === value){
        removeIndex = i;
        break;
      }
    }
    if (removeIndex === -1) return; // Item not found
    
    this.heap[removeIndex] = this.heap[--this.size];

    const parentIndex = Math.floor((removeIndex - 1) / 2);
    
    if (parentIndex >= 0 && this.heap[removeIndex].priority < this.heap[parentIndex].priority)
      this.FixUp(removeIndex);
    else 
      this.FixDown(removeIndex);
    
  }
  
  /*
     0
   1   2
  3 4 5 6 */
  
  FixUp(index){
    while (index > 0) {
      const parentIndex = Math.floor((index-1) / 2);
      if (this.heap[index].priority < this.heap[parentIndex].priority) {
        this.Swap(index, parentIndex);
        index = parentIndex;
      } else {
        break;
      }
    }
  }
  
  FixDown(index){
    const leftIndex = 2 * index + 1;
    const rightIndex = 2 * index + 2;
    let minIndex = index;

    if (leftIndex < this.size &&
      this.heap[leftIndex].priority < this.heap[minIndex].priority) {
      minIndex = leftIndex;
    }

    if (rightIndex < this.size &&
      this.heap[rightIndex].priority < this.heap[minIndex].priority
    ) {
      minIndex = rightIndex;
    }

    if (minIndex !== index) {
      this.Swap(index, minIndex);
      this.FixDown(minIndex);
    }
  }
  
  Swap(a,b){
    let temp = this.heap[a];
    this.heap[a] = this.heap[b];
    this.heap[b] = temp;
  }

}

module.exports = HeapPriorityQueue;