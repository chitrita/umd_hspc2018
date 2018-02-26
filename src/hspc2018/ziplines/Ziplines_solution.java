package hspc2018.ziplines;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;


public class Ziplines_solution {

    public static int largestIslandChain(Scanner sc) {
        //Read the Graph
        //System.out.print("How many islands? ");
        int islands=sc.nextInt();
        boolean graph[][] = new boolean[islands][islands];

        //System.out.print("How many ziplines? ");
        int ziplines=sc.nextInt();

        for (int edge=0; edge<ziplines; edge++) {
            int from=sc.nextInt();
            int to=sc.nextInt();
            graph[from][to]=true;
        }


        /* Find the SCCs... */

        //Utility Data Structures
        Stack<Integer> stack = new Stack<Integer>(); 


        //First DFS
        boolean[] visited01 = new boolean[islands];
        int exitTimeTicker=0;
        int[] exitTimes = new int[islands];
        int[] exitPoints = new int[islands];
        for (int island=0; island<islands; island++) {
            int source;
            if (!visited01[island])
            {
                visited01[island]=true;
                stack.push(island);
                source=island;

                int destination=0;
                while (!stack.isEmpty())
                {
                    source=stack.peek();
                    destination=0;
                    while (destination<islands)
                    {
                        if (graph[source][destination] && !visited01[destination])
                        {
                            stack.push(destination);
                            visited01[destination]=true;
                            source=destination;
                            destination=0;
                        }
                        else {
                            destination++;
                        }
                    }
                    exitTimes[stack.peek()]=exitTimeTicker;
                    exitPoints[exitTimeTicker]=stack.peek();
                    stack.pop();
                    exitTimeTicker++;
                }
            }
        }


        boolean graphTransposeUsingExits[][] = new boolean[islands][islands];
        for (int i=0; i<islands; i++)
        {
            for (int j=0; j<islands; j++)
            {
                if (graph[i][j])
                    graphTransposeUsingExits[exitTimes[j]][exitTimes[i]]=true;
            }
        }


        //Second DFS
        boolean[] visited02 = new boolean[islands];
        int[] numberOfSCC = new int[islands];
        int currSCCnum=0;
        for (int exitTime=islands-1; exitTime>=0; exitTime--)
        {
            if (!visited02[exitTime])
            {
                //entryPointToScc=exitTime;
                visited02[exitTime]=true;
                numberOfSCC[exitPoints[exitTime]]=++currSCCnum;
                stack.push(exitTime);
                int source=exitTime;
                while (!stack.isEmpty())
                {
                    source=stack.peek();
                    int destination=0;
                    while (destination<islands)
                    {
                        if (graphTransposeUsingExits[source][destination] && !visited02[destination])
                        {
                            if (numberOfSCC[exitPoints[destination]]==0) {
                                numberOfSCC[exitPoints[destination]]=currSCCnum;
                            }
                            stack.push(destination);
                            visited02[destination]=true;
                            source=destination;
                            destination=0;
                        }
                        else {
                            destination++;
                        }                    
                    }
                    stack.pop();
                } 
            }
        }


        int[] sizeOfSCCs = new int[currSCCnum];
        for (int i=0; i<islands; i++) {
            sizeOfSCCs[numberOfSCC[i]-1]++;
        }
        
        int sccsMax = Integer.MIN_VALUE;
        for(int i=0; i<sizeOfSCCs.length; ++i) {
        	if(sizeOfSCCs[i] > sccsMax) {
        		sccsMax = sizeOfSCCs[i];
        	}
        }
        return sccsMax;
        
        // Java 8
        //return Arrays.stream(sizeOfSCCs).summaryStatistics().getMax();
    }






    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numOfTestCases = sc.nextInt();
        sc.nextLine();

        for (int testCase=0; testCase < numOfTestCases; testCase++) { 
            int answer = largestIslandChain(sc);
            System.out.println("Biggest group size: " + answer);
        }
      
        sc.close();
    }

}
