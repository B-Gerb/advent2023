package advent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class day19 {
  public int expressionFigure(String fileName){
    try(Scanner sc = new Scanner(new File(System.getProperty("user.dir") + fileName))){
      Map<String, List<letterToFunc>> allValues = new HashMap<>();
      boolean valueGeneration = false;
      int total = 0;
      while(sc.hasNextLine()){

        String s = sc.nextLine();
        if(s.length() == 0){
          valueGeneration = true;
        }
        else{
          if(valueGeneration){
            s = s.substring(1, s.length()-1);
            Map<String, Integer> positionValue = new HashMap<>();
            String[] split =s.split(",");
            for (String s1 : split) {
              positionValue.put(s1.split("=")[0], Integer.parseInt(s1.split("=")[1]));
            }
             String state = "in";
             while(!state.equals("A") && !state.equals("R")){
                List<letterToFunc> functions = allValues.get(state);
               for (letterToFunc function : functions) {
                 String toGo = function.func.apply(positionValue.get(function.letter));
                 if(!toGo.equals("P")){
                   state = toGo;
                   break;
                 }
               }
             }
             if(state.equals("A")){
               for(Integer i : positionValue.values())
                 total += i;
             }
          }

          else{
            s = s.substring(0,s.length()-1);
            String[] splitting = s.split("\\{");
            String title = splitting[0];
            List<letterToFunc> functionValues = new ArrayList<>();
            String[] values = splitting[1].split(":");
            List<String[]> valuesSplit = new ArrayList<>();
            for(int i=0; i<values.length; ++i){
              valuesSplit.add(values[i].split(","));
            }
            for(int i=0; i<values.length-1; ++i){
              String function = valuesSplit.get(i)[valuesSplit.get(i).length-1];
              functionValues.add(new letterToFunc(function, valuesSplit.get(i+1), i==values.length-2));
            }
            allValues.put(title, functionValues);
          }
        }

      }
      return total;



    }
    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }
  public long rangeCreator(String fileName){
    try(Scanner sc = new Scanner(new File(System.getProperty("user.dir") + fileName))){
      Map<String, List<letterToFunc>> allValues = new HashMap<>();
      boolean valueGeneration = false;
      long total = 0;
      while(sc.hasNextLine()){

        String s = sc.nextLine();
        if(s.length() == 0){
          valueGeneration = true;
        }
        else{
          if(valueGeneration){
            Map<String, Range> startingRanges = new HashMap<>();
            startingRanges.put("x", new Range(1,4000));
            startingRanges.put("m", new Range(1,4000));
            startingRanges.put("a", new Range(1,4000));
            startingRanges.put("s", new Range(1,4000));

            List<Map<String, Range>> positionValue = values(allValues, startingRanges, "in");
            for (Map<String, Range> stringRangeMap : positionValue) {
              long part = 1;
              for (Range value : stringRangeMap.values()) {
                part *= value.difference();

              }
              total += part;

            }
            return total;


          }

          else{
            s = s.substring(0,s.length()-1);
            String[] splitting = s.split("\\{");
            String title = splitting[0];
            List<letterToFunc> functionValues = new ArrayList<>();
            String[] values = splitting[1].split(":");
            List<String[]> valuesSplit = new ArrayList<>();
            for(int i=0; i<values.length; ++i){
              valuesSplit.add(values[i].split(","));
            }
            for(int i=0; i<values.length-1; ++i){
              String function = valuesSplit.get(i)[valuesSplit.get(i).length-1];
              functionValues.add(new letterToFunc(function, valuesSplit.get(i+1), i==values.length-2));
            }
            allValues.put(title, functionValues);
          }
        }

      }
      return total;



    }
    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }
  public List<Map<String, Range>> values(Map<String, List<letterToFunc>> allValues, Map<String, Range> active, String state){
      List<Map<String, Range>> newValues = new ArrayList<>();
        List<letterToFunc> functions = allValues.get(state);
        for (letterToFunc function : functions) {
          int split = function.getNumber();
          //splitting
          String above;
          String below;
          String functionletter= function.getLetter();
          if(function.getFunc().apply(split+1).equals(function.getFunc().apply(split))){
             above = function.getFunc().apply(split);
             below = function.getFunc().apply(split-1);

          }
          else{
             above = function.getFunc().apply(split+1);
             below = function.getFunc().apply(split);
          }

          Map<String, Range> toAbove = new HashMap<>(active);
          Map<String, Range> toBelow = new HashMap<>(active);
          //creating
          Range aboveRange;
          Range belowRange;
          if(function.getFunc().apply(split+1).equals(function.getFunc().apply(split))) {
            aboveRange = new Range(split, active.get(functionletter).getMax());
            belowRange =  new Range(active.get(functionletter).getMin(), split-1);

          }
          else{
            aboveRange = new Range(split + 1, active.get(functionletter).getMax());
            belowRange =  new Range(active.get(functionletter).getMin(), split);
          }


          toAbove.remove(functionletter);
          toBelow.remove(functionletter);
          toAbove.put(functionletter, aboveRange);
          toBelow.put(functionletter, belowRange);

          if(above.equals("A") && aboveRange.difference()>=0) newValues.add(toAbove);
          if(below.equals("A") && belowRange.difference()>=0) newValues.add(toBelow);
          if(!above.equals("R") && !above.equals("P") && !above.equals("A")) newValues.addAll(values(allValues, toAbove, above));
          if(!below.equals("R") && !below.equals("P")&& !below.equals("A")) newValues.addAll(values(allValues, toBelow, below));
          if(above.equals("P")){
            active = toAbove;
          }
          if(below.equals("P")){
            active= toBelow;
          }
        }


    return newValues;

  }
  private class Range{
    int min;
    int max;

    public Range(int min, int max) {
      this.min = min;
      this.max = max;
    }

    public int getMin() {
      return min;
    }

    public int getMax() {
      return max;
    }
    public int difference(){
      return max-min+1;
    }

  }
  private class letterToFunc{
    private final String letter;
    private final Function<Integer, String> func;

    private final int number;

    public letterToFunc(String full, String[] results, boolean last) {
      this.letter = full.substring(0,1);
      String accept = results[0];
      String fail = last ? results[1] : "P";
      boolean greater = full.charAt(1) == '>';
      number = Integer.parseInt(full.substring(2));
      func = num -> greater ? num>Integer.parseInt(full.substring(2)) ? accept : fail
              : num<Integer.parseInt(full.substring(2)) ? accept : fail;
    }
    public int getNumber() {return number;}
    public String getLetter() {
      return letter;
    }

    public Function<Integer, String> getFunc() {
      return func;
    }
  }
}
