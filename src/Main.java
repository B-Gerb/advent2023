import advent.*;
public class Main {
  public static void main(String[] args) {
    int dayOne1 = dayone1.stringGather("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day1InfoP1.txt");
    int dayOne2 = dayone1.includeNum2("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day1InfoP1.txt");
    int dayTwo1 = daytwo.sum("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day2Info.txt");
    int dayTwo2 = daytwo.powerSet("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day2Info.txt");
    int dayThree1 = daythree.StringAjacent("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day3Info.txt");
    int dayThree2 = daythree.gearBox("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day3Info.txt");
    int dayFour1 = dayfour.score("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day4Info.txt");
    int dayFour2 = dayfour.copyCards("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day4Info.txt");
    Long dayFive1 = dayfive.smallestSoilWalk("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day5Info.txt");

    System.out.println(dayFive1);
  }
}