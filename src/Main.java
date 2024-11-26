import advent.*;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
public class Main {
  public static void main(String[] args) {

//    int dayOne1 = dayone1.stringGather("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day1InfoP1.txt");
//    int dayOne2 = dayone1.includeNum2("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day1InfoP1.txt");
//    int dayTwo1 = daytwo.sum("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day2Info.txt");
//    int dayTwo2 = daytwo.powerSet("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day2Info.txt");
//    int dayThree1 = daythree.StringAjacent("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day3Info.txt");
//    int dayThree2 = daythree.gearBox("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day3Info.txt");
//    int dayFour1 = dayfour.score("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day4Info.txt");
//    int dayFour2 = dayfour.copyCards("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day4Info.txt");
//    Long dayFive1 = dayfive.smallestSoilWalk("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day5Info.txt");
//    Long dayFive2 = dayfive.rangeSoilWalk("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day5Info.txt");
//    int daySix1 = daysix.timeSpent("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day6Info.txt");
//    long daySix2 = daysix.oneRace("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day6Info.txt");
//    dayseven seven = new dayseven();
//    int daySeven1 = seven.handRanking("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day7Info.txt", 1);
//    int daySeven2= seven.handRanking("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day7Info.txt", 2);
//    long dayEight1 = dayeight.camelFind("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day8Info.txt");
//    long dayEight2 = dayeight.camelEnding("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day8Info.txt");
//    int daynine1 = daynine.OASIS("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day9Info.txt", true);
//    int daynine2 = daynine.OASIS("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day9Info.txt", false);
//      int dayten1 = dayten.farthestDist("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day10Info.txt", 1);
//    int dayten2 = dayten.farthestDist("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day10Info.txt", 2);
//    dayeleven eleven = new dayeleven();
//    long dayeleven1 = eleven.shortPaths("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day11Info.txt", 1);
//    long dayeleven2 = eleven.shortPaths("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day11Info.txt", 1000000);
//    day12 twelve = new day12();
//    long daytwelve1 = twelve.possibilities("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day12Info.text");
//    long daytwelve2 = twelve.possibilitiespart2("C:\\Users\\high5\\coding\\advent2023\\src\\advent\\day12Info.text");
//    day13 thirteen = new day13();
//    int daythirteen1 = thirteen.reflections("\\src\\advent\\day13Info.txt");
//    int daythirteen2 = thirteen.reflections2("\\src\\advent\\day13Info.txt");
//    day14 fourteen = new day14();
//    int dayfourteen1 = fourteen.distance("\\src\\advent\\day14Info.txt");
//    int dayfourteen2 = fourteen.distanceMultiple("\\src\\advent\\day14Info.txt", 1000000000);
//    day15 fifteen = new day15();
//    int dayfifteen1 = fifteen.allValues("\\src\\advent\\day15Info.txt");
//    Instant start = Instant.now();
//    int dayfifteen2 = fifteen.mappingValues("\\src\\advent\\day15Info.txt");

//    day16 sixteen = new day16();
//    int daysixteen1 = sixteen.reflectionTravel("\\src\\advent\\day16Info");
//    int daysixteen2 = sixteen.allTravel("\\src\\advent\\day16Info");
//
//    day17 seventeen = new day17();
//    int dayseventeen1 = seventeen.leastDist("\\src\\advent\\day17Info.txt", false);
//    int dayseventeen2 = seventeen.leastDist("\\src\\advent\\day17Info.txt", true);
//    day18 eighteen = new day18();
//    long dayeighteen1 = eighteen.shoeLace("\\src\\advent\\day18Info.txt");
//    long dayeighteen2 = eighteen.hexCalc("\\src\\advent\\day18Info.txt");

//    day19 ninteen = new day19();
//    int dayninteen1 = ninteen.expressionFigure("\\src\\advent\\day19Info.txt");
//    long dayninteen2 = ninteen.rangeCreator("\\src\\advent\\day19Info.txt");

//    day20 twenty = new day20();
//    long daytwenty1 = twenty.PulseProp("\\src\\advent\\day20Info.txt");
//    long daytwenty2 = twenty.SingleRx("\\src\\advent\\day20Info.txt");

//    day21 twentyone = new day21();
//    int daytwentyone1 = twentyone.possibleGardens("\\src\\advent\\day21Info.txt", 64);
//    long daytwentyone2 = twentyone.simulation("\\src\\advent\\day21Info.txt", 26501365);

//    day22 twentytwo = new day22();
//    int daytwentytwo1 = twentytwo.brickFailing("\\src\\advent\\day22Info.txt");
//    int daytwentytwo2 = twentytwo.brickDisiengrate("\\src\\advent\\day22Info.txt");

//    day23 twentythree = new day23();
//    int daytwentythree1 = twentythree.ReverseDijkstra("\\src\\advent\\day23Info.txt");
//    int daytwentythree2 = twentythree.ReverseDijkstraModify("\\src\\advent\\day23Info.txt");

    day24 twentyfour = new day24();
    Instant start = Instant.now();

    double twentyfour1= twentyfour.amountColl("\\src\\advent\\day24Info.txt", 200000000000000d, 400000000000000d);
    double twentyfour2= twentyfour.integerIntersection("\\src\\advent\\day24Info.txt");




    Instant end = Instant.now();
    Duration timeSpent = Duration.between(start,end);

    System.out.println("Answer: " + twentyfour2 + " Time: " + timeSpent);
  }

}