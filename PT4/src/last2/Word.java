package last2;

public class Word {
	
	public String dictionaryWord;
	
	public Word()
	{
		this.dictionaryWord=dictionaryWord;
	}
	
	public Word(String dictionaryWord)
	{
		this.dictionaryWord=dictionaryWord;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dictionaryWord == null) ? 0 : dictionaryWord.hashCode());
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
		Word other = (Word) obj;
		if (dictionaryWord == null) {
			if (other.dictionaryWord != null)
				return false;
		} else if (!dictionaryWord.equals(other.dictionaryWord))
			return false;
		return true;
	}

	public String getDictionaryWord() {
		return dictionaryWord;
	}

	public void setDictionaryWord(String dictionaryWord) {
		this.dictionaryWord = dictionaryWord;
	}

	@Override
	public String toString() {
		return dictionaryWord;
	}
 
}
