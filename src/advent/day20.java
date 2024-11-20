package advent;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.print.attribute.standard.Destination;

public class day20 {
  public long PulseProp(String fileName){
    try(Scanner sc = new Scanner(new File(System.getProperty("user.dir") + fileName))){
      Mod broadCastTargets = new Mod("-1", "-1", "-1");
      Map<String, Mod> modules = new HashMap<>();
      while(sc.hasNextLine()){
        String s = sc.nextLine();
        s = s.replaceAll("\\s", "");
        String[] split = s.split("->");
        if(split[0].equals("broadcaster")){
          broadCastTargets = new Mod("-1", "broadcast", split[1]);
          modules.put("broadcast", broadCastTargets);
        }
        else{
          String type = split[0].substring(0,1);
          String name = split[0].substring(1);
          Mod newMod = new Mod(type, name, split[1]);
          modules.put(name, newMod);


        }


      }

      for(Mod m : modules.values()){
        for(String output : m.getDestinations()){
          if(modules.containsKey(output) && modules.get(output).getType().equals("&")){
            Mod toEdit = modules.get(output);
            toEdit.editMem(m, false);
          }
        }
      }
      List<Pulses> toGo = new ArrayList<>();
      long low = 0;
      long high = 0;
      for(int i =0; i<1000; ++i){
        low++;
        for(String targets : broadCastTargets.getDestinations()){
          toGo.add(new Pulses(broadCastTargets, modules.get(targets), false));
          low++;
        }

          while(!toGo.isEmpty()){
            Pulses current = toGo.remove(0);
              String pulseType = current.going.PulseType(current.receiver, current.Pulse);
            if(pulseType.equals("L")) {
              for(String targets : current.going.getDestinations()){
                ++low;
                if(modules.containsKey(targets)) toGo.add(new Pulses(current.going, modules.get(targets), false));}
            }
            if(pulseType.equals("H")) {
              for(String targets : current.going.getDestinations()){
                ++high;
                if(modules.containsKey(targets)) toGo.add(new Pulses(current.going, modules.get(targets), true));}
            }

          }
        }
      return low*high;



    }
    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }
  public long SingleRx(String fileName){
    try(Scanner sc = new Scanner(new File(System.getProperty("user.dir") + fileName))){
      Mod broadCastTargets = new Mod("-1", "-1", "-1");
      Map<String, Mod> modules = new HashMap<>();
      String feeder = "";
      while(sc.hasNextLine()){
        String s = sc.nextLine();
        s = s.replaceAll("\\s", "");
        String[] split = s.split("->");
        if(split[0].equals("broadcaster")){
          broadCastTargets = new Mod("-1", "broadcast", split[1]);
          modules.put("broadcast", broadCastTargets);
        }
        else{

          String type = split[0].substring(0,1);
          String name = split[0].substring(1);
          if(split[1].contains("rx")){
            if(feeder.length() != 0) throw new IllegalArgumentException("should not have two feeds");
            feeder = name;
          }
          Mod newMod = new Mod(type, name, split[1]);
          modules.put(name, newMod);


        }


      }
      List<Mod> intoFeeder  = new ArrayList<>();

      for(Mod m : modules.values()){
        for(String output : m.getDestinations()){
          if(output.equals(feeder)) intoFeeder.add(m);

          if(modules.containsKey(output) && modules.get(output).getType().equals("&")){
            Mod toEdit = modules.get(output);
            toEdit.editMem(m, false);
          }
        }
      }
      Map<Mod, Long> HighBeam = new HashMap<>();
      List<Pulses> toGo = new ArrayList<>();
      long count = 1;
      while(HighBeam.size() != intoFeeder.size() ){
        for(String targets : broadCastTargets.getDestinations()){
          if(targets.equals("rx")) return count;
          toGo.add(new Pulses(broadCastTargets, modules.get(targets), false));
        }

        while(!toGo.isEmpty()){
          Pulses current = toGo.remove(0);
          String pulseType = current.going.PulseType(current.receiver, current.Pulse);
          if(pulseType.equals("L")) {
            for(String targets : current.going.getDestinations()){
              if(targets.equals("rx")) return count;

              if(modules.containsKey(targets)) toGo.add(new Pulses(current.going, modules.get(targets), false));}
          }
          if(pulseType.equals("H")) {
            if(intoFeeder.contains(current.going) && !HighBeam.containsKey(current.going)){
              HighBeam.put(current.going, count);
            }

            for(String targets : current.going.getDestinations()){

              if(modules.containsKey(targets)) toGo.add(new Pulses(current.going, modules.get(targets), true));}
          }

        }
        count++;

      }
      List<Long> longValues = new ArrayList<>();
      for(long n : HighBeam.values()){
        longValues.add(n);
      }
      long divider = 1;
      for(int i =0; i<longValues.size(); i++){
        long v1 = longValues.get(i);
        divider = (divider*v1/gcd(v1,divider));

      }
      return divider;



    }
    catch(Exception e){
      System.out.println(e);
      return -1;
    }
  }
  public static long gcd(long left, long right){
    if(left < right){
      long t = left;
      left = right;
      right = t;
    }
    while(right!= 0) {
      long t = right;
      right = left % right;
      left = t;
    }
    return left;
  }


  public class Pulses{
    Mod receiver;
    Mod going;

    boolean Pulse;

    public Pulses(Mod receiver, Mod going, boolean pulse) {
      this.receiver = receiver;
      this.going = going;
      Pulse = pulse;
    }
  }


    public class Mod {
      String name;
      String type;
      List<String> destinations;

      String mem;

      Map<Mod, Boolean> memory;
      public void editMem(Mod m, Boolean high){
        memory.put(m, high);
      }
      public Mod(String name, String type, List<String> destinations, String mem, Map<Mod, Boolean> memory) {

        this.name = name;
        this.type = type;
        this.destinations = destinations;
        this.mem = mem;
        this.memory = memory;
      }

      public Mod clone(){
        List<String> copy = new ArrayList<>(destinations);
        return new Mod(name, type, copy, mem, memory);
      }
    public String PulseType(Mod receiver, boolean pulse){
        if(type.equals("%")){
          if(!pulse){
            if(mem.equals("off")) {
              mem = "on";
              return "H";
            }
            else{
              mem = "off";
              return "L";
            }
          }
          return "P";
        }
        else{
          memory.put(receiver, pulse);
          boolean allHigh = true;
          for(Boolean value : memory.values()){
            allHigh = allHigh && value;
            if(!allHigh) return "H";
          }
          return "L";
        }



    }




      public Mod(String type, String name, String destinations) {
        this.name = name;
        this.type = type;
        String[] outputs = destinations.split(",");
        this.destinations = Arrays.asList(outputs);
        if(type.equals("%")){
          this.mem = "off";
        }
        else{
          memory = new HashMap<>();
        }
      }


      public String getName() {
        return name;
      }

      public String getType() {
        return type;
      }

      public List<String> getDestinations() {
        return destinations;
      }

      public String getMem() {
        return mem;
      }
    }



}
