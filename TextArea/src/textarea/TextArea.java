/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package textarea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import sun.security.util.Length;

/**
 *
 * @author Lenovo-BM
 */
public class TextArea extends JFrame implements ActionListener {

    JFrame in;
    JTextArea textinput;
    JScrollPane scroller;
    int lines=0, words=0, characters=0;
    JLabel wordsCount;
        JLabel charactersCount;
        JLabel linesCount;
        JButton linesCalculate , wordsCalculate, charactersCalculate;
    TextArea()
    {
        in=new JFrame("Text Area App");
        textinput=new JTextArea();
        scroller=new JScrollPane(textinput);
        in.add(scroller,BorderLayout.CENTER);
        in.setSize(500,500);
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        in. setLocation( (screenSize.width - in.getWidth())/2, (screenSize.height - in.getHeight())/2);
        
        linesCalculate=new JButton("#Lines");
        charactersCalculate=new JButton("#Characters");
        wordsCalculate=new JButton("#Words");
        linesCalculate.addActionListener(this);
        charactersCalculate.addActionListener(this);
        wordsCalculate.addActionListener(this);
        JPanel east=new JPanel();
        east.setLayout(new GridLayout(3,1));
        east.add(linesCalculate);
        east.add(charactersCalculate);
        east.add(wordsCalculate);
        in.add(east,BorderLayout.EAST);
        
        JPanel south=new JPanel();
        south.setLayout(new GridLayout(1,3));
        wordsCount=new JLabel();
        charactersCount=new JLabel();
        linesCount=new JLabel();
        linesCount.setText("Lines Count :");
        charactersCount.setText("Characters Count : ");
        wordsCount.setText("Words Count : ");

        
        south.add(linesCount);
        south.add(charactersCount);
        south.add(wordsCount);
        in.add(south,BorderLayout.SOUTH);
        in.setVisible(true);
        
    }
    
    /**
     * @param args the command line arguments
     */
    public void paintComponent(Graphics g)
    {
       /**super.paintComponents(g);
       JPanel temp=new JPanel();
       temp.setLayout(new GridLayout(1,3));
       
       linesCount.setText("Lines Count "+ lines);
       wordsCount.setText("Words Count"+ words);
       charactersCount.setText("Characters Count"+characters);
       
       temp.add(linesCount);
       temp.add(charactersCount);
       temp.add(wordsCount);
       in.add(temp,BorderLayout.SOUTH);
**/      
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    TextArea test=new TextArea();
    WordCount wc=new WordCount("TESTING THE CALCULATION OF WORDS");
    int T=wc.returnWords();
    //test.setDefaultCloseOperation(JFrame.EXIT ON CLOSE);
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    JButton command =(JButton) e.getSource();
    
         if (command==linesCalculate)
         {
             lines=calculateLines(textinput.getText());
              linesCount.setText("Lines Count : "+ lines);
             //repaint();
         
         }
         if (command==charactersCalculate)
         {
             characters=calculateCharacters(textinput.getText());
             charactersCount.setText("characters Count : "+ characters);
             //repaint();
        
         }
         if (command==wordsCalculate)
         {
             words=calculateWords(textinput.getText());
             wordsCount.setText("Words Count : " + words);
             //repaint();
         }
    }
public int calculateLines(String in)
{
    return 10;
}
public int calculateCharacters(String in)
{
    return in.length();
}
public int calculateWords(String in)
{
    WordCount wc=new WordCount(in);
    wc.countWords();

    return wc.returnWords();
}

}
