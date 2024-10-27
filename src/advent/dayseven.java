package advent;

import java.io.File;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class dayseven {
  public static Map<String, Integer> values;
  public int handRanking(String fileName, int part){
    values = new HashMap<>();
    //part 1 = 1, part 2 == 2
    if(part == 1) {
      values.put("2", 0);
      values.put("3", 1);
      values.put("4", 2);
      values.put("5", 3);
      values.put("6", 4);
      values.put("7", 5);
      values.put("8", 6);
      values.put("9", 7);
      values.put("T", 8);
      values.put("J", 9);
      values.put("Q", 10);
      values.put("K", 11);
      values.put("A", 12);
    }
    else{
        values.put("2", 1);
        values.put("3", 2);
        values.put("4", 3);
        values.put("5", 4);
        values.put("6", 5);
        values.put("7", 6);
        values.put("8", 7);
        values.put("9", 8);
        values.put("T", 9);
        values.put("J", 0);
        values.put("Q", 10);
        values.put("K", 11);
        values.put("A", 12);
    }

    try(Scanner sc = new Scanner(new File(fileName))){
      List<List<HandBid>> finalValues = new ArrayList<List<HandBid>>();
      finalValues.add(new ArrayList<>());
      finalValues.add(new ArrayList<>());
      finalValues.add(new ArrayList<>());
      finalValues.add(new ArrayList<>());
      finalValues.add(new ArrayList<>());
      finalValues.add(new ArrayList<>());
      finalValues.add(new ArrayList<>());

      while(sc.hasNextLine()){
        String[] s = sc.nextLine().split(" ");
        HandBid hand = new HandBid(s[0], Integer.parseInt(s[1]));
        if(part == 1)
        finalValues.get(hand.strength()).add(hand);
        if(part == 2)
          finalValues.get(hand.strength2()).add(hand);
      }
      for(int i =0; i<finalValues.size(); ++i){
        Collections.sort(finalValues.get(i), (a, b) -> a.compareTo(a, b));
      }
      int total = 0;
      int number = 1;
      for (List<HandBid> finalValue : finalValues) {
        for (HandBid handBid : finalValue) {
          total += handBid.bid *number++;
        }
      }
      return total;


    }
    catch(Exception e){
      System.out.println(e);
      return -1;    }

  }

  public class HandBid{
    public String hand;
    public int bid;

    public HandBid(String hands, int bids){
      hand = hands;
      bid = bids;
    }
    public int strength(){
      int[] numbers = new int[13];
      for(int i=0; i<hand.length(); ++i){
        numbers[values.get(hand.substring(i,i+1))]++;
      }
      boolean two = false;
      boolean three = false;
      for (int number : numbers) {
        if(number ==5) return 6;
        if(number ==4) return 5;
        if(number ==3){
          if(two) return 4;
          else three = true;
        }
        if(number ==2){
          if(three) return 4;
          if(two) return 2;
          two = true;

        }
      }
      if(three) return 3;
      return two ? 1 : 0;
    }
    public int strength2(){
      int[] numbers = new int[13];

      int max = 0;
      int wild = 0;
      for(int i=0; i<hand.length(); ++i){
        int num = values.get(hand.substring(i,i+1));
        if(num == 0) wild++;
        else{
          numbers[num]++;
          if(numbers[num] > numbers[max]) max =num;}

      }
      numbers[max] += wild;
      boolean two = false;
      boolean three = false;
      for (int number : numbers) {
        if(number ==5) return 6;
        if(number ==4) return 5;
        if(number ==3){
          if(two) return 4;
          else three = true;
        }
        if(number ==2){
          if(three) return 4;
          if(two) return 2;
          two = true;

        }
      }
      if(three) return 3;
      return two ? 1 : 0;
    }

    public  int compareTo(HandBid hand1, HandBid hand2){

      for(int i =0;i<hand1.hand.length(); ++i){
        int l = values.get(hand1.hand.substring(i,i+1));
        int r = values.get(hand2.hand.substring(i,i+1));
        if(l!=r){
          if(l>r) return 1;
          else return -1;
        }
      }
      return 0;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      HandBid handBid = (HandBid) o;
      return bid == handBid.bid && Objects.equals(hand, handBid.hand);
    }

    @Override
    public int hashCode() {
      return Objects.hash(hand, bid);
    }

    @Override
    public String toString() {
      return "HandBid{" +
              "hand='" + hand + '\'' +
              ", bid=" + bid +
              '}';
    }
  }
}
