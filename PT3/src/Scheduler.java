import java.awt.List;
import java.util.ArrayList;

import javax.swing.JList;


public class Scheduler {
	
	//Server server;	
	
	ArrayList<Server> serv=new ArrayList<Server>(4); // servers list
	
    public int index=0;
    
    public synchronized  void add()
	{
    	index++;
	}
	public synchronized  void setZero()
	{
		index=0;
	}
	
	public Scheduler() {
		this.serv.add( new Server());
		Thread th=new Thread( serv.get(index));
		th.start();
	}
	
	public void createNewServer() {
		this.serv.add( new Server());
		add();
		Thread th=new Thread( serv.get(index));
		th.start();
	}
	
	public Server getServers(int i)
	{
		return serv.get(i);
	}
	
	public int getIndex()
	{
		return this.index;
	}
	public Server getServer() {
		return serv.get(index);
	}
/*
	public void setServer(Server server) {
		this.server=  server;
	} */
	
	public Task[] getTasks(){
		return serv.get(index).getTasks();
	}
	
	public Task[] getAllTasks(int i){
		return serv.get(i).getTasks();
	}
	
	void dispatchThreadOnServer(Task t,int i){
		int j=i+1;
		System.out.println("Dispatch task on Server"+j+": "  + t);
		serv.get(i).addTask(t);
	}
	
	int nrOfServers()
	{
		return serv.size();
	}
	
	boolean isDone(){
		return serv.isEmpty();
	}
}
