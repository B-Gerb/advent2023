package advent;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class day23 {
  Set<pairC> distance =  new HashSet<>();

  public int ReverseDijkstra(String fileName){
    try(Scanner sc = new Scanner((new File(System.getProperty("user.dir") + fileName)))){
      List<String> rows = new ArrayList<>();
      while(sc.hasNextLine()){
        rows.add(sc.nextLine());
      }
      Set<Path> Path = new HashSet<>();
      int startC  = rows.get(0).indexOf(".");
      int endC = rows.get(rows.size()-1).indexOf(".");
      int endR = rows.size()-1;
      Set<cords> curPath = new HashSet<>();

      curPath.add(new cords(0, startC));

      PriorityQueue<Path> values = new PriorityQueue<>((a,b) -> (-1*Integer.compare(a.path.size(), b.path.size())));
      values.add(new Path(curPath, new cords(0, startC)));
      int max = -1;
      while(values.size() != 0){
        Path current = values.remove();
        if(current.current.row == endR && current.current.col == endC){
          max = Math.max(max, current.path.size()-1);
        }
        List<cords> possible = current.possible(rows);
        for (cords cords : possible) {
          Set<cords> path = new HashSet<>(current.path);
          path.add(cords);
          values.add(new Path(path, cords));
        }
      }
      return max;



    }
    catch (Exception e){
      System.out.printf("e");
      return -1;
    }
  }
  public int ReverseDijkstraModify(String fileName){ // with memization
    try(Scanner sc = new Scanner((new File(System.getProperty("user.dir") + fileName)))){
      List<String> rows = new ArrayList<>();
      while(sc.hasNextLine()){
        String s = sc.nextLine();
        s = s.replaceAll("<", ".");
        s = s.replaceAll(">", ".");
        s = s.replaceAll("v", ".");

        rows.add(s);
      }
      Set<cords> points = new HashSet<>();
      for(int i=0;i<rows.size(); ++i){
        for(int j=0; j<rows.get(0).length(); ++j){
          if(rows.get(i).charAt(j) != '#') {
            cords tempC = new cords(i,j);
            List<cords> amt = (new Path(new HashSet<>(), tempC)).possible(rows);

            if(amt.size()>2) points.add(new cords(i, j));
          }

        }
      }
      int startC  = rows.get(0).indexOf(".");
      int endC = rows.get(rows.size()-1).indexOf(".");
      int endR = rows.size()-1;
      points.add(new cords(0, startC));
      points.add(new cords(endR, endC));


      for(cords p : points){
        Set<cords> seen = new HashSet<>();
        seen.add(p);
        List<Path> values = new ArrayList<>();
        Path curP  = new Path(seen, p);
        values.add(curP);
        while(values.size() != 0){
          Path current = values.remove(0);
          if(!p.equals(current.current) && points.contains(current.current)){
            distance.add(new pairC(p, current.current, current.path.size()-1));
          }
          else {
            List<cords> allP = current.possible(rows);
            for (cords cords : allP) {
              Set<cords> path = new HashSet<>(current.path);
              path.add(cords);
              values.add(new Path(path, cords));
            }
          }
        }
      }
      Set<cords> seen = new HashSet<>();
      return depthFirst(new cords(0, startC), seen, new cords(endR, endC));





    }
    catch (Exception e){
      System.out.printf("e");
      return -1;
    }
  }
  public int depthFirst(cords pt, Set<cords> seen, cords end){
    if(pt.equals(end)) return 0;
    int max = -9999999;
    seen.add(pt);
    for(pairC pair : distance){
      if(pair.contains(pt) && !seen.contains(pair.othercontains(pt))){
        max = Math.max(max, pair.length + depthFirst(pair.othercontains(pt), seen, end));
      }
    }
    seen.remove(pt);
    return max;


  }
  public class pairC{
    cords a;
    cords b;

    int length;
    public boolean contains(cords a){
      return this.a.equals(a) || this.b.equals(a);
    }
    public cords othercontains(cords a){
      return this.a.equals(a) ? b : this.a;
    }

    public pairC(cords a, cords b, int length) {
      this.a = a;
      this.b = b;
      this.length = length;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      pairC pairC = (pairC) o;
      return (Objects.equals(a, pairC.a) && Objects.equals(b, pairC.b)) ||(Objects.equals(a, pairC.b) && Objects.equals(b, pairC.a));
    }

    @Override
    public int hashCode() {
      return Objects.hash(a) + Objects.hash(b);
    }
  }
  public class Path{
    Set<cords> path;
    cords current;

    public Path(Set<cords> path, cords current) {
      this.path = path;
      this.current = current;
    }
    public List<cords> possible( List<String> inputs){
      int row = current.row;
      int col = current.col;
      char c = inputs.get(row).charAt(col);
      List<cords> possible = new ArrayList<>();
      if(c == '>'){
        if(col != inputs.get(0).length()-1 && inputs.get(row).charAt(col+1) != '#' && inputs.get(row).charAt(col+1) != '<'){
          cords cord  = new cords(row, col+1);
          if(!path.contains(cord)) possible.add(cord);
        }
        return possible;
      }
      if(c == '<'){
        if(col != 0 && inputs.get(row).charAt(col-1) != '#' && inputs.get(row).charAt(col-1) != '>'){
          cords cord  = new cords(row, col-1);
          if(!path.contains(cord)) possible.add(cord);
        }
        return possible;
      }
      if(c == '^'){
        if(row != 0 && inputs.get(row-1).charAt(col) != '#' && inputs.get(row-1).charAt(col) != 'v'){
          cords cord  = new cords(row-1, col);
          if(!path.contains(cord)) possible.add(cord);
        }
        return possible;
      }
      if(c == 'v'){
        if(row != inputs.size()-1 && inputs.get(row+1).charAt(col) != '#' && inputs.get(row+1).charAt(col) != '^'){
          cords cord  = new cords(row+1, col);
          if(!path.contains(cord)) possible.add(cord);
        }
        return possible;
      }
      if(col != inputs.get(0).length()-1 && inputs.get(row).charAt(col+1) != '#' && inputs.get(row).charAt(col+1) != '<'){
        cords cord  = new cords(row, col+1);
        if(!path.contains(cord)) possible.add(cord);
      }
      if(col != 0 && inputs.get(row).charAt(col - 1) != '#' && inputs.get(row).charAt(col - 1) != '>') {
          cords cord = new cords(row, col - 1);
          if (!path.contains(cord)) possible.add(cord);
        }
      if(row != 0 && inputs.get(row-1).charAt(col) != '#' && inputs.get(row-1).charAt(col) != 'v'){
        cords cord  = new cords(row-1, col);
        if(!path.contains(cord)) possible.add(cord);
      }
      if(row != inputs.size()-1 && inputs.get(row+1).charAt(col) != '#' && inputs.get(row+1).charAt(col) != '^'){
        cords cord  = new cords(row+1, col);
        if(!path.contains(cord)) possible.add(cord);
      }
      return possible;

    }


    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Path path1 = (Path) o;
      return Objects.equals(path, path1.path) && Objects.equals(current, path1.current);
    }

    @Override
    public int hashCode() {
      return Objects.hash(path, current);
    }
  }
  public class cords{
    int row;
    int col;

    public cords(int row, int col) {
      this.row = row;
      this.col = col;
    }


    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      cords cords = (cords) o;
      return row == cords.row && col == cords.col;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, col);
    }
  }




}
