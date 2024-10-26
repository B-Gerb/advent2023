package advent;
import java.io.File;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
public class daytwo {
  public static int sum(String fileName) {
      int total =0;
      int red = 12;
      int green = 13;
      int blue = 14;
    try (Scanner s = new Scanner( new File(fileName)))
    {
        while(s.hasNextLine()){
          String line = s.nextLine();
          String first = line.split(":")[0];
          line = line.split(":")[1];
          int game = Integer.parseInt(first.split(" ")[1]);
          List<String> values = Arrays.asList(line.split(";"));
          boolean correct = true;
          for (int i=0; i<values.size() && correct; ++i) {
            String value = values.get(i);
            List<String> numbers = Arrays.asList(value.split(","));
            for(int j =0; j<numbers.size(); ++j){
              if(numbers.get(j).indexOf("red") != -1){
                if(red < Integer.parseInt(numbers.get(j).split(" ")[1])){
                  correct = false;
                }

              }
              if(numbers.get(j).indexOf("blue") != -1){
                if(blue < Integer.parseInt(numbers.get(j).split(" ")[1])){
                  correct = false;
                }
              }
              if(numbers.get(j).indexOf("green") != -1){
                if(green < Integer.parseInt(numbers.get(j).split(" ")[1])){
                  correct = false;
                }
              }
            }

          }
          if(correct){
            total += game;
          }

        }
        return total;
      }
      catch(Exception e){
        System.out.println("error");
        return 1;
      }
  }
  public static int powerSet(String fileName) {
    int total =0;

    try (Scanner s = new Scanner( new File(fileName)))
    {
      while(s.hasNextLine()){
        int red = 0;
        int green = 0;
        int blue = 0;
        String line = s.nextLine();
        line = line.split(":")[1];
        List<String> values = Arrays.asList(line.split(";"));
        for (int i=0; i<values.size(); ++i) {
          String value = values.get(i);
          List<String> numbers = Arrays.asList(value.split(","));
          for(int j =0; j<numbers.size(); ++j){
            int num = Integer.parseInt(numbers.get(j).split(" ")[1]);
            if(numbers.get(j).indexOf("red") != -1){
              if(red < num){
                red = num;
              }

            }
            if(numbers.get(j).indexOf("blue") != -1){
              if(blue < num){
                blue = num;
              }
            }
            if(numbers.get(j).indexOf("green") != -1){
              if(green < num){
                green = num;
              }
            }
          }

        }
        total = total + (red*green*blue);

      }
      return total;
    }
    catch(Exception e){
      System.out.println("error");
      return 1;
    }
  }

}
