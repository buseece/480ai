import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Maze {

    int NUMBER_OF_ROWS;
    int NUMBER_OF_COLS;
    int points[][] = null;
    Coordinate start;
    Coordinate exit;
    //List<Coordinate> explored;
    int is_discovered[][] = null;
    //int index[][] = null;

    public Maze(){

    }

    public Maze(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        //get start point and exit point
        this.start = new Coordinate();
        this.start.setX(Integer.parseInt(sc.next()));
        this.start.setY(Integer.parseInt(sc.next()));
        this.exit = new Coordinate();
        this.exit.setX(Integer.parseInt(sc.next()));
        this.exit.setY(Integer.parseInt(sc.next()));
        sc.nextLine();
        //get maze
        int row=0;
        List<String> listMaze = new ArrayList<String>();
        while (sc.hasNextLine()){
            listMaze.add(sc.nextLine());
            row++;
//            StringTokenizer st1 = new StringTokenizer(sc.nextLine());
//            int col=0;
//            while(st1.hasMoreTokens()){
//                this.points[row][col] = Integer.parseInt(st1.nextToken());
//                col++;
//            }
//            row++;
        }
        String [] tokens = listMaze.get(0).split(" ");
        int col = tokens.length;
        points = new int[row][col];
        is_discovered = new int[row][col];
        //index = new int[row][col];
        for(int i=0; i<row; i++){
            StringTokenizer st1 = new StringTokenizer(listMaze.get(i));
            int j=0;
            while(st1.hasMoreTokens()){
                this.points[i][j] = Integer.parseInt(st1.nextToken());
                j++;
            }

        }
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                //System.out.print(this.points[i][j]+" ");
                this.is_discovered[i][j] = 0;
                //this.index[i][j] = 0;
            }
            //System.out.println();
        }
        //System.out.println("row "+row);
        //System.out.println("col "+ col);
        this.NUMBER_OF_ROWS = row;
        this.NUMBER_OF_COLS = col;
        //this.explored = new ArrayList<>();

    }

    public boolean isValidLocation(int row, int col){
        return (row>=0 && row<this.NUMBER_OF_ROWS && col>=0 && col<this.NUMBER_OF_COLS);
    }

    public boolean isImpossible(int h1, int h2){
        return Math.abs(h1-h2)>1;
    }

    public boolean isWall(int row, int col){
        return (this.points[row][col] == 1);
    }

    public boolean isExplored(int row, int col){
        return (this.is_discovered[row][col]==1);
        /*for(int i=0; i< this.explored.size(); i++){
            Coordinate temp = this.explored.get(i);
            if(temp.getX()==row && temp.getY() == col)
                return true;
        }
        return false;*/
    }

    public void resetMaze(){
        for(int i=0; i<this.NUMBER_OF_ROWS; i++){
            for(int j=0; j<this.NUMBER_OF_COLS; j++){
                this.is_discovered[i][j] = 0;


            }
        }
    }




}
