package advent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day24 {
  public double amountColl(String fileName, double min, double max ){
    try(Scanner sc = new Scanner(new File(System.getProperty("user.dir") + fileName))){
      List<path> all = new ArrayList<>();
      while(sc.hasNextLine()){
        String s = sc.nextLine();
        s = s.replaceAll("\\s", "");
        String[] parts = s.split("@");
        String[] pos = parts[0].split(",");
        String[] vel = parts[1].split(",");

        all.add(new path(Long.parseLong(pos[0]),Long.parseLong(pos[1]),Long.parseLong(pos[2]),
                Long.parseLong(vel[0]),Long.parseLong(vel[1]),Long.parseLong(vel[2])));
      }
      double total = 0;
      for(int i =0; i<all.size(); ++i){
        for(int j=i+1; j<all.size(); ++j){
          if(all.get(i).intersect(all.get(j), min, max)) ++total;
        }
      }
      return total;
    }
    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }

  public class path{
    double px;
    double py;
    double pz;
    double vx;
    double vy;
    double vz;
    public boolean intersect(path b, double min, double max){
      double aa = vy;
      double ab = -vx;
      double ac = vy*px-vx*py;

      double ba = b.vy;
      double bb = -b.vx;
      double bc = b.vy*b.px-b.vx*b.py;

      if(vy*b.vx  == b.vy*vx) return false;
      double x= (ac*bb-bc*ab)/(aa*bb-ba*ab);
      double y= (bc*aa - ac*ba)/(aa*bb- ba*ab);
      if(x>=min && x<= max && y>=min && y<=max) {
        if ((x - px) * vx >= 0 && (y - py) * vy >= 0 && (x - b.px) * b.vx >= 0 && (y - b.py) * b.vy >= 0)
          return true;
      }

      return false;



    }

    public path(double px, double py, double pz, double vx, double vy, double vz) {
      this.px = px;
      this.py = py;
      this.pz = pz;
      this.vx = vx;
      this.vy = vy;
      this.vz = vz;
    }
  }
}
