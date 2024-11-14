package advent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day18 {
  public long shoeLace(String fileName){
    try(Scanner sc = new Scanner(new File(System.getProperty("user.dir") + fileName))){
      List<cord> vertex = new ArrayList<>();
      cord prev = new cord(0,0);
      vertex.add(prev);
      while(sc.hasNextLine()){
        String s=sc.nextLine();
        String[] parts = s.split("\\s");
        cord toAdd = new cord(prev);
        if(parts[0].equals("R")){
          toAdd.addX(Integer.parseInt(parts[1]));
        }
        if(parts[0].equals("L")){
          toAdd.addX(-1*Integer.parseInt(parts[1]));
        }
        if(parts[0].equals("U")){
          toAdd.addY(-1*Integer.parseInt(parts[1]));
        }
        if(parts[0].equals("D")){
          toAdd.addY(Integer.parseInt(parts[1]));
        }
        prev = toAdd;
        vertex.add(prev);
      }
      return areaCalc(vertex);



    }
    catch(Exception e){
      System.out.println(e);
      return -1;
    }

  }
  public long hexCalc(String fileName){
    try(Scanner sc = new Scanner(new File(System.getProperty("user.dir") + fileName))){
      List<cord> vertex = new ArrayList<>();
      cord prev = new cord(0,0);
      vertex.add(prev);
      while(sc.hasNextLine()){
        String s=sc.nextLine().split("\\s")[2];
        cord toAdd = new cord(prev);
        int  decimal= Integer.parseInt(s.substring(2,7),16);

        if(s.charAt(7) == '0'){
          toAdd.addX(decimal);
        }
        if(s.charAt(7) == '2'){
          toAdd.addX(-1*decimal);
        }
        if(s.charAt(7) == '3'){
          toAdd.addY(-1*decimal);
        }
        if(s.charAt(7) == '1'){
          toAdd.addY(decimal);
        }
        prev = toAdd;
        vertex.add(prev);
    }
    return areaCalc(vertex);



  }
    catch(Exception e){
    System.out.println(e);
    return -1;
  }
  }
  public long areaCalc(List<cord> vertex){
    long total = 0;
    long perm  =0;
    for (int i = 0; i < vertex.size(); i++) {
      total +=(1.0*vertex.get(i).getY()*vertex.get((i+1)%vertex.size()).getX()) -
              (1.0*vertex.get(i).getX()*vertex.get((i+1)%vertex.size()).getY());
      perm += Math.sqrt(
              Math.pow(vertex.get(i).getX()-vertex.get((i+1)%vertex.size()).getX(),2) +
                      Math.pow(vertex.get(i).getY()-vertex.get((i+1)%vertex.size()).getY(),2));
    }
    return Math.abs(total/2)+perm/2+1;
  }

  private class cord{
    private int x;
    private int y;

    public cord(int x, int y) {
      this.x = x;
      this.y = y;
    }
    public cord(cord copy){
      x = copy.getX();
      y = copy.getY();
    }

    public int getX() {
      return x;
    }

    public void addX(int x) {
      this.x += x;
    }

    public int getY() {
      return y;
    }

    public void addY(int y) {
      this.y +=y;
    }
  }

}
