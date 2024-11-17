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
  private class letterToFunc{
    private final String letter;
    private final Function<Integer, String> func;

    public letterToFunc(String full, String[] results, boolean last) {
      this.letter = full.substring(0,1);
      String accept = results[0];
      String fail = last ? results[1] : "P";
      boolean greater = full.charAt(1) == '>';
      func = num -> greater ? num>Integer.parseInt(full.substring(2)) ? accept : fail
              : num<Integer.parseInt(full.substring(2)) ? accept : fail;
    }

    public String getLetter() {
      return letter;
    }

    public Function<Integer, String> getFunc() {
      return func;
    }
  }
}
