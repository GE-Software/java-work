/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package advancedguiex1;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.EnumSet;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * A simple paint program that is a first demonstration of using
 * an off-screen canvas.  This class can be run as a main program
 * and has a nested class PaintWithOffScreenCanvas.Applet that can be used as
 * an applet.  Note that the way that the off-screen canvas is used
 * in this class requires that the panel be non-resizable; this
 * is because the size of the off-screen canvas does not change
 * when the panel changes size.
 */
public class AdvancedGUIEX1 extends JPanel {
   
   /**
    * The main routine simply opens a window that shows a PaintWithOffScreenCanvas panel.
    */
   public static void main(String[] args) {
      JFrame window = new JFrame("PaintWithOffScreenCanvas");
      AdvancedGUIEX1 content = new AdvancedGUIEX1();
      window.setContentPane(content);
      window.setJMenuBar(content.getMenuBar());
      window.pack();  
      window.setResizable(false); 
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      window.setLocation( (screenSize.width - window.getWidth())/2,
            (screenSize.height - window.getHeight())/2 );
      window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      window.setVisible(true);
   }
   
   
   /**
    * The public static class PaintWithOffScreenCanvas$Applet represents this program
    * as an applet.  The applet's init() method simply sets the content 
    * pane of the applet to be a PaintWithOffScreenCanvas.  To use the applet on
    * a web page, use code="PaintWithOffScreenCanvas$Applet.class" as the name of
    * the class.  (This is a rather tricky way of defining an applet class to
    * run a program as an applet, instead of using a separate file to define the
    * applet.  It is not necessarily good style.)
    */
   public static class Applet extends JApplet {
       @Override
      public void init() {
         AdvancedGUIEX1 content = new AdvancedGUIEX1();
         content.setBorder(BorderFactory.createLineBorder(Color.GRAY,2));
         setContentPane( content );
         setJMenuBar(content.getMenuBar());
      }
   }
   
   
   /**
    * The possible drawing tools in this program.  (The CURVE tool allows the
    * user to sketch a free-hand curve, while the LINE tool draws a line
    * between two points.  The SMUDGE tool lets the user "spread paint around"
    * with the mouse.  The ERASE tool erases with a 10-by-10 pixel rectangle.)
    */
   private enum Tool { CURVE, LINE, RECT, OVAL, FILLED_RECT, FILLED_OVAL, SMUDGE, ERASE }
   
   /**
    * The set of Tools that represent "shapes."  Shapes are handled differently
    * during dragging than other tools, since they are drawn "on top of" the
    * current picture during a mouse drag and are only added permanently to the
    * picture on mouse release.
    */
   private final static EnumSet<Tool> SHAPE_TOOLS = EnumSet.range(Tool.LINE, Tool.FILLED_OVAL);

   /**
    * The currently selected drawing tool.  Initially Tool.CURVE.  Can be
    * changed by the user with commands in the "Tool" menu.
    */
   private Tool currentTool = Tool.CURVE;
   
   /**
    * The current drawing color.  Initially Color.BLACK.  Can be changed
    * by the user with the "Select Drawing Color" command in the "Color" menu.
    */
   private Color currentColor = Color.BLACK;
   
   /**
    * The background color that is used to fill the off-screen canvas when
    * it is created.  If the user selects the "Fill With Color", the fill
    * color changes, and the canvas is filled with the new fill color,
    * erasing whatever was there before.
    */
   private Color fillColor = Color.WHITE;

   /**
    * The off-screen canvas.  This is not created until the first time
    * paintComponent() is called.  The size of the canvas will not change
    * after it is crearted, so this program has no support for resizing
    * the window.
    */
   private BufferedImage OSC;

   /**
    * This is set to true when the user is dragging the mouse.
    */
   private boolean dragging;
   
   /**
    * The start position of the mouse during a mouse drag.
    */
   private int startX, startY;
   
   /**
    * The current position of the mouse during a mouse drag.
    */
   private int currentX, currentY;
   
   
   /**
    * The constructor sets the preferred size of the panel to
    * 640-by-480.  It also sets up mouse listeners.
    */
   public AdvancedGUIEX1() {
      setPreferredSize(new Dimension(640,480));
      MouseHandler mouseHandler = new MouseHandler();
      addMouseListener(mouseHandler);
      addMouseMotionListener(mouseHandler);
   }
   

   /**
    * The paintComponent() method copies the off-screen canvas to the screen
    * (first creating it, if necessary).  If a mouse drag is is progress,
    * then the current tool is not Tool.CURVE, then the shape that the user
    * is drawing is drawn over the off-screen canvas.  (This is to avoid
    * making the shape a permanent part of the picture until after the user
    * releases the mouse.  The effect is a "rubber band cursor" in which
    * the shape changes as the user drags the mouse, but the picture under
    * the shape is not affected.)
    */
    @Override
   public void paintComponent(Graphics g) {

      /* First create the off-screen canvas, if it does not already exist. */ 

      if (OSC == null) {
           createOSC();
       }

      /* Copy the off-screen canvas to the panel.  Since we know that the
            image is already completely available, the fourth "ImageObserver"
            parameter to g.drawImage() can be null.  Since the canvas completely
            fills the panel, there is no need to call super.paintComponent(g). */

      g.drawImage(OSC,0,0,null);

      /* If the user is currently dragging the mouse to draw a line, oval,
            or rectangle, draw the shape on top the image from the off-screen
            canvas, using the current drawing color.  (This is not done if the
            user is drawing a curve or using the smudge tool.) */

      if (dragging && SHAPE_TOOLS.contains(currentTool)) {
         g.setColor(currentColor);
         putCurrentShape(g);
      }

   }
   
   
   /**
    * This method creates the off-screen canvas and fills it with the current
    * fill color.
    */
   private void createOSC() {
      OSC = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);
      Graphics osg = OSC.getGraphics();
      Color fillColor=new Color(0,0,0);
      fillColor=Color.WHITE;
      osg.setColor(fillColor);
      osg.fillRect(0,0,getWidth(),getHeight());
      osg.dispose();
      
   }
   
   
   /**
    * A utility method to draw the current shape in a given graphics context.
    * It draws the correct shape for the current tool in a rectangle whose
    * corners are given by the starting position of the mouse and the current
    * position of the mouse.  This is used by paintComponent() during a
    * mouse drag.  It is also used to make the shape a permanent part of the
    * off-screen canvas when the mouse is released.
    * This method is not used when the current tool is Tool.CURVE or Tool.SMUDGE.  
    */
   private void putCurrentShape(Graphics g) {
      switch (currentTool) {
      case LINE:
         g.drawLine(startX, startY, currentX, currentY);
         break;
      case OVAL:
         putOval(g,false,startX, startY, currentX, currentY);
         break;
      case RECT:
         putRect(g,false,startX, startY, currentX, currentY);
         break;
      case FILLED_OVAL:
         putOval(g,true,startX, startY, currentX, currentY);
         break;
      case FILLED_RECT:
         putRect(g,true,startX, startY, currentX, currentY);
         break;
      }
   }
   
   
   /**
    * Draws a filled or unfilled rectangle with corners at the points (x1,y1)
    * and (x2,y2).  Nothing is drawn if x1 == x2 or y1 == y2.
    * @param g the graphics context where the rectangle is drawn
    * @param filled tells whether to draw a filled or unfilled rectangle.
    */
   private void putRect(Graphics g, boolean filled, int x1, int y1, int x2, int y2) {
      if (x1 == x2 || y1 == y2) {
           return;
       }
      if (x2 < x1) {  // Swap x1,x2 if necessary to make x2 > x1.
         int temp = x1;
         x1 = x2;
         x2 = temp;
      }
      if (y2 < y1) {  // Swap y1,y2 if necessary to make y2 > y1.
         int temp = y1;
         y1 = y2;
         y2 = temp;
      }
      if (filled) {
           g.fillRect(x1,y1,x2-x1,y2-y1);
       }
      else {
           g.drawRect(x1,y1,x2-x1,y2-y1);
       }
   }
   
   
   /**
    * Draws a filled or unfilled oval in the rectangle with corners at the 
    * points (x1,y1) and (x2,y2).  Nothing is drawn if x1 == x2 or y1 == y2.
    * @param g the graphics context where the oval is drawn
    * @param filled tells whether to draw a filled or unfilled oval.
    */
   private void putOval(Graphics g, boolean filled, int x1, int y1, int x2, int y2) {
      if (x1 == x2 || y1 == y2) {
           return;
       }
      if (x2 < x1) {  // Swap x1,x2 if necessary to make x2 > x1.
         int temp = x1;
         x1 = x2;
         x2 = temp;
      }
      if (y2 < y1) {  // Swap y1,y2 if necessary to make y2 > y1.
         int temp = y1;
         y1 = y2;
         y2 = temp;
      }
      if (filled) {
           g.fillOval(x1,y1,x2-x1,y2-y1);
       }
      else {
           g.drawOval(x1,y1,x2-x1,y2-y1);
       }
   }

   
   /**
    * Calls the repaint() method of this panel for the rectangle with corners
    * at the points (x1,y1) and (x2,y2).  An extra one-pixel border is added
    * to the area that is repainted; this allows for the size of the "pen"
    * that is used to draw lines and unfilled ovals and rectangles.
    */
   private void repaintRect(int x1, int y1, int x2, int y2) {
      if (x2 < x1) {  // Swap x1,x2 if necessary to make x2 >= x1.
         int temp = x1;
         x1 = x2;
         x2 = temp;
      }
      if (y2 < y1) {  // Swap y1,y2 if necessary to make y2 >= y1.
         int temp = y1;
         y1 = y2;
         y2 = temp;
      }
      x1--;
      x2++;
      y1--;
      y2++;
      repaint(x1,y1,x2-x1,y2-y1);
   }
   
   
   /**
    * Creates a menu bar for use with this panel, with "Color"
    * and "Tool" menus.
    */
   public JMenuBar getMenuBar() {
      JMenuBar menubar = new JMenuBar();
      JMenu fileMenu=new JMenu("File");
      JMenu colorMenu = new JMenu("Color");
      JMenu toolMenu = new JMenu("Tool");
      menubar.add(fileMenu);
      menubar.add(colorMenu);
      menubar.add(toolMenu);
      ActionListener listener = new MenuHandler();
      JMenuItem item;
      item=new JMenuItem("New File");
      item.addActionListener(listener);
      item.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
      fileMenu.add(item);
      item = new JMenuItem("Open");
      item.addActionListener(listener);
      item.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
      fileMenu.add(item);
      item=new JMenuItem("Save");
      item.addActionListener(listener);
      item.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
      fileMenu.add(item);
      fileMenu.addSeparator();
      
      item=new JMenuItem("Exit");
      item.addActionListener(listener);
      fileMenu.add(item);
      
      
      item = new JMenuItem("Draw With Black");
      item.addActionListener(listener);
      colorMenu.add(item);
      item = new JMenuItem("Draw With White");
      item.addActionListener(listener);
      colorMenu.add(item);
      item = new JMenuItem("Draw With Red");
      item.addActionListener(listener);
      colorMenu.add(item);
      item = new JMenuItem("Draw With Green");
      item.addActionListener(listener);
      colorMenu.add(item);
      item = new JMenuItem("Draw With Blue");
      item.addActionListener(listener);
      colorMenu.add(item);
      item = new JMenuItem("Draw With Yellow");
      item.addActionListener(listener);
      colorMenu.add(item);
      item = new JMenuItem("Select Drawing Color...");
      item.addActionListener(listener);
      colorMenu.add(item);
      colorMenu.addSeparator();
      item = new JMenuItem("Fill With Color...");
      item.addActionListener(listener);
      colorMenu.add(item);
      item = new JMenuItem("Curve");
      item.addActionListener(listener);
      toolMenu.add(item);
      toolMenu.addSeparator();
      item = new JMenuItem("Line");
      item.addActionListener(listener);
      toolMenu.add(item);
      item = new JMenuItem("Rectangle");
      item.addActionListener(listener);
      toolMenu.add(item);
      item = new JMenuItem("Oval");
      item.addActionListener(listener);
      toolMenu.add(item);
      item = new JMenuItem("Filled Rectangle");
      item.addActionListener(listener);
      toolMenu.add(item);
      item = new JMenuItem("Filled Oval");
      item.addActionListener(listener);
      toolMenu.add(item);
      toolMenu.addSeparator();
      item = new JMenuItem("Smudge");
      item.addActionListener(listener);
      toolMenu.add(item);
      item = new JMenuItem("Erase");
      item.addActionListener(listener);
      toolMenu.add(item);
      return menubar;
   }
   
   
   /**
    * This nested class defines the ActionListener that responds when the
    * user selects a command from one of the menus.  It is used in the
    * getMenuBar() method.
    */
   private class MenuHandler implements ActionListener {
       @Override
      public void actionPerformed(ActionEvent evt) {
         String command = evt.getActionCommand();
           switch (command) {
               case "New File":
               {
                   OSC = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);
                   Graphics osg = OSC.getGraphics();
                   osg.setColor(fillColor);
                   osg.fillRect(0,0,getWidth(),getHeight());
                   osg.dispose();
                   repaint();
                   break;
               }
               case "Open":
               {
                   JFileChooser chooser=new JFileChooser();
                   FileNameExtensionFilter filter=new FileNameExtensionFilter("JPG or PNG Images","jpg","png");
                   chooser.setFileFilter(filter);
                   int returnVal=chooser.showOpenDialog(null);
                   if(returnVal==JFileChooser.APPROVE_OPTION)
                   {
                    try{
                       
                       OSC=ImageIO.read(chooser.getSelectedFile());
                       Graphics2D g2=(Graphics2D) OSC.getGraphics();
                       
                       g2.scale((double)640/OSC.getWidth(), (double)480/OSC.getHeight());
                       g2.drawImage(OSC, 0,0,OSC.getWidth(),OSC.getHeight(),null);
                    }
                       catch (Exception e){
                       }
                   }
                   repaint();
                   break;
               }
               case "Save":
               {
                   JFileChooser fileDialog = new JFileChooser();
                    fileDialog.setSelectedFile(new File("image.PNG"));
                    fileDialog.setDialogTitle("Select File to be Saved");
                    int option = fileDialog.showSaveDialog(null);
                    if (option != JFileChooser.APPROVE_OPTION)
                    return; // User canceled or clicked the dialog’s close box.
                    
                    File selectedFile = fileDialog.getSelectedFile();
                    if (selectedFile.exists()) { // Ask the user whether to replace the file.
                        int response = JOptionPane.showConfirmDialog( null,"The file \"" 
                            + selectedFile.getName()+ "\" already exists.\nDo you want to replace it?",
                            "Confirm Save",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                        if (response != JOptionPane.YES_OPTION)
                        return; // User does not want to replace the file.
                    }
                    try {
                        boolean hasFormat = ImageIO.write(OSC,"PNG",selectedFile);
                        if ( ! hasFormat )
                                throw new Exception("PNG" + " format is not available.");
                    }
                   catch (Exception e) {
                    JOptionPane.showMessageDialog(null,"Sorry, an error occurred while trying to save image.");
                    e.printStackTrace();
                    }

                   
                   break;
               }
               case "Exit":
               {
                   System.exit(0);
               }
               
               
               case "Select Drawing Color...":
                   {
                       Color newColor = JColorChooser.showDialog(AdvancedGUIEX1.this, 
                             "Select Drawing Color", currentColor);
                       if (newColor != null)
                          currentColor = newColor;
                       break;
                   }
               case "Fill With Color...":
                   {
                       Color newColor = JColorChooser.showDialog(AdvancedGUIEX1.this, 
                             "Select Fill Color", fillColor);
                       if (newColor != null) {
                          fillColor = newColor;
                          Graphics osg = OSC.getGraphics();
                          osg.setColor(fillColor);
                          osg.fillRect(0,0,OSC.getWidth(),OSC.getHeight());
                          osg.dispose();
                          AdvancedGUIEX1.this.repaint();
                       }
                       break;
                   }
               case "Draw With Black":
                   currentColor = Color.BLACK;
                   break;
               case "Draw With White":
                   currentColor = Color.WHITE;
                   break;
               case "Draw With Red":
                   currentColor = Color.RED;
                   break;
               case "Draw With Green":
                   currentColor = Color.GREEN;
                   break;
               case "Draw With Blue":
                   currentColor = Color.BLUE;
                   break;
               case "Draw With Yellow":
                   currentColor = Color.YELLOW;
                   break;
               case "Curve":
                   currentTool = Tool.CURVE;
                   break;
               case "Line":
                   currentTool = Tool.LINE;
                   break;
               case "Rectangle":
                   currentTool = Tool.RECT;
                   break;
               case "Oval":
                   currentTool = Tool.OVAL;
                   break;
               case "Filled Rectangle":
                   currentTool = Tool.FILLED_RECT;
                   break;
               case "Filled Oval":
                   currentTool = Tool.FILLED_OVAL;
                   break;
               case "Smudge":
                   currentTool = Tool.SMUDGE;
                   break;
               case "Erase":
                   currentTool = Tool.ERASE;
                   break;
           }
      }
   } // end nested class MenuHandler
   
   
   /**
    * This nested class defines the object that listens for mouse and
    * mouse motion events on the panel.  It is used in the constructor.
    */
   private class MouseHandler implements MouseListener, MouseMotionListener {
      
      int prevX, prevY;  // Previous position of mouse during a drag.
      
      double[][] smudgeRed, smudgeGreen, smudgeBlue;  // data for smudge tool
      
      /**
       * When the ERASE or SMUDGE tools are used and the mouse jumps
       * from (x1,y1) to (x2,y2), the tool has to be applied to a
       * line of pixel positions between the two points in order to
       * cover the entire line that the mouse moves along.  The change
       * is made to the off-screen canvas, and repaint() is called to
       * copy the changes to the screen.
       */
      void applyToolAlongLine(int x1, int y1, int x2, int y2) {
         Graphics g = OSC.getGraphics();
         g.setColor(fillColor);    // (for ERASE only)
         int w = OSC.getWidth();   // (for SMUDGE only)
         int h = OSC.getHeight();  // (for SMUDGE only)
         int dist = Math.max(Math.abs(x2-x1),Math.abs(y2-y1));
             // dist is the number of points along the line from
             // (x1,y1) to (x2,y2) at which the tool will be applied.
         double dx = (double)(x2-x1)/dist;
         double dy = (double)(y2-y1)/dist;
         for (int d = 1; d <= dist; d++) {
                // Apply the tool at one of the points (x,y) along the
                // line from (x1,y1) to (x2,y2).
            int x = (int)Math.round(x1 + dx*d);
            int y = (int)Math.round(y1 + dy*d);
            if (currentTool == Tool.ERASE) {
                   // Erase a 10-by-10 block of pixels around (x,y)
               g.fillRect(x-5,y-5,10,10);
               repaint(x-5,y-5,10,10);
            }
            else { 
                  // For the SMUDGE tool, blend some of the color from
                  // the smudgeRed, smudgeGreen, and smudgeBlue arrays
                  // into the pixels in a 7-by-7 block around (x,y), and
                  // vice versa.  The effect is to smear out the color
                  // of pixels that are visited by the tool.
               for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 7; j++) {
                       int r = y + j - 3;
                       int c = x + i - 3;
                       if (!(r < 0 || r >= h || c < 0 || c >= w || smudgeRed[i][j] == -1)) {
                          int curCol = OSC.getRGB(c,r);
                          int curRed = (curCol >> 16) & 0xFF;
                          int curGreen = (curCol >> 8) & 0xFF;
                          int curBlue = curCol & 0xFF;
                          int newRed = (int)(curRed*0.7 + smudgeRed[i][j]*0.3);
                          int newGreen = (int)(curGreen*0.7 + smudgeGreen[i][j]*0.3);
                          int newBlue = (int)(curBlue*0.7 + smudgeBlue[i][j]*0.3);
                          int newCol = newRed << 16 | newGreen << 8 | newBlue;
                          OSC.setRGB(c,r,newCol);
                          smudgeRed[i][j] = curRed*0.3 + smudgeRed[i][j]*0.7;
                          smudgeGreen[i][j] = curGreen*0.3 + smudgeGreen[i][j]*0.7;
                          smudgeBlue[i][j] = curBlue*0.3 + smudgeBlue[i][j]*0.7;
                       }
                    }
                }
               repaint(x-3,y-3,7,7);
            }
         }
         g.dispose();
      }

      /**
       * Start a drag operation.
       */
       @Override
      public void mousePressed(MouseEvent evt) {
         startX = prevX = currentX = evt.getX();
         startY = prevY = currentY = evt.getY();
         dragging = true;
         if (currentTool == Tool.ERASE) {
               // Erase a 10-by-10 block around the starting mouse position.
            Graphics g = OSC.getGraphics();
            g.setColor(fillColor);
            g.fillRect(startX-5,startY-5,10,10);
            g.dispose();
            repaint(startX-5,startY-5,10,10);
         }
         else if (currentTool == Tool.SMUDGE) {
                // Record the colors in a 7-by-7 block of pixels around the
                // starting mouse position into the arrays smudgeRed, 
                // smudgeGreen, and smudgeBlue.  These arrays hold the
                // red, green, and blue components of the colors.
            if (smudgeRed == null) {
                  // Create the arrays, if they have not already been created.
               smudgeRed = new double[7][7];
               smudgeGreen = new double[7][7];
               smudgeBlue = new double[7][7];
            }
            int w = OSC.getWidth();
            int h = OSC.getHeight();
            int x = evt.getX();
            int y = evt.getY();
            for (int i = 0; i < 7; i++) {
                 for (int j = 0; j < 7; j++) {
                    int r = y + j - 3;
                    int c = x + i - 3;
                    if (r < 0 || r >= h || c < 0 || c >= w) {
                          // A -1 in the smudgeRed array indicates that the
                          // corresponding pixel was outside the canvas.
                       smudgeRed[i][j] = -1;
                    }
                    else {
                       int color = OSC.getRGB(c,r);
                       smudgeRed[i][j] = (color >> 16) & 0xFF;
                       smudgeGreen[i][j] = (color >> 8) & 0xFF;
                       smudgeBlue[i][j] = color & 0xFF;
                    }
                 }
             }
         }
      }
      
      /**
       * Continue a drag operation when the user drags the mouse.
       * For the CURVE tool, a line is drawn from the previous mouse
       * position to the current mouse position in the off-screen canvas,
       * and the repaint() method is called for a rectangle that contains
       * the line segment that was drawn.  For shape tools, the off-screen
       * canvas is not changed, but the repaint() method is called so
       * that the paintComponent() method can redraw the picture with
       * the user's shape in the new position.  For the SMUDGE and
       * ERASE tools, the tool is applied along a line from the previous
       * mouse position to the current position;
       */
       @Override
      public void mouseDragged(MouseEvent evt) {
         currentX = evt.getX();
         currentY = evt.getY();
         if (currentTool == Tool.CURVE) {
            Graphics g = OSC.getGraphics();
            g.setColor(currentColor);
            g.drawLine(prevX,prevY,currentX,currentY);
            g.dispose();
            repaintRect(prevX,prevY,currentX,currentY);
         }
         else if (SHAPE_TOOLS.contains(currentTool)) {
                 // Repaint the rectangles occupied by the previous position of
                 // the shape and by its current position.
            repaintRect(startX,startY,prevX,prevY);
            repaintRect(startX,startY,currentX,currentY);
         }
         else {
               // Tool has to be ERASE or SMUDGE
            applyToolAlongLine(prevX,prevY,currentX,currentY);
         }
         prevX = currentX;
         prevY = currentY;
      }

      /**
       * Finish a mouse drag operation.  Nothing is done unless the current tool
       * is a shape tool.  For shape tools, the user's shape is drawn to the
       * off-screen canvas, making it a permanent part of the picture, and
       * then the repaint() method is called to show the modified picture
       * on the screen.
       */
       @Override
      public void mouseReleased(MouseEvent evt) {
         dragging = false;
         if (SHAPE_TOOLS.contains(currentTool)) {
            Graphics g = OSC.getGraphics();
            g.setColor(currentColor);
            putCurrentShape(g);
            g.dispose();
            repaint();
         }
      }
      
       @Override
      public void mouseMoved(MouseEvent evt) { }
       @Override
      public void mouseClicked(MouseEvent evt) { }
       @Override
      public void mouseEntered(MouseEvent evt) { }
       @Override
      public void mouseExited(MouseEvent evt) { }
      
   } // end nested class MenuHandler


} // end class PaintWithOffScreenCanvas

