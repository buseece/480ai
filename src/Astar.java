
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


public class Astar {

    private static int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public Astar(){

    }


    public Coordinate getNextCoordinate(int row, int col, int i, int j) {
        return new Coordinate(row + i, col + j);
    }

    public List<Coordinate> explore(Maze maze, int type){
        List<Coordinate> openList = new ArrayList<>();
        List<Coordinate> closedList = new ArrayList<>();
        //List<Coordinate> path = new ArrayList<>();
        maze.start.g =0;
        maze.start.f =0;
        maze.start.h =0;
        openList.add(maze.start);
        while (!openList.isEmpty()){
            Collections.sort(openList,Coordinate.BY_F); //sort ascending
            double minF = openList.get(0).f;
            List<Coordinate> onlyMinF = new ArrayList<>();
            for(int i=0; i<openList.size(); i++){
                if(openList.get(i).f == minF)
                    onlyMinF.add(openList.get(i));
            }
            if(onlyMinF.size()>1){
                Collections.sort(onlyMinF,Coordinate.BY_INDEX_D); //gets the higher index
            }
            Coordinate current = onlyMinF.get(0);
            openList.remove(current);
            //path.add(current);
            //System.out.print(current.getX()+","+current.getY()+" ");
            maze.is_discovered[current.getX()][current.getY()] = 1;

            //if it is the exit node, exit from the function
            if(current.getX() == maze.exit.getX() && current.getY() == maze.exit.getY()){
                //return path;
                maze.exit = current;
                break;
            }

            //List<Coordinate> adjacents = new ArrayList<>();

            //get its successors
            for(int[] direction: DIRECTIONS){
                Coordinate newCoordinate = getNextCoordinate(current.getX(),current.getY(),direction[0],direction[1]);
                if(maze.isValidLocation(newCoordinate.getX(),newCoordinate.getY())){
                    if(!maze.isWall(newCoordinate.getX(),newCoordinate.getY())){
                        double newG = current.g+1;
                        double newH = getH(maze.exit.getX(),maze.exit.getY(),newCoordinate.getX(),newCoordinate.getY());
                        double newF= newG + newH;
                        newCoordinate.index = newCoordinate.getX()*maze.NUMBER_OF_COLS + newCoordinate.getY();
                        if(maze.is_discovered[newCoordinate.getX()][newCoordinate.getY()] == 0){
                            Coordinate oldCoordinate = containsXY(openList, newCoordinate);
                            if( oldCoordinate !=null){
                                //we already added this coordinate to the list, check if current f value is less than already considered value
                                if(oldCoordinate.f < newF){
                                    //skip this coordinate
                                }else{
                                    newCoordinate.g = newG;
                                    newCoordinate.h = newH;
                                    newCoordinate.f = newF;
                                    newCoordinate.setParent(current);
                                    openList.add(newCoordinate);
                                }
                            }else{
                                newCoordinate.g = newG;
                                newCoordinate.h = newH;
                                newCoordinate.f = newF;
                                newCoordinate.setParent(current);
                                openList.add(newCoordinate);
                            }
                        }else{
                            //even if it is visited, if new f is less than the old value, add it to open list
                            Coordinate oldCoordinate = containsXY(closedList, newCoordinate);
                            if( oldCoordinate !=null){
                                //we already added this coordinate to the list, check if current f value is less than already considered value
                                if(oldCoordinate.f < newF){
                                    //skip this coordinate
                                }else{
                                    newCoordinate.g = newG;
                                    newCoordinate.h = newH;
                                    newCoordinate.f = newF;
                                    newCoordinate.setParent(current);
                                    openList.add(newCoordinate);
                                }
                            }else{
                                System.out.println("impossible");
                            }
                        }


                    }
                }
            }
            closedList.add(current);

        }
        Coordinate parentCor = maze.exit;
        List<Coordinate> realPath = new ArrayList<>();
        while(!(parentCor.getX()==maze.start.getX() && parentCor.getY() == maze.start.getY())){
            realPath.add(parentCor);
            parentCor = parentCor.getParent();
        }
        realPath.add(parentCor);

        return reversePath(realPath);
        //return path;
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

    public double getH(int x1, int y1, int x2, int y2){
        return Math.abs(x1-x2)+Math.abs(y1-y2);
    }

    public Coordinate containsXY(List<Coordinate> openList, Coordinate check){
        for (Coordinate c: openList) {
            if((c.getX() == check.getX()) && (c.getY() == check.getY()))
                return c;
        }
        return null;
    }


}
