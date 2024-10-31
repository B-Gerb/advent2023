package advent;

public class dayten {
  public static int farthestDist(String fileName){
    try(Scanner sc = new Scanner(new File(fileName))){

      return 1;
    }
    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }
}
