// Priority queue implementation using array
// Lower priority value = higher priority
class ArrayPriorityQueue{
  constructor(){
    this.priorityArray = [];
    this.size = 0;
  }
  
  IsEmpty(){
    return this.size===0;
  }
  
  Enqueue(value,priority){
    this.priorityArray[this.size++] = {value: value, priority: priority}; 
  }
  
  // Return of index with lowest priority
  Peek(){
    let lowestPriority = this.priorityArray[0].priority;
    let index = 0;
    for (let i=1; i<this.size; i++){
      if (this.priorityArray[i].priority < lowestPriority){
        lowestPriority = this.priorityArray[i].priority;
        index = i;
      }
    }
    return index;
  }
  
  // return value with lowest priority;
  Dequeue(){
    if (this.IsEmpty())
      return -1;
    let lowestIndex = this.Peek();
    let returnValue = this.priorityArray[lowestIndex].value;
    
    // shift elements in array;
    for (let i = lowestIndex; i < this.size; i++) {
        this.priorityArray[i] = this.priorityArray[i + 1];
    }
    
    this.size--;
    
    return returnValue;
  }
  
  // remove value from array, shift subsequent elements down
  Remove(value){
    let found = false;
    for (let i=0; i<this.size; i++){
      found = found || this.priorityArray[i].value == value;
      if (found)
        this.priorityArray[i] = this.priorityArray[i + 1];
    }
    
    this.size--;
  }
  
}

module.exports = ArrayPriorityQueue;