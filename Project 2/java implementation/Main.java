import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main{
    static int edgeCount = 0;
    static int vertexCount = 10_000;
    static String csvFilePath = "./graphFiles/"+"v10000e25000000.csv";
    static int iterations = 10;


    public static void main(String[] args)
    {
        // Scanner to read csv file.
        Scanner scanner;
        try{
            scanner = new Scanner(new File(csvFilePath));
        } catch (FileNotFoundException e) {
            System.out.println("no");
            return;
        }

        System.out.printf("Creating graph with %d vertices.\n", vertexCount);

        // Swap between these two lines to test each structure.
        // Graph g = new GraphAdjacencyMatrix(vertexCount);
        Graph g = new GraphAdjacencyList(vertexCount);



        for (int i=0; i<25; i++){
            // Load 1,000,000 edges at a time
            LoadEdges(scanner,g,1_000_000);
            System.out.printf("%d: ", edgeCount);

            // Run the Dijkstra algorithm multiple times, take the mean time elapsed.
            long elapsed = 0;
            for (int j=0; j<iterations; j++){
                long start = System.currentTimeMillis();
                g.Solve(0);
                long end = System.currentTimeMillis();
                elapsed += end-start;
            }
            System.out.printf("%.2f\n", (float)elapsed/iterations);
        }
        scanner.close();
    }

    // Read n lines in csv file and add edge to graph.
    public static void LoadEdges(Scanner scanner, Graph graph,int n){
        for (int i=0; i<n; i++){
            if (!scanner.hasNextLine()) break;
            String line = scanner.nextLine();
            String[] values = line.split(",");
            if (values.length==3){
                int v1 = Integer.parseInt(values[0]);
                int v2 = Integer.parseInt(values[1]);
                int weight = Integer.parseInt(values[2]);

                graph.AddEdge(v1,v2,weight);
                edgeCount -=- 1;
            }
        }
    }
}