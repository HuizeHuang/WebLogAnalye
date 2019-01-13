
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*; 

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int uniCount = la.countUniqueIPs();
        System.out.println("There are " + uniCount + " unique IP addresses.");
    }
    
    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int uniCount = la.uniqueIPVisitsOnDay("Sep 27");
        System.out.println("There are " + uniCount + " unique IP addresses visted on someday.");
    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int uniCount = la.countUniqueIPsInRange(400,499);
        System.out.println("There are " + uniCount + " unique IP addresses in range.");
    }
    
    public void testPrintAllHigher(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testCountVisitisPerIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String,Integer> counts = la.countVisitisPerIP();
        System.out.println(counts);
    }
    
    public void testMostNumberVisitsByIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog263_log");
        HashMap<String,Integer> counts = la.countVisitisPerIP();
        int max = la.mostNumberVisitsByIP(counts);
        System.out.println("most: " + max);
    }
    
    public void testIPsMostVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> counts = la.countVisitisPerIP();
        ArrayList<String> list = la.iPsMostVisits(counts);
        System.out.println("ip with most visits: " + list);
    }
    
    public void testIPsForDays(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String,ArrayList<String>> map = la.iPsForDays();
        System.out.println(map);
    }
    
    public void testDayWithMostIPVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> map = la.iPsForDays();
        String dayMax = la.dayWithMostIPVisits(map);
        System.out.println(dayMax);
    }
    
    public void testIPsWithMostVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> map = la.iPsForDays();
        System.out.println(la.iPsWithMostVisitsOnDay(map,"Sep 29"));
    }
}
