import ncst.pgdst.*;

public class WordCount1
{

  public static void main (String[] arg) throws IOException
    {

    char[] str;                      //char array to store input string
    SimpleInput sin = new SimpleInput ();
    SimpleOutput sout = new SimpleOutput ();
    String input = new String ();       //string variable to get the line from the input
    int len, now = 0, i, j = 0, k = 0;  //now=no. of words, i,j=index variables
    int ch = 0;                 //to check for initial spaces
    char nw = ' ';              // next char in the string
    char pr = ' ';              //previous word
     while (sin.eof () != true)  //check for EOF
      {
         input = sin.readLine ();
         len = input.length ();
	 System.out.println("string length is "+len);
         str = input.toCharArray ();
         for (i = 0; i < len; i++)
         {
	         if (i == (len - 1))
	         {
	                nw = ' ';
		 }
		 else
	        {
	          nw = str[i + 1];
              }
        if (((str[i]!=' ')&(str[i]!='\t')&(str[i]!='\n'))&((nw==' ')|(nw=='\t')|(nw=='\n')))
       {
             ++now;
       }
       }
       }
  sout.writelnInt (now);
      }                             //end of main
																																		      }

