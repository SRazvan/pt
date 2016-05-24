import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class Bank implements Serializable{
	
	//@invariant(map!=null)
	
	protected  Map<Person,Set<Account>> map = new HashMap<Person,Set<Account>>();

	public void depositMoney(double Sum,int accountId,Person p)
	{
		Set<Account> acc=map.get(p);
		
		for(Account temp: acc)
		{
			if(temp.accountID==accountId)
			{
				temp.addObserver(p);
				temp.addMoney(Sum);
			}
		}
		assert isWellFormed();
	}
	
	/**
	 * @pre (p!=null)
	 * @pre (accountID>0)
	 * @post (Sum>0)
	 */
	public void withdrawMoney(double Sum,int accountId,Person p)
	{
		Set<Account> acc=map.get(p);
		
		for(Account temp: acc)
		{
			if(temp.accountID==accountId)
			{
				temp.addObserver(p);
				temp.substractMoney(Sum);
			}
		}
		assert isWellFormed();
	}
	
	/**
	 * @pre (p!=null)
	 * @pre (a!=null)
	 */
	public void addAccount(Person p,Account a)
	{
		if(map.get(p)!=null)	// check to see if the map is empty
		{
			Set<Account> acc=map.get(p);		// if not add account to existing map
			acc.add(a);
			map.replace(p, acc);
		}
		else					// else just map it
		{
			Set<Account> acc2=new HashSet<Account>();
			acc2.add(a);
			map.put(p, acc2);
		}
		assert isWellFormed();
	}
	
	//@pre (p!=null)
	public void removePerson(Person p)
	{
		System.out.println("Persone removed!");
		map.remove(p);
		assert isWellFormed();
	}

	/**
	 * @pre (p!=null)
	 * @pre (accountID!=null)
	 */
	public void removeAccount(Person p,int accountID)
	{
		if(map.get(p)!=null)	// check to see if the map is empty
		{
			Set<Account> acc=map.get(p);		// if not add account to existing map
			 
			
			for(Account temp : acc)		// get through all of the accounts
			{
				if(temp.accountID==accountID)	// if the id matches remove and exit loop
				{
					acc.remove(temp);
					System.out.println("Account deleted!");
					break;
				}
			}					
			map.replace(p, acc);		// replace old hash
		}
		assert isWellFormed();
	}
	
	/**
	 * @pre (person!=null)
	 */
	public Object searchPerson(String person)	// returns the key at which a person is found (for accounts)
	{
		Iterator it=map.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry pair = (Map.Entry)it.next();
			if(pair.getKey().toString().equals(person))
			{
				return   pair.getKey();
			}
			//it.remove();
		}
		return null;
	}
	
	/**
	 * @pre (person!=null)
	 */
	public Person searchReturnPerson(String person)	// returns the key at which a person is found (for person)
	{
		Iterator it=map.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry pair = (Map.Entry)it.next();
		 //	System.out.println(pair.getKey().toString());
			if(pair.getKey().toString().equals(person))
			{
				//System.out.println("FOUND");
				
				return   (Person) pair.getKey();

				//System.out.println(map.get(pair.getKey()));
				
			}
			//else System.out.println("NOT FOUND");
			
			//it.remove();
		}
		return null;
	}
	
	public boolean isWellFormed()	// isWellFormed is used for assertions
	{
		if(map==null)
			return false;
		Iterator it=map.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry pair = (Map.Entry)it.next();
			Person p=(Person) pair.getKey();
			
			Set<Account> set = map.get(p);
			for(Account teem: set)			// check accounts
			{
				if(teem.accountID<0 || teem.accountMoney<0)
					return false;
			}
			if(p.name==null)				// check name of person
				return false;
		}
		return true;
	}

	
}
