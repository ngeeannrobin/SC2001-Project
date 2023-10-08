// Import Priorityqueue classes from respective modules
const ArrayPriorityQueue = require("./PriorityQueue_Array.js")
const HeapPriorityQueue = require("./PriorityQueue_Heap.js")

class Graph{
  constructor(verticesCount){
    // Initialise adjList and adjMat, no connections
    this.verticesCount = verticesCount;
    this.edgeCount = 0;
    this.adjList = [];
    this.adjMat = [];
    for (let i = 0; i<verticesCount; i++){
      this.adjList.push([]);
      this.adjMat.push(new Array(verticesCount).fill(0));
    }
  }
  
  Dijkstra_Matrix(source){
    let distances = new Array(this.verticesCount).fill(Infinity);
    let predecesors = new Array(this.verticesCount).fill(null);
    let S = new Array(this.verticesCount).fill(0);
    
    let priorityQ = new ArrayPriorityQueue();
    
    distances[source] = 0;
    
    for (let v=0; v<this.verticesCount; v++){
      priorityQ.Enqueue(v,distances[v]);
    }
    
    let u;
    
    while (!priorityQ.IsEmpty()){
      u = priorityQ.Dequeue();
      S[u] = 1;
      
      for(let v=0; v<this.verticesCount; v++){
        // vertext v is adjacent to u
        if (this.adjMat[u][v] != 0){

          if (S[v] != 1 && distances[v] > distances[u] + this.adjMat[u][v]){
            priorityQ.Remove(v);
            distances[v] = distances[u] + this.adjMat[u][v];
            predecesors[v] = u;
            priorityQ.Enqueue(v,distances[v]);
          }
        }
      }
    }
    return distances;
  }
  
  Dijkstra_List(source){
    let distances = new Array(this.verticesCount).fill(Infinity);
    let predecesors = new Array(this.verticesCount).fill(null);
    let S = new Array(this.verticesCount).fill(0);
    
    let priorityQ = new HeapPriorityQueue();

    
    distances[source] = 0;
    
    for (let v=0; v<this.verticesCount; v++){
      priorityQ.Enqueue(v,distances[v]);
    }
    
    let u;
    
    while (!priorityQ.IsEmpty()){
      u = priorityQ.Dequeue();
      S[u] = 1;
      
      for(let adj of this.adjList[u]){



        if (S[adj.v] != 1 && distances[adj.v] > distances[u] + adj.weight){
          priorityQ.Remove(adj.v);
          distances[adj.v] = distances[u] + adj.weight;
          predecesors[adj.v] = u;
          priorityQ.Enqueue(adj.v,distances[adj.v]);
        }
      }
    }

    

    
    return distances;
  }
  
  EdgeExists(v1,v2){
    return this.adjMat[v1][v2] !== 0;
  }
  
  // Assuming undirected graph
  AddEdge(v1,v2,weight){
    if (this.EdgeExists(v1,v2)) return;

    
    // Update adjList
    this.adjList[v1].push({v:v2, weight:weight});
    this.adjList[v2].push({v:v1, weight:weight});
    
    // Update adjMat
    this.adjMat[v1][v2] = weight;
    this.adjMat[v2][v1] = weight;
    
    this.edgeCount -=- 1;
  }
  
  RemoveEdge(v1,v2){
    if (!this.EdgeExists(v1,v2)) return;
    
    // Update adjList
    this.adjList[v1] = this.adjList[v1].filter(function(adj) { return adj.v !== v2});
    this.adjList[v2] = this.adjList[v2].filter(function(adj) { return adj.v !== v1});
    
    // Update adjMat
    this.adjMat[v1][v2] = 0;
    this.adjMat[v2][v1] = 0;
    
    this.edgeCount +=- 1;
  }
  
  ConnectRandomVertices(edgeCount){
    // if (edgeCount/ (this.verticesCount*(this.verticesCount-1) / 2) < 0.1){
    for (let _ = 0; _<edgeCount; _++){
    
      let v1 = Math.floor(Math.random()*this.verticesCount);
      let v2 = Math.floor(Math.random()*this.verticesCount);
      
      while (this.adjMat[v1][v2] !== 0 || v1===v2){
        v1 = Math.floor(Math.random()*this.verticesCount);
        v2 = Math.floor(Math.random()*this.verticesCount);
      }
      this.AddEdge(v1,v2,Math.floor(Math.random()*10)+1);
      
    }
    // } 
    // else {
    //   let availablePairs = [];
    //   for (let i=0; i<this.verticesCount; i++){
    //     for (let j=0; j<i; j++){
    //       availablePairs.push([i,j]);
    //     }
    //   }
    //   for (let _=0; _<edgeCount; _++){
    //     const randomIndex = Math.floor(Math.random()*availablePairs.length);
    //     let [v1,v2] = availablePairs.splice(randomIndex,1)[0];

    //     this.AddEdge(v1,v2);
    //   }
    // }
    
  }
  
  // // Visuals only
  // DrawEdges(vertices){


  //   for (let v=0; v<this.adjList.length; v++){
  //     for (let adj of this.adjList[v]){
  //       let x1 = vertices[v].x;
  //       let y1 = vertices[v].y;
  //       let x2 = vertices[adj.v].x
  //       let y2 = vertices[adj.v].y
        
  //       let x3 = (x1+x2)/2
  //       let y3 = (y1+y2)/2
        
  //       stroke("#e9e9e2")
  //       line(x1,y1,x2,y2);
        
  //       rectMode(CENTER);
  //       fill(0);
  //       noStroke();
  //       rect(x3, y3, 15, 15)
        
  //       noStroke();
  //       fill("#e9e9e2");
  //       textAlign(CENTER,CENTER);
  //       text(adj.weight, x3, y3);
  //     }
  //   }
  // }
}

module.exports = Graph;