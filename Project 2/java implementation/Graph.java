interface Graph {
    boolean debugging = false;
    void AddEdge(int v1,int v2, int weight);
    void RemoveEdge(int v1,int v2);
    boolean EdgeExists(int v1, int v2);
    void PrintAdjacent();
    void Solve(int source);
}