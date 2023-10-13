import java.util.Arrays;

class GraphAdjacencyMatrix implements Graph{

    private int[][] adjMatrix;
    private int vertexCount;

    public GraphAdjacencyMatrix(int vertexCount){
        this.vertexCount = vertexCount;
        this.adjMatrix = new int[vertexCount][vertexCount];
    }

    public void AddEdge(int v1,int v2, int weight){
        if (this.EdgeExists(v1,v2)) return;

        this.adjMatrix[v1][v2] = weight;
        this.adjMatrix[v2][v1] = weight;
    }

    public void RemoveEdge(int v1,int v2){
        if (!this.EdgeExists(v1,v2)) return;

        this.adjMatrix[v1][v2] = 0;
        this.adjMatrix[v2][v1] = 0;
    }

    public boolean EdgeExists(int v1, int v2){
        return this.adjMatrix[v1][v2] != 0;
    }

    public void PrintAdjacent(){
        for (int i=0; i<this.vertexCount; i++){
            System.out.printf("%d: ", i);
            for (int j=0; j<this.vertexCount; j++){
                System.out.printf("%d ", this.adjMatrix[i][j]);
            }
            System.out.printf("\n");
        }
    }

    public void Solve(int source){
        int[] distances = new int[this.vertexCount]; // O(1)
        int[] S = new int[this.vertexCount]; // O(1)
        PriorityQueueArray priorityQueue = new PriorityQueueArray(this.vertexCount); // O(V)
        
        Arrays.fill(distances, Integer.MAX_VALUE); // O(V)
        distances[source] = 0; // O(1)
        for (int v=0; v<this.vertexCount; v++) //O(V)
            priorityQueue.Enqueue(v,distances[v]);
        // priorityQueue.Enqueue(source,distances[source]); // O(1)

        int u; // O(1)
        while (!priorityQueue.IsEmpty()){ // O(V)
            u = priorityQueue.Dequeue(); // O(V)
            S[u] = 1; // O(1)

            for (int v=0; v<this.vertexCount; v++){ // O(V)
                // Vertext v is adjacent to u
                if (this.adjMatrix[u][v] != 0){ // O(1)
                    if (S[v] != 1 && distances[v] > distances[u] + this.adjMatrix[u][v]){  // O(1)
                        priorityQueue.Remove(v); // O(1)
                        distances[v] = distances[u] + this.adjMatrix[u][v]; // O(1)
                        priorityQueue.Enqueue(v,distances[v]); // O(1)
                    }
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