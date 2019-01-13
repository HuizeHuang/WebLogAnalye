
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for(String s : fr.lines()){
             LogEntry le = WebLogParser.parseEntry(s);
             records.add(le);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public void printAllHigherThanNum(int num) {
         ArrayList<Integer> stsList = new ArrayList<Integer>();
         for (LogEntry le : records) {
             int stsCode = le.getStatusCode();
             if(stsCode > num){
                 if(!stsList.contains(stsCode))
                    stsList.add(stsCode);
            }
         }
         System.out.println(stsList);
     }
     
     public int countUniqueIPs(){
       ArrayList<String> uniqueIPs = new ArrayList<String>(); 
       for(LogEntry le : records){
           String ipAddr = le.getIpAddress();
           if(!uniqueIPs.contains(ipAddr)){
               uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
     }

     public int uniqueIPVisitsOnDay(String someday){
       ArrayList<String> uniqueIPs = new ArrayList<String>(); 
       for(LogEntry le : records){
           Date d = le.getAccessTime();
           String date = d.toString();
           if(date.contains(someday)){
               String ipAddr = le.getIpAddress();
               if(!uniqueIPs.contains(ipAddr)){
                   uniqueIPs.add(ipAddr);
               }
           }
        }
       return uniqueIPs.size();
     }
     
     public int countUniqueIPsInRange(int low, int high){
       ArrayList<String> uniqueIPs = new ArrayList<String>(); 
       for(LogEntry le : records){
           int stsCode = le.getStatusCode();
           if(stsCode >= low && stsCode <= high){
               String ipAddr = le.getIpAddress();
               if(!uniqueIPs.contains(ipAddr)){
                   uniqueIPs.add(ipAddr);
               }
           }
       }
       return uniqueIPs.size();
     }
     
     public HashMap<String,Integer> countVisitisPerIP(){
         HashMap<String,Integer> counts = new HashMap<String,Integer>();
         for(LogEntry le : records){
             String ip = le.getIpAddress();
             if(!counts.containsKey(ip)){
                 counts.put(ip,1);
             }
             else{
                 counts.put(ip,counts.get(ip)+1);
             }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String,Integer> counts){
         int max = 0;
         for(String s : counts.keySet()){
             if(counts.get(s) > max){
                 max = counts.get(s);
                }
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts){
         ArrayList<String> list = new ArrayList<String>();
         int max = mostNumberVisitsByIP(counts);
         for(String s : counts.keySet()){
             if(counts.get(s) == max)
                list.add(s);
         }
         return list;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays(){
         HashMap<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
         for(LogEntry le : records){
             String time = le.getAccessTime().toString();
             String date = time.substring(4,10);
             if(!map.containsKey(date)){
                 ArrayList<String> ipAddrList = new  ArrayList<String>();
                 ipAddrList.add(le.getIpAddress());
                 map.put(date,ipAddrList);
             }
             else{
                 map.get(date).add(le.getIpAddress());
             }
         }
         return map;
     }
     
     public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> map){
         int maxIp = 0;
         String dayMax = "";
         for(String s : map.keySet()){
             int currIp = map.get(s).size();
             if(currIp > maxIp){
                 maxIp = currIp;
                 dayMax = s;
                }
         }
         return dayMax;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay( HashMap<String, ArrayList<String>> map, String date){
         ArrayList<String> visitsOnDay = map.get(date);
         HashMap<String,Integer> ipCounts = new HashMap<String,Integer>();
         for(String ip : visitsOnDay){
             if(!ipCounts.containsKey(ip)){
                ipCounts.put(ip,1);
             }
             else{
                 ipCounts.put(ip,ipCounts.get(ip)+1);
             }
         }
         return iPsMostVisits(ipCounts);
     }
}
