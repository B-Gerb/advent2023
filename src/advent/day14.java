package advent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class day14 {
  public int distance(String fileName){
    try(Scanner sc = new Scanner(new File(System.getProperty("user.dir")+ fileName))) {
      List<String> values = new ArrayList<>();
      boolean first = true;
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        for (int i = 0; i < s.length(); ++i) {
          if (first) {
            values.add(s.substring(i, i + 1));
          } else {
            values.set(i, values.get(i) + s.substring(i, i + 1));
          }
        }
        first = false;

      }
      return north(values);
    }

    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }
  public int north(List<String> values){
    int total =0;
    for (String value : values) {
      while(value.indexOf("#") != -1){
        int amt = amt(value.substring(0, value.indexOf("#")), "O");
        int amt2 = amt*(amt-1)/2;
        total+= amt*(value.length()) -amt2;
        value = value.substring(value.indexOf("#")+1);
      }
      int amt = amt(value, "O");
      int amt2 = amt*(amt-1)/2;
      total+= amt*(value.length()) -amt2;

    }
    return total;
  }
  public int weight(List<String> values){
    int total =0;
    for (String value : values) {
      for (int i = 0; i < value.length(); ++i) {
        if (value.charAt(i) == 'O') {
          total += value.length() - i;
        }
      }
    }
    return total;

  }
  //rotation memory
  public int distanceMultiple(String fileName, int times){
    try(Scanner sc = new Scanner(new File(System.getProperty("user.dir")+ fileName))){
      List<String> values = new ArrayList<>();
      Map<List<String>, Integer> cycles = new HashMap<>();
      boolean first = true;
      while(sc.hasNextLine()){
        String s = sc.nextLine();
        for(int i=0;i<s.length(); ++i){
          if(first){
            values.add(s.substring(i,i+1));
          }
          else{
            values.set(i, values.get(i) + s.substring(i,i+1));
          }
        }
        first = false;
      }
      for(int cyc =0; cyc <times; ++cyc) {
        if(cycles.containsKey(values)){
          int distance = times - cycles.get(values);
          int amount = cyc- cycles.get(values);
          int search = distance%amount + cycles.get(values);
          for (Map.Entry<List<String>, Integer> entry : cycles.entrySet()) {
            if (Objects.equals(search, entry.getValue())) {
              return weight(entry.getKey());
            }
          }
        }


        cycles.put(values, cyc);
        for (int i = 0; i < 4; ++i) {
          List<String> newValues = new ArrayList<>();

          for (int v = 0; v < values.size(); ++v) {
            String value = values.get(v);
            String toAdd = "";
            while (value.indexOf("#") != -1) {
              int amt = amt(value.substring(0, value.indexOf("#")), "O");
              int amt2 = amt(value.substring(0, value.indexOf("#")), ".");
              toAdd += "O".repeat(amt);
              toAdd += ".".repeat(amt2);
              toAdd += "#";
              value = value.substring(value.indexOf("#") + 1);
            }
            int amt = amt(value, "O");
            int amt2 = amt(value, ".");
            toAdd += "O".repeat(amt);
            toAdd += ".".repeat(amt2);
            newValues.add(toAdd);
          }
          newValues = rotate(newValues);
          values = newValues;
        }
      }



      return 1;

    }

    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }
  public static List<String> rotate(List<String> input) {

    int numRows = input.size();
    int numCols = input.get(0).length();

    List<String> toRet = new ArrayList<>();

    for (int col = numCols - 1; col >= 0; col--) {
      StringBuilder newRow = new StringBuilder();
      for (int row = 0; row < numRows; row++) {
        newRow.append(input.get(row).charAt(col));
      }
      toRet.add(newRow.toString());
    }

    return toRet;
  }

  public int[] index(String main, String split){
    int[] toRet = new int[amt(main, split)];
    int spotIndex = 0;
    int num =0;
    while(main.indexOf(split) != -1){
      toRet[num] = spotIndex + main.indexOf(split)+1;
      spotIndex = toRet[num++];
      main = main.substring(1+main.indexOf(split));
    }
    return toRet;

  }
  public int amt(String main, String find){
    int count =0;
    while(main.indexOf(find) != -1){
      count++;
      main = main.substring(main.indexOf(find) +1);
    }
    return count;
  }
}
