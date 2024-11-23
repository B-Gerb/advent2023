package advent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class day22 {
  public int brickFailing(String fileName){
    try(Scanner sc = new Scanner(new File(System.getProperty("user.dir")+fileName))){
      List<bricks> values = new ArrayList<>();
      while(sc.hasNextLine()){
        String s = sc.nextLine();
        String left = s.split("~")[0];
        String right = s.split("~")[1];

        bricks B = new bricks(Integer.parseInt(left.split(",")[0]),Integer.parseInt(left.split(",")[1]),Integer.parseInt(left.split(",")[2])
        ,Integer.parseInt(right.split(",")[0]),Integer.parseInt(right.split(",")[1]),Integer.parseInt(right.split(",")[2]));
        values.add(B);
      }
      values.sort((a,b) -> a.compareTo(b));



      for(int i=0; i<values.size(); ++i){
        bricks b = values.get(i);
        int min = 1;
        for (int j = 0; j < i; ++j) {
            bricks second = values.get(j);
            if(overlaps(b, second)){
              min = Math.max(min, second.z2+1);
            }
          }
        int difference = b.z1-min;
        b.z1 -=difference;
        b.z2 -=difference;
      }
      for(int i =0;i<values.size(); ++i){
        bricks b = values.get(i);
        for(int j=i-1; j>=0; --j){
          if(values.get(j).z2 +1 == b.z1 && overlaps(values.get(j), b)) {
            b.restingOn.add(values.get(j));
            values.get(j).supporting.add(b);
          }
        }
      }
      int total = 0;
      for(bricks b : values){
        boolean allMore = true;
        for(bricks checking : b.supporting){
          allMore = allMore && checking.restingOn.size()>1;

        }
        if(allMore) ++total;
      }

      return total;

    }
    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }
  public int brickDisiengrate(String fileName){
    try(Scanner sc = new Scanner(new File(System.getProperty("user.dir")+fileName))){
      List<bricks> values = new ArrayList<>();
      while(sc.hasNextLine()){
        String s = sc.nextLine();
        String left = s.split("~")[0];
        String right = s.split("~")[1];

        bricks B = new bricks(Integer.parseInt(left.split(",")[0]),Integer.parseInt(left.split(",")[1]),Integer.parseInt(left.split(",")[2])
                ,Integer.parseInt(right.split(",")[0]),Integer.parseInt(right.split(",")[1]),Integer.parseInt(right.split(",")[2]));
        values.add(B);
      }
      values.sort((a,b) -> a.compareTo(b));



      for(int i=0; i<values.size(); ++i){
        bricks b = values.get(i);
        int min = 1;
        for (int j = 0; j < i; ++j) {
          bricks second = values.get(j);
          if(overlaps(b, second)){
            min = Math.max(min, second.z2+1);
          }
        }
        int difference = b.z1-min;
        b.z1 -=difference;
        b.z2 -=difference;
      }
      for(int i =0;i<values.size(); ++i){
        bricks b = values.get(i);
        for(int j=i-1; j>=0; --j){
          if(values.get(j).z2 +1 == b.z1 && overlaps(values.get(j), b)) {
            b.restingOn.add(values.get(j));
            values.get(j).supporting.add(b);
          }
        }
      }
      int total = 0;
      for(bricks b : values){
        Set<bricks> possible = new HashSet<>();
        List<bricks> newleyAdded = new ArrayList<>();
        possible.add(b);
        for(bricks checking : b.supporting){
           if(checking.restingOn.size()==1){
             possible.add(checking);
             newleyAdded.add(checking);
           }
        }
        while(newleyAdded.size() != 0) {
          List<bricks> toOver = new ArrayList<>();
          for (bricks check : newleyAdded) {
            for (bricks checking : check.supporting) {
              boolean allGood = true;
              for (bricks c : checking.restingOn) {
                if (!possible.contains(c)) {
                  allGood = false;
                  break;
                }
              }
              if (allGood) {
                possible.add(checking);
                toOver.add(checking);
              }
            }
          }
          newleyAdded = toOver;

        }
        total += possible.size()-1;

      }

      return total;

    }
    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }

  public boolean overlaps(bricks a, bricks b) {
    int maxXa = Math.max(a.x1, a.x2);
    int minXa = Math.min(a.x1, a.x2);
    int maxYa = Math.max(a.y1, a.y2);
    int minYa = Math.min(a.y1, a.y2);

    int maxXb = Math.max(b.x1, b.x2);
    int minXb = Math.min(b.x1, b.x2);
    int maxYb = Math.max(b.y1, b.y2);
    int minYb = Math.min(b.y1, b.y2);
    return Math.max(minXa, minXb) <= Math.min(maxXa, maxXb) &&
            Math.max(minYa, minYb) <= Math.min(maxYa, maxYb);

  }
  public class bricks{
    List<bricks> restingOn;
    List<bricks> supporting;

    int x1;
    int y1;
    int z1;

    int x2;
    int y2;
    int z2;


    public bricks(int x1, int y1, int z1, int x2, int y2, int z2) {
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
      restingOn = new ArrayList<>();
      supporting = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      bricks bricks = (bricks) o;
      return x1 == bricks.x1 && y1 == bricks.y1 && z1 == bricks.z1 && x2 == bricks.x2 && y2 == bricks.y2 && z2 == bricks.z2;
    }
    public int compareTo(bricks b){
      if(z1>b.z1) return 1;
      return -1;
    }


    @Override
    public int hashCode() {
      return Objects.hash(x1, y1, z1, x2, y2, z2);
    }
  }
}
