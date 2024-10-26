package advent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class dayfive {
  public static long rangeSoilWalk(String filename){
    try (Scanner sc = new Scanner( new File(filename)))
    {
      String seed = sc.nextLine();
      Map<Long, Long> seeds = new HashMap<>();
      seed = seed.split(":")[1];
      String[] vals = seed.split(" ");
      for(int i =1; i<vals.length; i+=2){
          seeds.put(Long.parseLong(vals[i]),Long.parseLong(vals[i+1]) + Long.parseLong(vals[i])-1);
      }
      sc.nextLine();
      while(sc.hasNextLine()){
        String s = sc.nextLine();
        if(s.indexOf("-") != -1){
          s = sc.nextLine();
          Map<Long, Long> newRange = new HashMap<>();
          Map<Long, Long> valuesNotUsed = new HashMap<>();


          while(s.length() != 0) {
            valuesNotUsed = new HashMap<>();
            vals = s.split(" ");
            long left = Long.parseLong(vals[0]);
            long right = Long.parseLong(vals[1]);
            long range = Long.parseLong(vals[2]);
            for(long keyVal : seeds.keySet()){
              if(keyVal < right && seeds.get(keyVal) > right+range){
                newRange.put(left, left +range-1); // good
                valuesNotUsed.put(keyVal, right-1);
                valuesNotUsed.put(right+range, seeds.get(keyVal));

              }
              else if(keyVal < right && (seeds.get(keyVal) >= right && seeds.get(keyVal) <= right + range)){
                long dist = seeds.get(keyVal) - right;
                newRange.put(left, left+dist);
                valuesNotUsed.put(keyVal, right-1);
              }
              else if(keyVal >= right && keyVal <= right+range && seeds.get(keyVal) >= right + range){
                long dist = keyVal - right;
                newRange.put(left+dist, left+range-1);
                valuesNotUsed.put(right+range, seeds.get(keyVal));

              }
              else if(keyVal >= right && keyVal <= right+range && seeds.get(keyVal) >= right && seeds.get(keyVal) <= right+range){
                long distL = keyVal - right;
                long distR = seeds.get(keyVal) - right;

                newRange.put(left+distL, left+distR);
              }
              else{
                valuesNotUsed.put(keyVal, seeds.get(keyVal));
              }
            }
            seeds = valuesNotUsed;

            if(!sc.hasNextLine()) break;
            s = sc.nextLine();
          }

          newRange.putAll(valuesNotUsed);
          seeds = newRange;
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




  public static long smallestSoilWalk(String filename){
    try (Scanner sc = new Scanner( new File(filename)))
    {
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
