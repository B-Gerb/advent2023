package advent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;

public class dayfour {

  public static int score(String filename){
    try {
      int total = 0;
      File file = new File(filename);
      Scanner sc = new Scanner(file);
      List<String> values = new ArrayList<>();
      while(sc.hasNextLine()) values.add(sc.nextLine());
      for (String value : values) {
        int amount = 0;
        String winningNum = value.split(":")[1].split("\\|")[1];
        List<String> winningNums = Arrays.asList(winningNum.split(" "));
        String[] possible = value.split(":")[1].split("\\|")[0].split(" ");
        for (String s : possible) {
          if(s.length() != 0 && winningNums.contains(s)){
            amount ++;
          }
        }
        if(amount != 0){
          total = total + (int) Math.pow(2, amount-1);
        }

      }
      return total;

    }
    catch(Exception e){
      System.out.println("error");
      return -3;
    }
  }
  public static int copyCards(String filename){
    try {
      int total = 0;
      File file = new File(filename);
      Scanner sc = new Scanner(file);
      List<String> values = new ArrayList<>();
      while(sc.hasNextLine()) values.add(sc.nextLine());
      int amtCards = values.size();
      Map<Integer, List<Integer>> toRepeat = new HashMap<>();
      for (String value : values) {
        int amount = 0;
        String[] toBe = value.split(":")[0].split(" ");
        int game = Integer.parseInt(toBe[toBe.length-1]);
        String winningNum = value.split(":")[1].split("\\|")[1];
        List<String> winningNums = Arrays.asList(winningNum.split(" "));
        String[] possible = value.split(":")[1].split("\\|")[0].split(" ");
        for (String s : possible) {
          if(s.length() != 0 && winningNums.contains(s)){
            amount ++;
          }
        }
        List<Integer> toAdd = new ArrayList<>();
        toAdd.add(amount);
        toAdd.add(1);
        toRepeat.put(game, toAdd);

      }

      for(int i =1; i<=amtCards; ++i){
        int futureCards = toRepeat.get(i).get(0);
        int increment = toRepeat.get(i).get(1);
        total += increment;

        for(int j=i+1; j<i+1+futureCards; ++j){
          List<Integer> value = toRepeat.get(j);
          int num = value.remove(1);
          value.add(num+increment);
        }
      }
      return total;

    }
    catch(Exception e){
      System.out.println(e);
      return -3;
    }

  }
}
