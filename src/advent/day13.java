package advent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day13 {
  public int reflections(String fileName){
    try(Scanner sc = new Scanner(new File(System.getProperty("user.dir")+ fileName))){
      List<String> valuesR = new ArrayList<>();
      List<String> valuesC = new ArrayList<>();
      int amt =0;
      while(sc.hasNextLine()) {
        String s = sc.nextLine();
        if(s.length() != 0) {
          valuesR.add(s);
          for (int i = 0; i < s.length(); ++i) {
            if(valuesC.size()<i+1){
              valuesC.add(s.substring(i,i+1));
            }
            else {
              valuesC.set(i, valuesC.get(i) + s.charAt(i));
            }
          }
        }
        if(s.length()==0 || !sc.hasNextLine()) {
          int temp=0;
          for (int i = 0; i < valuesR.size()-1; i++) {
            if (reflection(valuesR, i)) {
              temp += i + 1;
            }
          }

          temp *= 100;
          if (s.length() == 0 || !sc.hasNextLine()) {
            for (int i = 0; i < valuesC.size()-1; i++) {
              if (reflection(valuesC, i)) {
                temp += i + 1;
              }
            }

          }
          valuesR = new ArrayList<>();
          valuesC = new ArrayList<>();
          amt+=temp;
        }
      }
      return amt;
    }
    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }
  public int reflections2(String fileName){
    try(Scanner sc = new Scanner(new File(System.getProperty("user.dir")+ fileName))){
      List<String> valuesR = new ArrayList<>();
      List<String> valuesC = new ArrayList<>();
      int amt =0;

      while(sc.hasNextLine()) {
        String s = sc.nextLine();
        if(s.length() != 0) {
          valuesR.add(s);
          for (int i = 0; i < s.length(); ++i) {
            if(valuesC.size()<i+1){
              valuesC.add(s.substring(i,i+1));
            }
            else {
              valuesC.set(i, valuesC.get(i) + s.charAt(i));
            }
          }
        }
        int memoryR = -1;
        if(s.length()==0 || !sc.hasNextLine()) {
          for (int i = 0; i < valuesR.size()-1; i++) {
            if (reflection(valuesR, i)) {
              memoryR = i;
              break;
            }
          }
          int memoryC = -1;

          if (s.length() == 0 || !sc.hasNextLine()) {
            for (int i = 0; i < valuesC.size()-1; i++) {
              if (reflection(valuesC, i)) {
                memoryC = i;
                break;
              }
            }
          }
          boolean fullLeave= true;
          for(int i=0;i<valuesR.size() && fullLeave; ++i){
            for(int j=0;j <valuesR.get(0).length() && fullLeave; ++j){
              String first = valuesR.get(i).charAt(j) == '.' ? "#" : ".";
              String second = valuesR.get(i).charAt(j) == '.' ? "." : "#";

              valuesR.set(i, valuesR.get(i).substring(0,j) + first +  (j+1 ==valuesR.get(0).length() ? "" :
                      valuesR.get(i).substring(j+1)));
              valuesC.set(j, valuesC.get(j).substring(0,i) + first + (i+1 ==valuesR.size() ? "" :
                      valuesC.get(j).substring(i+1)));
              for (int k = 0; k < valuesR.size()-1; k++) {
                if (k!= memoryR && reflection(valuesR, k)) {
                  amt += ((k+1)*100);
                  fullLeave = false;
                  break;
                }
              }
              for (int k = 0; k < valuesC.size()-1 && fullLeave; k++) {
                if (k!= memoryC && reflection(valuesC, k)) {
                  amt += (k+1);
                  fullLeave = false;
                  break;
                }
              }
              valuesR.set(i, valuesR.get(i).substring(0,j) + second +  (j+1 ==valuesR.get(0).length() ? "" :
                      valuesR.get(i).substring(j+1)));
              valuesC.set(j, valuesC.get(j).substring(0,i) + second + (i+1 ==valuesR.size() ? "" :
                      valuesC.get(j).substring(i+1)));
              }
          }

          valuesR = new ArrayList<>();
          valuesC = new ArrayList<>();
        }
      }
      return amt;
    }
    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }
  public boolean reflection(List<String> value, int left){
    int right = left+1;
    while(left>=0 && right<value.size()){
      if(!value.get(left).equals(value.get(right))){
        return false;
      }
      left--;
      right++;
    }
    return true;
  }
}
