package advent;

import java.io.File;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class day17 {
  public int leastDist(String fileName, boolean Ultra){//going to use Dijkstra's
    try(Scanner sc = new Scanner(new File(System.getProperty("user.dir") + fileName))){
      List<List<Integer>> values = new ArrayList<>();
      while(sc.hasNextLine()){
        String s = sc.nextLine();
        List<Integer> toAdd = new ArrayList<>();
        for(int i =0;i<s.length();++i){
          toAdd.add(s.charAt(i)-'0');
        }
        values.add(toAdd);
      }
      return Dijkstra(0,0,values.size()-1, values.get(0).size()-1, 3, values, Ultra);

    }

    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }
  public int Dijkstra(int startR, int startC, int endR, int endC, int MaxDistance, List<List<Integer>> grid, boolean ultra){
    node[][] smallest = new node[grid.size()][grid.get(0).size()];
    PriorityQueue<node> queue = new PriorityQueue<>(Comparator.comparingInt(node::getValue));
    Set<node> seen = new HashSet<>();

    node current = new node(startR, startC);
    smallest[startR][startC] = current;
    int neededValue = (endR+1)*(endC+1)-1;
    while(neededValue >0){
        seen.add(current);
        int r = current.getRow();
        int c = current.getCol();

        if (ultra? ValidUltra(current, "R", MaxDistance, grid) : (Valid(current, "R", MaxDistance, grid))) {
          node possible = new node(r, c + 1, current.getPath(), current.getValue() + grid.get(r).get(c + 1));
          possible.addPath("R");
          if(!seen.contains(possible))
            queue.add(possible);

        }
        if (ultra? ValidUltra(current, "L", MaxDistance, grid) : (Valid(current, "L", MaxDistance, grid))) {
          node possible = new node(r, c - 1, current.getPath(), current.getValue() + grid.get(r).get(c - 1));
          possible.addPath("L");
          if(!seen.contains(possible))
            queue.add(possible);
        }
        if (ultra? ValidUltra(current, "U", MaxDistance, grid) : (Valid(current, "U", MaxDistance, grid))) {
          node possible = new node(r - 1, c, current.getPath(), current.getValue() + grid.get(r - 1).get(c));
          possible.addPath("U");
          if(!seen.contains(possible))
            queue.add(possible);
        }
        if (ultra? ValidUltra(current, "D", MaxDistance, grid) : (Valid(current, "D", MaxDistance, grid))) {
          node possible = new node(r + 1, c, current.getPath(), current.getValue() + grid.get(r + 1).get(c));
          possible.addPath("D");
          if(!seen.contains(possible))
            queue.add(possible);
        }
      while(seen.contains(current)) current = queue.remove();


//      if(smallest[current.getRow()][current.getCol()] == null ) neededValue--;
//      if(smallest[current.getRow()][current.getCol()] == null || current.getValue() < smallest[current.getRow()][current.getCol()].getValue()){
//        smallest[current.getRow()][current.getCol()] = current;
//      }
      if(current.getRow() == endR && current.getCol() == endC){
        return current.getValue();
      }
    }
    return smallest[endR][endC].getValue();
  }
  public boolean ValidUltra(node current, String dirc, int MaxDistance, List<List<Integer>> values){
    boolean empty = current.getPath().length() ==0;
    if(dirc.equals("R") && !current.getPath().contains("L")) {
      if (current.getCol() + 1 == values.get(0).size()) return false;
      if (current.getPath().contains("R") || empty) return current.getPath().length()<10;
      return current.getPath().length()>3;
    }
    if(dirc.equals("L") && !current.getPath().contains("R")) {
      if (current.getCol() - 1 == -1) return false;
      if (current.getPath().contains("L") || empty) return current.getPath().length()<10;
      return current.getPath().length()>3;
    }
    if(dirc.equals("U") && !current.getPath().contains("D")) {
      if (current.getRow() - 1 == -1) return false;
      if (current.getPath().contains("U") || empty) return current.getPath().length()<10;
      return current.getPath().length()>3;
    }
    if(dirc.equals("D") && !current.getPath().contains("U")) {
      if (current.getRow() + 1 == values.size()) return false;
      if (current.getPath().contains("D")|| empty) return current.getPath().length()<10;
      return current.getPath().length()>3;
    }
    return false;
  }
  public boolean Valid(node current, String dirc, int MaxDistance, List<List<Integer>> values){
    if(dirc.equals("R") && !current.getPath().contains("L"))
      return (current.getCol()+1 != values.get(0).size() &&
              !current.getPath().equals("R".repeat(MaxDistance)));
    if(dirc.equals("L") && !current.getPath().contains("R"))
      return (current.getCol()-1 != -1 &&
              !current.getPath().equals("L".repeat(MaxDistance)));
    if(dirc.equals("U") && !current.getPath().contains("D"))
      return (current.getRow()-1 != -1 &&
              !current.getPath().equals("U".repeat(MaxDistance)));
    if(dirc.equals("D") && !current.getPath().contains("U"))
      return (current.getRow()+1 != values.size() &&
              !current.getPath().equals("D".repeat(MaxDistance)));
    return false;
  }
  public class node{
    private int value = 0;
    private String path = "";
    private int row;
    private int col;

    public node(int row, int col) {
      this.row = row;
      this.col = col;
    }

    public node(int row, int col, String path, int value) {
      this.value = value;
      this.path = path;
      this.row = row;
      this.col = col;
    }

    public int getRow() {
      return row;
    }

    public int getCol() {
      return col;
    }

    public int getValue() {
      return value;
    }

    public String getPath() {
      return path;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      node node = (node) o;
      return  row == node.row && col == node.col && path.equals(node.path);
    }
    public void addPath(String letter){
      path = path.contains(letter) ?
        path + letter: letter;

    }

    @Override
    public int hashCode() {
      return Objects.hash(row, col, path);
    }
  }
}
