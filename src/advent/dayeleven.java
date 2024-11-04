package advent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class dayeleven {
  List<Integer> rows = new ArrayList<>();
  List<Integer> cols= new ArrayList<>();
  public long shortPaths(String filename, int extra){
    try(Scanner sc = new Scanner(new File(filename))){
      Map<Integer, pairs> galaxies = new HashMap<>();
      int rowNum = 0;
      int galaxyNumber = 0;
      boolean first = true;
      boolean[] columns = new boolean[0];
      while(sc.hasNextLine()){
        String s = sc.nextLine();
        if(first){
          columns = new boolean[s.length()];
          first = false;
        }
        if(s.contains("#")){
          int starSpot = s.indexOf("#");
          while(starSpot != -1){
            s = s.substring(0,starSpot) + "." + s.substring(starSpot+1);
            galaxies.put(galaxyNumber++, new pairs(rowNum, starSpot));
            columns[starSpot] = true;
            starSpot = s.indexOf("#");
          }
        }
        else{
          rows.add(rowNum);
        }
        rowNum++;
      }
      for(int i =0; i<columns.length; ++i) {
        if (!columns[i]) cols.add(i);
      }
      long total = 0;
      for(int i =0;i<galaxyNumber-1; ++i){
        for(int j=i+1; j<galaxyNumber;++j){
          total += galaxies.get(i).distance(galaxies.get(j), extra);
        }
      }
      return total;


    }
    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }
  public class pairs{
    final int row;
    final int col;


    public pairs(int row, int col) {
      this.row = row;
      this.col = col;
    }
    public int distance(pairs other, int extra){
      int add = 0;
      for(Integer s : rows){
        if((other.row > s && row < s) || (other.row < s && row > s)) ++add;
      }
      for(Integer c : cols){
        if((other.col > c && col < c) || (other.col < c && col > c)) ++add;
      }
      return Math.abs(other.row- row) +Math.abs(other.col- col)+(add*(extra-1));
    }

  }
}
