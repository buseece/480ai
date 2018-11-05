import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.text.DecimalFormat;


public class OutputHelper {

    public OutputHelper(){

    }

    public void writeToFile(String filePath, List<Coordinate> solution, int[][] is_discovered, double distance) throws IOException {
        File outputFile = new File(filePath);
        FileWriter writer = new FileWriter(outputFile);
        for(int i=0; i<is_discovered.length; i++){
            for(int j=0; j<is_discovered[0].length; j++){
                writer.write(is_discovered[i][j]+" ");
            }
            writer.write("\n");
        }
        writer.write(solution.size()+"\n");
        for(int i=0; i<solution.size(); i++){
            writer.write(solution.get(i).getX()+" "+solution.get(i).getY()+"\n");
        }


        //writer.write(String.format( "%.2f", distance ));
        String toWrite = String.format( "%.2f", distance );
        String part1 = toWrite.substring(0,toWrite.indexOf(","));
        String part2 = toWrite.substring(toWrite.indexOf(",")+1);
        writer.write(part1 +"."+part2);
        writer.flush();
        writer.close();
    }

}
