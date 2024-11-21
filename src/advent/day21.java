package advent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class day21 {
  public int possibleGardens(String fileNames, int steps){
    try(Scanner sc = new Scanner(new File(System.getProperty("user.dir") + fileNames))){
      List<String> spots = new ArrayList<>();
      Cords start = new Cords(-1,-1);
      while(sc.hasNextLine()){
        String s = sc.nextLine();
        if(s.contains("S")){
          start = new Cords(spots.size(), s.indexOf("S"));
          s = s.replace("S", ".");
        }
        spots.add(s);
      }
      Set<Cords> poss = new HashSet<>();
      poss.add(start);
      for(int i =0; i<steps; ++i){
        Set<Cords> next = new HashSet<>();
        for(Cords c : poss){
          if(c.row>0 && spots.get(c.row-1).charAt(c.col) == '.') next.add(new Cords(c.row-1, c.col));
          if(c.row<spots.size()-1 && spots.get(c.row+1).charAt(c.col) == '.') next.add(new Cords(c.row+1, c.col));
          if(c.col>0 && spots.get(c.row).charAt(c.col-1) == '.') next.add(new Cords(c.row, c.col-1));
          if(c.col<spots.get(0).length()-1 && spots.get(c.row).charAt(c.col+1) == '.') next.add(new Cords(c.row, c.col+1));

        }
        poss = next;
      }
      return poss.size();
    }

    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }
  public int correct(int smaller, int mod){
    int val = smaller%mod;
    if(val<0) return mod+val;
    return val;
  }
  public long simulation(String fileNames, int steps){
    try(Scanner sc = new Scanner(new File(System.getProperty("user.dir") + fileNames))) {

      List<String> spots = new ArrayList<>();
      Cords start = new Cords(-1, -1);
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.contains("S")) {
          start = new Cords(spots.size(), s.indexOf("S"));
          s = s.replace("S", ".");
        }
        spots.add(s);
      }
      long first  = infiniteGardens(new ArrayList<>(spots), start, 65);
      long second  = infiniteGardens(new ArrayList<>(spots), start, 196);
      long third  = infiniteGardens(new ArrayList<>(spots), start, 327);
      long a = (third-(2*second)+first)/2;
      long b = second-first-a;
      long c = first;
      steps = steps/131;
      return (a*((long) Math.pow(steps, 2)) +(b*steps)+c);

    }
    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }
  public long infiniteGardens(List<String> spots, Cords start, int steps){
      Set<Cords> poss = new HashSet<>();
      poss.add(start);
      for(int i =0; i<steps; ++i){
        Set<Cords> next = new HashSet<>();
        for(Cords c : poss){
          int leftc = correct(c.col-1, spots.get(0).length());
          int rightc = correct(c.col+1, spots.get(0).length());
          int leftr = correct(c.row-1, spots.size());
          int rightr = correct(c.row+1, spots.size());
          int correctc = correct(c.col, spots.get(0).length());
          int correctr = correct(c.row, spots.size());

          if(spots.get(leftr).charAt(correctc) == '.') next.add(new Cords(c.row-1, c.col));
          if(spots.get(rightr).charAt(correctc) == '.') next.add(new Cords(c.row+1, c.col));
          if(spots.get(correctr).charAt(leftc) == '.') next.add(new Cords(c.row, c.col-1));
          if(spots.get(correctr).charAt(rightc) == '.') next.add(new Cords(c.row, c.col+1));

        }
        poss = next;
      }
      return poss.size();

  }
  public class Cords{
    int row;
    int col;

    public Cords(int row, int col) {
      this.row = row;
      this.col = col;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Cords cords = (Cords) o;
      return row == cords.row && col == cords.col;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, col);
    }
  }
}
