package advent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class day15 {
  public int allValues(String fileName){
    try(Scanner sc = new Scanner(new File(System.getProperty("user.dir")+ fileName))){
      String s = sc.nextLine();
      String[] split = s.split(",");
      int total =0;
      for (String s1 : split) {
        total += hashValue(s1);
      }
      return total;
    }
    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }
  public int mappingValues(String fileName){
    try(Scanner sc = new Scanner(new File(System.getProperty("user.dir") + fileName))){
      Map<Integer, List<StringIndexPair>> values= new HashMap<>();
      String s = sc.nextLine();
      String[] split = s.split(",");
      int total =0;
      for (String s1 : split) {
        int index = s1.contains("-")? s1.indexOf("-") :s1.indexOf("=");
        String stringIndex = s1.substring(0, index);
        boolean add = s1.contains("=");
        int box = hashValue(stringIndex);
        if(add){
          if(values.containsKey(box)){
            List<StringIndexPair> modify = values.get(box);
            boolean added = false;
            for(int i =0; i<values.get(box).size() && !added; ++i){
              if(modify.get(i).getStringInd().equals(stringIndex)){
                modify.get(i).setLense(s1.charAt(index+1)-'0');
                added = true;
              }
            }
            if(!added) modify.add(new StringIndexPair(stringIndex, s1.charAt(index+1)-'0'));
          }
          else{
            List<StringIndexPair> toAdd = new ArrayList<>();
            toAdd.add(new StringIndexPair(stringIndex, s1.charAt(index+1)-'0'));
            values.put(box, toAdd);
          }
        }
        else{
          if(values.containsKey(box)) {
            List<StringIndexPair> modify = values.get(box);

            boolean removed = false;
            for (int i = 0; i < values.get(box).size() && !removed; ++i) {
                if(modify.get(i).getStringInd().equals(stringIndex)){
                  modify.remove(i);
                  removed = true;
                }
              }
          }
        }
      }
      for (Map.Entry<Integer, List<StringIndexPair>> entry : values.entrySet()) {
        int boxNum = entry.getKey()+1;
        for(int i =0; i<entry.getValue().size(); ++i){
          total += (boxNum*(i+1)*entry.getValue().get(i).getLense());
        }
      }
      return total;
    }
    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }

  public int hashValue(String s){
    int current = 0;
    for(int i=0;i<s.length(); ++i){
      current += s.charAt(i);
      current *= 17;
      current %= 256;
    }
    return current;
  }
  public class StringIndexPair{
    private String stringInd;
    private Integer lense;

    public StringIndexPair(String stringInd, Integer lense) {
      this.stringInd = stringInd;
      this.lense = lense;
    }

    public String getStringInd() {
      return stringInd;
    }

    public Integer getLense() {
      return lense;
    }

    public void setLense(Integer lense) {
      this.lense = lense;
    }
  }
}
