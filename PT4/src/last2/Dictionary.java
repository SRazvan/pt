package last2;

import java.awt.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Dictionary  implements Container
{
	//@invariant(map!=null)
	Map <Word, String> map = new HashMap<Word,String>();
	
	@SuppressWarnings("rawtypes")
	@Override
	public Iterator getIterator() 
	{
	      return new DictionaryIterator();
	   }
	
	private static Dictionary instance = new Dictionary();
	
	public static Dictionary getInstance()
	{
		return instance;
	}

	private Dictionary() // public Dictionary()
	{
	 JSONParser parser = new JSONParser();
	 try 
	 {
		 Object obj = parser.parse(new FileReader("d:\\NAL_Glossary2016.xml")); // Object obj = parser.parse(new FileReader("d:\\NAL_Glossary2016.xml")); 
		JSONObject jsonObject = (JSONObject) obj;		// get all the data from the json file  
														// the xml file is in json format
		
		JSONArray msg = (JSONArray) jsonObject.get("DICTIONARY");	// create an array for the element DICTIONARY
		Iterator <JSONObject> it=msg.iterator();	// iterate that array
		while (it.hasNext())
		{
			JSONObject temp = it.next();	// temp put the iterator in an object
			String name = (String) temp.get("THE WORD");	// now we get the word and its explanation from the iterator
	
			Word x=new Word(name);
			String name2 = (String) temp.get("EXPLENATION");
			map.put(x, name2);
				
		}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

     }
	
	@SuppressWarnings("unchecked")
	public void saveJSON() throws IOException	// Export the MAP to file
	{
		assert isWellFormed();
		JSONObject json = new JSONObject();
		json.putAll(map);
		
		FileWriter file=new FileWriter("d:\\Export.json");
		file.write(json.toJSONString());
		file.flush();
		file.close();
		
		System.out.println("JSON exported!");
	}
	
	/**
	 * @pre (givenWord!=null)
	 */
	public Object searchWord(String givenWord)	// returns the key at which a givenWord is found
	{
//		 for(Iterator iter = getIterator(); iter.hasNext();)
//	      {
//	    	  Map.Entry pair = (Map.Entry)iter.next();
//	    	  if(pair.getKey().toString().equals(givenWord))
//				{
//					return  (Word) pair.getKey();
//				}
//	      } 
//		
//		return null;

		
		 LinkedList resultList = new LinkedList();
		 Stream<Word> stream = map.keySet().stream();
		 stream.filter(entry -> Pattern.matches(givenWord, entry.getDictionaryWord())).forEach(entry -> resultList.add((Object)entry));
		return (Word) resultList.get(0);
		
	}
	
	/**
	 * @pre (givenWord!=null)
	 */
	public Word searchReturnWord(String word)	// returns the key at which a word is found (for word)
	{
		 for(Iterator iter = getIterator(); iter.hasNext();)
	      {
	    	  Map.Entry pair = (Map.Entry)iter.next();
	    	  if(pair.getKey().toString().equals(word))
				{
//					return   (Word) pair.getKey();
				}
	      } 
		 LinkedList resultList = new LinkedList();
		 Stream<Word> stream = map.keySet().stream();
		 stream.filter(entry -> Pattern.matches(word, entry.getDictionaryWord())).forEach(entry -> resultList.add((Object)entry));
		return (Word) resultList.get(0);
	}
	
	//@pre (w!=null)
	public void removeWord(Word w)			// removes a word from the dictionary
	{
		System.out.println("Word removed!");
		map.remove(w);
		assert isWellFormed();
	}
	
	/**
	 * @pre (word!=null)
	 * @pre (meaning!=null)
	 */
	public void addWord(String word,String meaning)		// adds a word to the dictionary
	{
		Word w=searchReturnWord(word);
		if(meaning!=null && w==null)	// check if meaning is not null 
		{								// and if the word is not already given
			w=new Word(word);
			map.put(w,meaning);
			System.out.println("Word added!");
		}
		assert isWellFormed();
	}
	
	// @pre (word!=null)
	public void findWords(String word)					// finds all the words which match the given one
	{
		for(Iterator iter = getIterator(); iter.hasNext();)
		{
			Map.Entry pair = (Map.Entry)iter.next();
			
			if(pair.getKey().toString().regionMatches(0, word, 0, word.length()))
			{
				System.out.println( pair );
			}
		}
		
	}
	
	public void iterateAll()	// display all the words from the dictionary
	{
	      for(Iterator iter = getIterator(); iter.hasNext();)
	      {
	    	  Map.Entry pair = (Map.Entry)iter.next();
 
	         System.out.println( pair);
	      } 
	      
	}
	
	public boolean isWellFormed()	// isWellFormed is used for assertions
	{
		if(map==null)
			return false;
		for(Iterator iter = getIterator(); iter.hasNext();)
	      {
	    	  Map.Entry pair = (Map.Entry)iter.next();
	    	  
	    	  Word w=(Word) pair.getKey();
	    	  String expl=(String) pair.getValue();
	    	  
	    	  if(w.dictionaryWord==null || expl==null) // checks if every dictionary word and explanation is null
	    	  {
	    		  return false;
	    	  }
	      } 
		return true;
	}
	
	@SuppressWarnings("rawtypes")
	private class DictionaryIterator implements Iterator	// class used to get iterator information
     {
		Iterator itt=map.entrySet().iterator();	// the iterator gets the entrySet of the map

		@Override				// hasNext() and next() override the iterator class
		public boolean hasNext() {	// we check if there exists a next element and return true or false
			
			if(itt.hasNext()) 
	    	 {
				return true;
	    	 }
			return false;
		}

		@Override			// Map.Entry gets the key at the iterator position
		public Object next() {
			if(this.hasNext())
			{
				Map.Entry pair = (Map.Entry)itt.next();
				return  pair;
			}
			return null;
		}

     }

}
