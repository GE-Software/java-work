/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package karatsuba;

/**
 *
 * @author Lenovo-BM
 */
public class Karatsuba {
   
    
    Karatsuba()
    {
    }    
    public StringBuilder multiply(StringBuilder m, StringBuilder n)
    {  
       
        if (testvalue(m)<10 || testvalue(n)<10)
       {
           Long t=testvalue(m)*testvalue(n);
           
           return (new StringBuilder().append(Long.toString(t)));
       }
       else
       {
            int difference;
       if (m.length()>n.length())
       {
           difference=m.length()-n.length();
           for (int i=0;i<difference;i++)
               n.insert(0,"0");
       }
       else if (n.length()>m.length()){
           difference=n.length()-m.length();
           for (int i=0;i<difference;i++)
               m.insert(0,"0");
       }
           
           
           
           int length1=m.length();
           int length2=n.length();
           StringBuilder a=new StringBuilder(m.substring(0,length1/2));
           StringBuilder b=new StringBuilder(m.substring(length1/2,length1));
           StringBuilder c=new StringBuilder(n.substring(0,length2/2));
           StringBuilder d=new StringBuilder(n.substring(length2/2,length2));
           
           int sh=b.length();
           
           StringBuilder sum1=new StringBuilder(add(a,b));
           StringBuilder sum2=new StringBuilder(add(c,d));
           
           
                      
           StringBuilder t1=new StringBuilder(multiply(a,c));
           StringBuilder t2=new StringBuilder(multiply(b,d));
           StringBuilder t3=new StringBuilder(multiply(sum1,sum2));
           StringBuilder t4=new StringBuilder(subtract(t3,add(t1,t2)));
           StringBuilder t1padded=new StringBuilder(padding(t1,2*sh));
           StringBuilder t4padded=new StringBuilder(padding(t4,sh));
           
          
           return add(t1padded,add(t2,t4padded));
           
       }
          
    }
   
    public StringBuilder add(StringBuilder m, StringBuilder n)
    {
     String t=new String();
     String q=new String();
     
     t=m.toString();
     q=n.toString();
     
     Long sum=Long.parseLong(t)+Long.parseLong(q);
     StringBuilder temp = new StringBuilder(Long.toString(sum));
     return temp;
    }
   
    private String subtract(StringBuilder m, StringBuilder n) // special subtract does not take into consideration -ve result
    {
     String t=new String();
     String q=new String();
     t=m.toString();
     q=n.toString();
    
     Long sum=Long.parseLong(t)-Long.parseLong(q);
     
     return Long.toString(sum);
    }
    
   private StringBuilder padding(StringBuilder m, int nzeroes)
   {
       
       
       for (int i=0;i<nzeroes;i++)
       {
          m.append(0);
       }
       return m;
   }
   
   
   private Long testvalue(StringBuilder m)
   {
       int npositive=0;
       String t=m.toString();
       return Long.parseLong(t);
   }
   
   
   
    /**
     * @param args the command line arguments
     */
   /** public static void main(String[] args) {
        // TODO code application logic here
   
        StringBuilder a=new StringBuilder("100123");
        StringBuilder b=new StringBuilder("123456789");
        StringBuilder c=new StringBuilder();
        
        Karatsuba t=new Karatsuba();
        c=t.multiply(a, b);
        System.out.println(c.toString());
    
    
    }*/
}
