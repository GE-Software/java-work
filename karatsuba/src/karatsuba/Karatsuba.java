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
    {  StringBuilder mul;
       
        if (testvalue(m)<10 || testvalue(n)<10)
       {
           Long t=testvalue(m)*testvalue(n);
           
           mul=new StringBuilder(Long.toString(t));
           
           return mul;
       }
       else
       {
            if(m.length()>=n.length())
                n=align(n,m.length());
            else
                m=align(m,n.length());
           
           int length1=m.length();
           int length2=n.length();
           StringBuilder a=new StringBuilder(m.substring(0,length1/2));
           StringBuilder b=new StringBuilder(m.substring(length1/2,length1));
           StringBuilder c=new StringBuilder(n.substring(0,length2/2));
           StringBuilder d=new StringBuilder(n.substring(length2/2,length2));
           
       /*    
          int value=Integer.parseInt(a.toString());
          a.delete(0,a.length());
          a.append(value);
          
          value=Integer.parseInt(b.toString());
          b.delete(0,b.length());
          b.append(value);
          
          value=Integer.parseInt(c.toString());
          c.delete(0,c.length());
          c.append(value);
          value=Integer.parseInt(d.toString());
          d.delete(0,d.length());
          d.append(value);
           */
           StringBuilder sum1=new StringBuilder(add(a,b));
           StringBuilder sum2=new StringBuilder(add(c,d));
          
          /*value=Integer.parseInt(sum1.toString());
          sum1.delete(0,sum1.length());
          sum1.append(value);
          
          value=Integer.parseInt(sum2.toString());
          sum2.delete(0,sum2.length());
          sum2.append(value);
            */          
           StringBuilder t1=new StringBuilder(multiply(a,c));
           StringBuilder t2=new StringBuilder(multiply(b,d));
           
           StringBuilder t3=new StringBuilder(multiply(sum1,sum2));
           
           
           
           
           StringBuilder t4=new StringBuilder(subtract(t3,add(t1,t2)));
           
           int sh=b.length();
           StringBuilder t1padded=new StringBuilder(padding(t1,2*sh));
           StringBuilder t4padded=new StringBuilder(padding(t4,sh));
           
          
           return add(t1padded,add(t2,t4padded));
         
           
       }
          
    }
   public StringBuilder align(StringBuilder m, int length )
   {
        int difference;
       if (m.length()<length)
       {
           difference=length-m.length();
           for (int i=0;i<difference;i++)
               m.insert(0,"0");
          
       }
       
           return m;
           
    
   }
    public StringBuilder align(StringBuilder m, StringBuilder n)
    {   StringBuilder returnValue=new StringBuilder();
        int difference;
       if (m.length()>=n.length())
       {
           difference=m.length()-n.length();
           for (int i=0;i<difference;i++)
               n.insert(0,"0");
           returnValue=n;
       }
       else if (n.length()>m.length()){
           difference=n.length()-m.length();
           for (int i=0;i<difference;i++)
               m.insert(0,"0");
           returnValue=m;
       }
       return returnValue;
    }
    
    public StringBuilder add(StringBuilder m, StringBuilder n)
    {
        /*
        StringBuilder temp=new StringBuilder();
        temp.append(Long.parseLong(m.toString())+Long.parseLong(n.toString()));
        return temp;
        */
        
      int carryover=0 , sum=0;
      StringBuilder result=new StringBuilder();
     if(m.length()>=n.length())
     {
         n=align(n,m.length());
     }
     else{
        m=align(m,n.length());
     }
   
    String temp1=new String();
    String temp2=new String();
     for (int i=m.length()-1;i>=0;i--) {
       
         
        temp1=temp1+m.charAt(i);
        temp2=temp2+n.charAt(i);
        sum=Integer.parseInt(temp1)+Integer.parseInt(temp2)+carryover;
        //sum=(int) m.charAt(i)+(int)n.charAt(i)+carryover;
            if (sum>=10)
            {
                carryover=1;
                sum=sum-10;
                result.insert(0,sum);
                temp1="";
                temp2="";
            }
            else{
                carryover=0;
                result.insert(0,sum);
                temp1="";
                temp2="";
            }       
    }
    if (carryover==1) {
            result.insert(0,carryover);
        }
    //int value=Integer.parseInt(result.toString());
    //result.delete(0,result.length());
    //result.append(value);
    //if (result.length()<m.length())
    //    result=align(result,m);
    
    return result;
   
   }
   
    private StringBuilder subtract(StringBuilder m, StringBuilder n) // special subtract does not take into consideration -ve result
    {
        /*StringBuilder temp=new StringBuilder();
        temp.append(Long.parseLong(m.toString())-Long.parseLong(n.toString()));
        return  temp;
        */
        
        
    int borrow=0 , difference=0;
    StringBuilder result=new StringBuilder();
     if(m.length()>=n.length())
     {
         n=align(n,m.length());
        
     }
     else{
        m=align(m,n.length());
        
     }
    
    String temp1=new String();
    String temp2=new String();
     for (int i=m.length()-1;i>=0;i--) {
       
         
        temp1=temp1+m.charAt(i);
        temp2=temp2+n.charAt(i);
       
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
    //int value=Integer.parseInt(result.toString());
    //result.delete(0,result.length());
    //result.append(value);
    
        
    //if (result.length()<m.length()) {
//            result=align(result,m);
       // }
    
    return result;
       
    
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
        Long n=0l;
       String t=m.toString();
       try{
       n=Long.parseLong(t);
       }
       catch (Exception e)
       {
         n=11l;  
       }
   return n;
   }
   
   
   public StringBuilder removeLeadingZeores(StringBuilder m)
   {
       boolean leadingzero=true;
       
       while (leadingzero==true){
           if (m.charAt(0)=='0')
               m.deleteCharAt(0);
           else
               leadingzero=false;
       }
   return m;
   }
   
   
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
   
        StringBuilder a=new StringBuilder("1111222233334444555566667777888899990000");
        StringBuilder b=new StringBuilder("1111222233334444555566667777888899990000");
        StringBuilder c=new StringBuilder();
        
        
        
        
        Karatsuba t=new Karatsuba();
        StringBuilder sum=new StringBuilder();
       sum=t.add(a,b);
        //sum=t.subtract(a, b);
        c=t.removeLeadingZeores(t.multiply(a, b));
        
        System.out.println(c.toString());
    
    
    }
}
