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
            if(m.length()>n.length())
                n=align(m,n);
            else
                m=align(m,n);
           
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
           
          
           return new StringBuilder(add(t1padded,add(t2,t4padded)).toString().trim());
         
           
       }
          
    }
   public StringBuilder align(StringBuilder m, int length )
   {
        int difference;
       if (m.length()>length)
       {
           difference=m.length()-length;
           for (int i=0;i<difference;i++)
               m.insert(0,"0");
           return m;
       }
       
           return m;
           
    
   }
    public StringBuilder align(StringBuilder m, StringBuilder n)
    {
        int difference;
       if (m.length()>n.length())
       {
           difference=m.length()-n.length();
           for (int i=0;i<difference;i++)
               n.insert(0,"0");
           return n;
       }
       else if (n.length()>m.length()){
           difference=n.length()-m.length();
           for (int i=0;i<difference;i++)
               m.insert(0,"0");
       }
           return m;
           
    }
    public StringBuilder add(StringBuilder m, StringBuilder n)
    {
      int carryover=0 , sum=0;
      StringBuilder result=new StringBuilder();
     if(m.length()>n.length())
     {
         n=align(m,n);
     }
     else{
        m=align(m,n);
     }
   
    String temp1=new String();
    String temp2=new String();
     for (int i=m.length()-1;i>=0;i--) {
       
         
        temp1=temp1+m.charAt(i);
        temp2=temp2+n.charAt(i);
        sum=Integer.parseInt(temp1)+Integer.parseInt(temp2)+carryover;
        //sum=(int) m.charAt(i)+(int)n.charAt(i)+carryover;
            if (sum>10)
            {
                carryover=1;
                sum=sum-10;
                result.insert(0,Integer.toString(sum));
                temp1="";
                temp2="";
            }
            else{
                carryover=0;
                result.insert(0,Integer.toString(sum));
                temp1="";
                temp2="";
            }
                
            
    }
    if (carryover==1) {
            result.insert(0,Integer.toString(carryover));
        }
    if (result.length()<m.length())
        result=align(result,m);
    
    return new StringBuilder(result.toString().trim());
    }
   
    private StringBuilder subtract(StringBuilder m, StringBuilder n) // special subtract does not take into consideration -ve result
    {
     String t=new String();
     String q=new String();
     t=m.toString();
     q=n.toString();
    
     Long sum=Long.parseLong(t)-Long.parseLong(q);
     
     return new StringBuilder(Long.toString(sum));
     /*
    int borrow=0 , difference=0;
    StringBuilder result=new StringBuilder();
     if(m.length()>n.length())
     {
         n=align(m,n);
        
     }
     //else{
       // m=align(m,n);
        
     //}
    
    String temp1=new String();
    String temp2=new String();
     for (int i=m.length()-1;i>=0;i--) {
       
         
        temp1=temp1+m.charAt(i);
        temp2=temp2+n.charAt(i);
        //difference=Integer.parseInt(temp1)-Integer.parseInt(temp2)-borrow;
        //sum=(int) m.charAt(i)+(int)n.charAt(i)+carryover;
            if (Integer.parseInt(temp1)>=(Integer.parseInt(temp2)+borrow))
            {
                difference=Integer.parseInt(temp1)-Integer.parseInt(temp2)-borrow;
                borrow=0;
                
                result.insert(0,Integer.toString(difference));
                temp1="";
                temp2="";
            }
            else{
                difference=Integer.parseInt(temp1)+10-Integer.parseInt(temp2)-borrow;
                borrow=1;
                result.insert(0,Integer.toString(difference));
                temp1="";
                temp2="";
            }
                
            
    }
    /*String temp=new String();
    temp=result.toString();
    temp.trim();
    result.delete(0, result.length());
    result.append(temp);
      */  
    /*if (result.length()<m.length()) {
            result=align(result,m);
        }
    
    return new StringBuilder(result.toString().trim());
        
*/      
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
    public static void main(String[] args) {
        // TODO code application logic here
   
        StringBuilder a=new StringBuilder("10015");
        StringBuilder b=new StringBuilder("123");
        StringBuilder c=new StringBuilder();
        
        
        
        
        Karatsuba t=new Karatsuba();
        StringBuilder sum=new StringBuilder();
       //sum=t.add(a,b);
       // sum=t.subtract(a, b);
        c=t.multiply(a, b);
        
        System.out.println(c.toString());
    
    
    }
}
