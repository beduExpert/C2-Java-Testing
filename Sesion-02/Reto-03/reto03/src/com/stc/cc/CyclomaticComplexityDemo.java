package com.stc.cc;

	public class CyclomaticComplexityDemo {

int strequal (char[] x, char[] y)
	{ int i = 0, igual = 0;
  		if (x == y)
      			igual  = 1;
  		else while (x[i] == y [i])
       		     {  if(x[i] == '\0')
          		{
            			igual = 1;
            			y[i] = '\7';
          		}
          		else i++;
       		}
       
    	return igual;
	}
	}