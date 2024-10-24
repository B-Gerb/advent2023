package advent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class dayfive {

  public static long smallestSoilWalk(String filename){
    try {
      File file = new File(filename);
      Scanner sc = new Scanner(file);
      String seed = sc.nextLine();
      Map<Long, Boolean> seeds = new HashMap<>();
      seed = seed.split(":")[1];
      String[] vals = seed.split(" ");
      for (String val : vals) {
        if(val.length() != 0)
        seeds.put(Long.parseLong(val), false);
      }
      sc.nextLine();
      while(sc.hasNextLine()){
        String s = sc.nextLine();
        ArrayList<String> line = new ArrayList<>();
        if(s.indexOf("-") != -1){
          s = sc.nextLine();
          ArrayList<Long> tooAddLater = new ArrayList<>();
          ArrayList<Long> tooRemoveLater = new ArrayList<>();

          while(s.length() != 0) {
            vals = s.split(" ");
            long right = Long.parseLong(vals[0]);
            long left = Long.parseLong(vals[1]);
            long range = Long.parseLong(vals[2]);
            ArrayList<Long> values = new ArrayList<>();
            for(Long keyValue : seeds.keySet()){
              values.add(keyValue);
            }
            for (Long keyValue : values) {
              if(keyValue >= left && keyValue < left+range && !seeds.get(keyValue)){
                long val = keyValue - left;
                seeds.remove(keyValue);
                if(seeds.containsKey(right+val)){
                  tooAddLater.add(right+val);
                }
                else{
                  seeds.put(val + right, true);
                }

              }

            }
//            for (long i = 0; i < range; i++) {
//              if (seeds.containsKey(i + left) && !seeds.get(i+left)) {
//                seeds.remove(i + left);
//                if(seeds.containsKey(i+right)){
//                  tooAddLater.add(i+right);
//                }
//                else {
//                  seeds.put(i + right, true);
//                }
//              }
//            }
            if(!sc.hasNextLine()) break;
            s = sc.nextLine();
          }
          for (Long integer : tooAddLater) {
            seeds.put(integer, false);
          }
          for (Long integer : tooRemoveLater) {
            seeds.remove(integer, false);
          }
          for(Long val : seeds.keySet()){
            seeds.replace(val, false);
          }
        }
      }
      Long min = Long.MAX_VALUE;
      for(Long val : seeds.keySet()) {
        if(val < min) min = val;
      }

        return min;
    }
    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }
}
