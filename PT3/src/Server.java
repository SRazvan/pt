import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
	BlockingQueue<Task> queue;
    AtomicInteger waitingTime;
 
    
    public Server()
    {
    	  queue=new LinkedBlockingQueue<Task>();
    	  waitingTime=new AtomicInteger(0); 
    }
    
	public AtomicInteger getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(AtomicInteger waitingTime) {
		this.waitingTime = waitingTime;
	}

	public void run() {
		Task curT = null;
		while (true){
		try {
		
			curT = queue.take();	// take from the queue the task
			System.out.println("Start processing task : " +curT);
			Thread.sleep(curT.getProcessTime()*1000); // sleep 1 sec * proc time (1000=1sec)
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//System.out.println("waitingTime= "+ (-1)*curT.getProcessTime());
	     waitingTime.addAndGet((-1)*curT.getProcessTime()); // waiting time gets -(proc time)
		}
	}
	
	void addTask(Task t){
		queue.add(t);
		waitingTime.addAndGet(t.getProcessTime());
	}
	
	
	Task[] getTasks(){
		Task[] tasks=new Task[queue.size()];
		queue.toArray(tasks);
		return tasks;
		
	}
	
	Task[] getAllTasks(int i){
		Task[] tasks=new Task[queue.size()];
		queue.toArray(tasks);
		return tasks;
		
	}
	
	public BlockingQueue<Task> getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue<Task> queue) {
		this.queue = queue;
	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}
  
}
