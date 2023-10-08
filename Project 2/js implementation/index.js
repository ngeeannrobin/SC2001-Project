// Import Graph class from graph.js module
const Graph = require("./Graph.js")

const verticesCount = 25000;
const maxEdgesCount = verticesCount*(verticesCount-1) / 2; 

const edgeCount = maxEdgesCount * 0.1; // 10% of maximum possible number of edges

console.log(`Initialising graph with ${verticesCount} vertices.`)
let graph = new Graph(verticesCount);

console.log(`Randomly generating ${edgeCount} edges.`)
graph.ConnectRandomVertices(edgeCount);

console.log(`\nRunning Dijstra algorithm with adjacency matrix and array priority queue.`)
let start = Date.now();
graph.Dijkstra_Matrix(0);
let duration = Date.now() - start;
console.log(`Time taken: ${duration}ms`);

console.log(`\nRunning Dijstra algorithm with adjacency list and minimising heap priority queue.`)
start = Date.now();
graph.Dijkstra_List(0);
duration = Date.now() - start;
console.log(`Time taken: ${duration}ms`);