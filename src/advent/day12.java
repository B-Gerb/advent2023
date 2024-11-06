package advent;

import java.io.File;
import java.util.ArrayList;

import java.util.Scanner;

public class day12 {
  public int possibilities(String fileName){
    try(Scanner sc = new Scanner(new File(fileName))) {
      int total = 0;
      while(sc.hasNextLine()){
        String s = sc.nextLine();
        String[] numbers = s.split(" ")[1].split(",");
        s = s.split(" ")[0];
        ArrayList<Integer> nums = new ArrayList<>();
        for (String number : numbers) {
          nums.add(Integer.parseInt(number));
        }
        total += combos(s, new ArrayList<>(nums));

      }
      return total;
    }
    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }
  private int combos(String values, ArrayList<Integer> nums){
    if(values.length()==0){
      if(nums.size() ==0)return 1;
      return 0;
    }
    if(nums.size() == 0){
      return values.contains("#") ? 0 : 1;
    }
    if(nums.get(0) > values.length()) return 0;
    int result = 0;
    if(values.charAt(0) == '.' || values.charAt(0) == '?'){
      result += combos(values.substring(1), new ArrayList<>(nums));
    }
    if(values.charAt(0) == '#' || values.charAt(0) == '?'){
      if(nums.get(0) == values.length() && !values.contains(".") && nums.size() ==1){
        return 1;
      }
      if(nums.get(0) == values.length() && nums.size() !=1){
        return 0;
      }

      else if((!(values.contains(".")) || nums.get(0) <= values.indexOf("."))  &&
              ((nums.get(0) == values.length() ||
              (values.charAt(nums.get(0)) != '#')))){
        ArrayList<Integer> copy = new ArrayList<>(nums);
        copy.remove(0);
        result += combos(values.substring(nums.get(0)+1),  copy);
      }
    }
    return result;


  }
}
