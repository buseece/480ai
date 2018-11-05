import java.util.Comparator;

/**
 * Created by buseece on 28.10.2018.
 */
public class Coordinate {

    public static final Comparator<Coordinate> BY_INDEX_D = new ByIndexDesc();
    public static final Comparator<Coordinate> BY_INDEX_A = new ByIndexAsc();
    public static final Comparator<Coordinate> BY_F = new ByF();

    private int x;
    private int y;
    private Coordinate parent;
    public int index;
    public double h;
    public double g;
    public double f;

    public Coordinate(){

    }
    public Coordinate(int i, int j){
        this.x = i;
        this.y = j;
    }
    /*public Coordinate(int i, int j, Coordinate parent){
        this.x = i;
        this.y = j;
        this.parent = parent;
    }*/

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setX(int i){
        this.x = i;
    }

    public void setY(int j){
        this.y = j;
    }

    public Coordinate getParent(){ return this.parent;}

    public void setParent(Coordinate parent){ this.parent = parent; }

    /*@Override //DFS
    public int compareTo(Coordinate o) { //TODO:dfs bfs different
        return this.index - o.index;
    }*/
    /*@Override //BFS
    public int compareTo(Coordinate o) { //TODO:dfs bfs different
        return o.index - this.index;
    }*/
    private static class ByIndexDesc implements Comparator<Coordinate> {
        public int compare(Coordinate v, Coordinate w){
            return w.index-v.index; //TODO works descending
        }
    }

    private static class ByIndexAsc implements Comparator<Coordinate> {
        public int compare(Coordinate v, Coordinate w){
            return v.index-w.index; //TODO works ascending
        }
    }

    private static class ByF implements Comparator<Coordinate> {
        public int compare(Coordinate v, Coordinate w){
            return (int) (v.f - w.f); //TODO works ascending
        }
    }
}


