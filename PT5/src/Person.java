import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

// Observer

public class Person implements Observer{

	public String name;
	
	private Account accountUpdate;
	
	public Person(String name)		// Constructor (for name only)
	{
		this.name=name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String toString() 
	{ 
	    return "Name: " + this.name ;
	}

	@Override									/// Observer/Observable part (Person is notified if some account is modified)
	public void update(Observable o, Object arg) {
		accountUpdate= (Account) o;

		System.out.println("Account updated: "+accountUpdate);
	}

 
	
}
