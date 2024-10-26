package advent;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class daysix {
  public static int timeSpent(String fileName){
    try (Scanner sc = new Scanner( new File(fileName))) {
      String time = sc.nextLine();
      time = time.split(":")[1];
      String distance = sc.nextLine();
      distance = distance.split(":")[1];
      distance = distance.trim();
      time = time.trim();
      Map<Integer, Integer> values = new HashMap<>();
      String[] timeVal = time.split("\\s+");
      String[] distVal = distance.split("\\s+");
      for (int i = 0; i < distVal.length; i++) {
          values.put(Integer.parseInt(timeVal[i]), Integer.parseInt(distVal[i]));
      }
      int total = 1;
      for (Integer timeV : values.keySet()) {
        int amount = 0;
        Integer scoreBeat = values.get(timeV);
        int i =1;
        while(i*(timeV-i) <= scoreBeat) ++i;
//        int j = timeV-1;
//        while(j*(timeV-j) < scoreBeat) --j;
        amount = timeV-i-i+1;

        total *= amount;
      }
      return total;


    }
    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }
  public static long oneRace(String fileName){
    try (Scanner sc = new Scanner( new File(fileName))) {
      String time = sc.nextLine();
      time = time.split(":")[1];
      String distance = sc.nextLine();
      distance = distance.split(":")[1];
      distance = distance.replaceAll("\\s+","");
      time = time.replaceAll("\\s+","");
      long timeV = Long.parseLong(time);
      long scoreBeat = Long.parseLong(distance);

        int i =1;
        while(i*(timeV-i) <= scoreBeat) ++i;
//      int j = timeV-1;
//      while(j*(timeV-j) < scoreBeat) --j;
        return timeV-i-i+1;
    }
    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }
}
