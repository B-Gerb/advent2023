package advent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class day14 {
  public int distance(String fileName){
    try(Scanner sc = new Scanner(new File(System.getProperty("user.dir")+ fileName))){
      List<String> values = new ArrayList<>();
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
    catch(Exception e){
      System.out.println(e);
      return -1;
    }
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
