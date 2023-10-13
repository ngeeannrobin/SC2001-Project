import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateRandom {

    static int vertexCount = 20_000;
    static int maxEdges = vertexCount*(vertexCount-1) / 2;
    // static int edgeCount = (int) Math.floor(maxEdges * 0.5); // 50% of possible edges
    static int edgeCount = 25_000_000;

    static int maxWeight = 9; // weight ranges from 1 to maxWeight+1


    public static void main(String[] args)
    {
        Random rand = new Random();
        try {
            FileWriter writer = new FileWriter("./graphFiles/v" + vertexCount + "e" + edgeCount + ".csv");
            
            boolean[][] adjMatrix = new boolean[vertexCount][vertexCount];
            int v1, v2;
            for (int i=0; i<edgeCount; i++){
                do {
                    v1 = rand.nextInt(vertexCount);
                    v2 = rand.nextInt(vertexCount);
                } while (adjMatrix[v1][v2] != false);

                adjMatrix[v1][v2] = true;
                adjMatrix[v2][v1] = true;

                writer.write(String.format("%d,%d,%d\n", v1,v2,rand.nextInt(maxWeight)+1));
                
            }

            writer.close();
        } catch (IOException e){
            System.out.println("nah");
        }
        
    }
}