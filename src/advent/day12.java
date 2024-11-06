package advent;

import java.io.File;
import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class day12 {
  Map<ValuePair, Long> memorization = new HashMap<>();
  public long possibilities(String fileName){
    try(Scanner sc = new Scanner(new File(fileName))) {
      long total = 0;
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
  public long possibilitiespart2(String fileName){
    try(Scanner sc = new Scanner(new File(fileName))) {
      long total = 0;
      while(sc.hasNextLine()){
        String s = sc.nextLine();
        String[] numbers = s.split(" ")[1].split(",");
        s = s.split(" ")[0];

        ArrayList<Integer> nums = new ArrayList<>();
        for (String number : numbers) {
          nums.add(Integer.parseInt(number));
        }
        String toAdd = String.join("?", Collections.nCopies(5, s));
        ArrayList<Integer> finals = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
          finals.addAll(nums);
        }
        total += combos(toAdd, new ArrayList<>(finals));

      }
      return total;
    }
    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }
  private long combos(String values, ArrayList<Integer> nums){

    if(memorization.containsKey(new ValuePair(values, nums))) return memorization.get(new ValuePair(values, nums));
    if(values.length()==0){
      if(nums.size() ==0){
        memorization.put(new ValuePair(values, nums), 1L);
        return 1;
      }
      memorization.put(new ValuePair(values, nums), 0L);

      return 0;
    }
    if(nums.size() == 0){
      return values.contains("#") ? 0 : 1;
    }
    if(nums.get(0) > values.length()){
      memorization.put(new ValuePair(values, nums), 0L);

      return 0;
    }
    long result = 0;
    if(values.charAt(0) == '.' || values.charAt(0) == '?'){
      result += combos(values.substring(1), new ArrayList<>(nums));
    }
    if(values.charAt(0) == '#' || values.charAt(0) == '?'){
      if(nums.get(0) == values.length() && !values.contains(".") && nums.size() ==1){
        memorization.put(new ValuePair(values, nums), 1L);

        return 1;
      }
      if(nums.get(0) == values.length() && nums.size() !=1){
        memorization.put(new ValuePair(values, nums), 0L);

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
    memorization.put(new ValuePair(values, nums), result);

    return result;


  }
  private static class ValuePair{
    String values;
    ArrayList<Integer> nums;

    public ValuePair(String values, ArrayList<Integer> nums) {
      this.values = values;
      this.nums = nums;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ValuePair valuePair = (ValuePair) o;
      return Objects.equals(values, valuePair.values) && Objects.equals(nums, valuePair.nums);
    }

    @Override
    public int hashCode() {
      return Objects.hash(values, nums);
    }
  }

}
