package com.company;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.*;

public class Main {
    public static List<List<Integer>> bruteThreeSum(int[] list){        //Brute Three Sum Start
        Arrays.sort(list);                                              //sorts the array
        List<List<Integer>> bruteSum = new LinkedList<>();
        int a,b,c,i,j,k;

        for(i = 0; i< list.length - 2; i++){                            //nested loops, looping until reaching near the end of the list
            a = list[i];
            if( i == 0 || list[i] != list[i-1]){
                for(j = i+1; j< list.length -1; j++){
                    b = list[j];
                    if(list[j] != list[j-1]){
                        for(k = j+1; k < list.length; k++){
                            c = list[k];
                            if(list[k] != list[k-1]){
                                if(a + b + c == 0) {
                                    bruteSum.add(Arrays.asList(a,b,c)); //returns a fixed sized to bruteSum
                                }
                            }
                        }
                    }
                }
            }
        }
        return bruteSum;
    }
    public static List<List<Integer>> fastThreeSum(int[] list){         //fast three sum start
        Arrays.sort(list);                                              //sorts the array
        List<List<Integer>> fastSum = new LinkedList<>();

        for(int i  = 0; i < list.length - 2; i++){                      //goes through the length of the list
            if(i == 0 || list[i] != list[i-1]){
                 int a = i + 1;
                 int b = list.length -1;
                 int c = 0;
                 while(a < b){
                     if(list[a] + list[b] == c){
                         fastSum.add(Arrays.asList(list[i], list[a], list[b])); //returns a fixed sized list to fastSum
                         while( a < b && list[a] == list[a+1]){
                             a++;
                         }
                         while(a < b && list[b] == list[b -1]){
                             b--;
                         }
                         a++;
                         b--;
                     }
                     else if(list[a] + list[b] > c){
                         b--;
                     }else{
                         a++;
                     }
                 }
            }
        }
        return fastSum;
    }
    public static List<List<Integer>>  fastestThreeSum(int [] list){        //fastest three sum start
        Arrays.sort(list);                                                  // sorts the array
        List<List<Integer>> fastestSum = new LinkedList<>();
        for(int i = 0; i < list.length-2; i++){                             //goes through the length of the list
            int a = i + 1;
            int b = list.length -1;
            while(a < b){
                if((list[i] + list[a] + list[b]) == 0){
                    fastestSum.add(Arrays.asList(list[i], list[a], list[b]));// returns a fixed sized list to fastestSum
                    a++;
                    b--;
                }
                else if((list[i] + list[a] + list[b]) > 0){
                    b--;
                }else{
                    a++;
                }
            }
        }
        return fastestSum;
    }

    public static int[] listGeneration(int len)         //generates a list of random numbers
    {
        Random rand = new Random();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++){
            arr[i] = rand.nextInt(23);
        }
        return arr;
    }
    public static void runTimeTests(int[] list) {
        long N =4;
        long startTime = 0;
        long endTime = 0;
        long totalTime = 0;
        long brutePrevTime = 1;
        long fastPrevTime = 1;
        long fastestPrevTime = 1;
        float doubling;
        double expDoubling;

        System.out.printf("| %15s| %15s| %15s| %15s| %15s| %15s| %15s| %15s| %15s| %15s|\n", "", "Brute 3sum", "", "", "Faster 3sum", "", "", "Fastest 3sum", "", "");
        System.out.printf("| %15s| %15s| %15s| %15s| %15s| %15s| %15s| %15s| %15s| %15s|\n", "N", "Time", "D Ratio", "Exp D Ratio", "Time", "D Ratio", "Exp D Ratio", "Time", "D Ratio", "Exp D Ratio");
        for (N = 4; N < 1000000000; N *= 2) {                   //loops to the highest allowed value of N
            System.out.printf("| %15d|", N);

            startTime = getCpuTime();
            List<List<Integer>> bruteThreeSum = bruteThreeSum(list); // calls the brute function
            endTime = getCpuTime();
            totalTime = endTime - startTime;                    //finds the difference between the two times
            System.out.printf(" %15d|", totalTime);
            if (N != 0 && N % 2 == 0) {
                doubling = totalTime / brutePrevTime;           //doubling ratio
                expDoubling = (Math.pow(N, 3) / (Math.pow((N / 2), 3)));  // expected doubling ratio N^3
                System.out.printf(" %15.2f| %15.2f|", doubling, expDoubling);
            } else {
                System.out.printf(" %15s| %15s|", "NA", "NA");
            }
            brutePrevTime = totalTime;

            startTime = getCpuTime();
            List<List<Integer>> fastThreeSum = fastThreeSum(list); // calls the fast function
            endTime = getCpuTime();
            totalTime = endTime - startTime;                    //finds the difference between the two times
            System.out.printf(" %15d|", totalTime);
            if (N != 0 && N % 2 == 0) {
                doubling = totalTime / fastPrevTime;            //doubling ratio
                expDoubling = (Math.log(N) / Math.log(2)) / (Math.log(N / 2) / Math.log(2)); // expected doubling N^2long(N)
                System.out.printf(" %15.2f| %15.2f|", doubling, expDoubling);
            } else {
                System.out.printf(" %15s| %15s|", "NA", "NA");
            }
            fastPrevTime = totalTime;

            startTime = getCpuTime();
            List<List<Integer>> fastestThreeSum = fastestThreeSum(list); // calls the fastest function
            endTime = getCpuTime();
            totalTime = endTime - startTime;                    //finds the difference between the two times

            System.out.printf(" %15d|", totalTime);
            if (N != 0 && N % 2 == 0) {
                doubling = totalTime / fastestPrevTime;         //doubling ratio
                expDoubling = (N * N) / ((N/2) * (N/2));        // expected doubling ration N^2
                System.out.printf(" %15.2f| %15.2f|\n", doubling, expDoubling);
            } else {
                System.out.printf(" %15s| %15s|\n", "NA", "NA");
            }
            fastestPrevTime = totalTime;
        }

    }

    /** Get CPU time in nanoseconds since the program(thread) started. */
    /**
     * from: http://nadeausoftware.com/articles/2008/03/java_tip_how_get_cpu_and_user_time_benchmarking#TimingasinglethreadedtaskusingCPUsystemandusertime
     **/
    public static long getCpuTime() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ?
                bean.getCurrentThreadCpuTime() : 0L;

    }
    public static void main(String[] args) {            // start of main function
	    int N = 20;
	    int[] list = listGeneration(N);                 // populates the list with random numbers
	   runTimeTests(list);                              // calls the test function
    }
}
