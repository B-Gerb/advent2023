package advent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.AbstractList;

public class day16 {
  public int allTravel(String fileName) {
    try (Scanner sc = new Scanner(new File(System.getProperty("user.dir") + fileName))) {
      List<List<Character>> grid = new ArrayList<>();
      List<List<visitor>> visitingPath = new ArrayList<>();
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        List<Character> toAddGrid = new ArrayList<>();
        List<visitor> toAddPath = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
          toAddGrid.add(s.charAt(i));
          toAddPath.add(new visitor(false, ""));
        }
        grid.add(toAddGrid);
        visitingPath.add(toAddPath);
      }
      int max = -1;
      for(int i=0;i<grid.size(); ++i){
        List<List<visitor>> shallowVisit1 = new ArrayList<>();
        List<List<visitor>> shallowVisit2 = new ArrayList<>();
        for (List<visitor> visitors : visitingPath) {
          shallowVisit1.add(new ArrayList<>(visitors));
          shallowVisit2.add(new ArrayList<>(visitors));
        }
        travel(grid, shallowVisit1, i, 0, "R");
        travel(grid, shallowVisit2, i, grid.get(0).size()-1, "L");
        max = Math.max(max, allCount(shallowVisit1));
        max = Math.max(max, allCount(shallowVisit2));


      }
      for(int i=0;i<grid.get(0).size(); ++i){
        List<List<visitor>> shallowVisit1 = new ArrayList<>();
        List<List<visitor>> shallowVisit2 = new ArrayList<>();
        for (List<visitor> visitors : visitingPath) {
          shallowVisit1.add(new ArrayList<>(visitors));
          shallowVisit2.add(new ArrayList<>(visitors));
        }
        travel(grid, shallowVisit1, 0, i, "D");
        travel(grid, shallowVisit2,grid.size()-1, i, "U");
        max = Math.max(max, allCount(shallowVisit1));
        max = Math.max(max, allCount(shallowVisit2));
      }
      return max;
    }
    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }
  public int allCount(List<List<visitor>> counting){
    int total = 0;
    for(int i=0; i<counting.size(); ++i){
      for (visitor booleans : counting.get(i)) {
        total += booleans.isVisited() ? 1 : 0;
      }
    }


    return total;
  }
  public int  reflectionTravel(String fileName){
    try(Scanner sc = new Scanner(new File( System.getProperty("user.dir")+ fileName))) {
      List<List<Character>> grid = new ArrayList<>();
      List<List<visitor>> visitingPath = new ArrayList<>();
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        List<Character> toAddGrid = new ArrayList<>();
        List<visitor> toAddPath = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
          toAddGrid.add(s.charAt(i));
          toAddPath.add(new visitor(false, ""));
        }
        grid.add(toAddGrid);
        visitingPath.add(toAddPath);
      }
      travel(grid, visitingPath, 0, 0, "R");
      return allCount(visitingPath);
    }

    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }
  Map<String, String> backSlash = Map.of( // blackSlash = /
          "R", "U",
          "L", "D",
          "U", "R",
          "D", "L"
  );
  Map<String, String> backSlashReceive = Map.of( // blackSlash = /
          "R", "RD",
          "L", "LU",
          "U", "UL",
          "D", "RD"
  );
  Map<String, String> forwardSlashReceive = Map.of( // fowardslash = \
          "R", "RU",
          "L", "LD",
          "U", "UR",
          "D", "LD"
  );
  Map<String, String> fowardSlash = Map.of( // fowardSlash = \
          "R", "D",
          "L", "U",
          "U", "L",
          "D", "R"
  );
  public void travel(List<List<Character>> grid, List<List<visitor>> visitingPath, int row, int col, String dirc) {
    boolean leave = false;
    while (!leave && row >= 0 && row < grid.size() && col >= 0 && col < grid.get(0).size()) {
      if (grid.get(row).get(col) != '.' && (visitingPath.get(row).get(col).isVisited()
              && visitingPath.get(row).get(col).getDirc().contains(dirc))) leave = true;
      else {
        boolean action = false;
        if (grid.get(row).get(col) == '-' && (dirc.equals("U") || dirc.equals("D"))) {
          action = true;
          visitingPath.get(row).set(col, new visitor(true, dirc+visitingPath.get(row).get(col).getDirc()));
          travel(grid, visitingPath, row , col-1, "L");
          travel(grid, visitingPath, row, col+1, "R");
          leave = true;
        }
        if (grid.get(row).get(col) == '|' && (dirc.equals("R") || dirc.equals("L"))) {
          action = true;
          visitingPath.get(row).set(col, new visitor(true, dirc+visitingPath.get(row).get(col).getDirc()));
          travel(grid, visitingPath, row-1, col , "U");
          travel(grid, visitingPath, row+1, col, "D");
          leave = true;
        }
        if(grid.get(row).get(col) == '/'){
          visitingPath.get(row).set(col, new visitor(true, backSlashReceive.get(dirc)));

          dirc = backSlash.get(dirc);
        }
        if(grid.get(row).get(col) == '\\'){
          visitingPath.get(row).set(col, new visitor(true, forwardSlashReceive.get(dirc)));

          dirc = fowardSlash.get(dirc);
        }
        if (!action) {
          visitingPath.get(row).set(col, new visitor(true, visitingPath.get(row).get(col).getDirc()));
          if (dirc.equals("L")) --col;
          if (dirc.equals("R")) ++col;
          if (dirc.equals("U")) --row;
          if (dirc.equals("D")) ++row;
        }
      }
    }
  }
  private class visitor{
    private boolean visited;
    private String dirc;

    public visitor(boolean visited, String dirc) {
      this.visited = visited;
      this.dirc = dirc;
    }

    public boolean isVisited() {
      return visited;
    }

    public void setVisited(boolean visited) {
      this.visited = visited;
    }

    public String getDirc() {
      return dirc;
    }

    public void setDirc(String dirc) {
      this.dirc = dirc;
    }
  }
}
