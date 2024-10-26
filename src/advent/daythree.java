package advent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class daythree {
  public static int StringAjacent(String filename) {
      int total = 0;
      int value;
      try (Scanner sc = new Scanner( new File(filename)))
      {
      List<String> values = new ArrayList<>();
      while (sc.hasNextLine()) values.add(sc.nextLine());
      Map<Integer, List<Integer>> cordinates = new HashMap<>();
      for (int r = 0; r < values.size(); ++r) {
        for (int c = 0; c < values.size(); ++c) {
          if (!Character.isDigit(values.get(r).charAt(c)) && values.get(r).charAt(c) != '.') {
            for (int checkR = r - 1; checkR <= r + 1 ; ++checkR) {
              for (int checkC = c - 1; checkC <= c + 1; ++checkC) {
                if (checkR >=0 && checkR < values.size() && checkC >= 0 && checkC < values.get(checkR).length() &&
                        Character.isDigit(values.get(checkR).charAt(checkC))) {
                  int val = checkC;
                  while (val-1 >= 0 && Character.isDigit(values.get(checkR).charAt(val-1))) {
                    --val;
                  }
                  if (cordinates.containsKey(checkR)) {
                    if (!cordinates.get(checkR).contains(val)) {
                      cordinates.get(checkR).add(val);
                    }
                  } else {
                    List<Integer> toAdd = new ArrayList<>();
                    toAdd.add(val);
                    cordinates.put(checkR, toAdd);
                  }

                }

              }
            }

          }
        }
      }
      for (Integer integers : cordinates.keySet()) {
        for (Integer integer : cordinates.get(integers)) {
          int leftValue = integer;
          while(leftValue < values.get(integers).length() &&
                  Character.isDigit(values.get(integers).charAt(leftValue))){
            leftValue++;
          }
          total += Integer.parseInt(values.get(integers).substring(integer, leftValue));

        }
      }

      return total;
    } catch (Exception e) {
      System.out.println("error");
      return -1;
    }
  }

  public static int gearBox(String filename){
      int total = 0;
      int value;
      try (Scanner sc = new Scanner( new File(filename)))
      {
      List<String> values = new ArrayList<>();
      while (sc.hasNextLine()) values.add(sc.nextLine());
      for (int r = 0; r < values.size(); ++r) {
        for (int c = 0; c < values.size(); ++c) {
          int ajacent = 0;
          Map<Integer, List<Integer>> cordinates = new HashMap<>();
          if (values.get(r).charAt(c) == '*') {
            for (int checkR = r - 1; checkR <= r + 1 ; ++checkR) {
              for (int checkC = c - 1; checkC <= c + 1; ++checkC) {
                if (checkR >=0 && checkR < values.size() && checkC >= 0 && checkC < values.get(checkR).length() &&
                        Character.isDigit(values.get(checkR).charAt(checkC))) {
                  int val = checkC;
                  while (val-1 >= 0 && Character.isDigit(values.get(checkR).charAt(val-1))) {
                    --val;
                  }
                  if (cordinates.containsKey(checkR)) {
                    if (!cordinates.get(checkR).contains(val)) {
                      ajacent++;
                      cordinates.get(checkR).add(val);
                    }
                  } else {
                    List<Integer> toAdd = new ArrayList<>();
                    toAdd.add(val);
                    cordinates.put(checkR, toAdd);
                    ajacent++;
                  }

                }

              }
            }
            if(ajacent == 2) {
              value = 1;
              for (Integer integers : cordinates.keySet()) {
                for (Integer integer : cordinates.get(integers)) {
                  int leftValue = integer;
                  while (leftValue < values.get(integers).length() &&
                          Character.isDigit(values.get(integers).charAt(leftValue))) {
                    leftValue++;
                  }
                  value *= Integer.parseInt(values.get(integers).substring(integer, leftValue));

                }
              }
              total += value;
            }

          }
        }
      }


      return total;
    } catch (Exception e) {
      System.out.println("error");
      return -1;
    }
  }
}
  /*
  public static int StringAjacent(String filename){
    try {
      int total = 0;
      int value;
      File file = new File(filename);
      Scanner sc = new Scanner(file);
      List<String> values = new ArrayList<>();

      while(sc.hasNextLine()) values.add(sc.nextLine());
      for(int i=0; i<values.size(); ++i){
        String line = values.get(i);
        for(int j=0; j<line.length(); ++j){
          boolean num = (line.charAt(j)-'0' >=0 && line.charAt(j)-'0'<=9);
          if(!num && line.charAt(j) != '.'){
            String replace = "";

            boolean leftB = CheckNum(values, i-1, j-1);
            if(leftB) {
              int farLeft = j - 1;
              while (CheckNum(values, i-1, farLeft - 1)) {
                farLeft--;
              }
              int farRight = j - 1;
              while (CheckNum(values, i-1, farRight + 1)) {
                farRight++;
              }
              value = Integer.parseInt(values.get(i-1).substring(farLeft, farRight+1));
              replace = values.get(i-1).substring(0,farLeft) + ".".repeat(farRight-farLeft+1) + values.get(i-1).substring(farRight+1);
              total += value;
              value = 0;
              values.remove(i-1);
              values.add(i-1,replace);
              replace = "";
            }
            boolean middleB = CheckNum(values, i-1, j);
            if(middleB){
              int farLeft = j;
              while (CheckNum(values, i-1, farLeft - 1)) {
                farLeft--;
              }
              int farRight = j;
              while (CheckNum(values, i-1, farRight  + 1)) {
                farRight++;
              }
              value = Integer.parseInt(values.get(i-1).substring(farLeft, farRight+1));
              replace = values.get(i-1).substring(0,farLeft) + ".".repeat(farRight-farLeft+1) + values.get(i-1).substring(farRight+1);
              total += value;
              value = 0;
              values.remove(i-1);
              values.add(i-1,replace);
              replace = "";            }
            boolean rightB = CheckNum(values, i-1, j+1);
            if(rightB) {
              int farLeft = j + 1;
              while (CheckNum(values, i-1, farLeft - 1)) {
                farLeft--;
              }
              int farRight = j + 1;
              while (CheckNum(values, i-1, farRight + 1)) {
                farRight++;
              }
              value = Integer.parseInt(values.get(i-1).substring(farLeft, farRight+1));
              replace = values.get(i-1).substring(0,farLeft) + ".".repeat(farRight-farLeft+1) + values.get(i-1).substring(farRight+1);
              total += value;
              value = 0;
              values.remove(i-1);
              values.add(i-1,replace);
              replace = "";            }

            boolean leftC = CheckNum(values, i, j-1);
            if(leftC){
              int farLeft = j-1;
              while(CheckNum(values, i, farLeft -1)){
                farLeft--;
              }
              value =Integer.parseInt(line.substring(farLeft, j));
              line = line.substring(0,farLeft) + ".".repeat(j-farLeft) + line.substring(j);
              total += value;
              value = 0;
              values.remove(i);
              values.add(i,line);
            }
            boolean rightC = CheckNum(values, i, j+1);
            if(rightC){
              int farRight = j+1;
              while(CheckNum(values, i, farRight +1)){
                farRight++;
              }
              value =Integer.parseInt(line.substring(j+1, farRight+1));
              line = line.substring(0,j+1) + ".".repeat(farRight-j) + line.substring(farRight+1);
              total += value;
              value = 0;
              values.remove(i);
              values.add(i,line);
            }
            boolean leftU = CheckNum(values, i+1, j-1);
            if(leftU) {
              int farLeft = j - 1;
              while (CheckNum(values, i+1, farLeft - 1)) {
                farLeft--;
              }
              int farRight = j - 1;
              while (CheckNum(values, i+1, farRight + 1)) {
                farRight++;
              }
              value = Integer.parseInt(values.get(i+1).substring(farLeft, farRight+1));
              replace = values.get(i+1).substring(0,farLeft) + ".".repeat(farRight-farLeft+1) + values.get(i+1).substring(farRight+1);
              total += value;
              value = 0;
              values.remove(i+1);
              values.add(i+1,replace);
              replace = "";            }
            boolean middleU = CheckNum(values, i+1, j);
            if(middleU){
              int farLeft = j;
              while (CheckNum(values, i+1, farLeft - 1)) {
                farLeft--;
              }
              int farRight = j;
              while (CheckNum(values, i+1, farRight + 1)) {
                farRight++;
              }
              value = Integer.parseInt(values.get(i+1).substring(farLeft, farRight+1));
              replace = values.get(i+1).substring(0,farLeft) + ".".repeat(farRight-farLeft+1) + values.get(i+1).substring(farRight+1);
              total += value;
              value = 0;
              values.remove(i+1);
              values.add(i+1,replace);
              replace = "";            }
            boolean rightU = CheckNum(values, i+1, j+1);
            if(rightU) {
              int farLeft = j + 1;
              while (CheckNum(values, i+1, farLeft - 1)) {
                farLeft--;
              }
              int farRight = j + 1;
              while (CheckNum(values, i+1, farRight + 1)) {
                farRight++;
              }
              value = Integer.parseInt(values.get(i+1).substring(farLeft, farRight+1));
              replace = values.get(i+1).substring(0,farLeft) + ".".repeat(farRight-farLeft+1) + values.get(i+1).substring(farRight+1);
              total += value;
              value = 0;
              values.remove(i+1);
              values.add(i+1,replace);
              replace = "";            }

          }
        }
      }
      return total;


    }
    catch(Exception e){
      System.out.println("error");
      return -1;
    }
  }
  public static int badCode(String filename){
    try {
      int total = 0;
      int nearby = 0;
      int value = 1;
      File file = new File(filename);
      Scanner sc = new Scanner(file);
      List<String> values = new ArrayList<>();
      while(sc.hasNextLine()) values.add(sc.nextLine());
      for(int i=0; i<values.size(); ++i){
        String line = values.get(i);
        for(int j=0; j<line.length(); ++j){
          boolean num = (line.charAt(j)-'0' >=0 && line.charAt(j)-'0'<=9);
          if(!num && line.charAt(j) != '.'){
            String replace = "";

            boolean leftB = CheckNum(values, i-1, j-1);
            if(leftB) {
              nearby++;
              int farLeft = j - 1;
              while (CheckNum(values, i-1, farLeft - 1)) {
                farLeft--;
              }
              int farRight = j - 1;
              while (CheckNum(values, i-1, farRight + 1)) {
                farRight++;
              }
              value *= Integer.parseInt(values.get(i-1).substring(farLeft, farRight+1));
              replace = values.get(i-1).substring(0,farLeft) + ".".repeat(farRight-farLeft+1) + values.get(i-1).substring(farRight+1);
              values.remove(i-1);
              values.add(i-1,replace);
              replace = "";
            }
            boolean middleB = CheckNum(values, i-1, j);
            if(middleB){
              nearby++;

              int farLeft = j;
              while (CheckNum(values, i-1, farLeft - 1)) {
                farLeft--;
              }
              int farRight = j;
              while (CheckNum(values, i-1, farRight  + 1)) {
                farRight++;
              }
              value *= Integer.parseInt(values.get(i-1).substring(farLeft, farRight+1));
              replace = values.get(i-1).substring(0,farLeft) + ".".repeat(farRight-farLeft+1) + values.get(i-1).substring(farRight+1);
              values.remove(i-1);
              values.add(i-1,replace);
              replace = "";            }
            boolean rightB = CheckNum(values, i-1, j+1);
            if(rightB) {
              nearby++;

              int farLeft = j + 1;
              while (CheckNum(values, i-1, farLeft - 1)) {
                farLeft--;
              }
              int farRight = j + 1;
              while (CheckNum(values, i-1, farRight + 1)) {
                farRight++;
              }
              value *= Integer.parseInt(values.get(i-1).substring(farLeft, farRight+1));
              replace = values.get(i-1).substring(0,farLeft) + ".".repeat(farRight-farLeft+1) + values.get(i-1).substring(farRight+1);
              values.remove(i-1);
              values.add(i-1,replace);
              replace = "";            }

            boolean leftC = CheckNum(values, i, j-1);
            if(leftC){
              nearby++;

              int farLeft = j-1;
              while(CheckNum(values, i, farLeft -1)){
                farLeft--;
              }
              value =Integer.parseInt(line.substring(farLeft, j));
              line = line.substring(0,farLeft) + ".".repeat(j-farLeft) + line.substring(j);
              total += value;
              value = 0;
              values.remove(i);
              values.add(i,line);
            }
            boolean rightC = CheckNum(values, i, j+1);
            if(rightC){
              int farRight = j+1;
              while(CheckNum(values, i, farRight +1)){
                farRight++;
              }
              value *=Integer.parseInt(line.substring(j+1, farRight+1));
              line = line.substring(0,j+1) + ".".repeat(farRight-j) + line.substring(farRight+1);
              values.remove(i);
              values.add(i,line);
            }
            boolean leftU = CheckNum(values, i+1, j-1);
            if(leftU) {
              nearby++;

              int farLeft = j - 1;
              while (CheckNum(values, i+1, farLeft - 1)) {
                farLeft--;
              }
              int farRight = j - 1;
              while (CheckNum(values, i+1, farRight + 1)) {
                farRight++;
              }
              value *= Integer.parseInt(values.get(i+1).substring(farLeft, farRight+1));
              replace = values.get(i+1).substring(0,farLeft) + ".".repeat(farRight-farLeft+1) + values.get(i+1).substring(farRight+1);
              values.remove(i+1);
              values.add(i+1,replace);
              replace = "";            }
            boolean middleU = CheckNum(values, i+1, j);
            if(middleU){
              nearby++;

              int farLeft = j;
              while (CheckNum(values, i+1, farLeft - 1)) {
                farLeft--;
              }
              int farRight = j;
              while (CheckNum(values, i+1, farRight + 1)) {
                farRight++;
              }
              value *= Integer.parseInt(values.get(i+1).substring(farLeft, farRight+1));
              replace = values.get(i+1).substring(0,farLeft) + ".".repeat(farRight-farLeft+1) + values.get(i+1).substring(farRight+1);
              values.remove(i+1);
              values.add(i+1,replace);
              replace = "";            }
            boolean rightU = CheckNum(values, i+1, j+1);
            if(rightU) {
              nearby++;

              int farLeft = j + 1;
              while (CheckNum(values, i+1, farLeft - 1)) {
                farLeft--;
              }
              int farRight = j + 1;
              while (CheckNum(values, i+1, farRight + 1)) {
                farRight++;
              }
              value *= Integer.parseInt(values.get(i+1).substring(farLeft, farRight+1));
              replace = values.get(i+1).substring(0,farLeft) + ".".repeat(farRight-farLeft+1) + values.get(i+1).substring(farRight+1);
              values.remove(i+1);
              values.add(i+1,replace);
              replace = "";            }

          }
          if(nearby==2){
            nearby = 0;
            total += value;
            value = 0;
          }
        }
      }
      return total;


    }
    catch(Exception e){
      System.out.println("error");
      return -1;
    }
  }
  public static boolean CheckNum(List<String> values, int row, int col){
    try{
      return values.get(row).charAt(col)-'0' >=0 && values.get(row).charAt(col)-'0'<=9;

    }
    catch(Exception e) {
      return false;
    }
  }
}

   */
