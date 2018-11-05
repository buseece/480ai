import java.util.*;

public class BFS {

    private static int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };


    public BFS(){

    }

    public Coordinate getNextCoordinate(int row, int col, int i, int j) {
        return new Coordinate(row + i, col + j);
    }

    public List<Coordinate> explore(Maze maze, int type){
        //maze.index = setIndex(maze.NUMBER_OF_ROWS,maze.NUMBER_OF_COLS);
        Stack<Coordinate> stack = new Stack<>();
        Queue<Coordinate> queue = new LinkedList<>();
        List<Coordinate> path = new ArrayList<>();
        Coordinate current = maze.start;
        queue.add(current);

        while(!queue.isEmpty()){
            current = queue.peek();
            queue.remove();

            if(maze.is_discovered[current.getX()][current.getY()] == 0){
                maze.is_discovered[current.getX()][current.getY()] = 1; //set node as visited
                path.add(current); //add current node to the path

            }
            //if it is the exit node, exit from the function
            if(current.getX() == maze.exit.getX() && current.getY() == maze.exit.getY()){
                //return path;
                break;
            }

            List<Coordinate> adjacents = new ArrayList<>();

            for(int[] direction: DIRECTIONS){
                Coordinate newCoordinate = getNextCoordinate(current.getX(),current.getY(),direction[0],direction[1]);
                if(maze.isValidLocation(newCoordinate.getX(),newCoordinate.getY())){
                    int h1= maze.points[current.getX()][current.getY()];
                    int h2= maze.points[newCoordinate.getX()][newCoordinate.getY()];
                    if((type==0 && !maze.isWall(newCoordinate.getX(),newCoordinate.getY())) || (type==1 && !maze.isImpossible(h1,h2))){
                        if(maze.is_discovered[newCoordinate.getX()][newCoordinate.getY()] == 0){
                            newCoordinate.index = newCoordinate.getX()*maze.NUMBER_OF_COLS + newCoordinate.getY();
                            adjacents.add(newCoordinate); //list of not visited adjacents
                            if(newCoordinate.getParent() == null){
                                newCoordinate.setParent(current);
                                if(newCoordinate.getX()==maze.exit.getX() && newCoordinate.getY() ==maze.exit.getY()){
                                    maze.exit = newCoordinate;
                                }
                            }
                        }
                    }
                }
            }

            //push unvisited adjacents wrt their indexes
            Collections.sort(adjacents,Coordinate.BY_INDEX_D);

            for(Coordinate c: adjacents){
                queue.add(c);
                //System.out.println(c.getX()+","+c.getY()+" added to queue");

            }

        }

        Coordinate parentCor = maze.exit;
        List<Coordinate> realPath = new ArrayList<>();
        while(!(parentCor.getX()==maze.start.getX() && parentCor.getY() == maze.start.getY())){
            realPath.add(parentCor);
            parentCor = parentCor.getParent();
        }
        realPath.add(parentCor);

        return reversePath(realPath);
    }
    public List<Coordinate> explore(Mountain maze, int type){
        //maze.index = setIndex(maze.NUMBER_OF_ROWS,maze.NUMBER_OF_COLS);
        Stack<Coordinate> stack = new Stack<>();
        Queue<Coordinate> queue = new LinkedList<>();
        List<Coordinate> path = new ArrayList<>();
        Coordinate current = maze.start;
        queue.add(current);

        while(!queue.isEmpty()){
            current = queue.peek();
            queue.remove();

            if(maze.is_discovered[current.getX()][current.getY()] == 0){
                maze.is_discovered[current.getX()][current.getY()] = 1; //set node as visited
                path.add(current); //add current node to the path

            }
            //if it is the exit node, exit from the function
            if(current.getX() == maze.exit.getX() && current.getY() == maze.exit.getY()){
                //return path;
                break;
            }

            List<Coordinate> adjacents = new ArrayList<>();

            for(int[] direction: DIRECTIONS){
                Coordinate newCoordinate = getNextCoordinate(current.getX(),current.getY(),direction[0],direction[1]);
                if(maze.isValidLocation(newCoordinate.getX(),newCoordinate.getY())){
                    double h1= maze.points[current.getX()][current.getY()];
                    double h2= maze.points[newCoordinate.getX()][newCoordinate.getY()];
                    if((type==0 && !maze.isWall(newCoordinate.getX(),newCoordinate.getY())) || (type==1 && !maze.isImpossible(h1,h2))){
                        if(maze.is_discovered[newCoordinate.getX()][newCoordinate.getY()] == 0){
                            newCoordinate.index = newCoordinate.getX()*maze.NUMBER_OF_COLS + newCoordinate.getY();
                            adjacents.add(newCoordinate); //list of not visited adjacents
                            if(newCoordinate.getParent() == null){
                                newCoordinate.setParent(current);
                                if(newCoordinate.getX()==maze.exit.getX() && newCoordinate.getY() ==maze.exit.getY()){
                                    maze.exit = newCoordinate;
                                }
                            }
                        }
                    }
                }
            }

            //push unvisited adjacents wrt their indexes
            Collections.sort(adjacents,Coordinate.BY_INDEX_D);

            /*for(int i=0; i<adjacents.size(); i++){
                System.out.print(adjacents.get(i).index+" ");
            }
            System.out.println();*/
            for(Coordinate c: adjacents){
                queue.add(c);
                //System.out.println(c.getX()+","+c.getY()+" added to queue");

            }

        }

        Coordinate parentCor = maze.exit;
        List<Coordinate> realPath = new ArrayList<>();
        while(!(parentCor.getX()==maze.start.getX() && parentCor.getY() == maze.start.getY())){
            realPath.add(parentCor);
            parentCor = parentCor.getParent();
        }
        realPath.add(parentCor);

        return reversePath(realPath);
    }

    public List<Coordinate> reversePath(List<Coordinate> original){
        Stack<Coordinate> stack = new Stack<>();
        List<Coordinate> reversed = new ArrayList<>();
        for (Coordinate c: original) {
            stack.push(c);
        }
        while(!stack.isEmpty()){
            reversed.add(stack.pop());
        }
        return  reversed;
    }



}
