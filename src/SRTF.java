import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class SRTF {
    ArrayList<process> processes=new ArrayList<process>();
    int n;
    int context=1000;
    int i, currentTime=0, totalOfTerminated=0;
    float averageWaitingTime=0, averageTurnAround=0;

    public SRTF(int n) {
        this.n = n;
    }
    void Run() throws InterruptedException {
        Scanner in=new Scanner(System.in);
        for (i=0;i<this.n;i++)
        {
            process s = new process();
            s.processID=i+1;
            //  processes.get(i).processID = i+1;
            System.out.println ("enter process " +(i+1)+ " arrival time:");
            s.arrivalTime=in.nextInt();
            // processes.get(i).arrivalTime= in.nextInt();
            System.out.println("enter process " +(i+1)+ " burst time:");
            // processes.get(i).burstTime= in.nextInt();
            s.burstTime=in.nextInt();
            s.burstTime2=s.burstTime;
            //processes.get(i).burstTime2=  processes.get(i).burstTime;
            //processes.get(i).flag= 0;
            s.flag=0;
            processes.add(s);
        }

        while(true){
            int min=100;
            int c =n;
            if (totalOfTerminated==n)
                break;
            for ( i=0;i<n;i++)
            {
                if ( (processes.get(i).arrivalTime<=currentTime) && (processes.get(i).flag== 0) && (processes.get(i).burstTime<min))
                {
                    min=processes.get(i).burstTime;
                    /* sleep(context);
                     currentTime++;*/
                    c=i;
                }
            }
            if (c==n)
                currentTime++;
            else
            {
                processes.get(c).burstTime--;
                currentTime++;
                if (processes.get(c).burstTime==0)
                {
                    processes.get(c).completeTime= currentTime;
                    processes.get(c).flag=1;
                    totalOfTerminated++;
                }
            }
        }

        for(i=0;i<n;i++)
        {
            processes.get(i).turnAround = processes.get(i).completeTime - processes.get(i).arrivalTime;
            processes.get(i).waitingTime = processes.get(i).turnAround- processes.get(i).burstTime2;
            averageWaitingTime+= processes.get(i).waitingTime ;
            averageTurnAround+=  processes.get(i).turnAround;
        }

        System.out.println("pid\tarrival\tburst\tcomplete\tturn\twaiting");
        for(i=0;i<n;i++)
        {
            System.out.println( processes.get(i).processID +"\t\t"+ processes.get(i).arrivalTime+"\t\t"+ processes.get(i).burstTime2+"\t\t"+ processes.get(i).completeTime +"\t\t"+ processes.get(i).turnAround +"\t\t"+processes.get(i).waitingTime);
        }
        System.out.println("\naverage tarnAround is "+ (float)(averageTurnAround/n));
        System.out.println("average waitTime is "+ (float)(averageWaitingTime/n));
        in.close();
    }
    int mahmoud(){
        return 1+2;
    }

}
