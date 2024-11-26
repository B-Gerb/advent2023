package advent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day24 {
  public double integerIntersection(String fileName){
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
        double[][] gausianElimiation = new double[4][5];

        for(int amt =0 ;amt<4; ++amt){
          path i = all.get(amt);
          path j = all.get(amt+1);
          gausianElimiation[amt][0] = j.py - i.py;
          gausianElimiation[amt][1] = i.vy - j.vy;
          gausianElimiation[amt][2] = i.px - j.px;
          gausianElimiation[amt][3] = j.vx - i.vx;
          gausianElimiation[amt][4] = (i.px * i.vy) - (j.px * j.vy) - (i.py * i.vx) + (j.py * j.vx);
        }
        solveGaussian(gausianElimiation);

        double rpy = gausianElimiation[3][4];
        double rvy = gausianElimiation[2][4]-
                (rpy*gausianElimiation[2][3]);
        double rpx = gausianElimiation[1][4]-
                (rpy*gausianElimiation[1][3])-(rvy*gausianElimiation[1][2]);
        double rvx = gausianElimiation[0][4]-
                (rpy*gausianElimiation[0][3])-(rvy*gausianElimiation[0][2])-(rpx*gausianElimiation[0][1]);


        double[][] zaxis = new double[2][3];
        for(int amt = 0; amt<2; ++amt) {
          path i = all.get(amt);
          zaxis[amt][0] = (rpx-i.px)/(i.vx-rvx);
          zaxis[amt][1] = 1;
          zaxis[amt][2] = i.pz+((rpx-i.px)*(i.vz))/(i.vx-rvx);
        }
        solveGaussian(zaxis);

        return rpx+rpy+zaxis[1][2];


      }
      catch(Exception e){
        System.out.println(e);
        return -1;
      }

    }
  private void solveGaussian(double[][] matrix) {
    int rows = matrix.length;
    int cols = matrix[0].length;

    for (int i = 0; i < rows; ++i) {
      double todivide = matrix[i][i];
      for (int j = 0; j < cols; ++j) {
        matrix[i][j] /= todivide;
      }

      for (int k = i + 1; k < rows; ++k) {
        double multiplier = matrix[k][i];
        for (int j = 0; j < cols; ++j) {
          matrix[k][j] -= multiplier * matrix[i][j];
        }
      }
    }

    for (int i = rows - 1; i >= 0; --i) {
      for (int k = i - 1; k >= 0; --k) {
        double multiplier = matrix[k][i];
        for (int j = 0; j < cols; ++j) {
          matrix[k][j] -= multiplier * matrix[i][j];
        }
      }
    }
  }


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
