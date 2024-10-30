package advent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class daynine {
  public static int OASIS(String fileName, boolean forward){
    try (Scanner sc = new Scanner(new File(fileName))){
      int total = 0;
      while(sc.hasNextLine()){
        String s = sc.nextLine();
        s.trim();
        List<List<Integer>> values = new ArrayList<List<Integer>>();
        String[] lineOneValue = s.split("\\s");
        ArrayList<Integer> firstLine = new ArrayList<>();
        for (String s1 : lineOneValue) {
          firstLine.add(Integer.parseInt(s1));
        }
        values.add(firstLine);
        if(forward){
          total += daynine.forward(values);

        }
        else{
          total+= daynine.backward(values);
        }

      }
      return total;
    }
    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }
  public static int forward(List<List<Integer>> values){
    boolean notAllZero = true;
    int previous = 0;
    while(notAllZero){
      boolean allZeros = true;
      ArrayList<Integer> nextLine = new ArrayList<>();
      for(int i=0;i<values.get(previous).size()-1; ++i){
        int toAdd = values.get(previous).get(i+1)-values.get(previous).get(i);
        if(allZeros){
          allZeros = toAdd == 0;
        }
        nextLine.add(toAdd);
      }
      if(allZeros) notAllZero = false;
      values.add(nextLine);
      previous++;
    }

    while(previous > 0){
      int amtLessThan = values.get(previous-1).size()-1;
      int amtAt = values.get(previous).size()-1;

      values.get(previous-1).set(amtLessThan, values.get(previous-1).get(amtLessThan) + values.get(previous).get(amtAt));
      previous--;
    }
    return values.get(0).get(values.get(0).size()-1);
  }
  public static int backward(List<List<Integer>> values){
    boolean notAllZero = true;
    int previous = 0;
    while(notAllZero){
      boolean allZeros = true;
      ArrayList<Integer> nextLine = new ArrayList<>();
      for(int i=0;i<values.get(previous).size()-1; ++i){
        int toAdd = values.get(previous).get(i+1)-values.get(previous).get(i);
        if(allZeros){
          allZeros = toAdd == 0;
        }
        nextLine.add(toAdd);
      }
      if(allZeros) notAllZero = false;
      values.add(nextLine);
      previous++;
    }

    while(previous > 0){
      int amtLessThan = values.get(previous-1).size()-1;
      int amtAt = values.get(previous).size()-1;

      values.get(previous-1).set(0, values.get(previous-1).get(0) - values.get(previous).get(0));
      previous--;
    }
    return values.get(0).get(0);
  }
}
