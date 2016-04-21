import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;


public class Simulator implements Runnable{ //task generator

	int time_limit=45;		// 100 time data structure  (number of tasks)
	int minProcTime=1;		//sec
	int maxProcTime=10;	
	double avgProcTime=0;
	static Scheduler s;
	Display frame;
	public volatile static int [] server=new int[10];
	public volatile static int pos=0;
	private Scanner scan;
	
	public Simulator()
	{
	    s=new Scheduler();
	    frame=new Display();	
	}
	
	public static int verify(int i)
	{
		Task[] listT=s.getAllTasks(i);
		server[i]=0;
		for(Task temp : listT)
		{
			server[i]++;
		}
		return server[i];
	}
	
	
	public void run() {
		System.out.println("Give the number of tasks= ");
		scan = new Scanner(System.in);
		time_limit = scan.nextInt();
		int currentTime=0;		//TIME;
		while(currentTime<time_limit){ // when we exit while, we still have to display the remaining tasks
			currentTime++;
			int processTime=(int) (Math.random()*((maxProcTime-minProcTime)+1)+minProcTime);
								// ((max - min) + 1) + min;
			Task t=new Task(currentTime,processTime);
			System.out.println("Generate task : " + t);
			avgProcTime+=processTime;
			for(int i=0; i<s.nrOfServers(); i++)
			{
				server[i]=verify(i);
			}
			
			int min=9999;
			for(int i=0; i<s.nrOfServers(); i++)
			{
				if(min>server[i]) 
					{
					min=server[i];
					pos=i;
					}
			}
			s.dispatchThreadOnServer(t, pos);
			
			
			if(s.nrOfServers()==1)
			{
				if(server[0]>time_limit/6)
				{
					s.createNewServer();
				}
			}
			
			if(s.nrOfServers()==2)
			{
				if(server[1]>time_limit/6)
				{
					s.createNewServer();
				}
			}
			if(s.nrOfServers()==3)
			{
				if(server[2]>time_limit/6)
				{
					s.createNewServer();
				}
			}
			
			
			if(s.nrOfServers()==1) frame.displayData(s.getServers(0).getAllTasks(0));
			else if(s.nrOfServers()==2)
			{
				frame.displayData(s.getServers(0).getAllTasks(0));
				frame.displayData2(s.getServers(1).getAllTasks(1));
			}
			else if(s.nrOfServers()==3)
			{
				frame.displayData(s.getServers(0).getAllTasks(0));
				frame.displayData2(s.getServers(1).getAllTasks(1));
				frame.displayData3(s.getServers(2).getAllTasks(2));
			}
			else if(s.nrOfServers()==4)
			{
				frame.displayData(s.getServers(0).getAllTasks(0));
				frame.displayData2(s.getServers(1).getAllTasks(1));
				frame.displayData3(s.getServers(2).getAllTasks(2));
				frame.displayData4(s.getServers(3).getAllTasks(3));
			}
			try {
				Thread.sleep(1000); // sleep time (1000 -> 1 second)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		avgProcTime=avgProcTime/time_limit;
		
		System.out.println("Average waiting time= "+avgProcTime+" seconds");
		
		while(!s.isDone()){
			if(s.nrOfServers()==1) frame.displayData(s.getServers(0).getAllTasks(0));
			else if(s.nrOfServers()==2)
			{
				frame.displayData(s.getServers(0).getAllTasks(0));
				frame.displayData2(s.getServers(1).getAllTasks(1));
			}
			else if(s.nrOfServers()==3)
			{
				frame.displayData(s.getServers(0).getAllTasks(0));
				frame.displayData2(s.getServers(1).getAllTasks(1));
				frame.displayData3(s.getServers(2).getAllTasks(2));
			}
			else if(s.nrOfServers()==4)
			{
				frame.displayData(s.getServers(0).getAllTasks(0));
				frame.displayData2(s.getServers(1).getAllTasks(1));
				frame.displayData3(s.getServers(2).getAllTasks(2));
				frame.displayData4(s.getServers(3).getAllTasks(3));
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	public static void main(String[] args)
	{
		Simulator sim=new Simulator();
		Thread th =new Thread(sim);
		th.start();
		
	}
}
