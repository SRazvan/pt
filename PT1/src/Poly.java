public class Poly { 
	
	// This is a constructor which creates a monom a*x^b
	
	public Poly(double a, int b) {
		terms = new double[b+1];
		terms[b] = a;		// power of x = position of number (5x^2 = terms[2]=5)
		power = degree();		// power = power of x (we use the method for in case of 0)
	}
	
	// ex: 2x^3 ; a=2; b=3;
	// terms {0,0,2,0}  
	// power gets 2 (using the degree method)
	
	public Poly(int a) {		
		power = a;
		terms = new double[a+1];
	}

	// returns the degree of this monom
	public int degree() {
		int d = 0;
		for (int i = 0; i < terms.length; i++)
			if (terms[i] != 0) d = i;
		return d;
	}

	public String toString() {
		if (power ==  0) 
			return "" + terms[0]; // ex: a=1 -> print 1
		if (power ==  1) 
			return terms[1] + "x^1+" + terms[0];  // ex: a=2x+6 -> 2x^1+6
		String s = terms[power] + "x^" + power;  
		for (int i = power-1; i >= 0; i--)   
		{									
			if      (terms[i] == 0) 		
				continue;					
			else if (terms[i]  > 0) 		
				s = s + "+" + ( terms[i]); 
			else if (terms[i]  < 0)
				s = s + "-" + (-terms[i]); 
			if      (i == 1) 
				s = s + "x^1";
			else if (i > 1) s = s + "x^" + i;
		}
		System.out.println(s);
		return s;
	}
	/* ex: a=2x^2+3x^1+5 -> print 2x^2
	 * a={5,3,2}
	 * i=1 -> vf a[1]==0? no -> a[i]>0 yes s=2x^2+3
	 * vf i==1 (yes) -> s=2x^2+3x^1
	 * i=0 -> vf a[0]==0? no -> a[0]>0 yes s=2x^2+3x^1+5
	 * vf i==1 no , return s=2x^2+3x^1+5 
	 */
	
	
	public double[] terms;  // terms array
	public int power;     // power of the polynomial


	public Poly plus(Poly b) {
		Poly a = (Poly) this;				// gets polynomial a		
		int maxPower=Math.max(a.power, b.power);
		Poly c = new Poly(0, maxPower); // c gets the max power out of a and b; all terms=0 (creates array of size max a,b)
		for (int i = 0; i <= a.power; i++) 
			c.terms[i] = c.terms[i]+ a.terms[i];					// adds the terms 
		for (int i = 0; i <= b.power; i++) 
			c.terms[i] = c.terms[i]+ b.terms[i];
		c.power = c.degree();				// gets power (because there could be a=2x^2, b=-2x^2
		return c;							// and the degree might change
	
	}

	public Poly minus(Poly b) {
		Poly a = (Poly) this;
		int maxPower=Math.max(a.power, b.power);
		Poly c = new Poly(0, maxPower);
		for (int i = 0; i <= a.power; i++) 
			c.terms[i] = c.terms[i] + a.terms[i];			// c=a
		for (int i = 0; i <= b.power; i++) 
			c.terms[i] = c.terms[i] - b.terms[i];			// c=a-b
		c.power = c.degree();		// gets the power
		return c;
	}

	public Poly multiplies(Poly b) {
		Poly a = (Poly) this;
		int maxPower=a.power + b.power;
		Poly c = new Poly(0, maxPower); // build the terms  array
		for (int i = 0; i <= a.power; i++)
			for (int j = 0; j <= b.power; j++)
				c.terms[i+j] += (a.terms[i] * b.terms[j]); // c gets a*b
		c.power = c.degree();		// gets the power
		return c;
	}
	
	/* ex: a=2x^2+1 ; b=3x^2+2 
	* -> a.terms{1,0,2} power=2
	* -> b.terms{2,0,3} power=2
	* 			=> c{0,0,0,0,0}
	* Step 1 in for (i=0)
	*	j=0,1,2; c[0]=1*2+0 -> c[0]=2 |  c[1]=1*0+0 | c[2]=1*3+0
	* Step 2 in for (i=1)
	* j=0,1,2 -> c[1]=0+0*2=0 | c[2]=3+0=3 | c[3]=0+3*0=0
	* Step 3 in for (i=2)
	* j=0,1,2 -> c[2]=3+2*2=7 | c[3]=0+0*2=0 | c[4]=0+2*3=6
	* => c.terms={2,0,7,0,6} -> 6x^4+7x^2+2
	*/
	
	public Poly derivative() {
		if (power == 0) return new Poly(0, 0);
		Poly c = new Poly(0, power - 1); // the power will be always smaller
		c.power = power - 1;		// update power
		for (int i = 0; i < power; i++)
			c.terms[i] = (i + 1) * terms[i + 1]; // 2x^2 -> i+1 -> term = term+1 * power+1
		return c;							// curent term gets next term * next terms power	
	}
	
	/* ex: a=2x^2+1 ; b=3x^2+2 
	* -> a.terms{1,0,2} power=2
	* -> b.terms{2,0,3} power=2
	* in case of a-> c={0,0}
	* power = 1
	* c[0]=1*0=0
	* c[1]=2*2=4
	* c={4,0} -> 4x^1
	* in case of b -> c={0,0}
	* power =1
	* c[0]=1*0=0
	* c[1]=2*3=6
	* c={0,6} => c=6x^1
	*/
	
	public Poly integrate()
	{
		Poly c = new Poly(power + 1); // the power of the given polynomial
		for (int i = 1; i <= power+1; i++)
		{
			c.terms[i] = (terms[i - 1] / i);
		}					// increase the power and divide by prev power
		return c;
	}
	
	/* ex: a=2x^2+1 ; b=3x^2+2 ; power=2 -> a=2x^3/2 
	* -> a.terms{1,0,2} power=2
	* -> b.terms{2,0,3} power=2
	* c.terms{0,0,0,0}
	* c[1]=1/1;
	* c[2]=0/2;
	* c[3]=2/3;
	* return c={0,1,0,2/3}
	* b=3x^2+2
	*/
}
