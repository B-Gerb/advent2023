package advent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class dayten {
  /*
  | is a vertical pipe connecting north and south.
- is a horizontal pipe connecting east and west.
L is a 90-degree bend connecting north and east.
J is a 90-degree bend connecting north and west.
7 is a 90-degree bend connecting south and west.
F is a 90-degree bend connecting south and east.
. is ground; there is no pipe in this tile.
S is the starting position of the animal;
 there is a pipe on this tile, but your sketch doesn't show what shape the pipe has.
   */
  public static int farthestDist(String fileName, int part){
    try(Scanner sc = new Scanner(new File(fileName))){
      List<List<String>> grid = new ArrayList<>();
      int row = 0;
      int colum = 0;
      while(sc.hasNextLine()){
        String s = sc.nextLine();
        List<String> line1 = new ArrayList<>();
        for(int i =0; i<s.length(); ++i) {
          if (s.charAt(i) != ' ') {
            if (s.charAt(i) == 'S') {
              row = grid.size();
              colum = i;
            }
            line1.add(s.substring(i, i + 1));

          }
        }
        grid.add(line1);
      }
      Map<String, String> opposite = new HashMap<>();
      opposite.put("L", "R");
      opposite.put("R", "L");
      opposite.put("U", "D");
      opposite.put("D", "U");
      List<cordinates> vectors = new ArrayList<>();


      int newR = row;
      int newC = colum;
      int dist = 1;
      String prev = "";

      String firstDirection = dayten.direction(grid, row, colum, prev);

      while(newR != row || newC != colum || dist ==1) {
        String s =grid.get(newR).get(newC);
        if(!s.equals("-") && !s.equals("|")) vectors.add(new cordinates(newR, newC));

        if (firstDirection.equals("L")) {
          newC--;
        }
        if (firstDirection.equals("R")) {
          newC++;
        }
        if (firstDirection.equals("U")) {
          newR--;
        }
        if (firstDirection.equals("D")) {
          newR++;
        }
        prev = opposite.get(firstDirection);
        firstDirection = dayten.dirc(grid, newR, newC, prev);
        dist++;
      }

      if(part == 1) return dist/2;
      int total = 0;
      // shoelace
      for (int i = 0; i < vectors.size(); i++) {
         total +=(vectors.get(i).getY()*vectors.get((i+1)%vectors.size()).getX()) -
                 (vectors.get(i).getX()*vectors.get((i+1)%vectors.size()).getY());

      }

      return Math.abs(total/2)-dist/2+1;


    }
    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }
  public static class cordinates{
    private final int x;
    private final int y;

    public cordinates(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }

  }
  public static String direction(List<List<String>> grid, int row, int col, String prev){
    Map<String, String> opposite = new HashMap<>();
    opposite.put("L", "R");
    opposite.put("R", "L");
    opposite.put("U", "D");
    opposite.put("D", "U");
    if(opposite.containsKey(prev)){
      prev = opposite.get(prev);
    }
    if(row  >0&& !prev.equals("U")) { //Subtarct U
      String s = grid.get(row - 1).get(col);
      if (s.equals("|") || s.equals("7") || s.equals("F") || s.equals("S")) {
        return "U";
      }
    }
    if(col < grid.get(0).size()-1 && !prev.equals("R")){ // add R
      String s = grid.get(row).get(col+1);
      if(s.equals("-") || s.equals("J") || s.equals("7") || s.equals("S")){
        return "R";
      }
    }
    if(row < grid.size()-1 && !prev.equals("D")){ // ADD D
      String s = grid.get(row+1).get(col);
      if(s.equals("|") || s.equals("L") || s.equals("J") || s.equals("S")){
        return "D";
      }
    }
    if(col > 0 && !prev.equals("L")){// subtract L
      String s = grid.get(row).get(col-1);
      if(s.equals("-") || s.equals("L") || s.equals("F")|| s.equals("S")){
        return "L";
      }
    }

    return "Error";

  }
  public static String dirc(List<List<String>> grid, int row, int col, String prev){
    String s = grid.get(row).get(col);
    if(s.equals("|")){
      if(prev.equals("U")) return "D";
      else return "U";
    }
    if(s.equals("-")){
      if(prev.equals("R")) return "L";
      else return "R";
    }
    if(s.equals("L")){
      if(prev.equals("U")) return "R";
      else return "U";
    }
    if(s.equals("J")){
      if(prev.equals("U")) return "L";
      else return "U";
    }
    if(s.equals("7")){
      if(prev.equals("D"))return "L";
      else return "D";
    }
    if(s.equals("F")){
      if(prev.equals("D")) return "R";
      else return "D";
    }
    return "Error";
  }

}
