import java.util.Arrays;
import java.util.ArrayList;

class Edge{
    public int adj;
    public int weight;

    public Edge(int adj,int weight){
        this.adj = adj;
        this.weight = weight;
    }
}

class GraphAdjacencyList implements Graph{
    private int vertexCount;
    private ArrayList<ArrayList<Edge>> adjArray;

    public GraphAdjacencyList(int vertexCount){
        this.vertexCount = vertexCount;
        this.adjArray = new ArrayList<ArrayList<Edge>>();
        for (int v=0; v<vertexCount; v++){
            this.adjArray.add(new ArrayList<Edge>());
        }
    }

    public void AddEdge(int v1,int v2, int weight){
        if (EdgeExists(v1,v2)) return;
        
        adjArray.get(v1).add(new Edge(v2,weight));
        adjArray.get(v2).add(new Edge(v1,weight));

    }
    public void RemoveEdge(int v1,int v2){
        if (!EdgeExists(v1,v2)) return;

        adjArray.get(v1).removeIf(edge->edge.adj == v2);
        adjArray.get(v2).removeIf(edge->edge.adj == v1);
    }

    public boolean EdgeExists(int v1, int v2){
        for (Edge edge : adjArray.get(v1)){
            if (edge.adj == v2){
                return true;
            }
        }
        return false;
    }

    public void PrintAdjacent(){
        for (int v=0; v<this.vertexCount; v++){
            System.out.printf("%d: ", v);
            for (Edge edge : this.adjArray.get(v)){
                System.out.printf("%d(%d) ", edge.adj, edge.weight);
            }
            System.out.printf("\n");
        }
    }

    public void Solve(int source){

        int[] distances = new int[this.vertexCount]; // O(1)
        int[] S = new int[this.vertexCount]; // O(1)
        PriorityQueueHeap priorityQueue = new PriorityQueueHeap(this.vertexCount); // O(V)
        
        Arrays.fill(distances, Integer.MAX_VALUE); // O(V)
        distances[source] = 0;  // O(1)
        for (int v=0; v<this.vertexCount; v++) // O(V)
            priorityQueue.Enqueue(v,distances[v]);  // O(logV)

        int u; // O(1)
        while (!priorityQueue.IsEmpty()){ // O(V)
            u = priorityQueue.Dequeue(); // O(logV)
            S[u] = 1;// O(1)

            for (Edge edge : this.adjArray.get(u)){ // O(E) : O(1)
                if (S[edge.adj] != 1 && distances[edge.adj] > distances[u] + edge.weight){
                    priorityQueue.Remove(edge.adj); // O(logV)
                    distances[edge.adj] = distances[u] + edge.weight; // O(1)
                    priorityQueue.Enqueue(edge.adj,distances[edge.adj]); // O(logV)
                }
            }
        }

        if (debugging){
            System.out.println("Solved!");
            for (int i=0; i<this.vertexCount; i++){
                System.out.printf("Distance from %d to %d: %d\n", source, i, distances[i]);
            }
        }

    }
}