
public class Task {
	int arrivalTime;
	int processTime;

	public Task(int arrivalTime,int processTime)
	{
		this.arrivalTime=arrivalTime;
		this.processTime=processTime;
	}
	
	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getProcessTime() {
		return processTime;
	}

	public void setProcessTime(int processTime) {
		this.processTime = processTime;
	}
	
	public String toString(){
		return arrivalTime+" "+processTime;
	}

}
