import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;



public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here

        int NUMBER_OF_FILES_LAB;
        int NUMBER_OF_FILES_MOUNTAIN;

        DFS dfs = new DFS();
        BFS bfs = new BFS();
        Astar astar = new Astar();
        OutputHelper outputHelper = new OutputHelper();
        String labyrinthPath = "examples_without_output/labyrinth";
        String mountainPath = "examples_without_output/mountain";
        String outputBasePath = "examples_without_output/outputs";

        NUMBER_OF_FILES_LAB = (int) getFileInformation(labyrinthPath);
        NUMBER_OF_FILES_MOUNTAIN =(int) getFileInformation(mountainPath);
        //System.out.println("Number of files: "+NUMBER_OF_FILES_LAB);

        for (int i=1; i<NUMBER_OF_FILES_LAB; i++){
            String fileName = labyrinthPath+"/labyrinth_example_"+i+".txt";
            Maze maze = new Maze(fileName);
            //solve with dfs
            List<Coordinate> solution = dfs.explore(maze,0);
            String outputFileName = outputBasePath+"/labyrinth_example_"+i+"_dfs_out.txt";
            int distance = 0;
            if(solution.size()>0)
                distance = solution.size()-1;
                //distance = Math.abs(maze.start.getX()-maze.exit.getX())+Math.abs(maze.start.getY()-maze.exit.getY());
            outputHelper.writeToFile(outputFileName,solution,maze.is_discovered,distance);

            //solve with bfs
            maze.resetMaze();
            //maze = new Maze(fileName);
            solution = bfs.explore(maze,0);
            outputFileName = outputBasePath+"/labyrinth_example_"+i+"_bfs_out.txt";
            distance = 0;
            if(solution.size()>0)
                distance = Math.abs(maze.start.getX()-maze.exit.getX())+Math.abs(maze.start.getY()-maze.exit.getY());
            outputHelper.writeToFile(outputFileName,solution,maze.is_discovered,distance);

            //solve with a*
            maze.resetMaze();
            solution = astar.explore(maze,0);
            outputFileName = outputBasePath+"/labyrinth_example_"+i+"_a_star_out.txt";
            distance = 0;
            if(solution.size()>0)
                distance = solution.size()-1;
            outputHelper.writeToFile(outputFileName,solution,maze.is_discovered,distance);
        }

        for (int i=1; i<NUMBER_OF_FILES_MOUNTAIN; i++){
            String fileName = mountainPath+"/mountain_example_"+i+".txt";
            Mountain maze = new Mountain(fileName);
            //solve with dfs
            List<Coordinate> solution = dfs.explore(maze,1);
            String outputFileName = outputBasePath+"/mountain_example_"+i+"_dfs_out.txt";
            int x1 = maze.start.getX();
            int y1 = maze.start.getY();
            int x2 = maze.exit.getX();
            int y2 = maze.exit.getY();
            double distance = euclidean(x1,y1,maze.points[x1][y1],x2,y2,maze.points[x2][y2]);
            outputHelper.writeToFile(outputFileName,solution,maze.is_discovered,distance);

            //solve with bfs
            maze.resetMaze();
            //maze = new Maze(fileName);
            solution = bfs.explore(maze,1);
            outputFileName = outputBasePath+"/mountain_example_"+i+"_bfs_out.txt";
            x1 = maze.start.getX();
            y1 = maze.start.getY();
            x2 = maze.exit.getX();
            y2 = maze.exit.getY();
            distance = euclidean(x1,y1,maze.points[x1][y1],x2,y2,maze.points[x2][y2]);
            outputHelper.writeToFile(outputFileName,solution,maze.is_discovered,distance);


        }


    }

    public static long getFileInformation(String path) throws Exception {
        try (Stream<Path> files = Files.list(Paths.get(path))) {
            return files.count();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static double euclidean(int x1, int y1, double h1, int x2, int y2, double h2){
        double A= Math.pow((x1-x2),2);
        double B= Math.pow((y1-y2),2);
        double C= Math.pow((h1-h2),2);
        return Math.sqrt(A+B+C);
    }
}
