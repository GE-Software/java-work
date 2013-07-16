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
    public String multiply(StringBuilder m, StringBuilder n)
    {   
       if (testvalue(m)<10 || testvalue(n)<10)
       {
           int t=testvalue(m)*testvalue(n);
           
           return (Integer.toString(t));
       }
       else
       {
           int length1=m.length();
           int length2=n.length();
           StringBuilder a=new StringBuilder();
           StringBuilder b=new StringBuilder();
           StringBuilder c=new StringBuilder();
           StringBuilder d=new StringBuilder();
           
           a.append(m.substring(0,length1/2));
           b.append(m.substring(length1/2,length1));
           c.append(n.substring(0,length2/2));
           d.append(n.substring(length2/2,length2));
           
           StringBuilder t1=new StringBuilder();
           StringBuilder t2=new StringBuilder();
           StringBuilder t3=new StringBuilder();
           StringBuilder t4=new StringBuilder();
           
           String temp=new String(multiply(a,c));
          
            t1.append((multiply(a,c)));
           t2.append(multiply(b,d).toString());
           //t3=t3.append(multiply(add(a,b),add(c,d)));
           //t4=t4.append(subtract(t3,add(t1,t2)));
           //t1=padding(t1,Math.max(length1,length2));
           //t4=padding(t4,Math.max(length1,length2)/2);
           
           //return add(t1,add(t2,t4));
           return t1.toString();
       }
          
    }
    
    /*private int[] add(int[]m,int[] n)
    {
        int carryover=0;
        int[]temp=new int[m.length+1];
        for (int i=m.length-1; i>=0;i--)
        {
            int t=m[i]+n[i]+carryover;
            if(t>9)
            {
                carryover=1;
                temp[i+1]=t-10;
            }
            else
            {
                carryover=0;
                temp[i+1]=t;
            }
            if (i==0)
             
                    temp[0]=carryover;
        }
    
        return temp;
    
    
    }
     **/  
    public String add(StringBuilder m, StringBuilder n)
    {
     String t=new String();
     String q=new String();
     
     t=m.toString();
     q=n.toString();
     
     int sum=Integer.parseInt(t)+Integer.parseInt(q);
     
     return Integer.toString(sum);
    }
    private String subtract(StringBuilder m, StringBuilder n) // special subtract does not take into consideration -ve result
    {
     String t=new String();
     String q=new String();
     t=m.toString();
     q=n.toString();
     //StringBuilder Result=new StringBuilder();
     int sum=Integer.parseInt(t)-Integer.parseInt(q);
     
     return Integer.toString(sum);
    }
    
   private String padding(StringBuilder m, int nzeroes)
   {
       StringBuilder temp=new StringBuilder();
       temp=m;
       for (int i=0;i<nzeroes;i++)
       {
          temp.append(0);
       }
       return temp.toString();
   }
   
   
   private int testvalue(StringBuilder m)
   {
       int npositive=0;
       String t=m.toString();
       return Integer.parseInt(t);
   }
   
   
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
   
        StringBuilder a=new StringBuilder("1234");
        StringBuilder b=new StringBuilder("5678");
        String c=new String();
        
        Karatsuba t=new Karatsuba();
        c=t.multiply(a, b);
        
    
    
    }
}
