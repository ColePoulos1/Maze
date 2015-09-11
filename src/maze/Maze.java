/*
 * My Maze
 */
package maze;
 
import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class Maze extends JFrame implements Runnable {
    static final int WINDOW_WIDTH = 641;
    static final int WINDOW_HEIGHT = 673;

    final int TOP_BORDER = 40;
    final int SIDE_BORDER = 8;
    final int BOTTOM_BORDER = 8;
    final int YTITLE = 25;
     boolean animateFirstTime = true;
    int xsize = -1;
    int ysize = -1;
    Image image;
    Graphics2D g;


    final int numRows = 25;
    final int numColumns = 25;
    
//board variables
    final int PATH = 0;
    final int WALL = 1;
    final int SECR = 2;
    
//make the board
        int board[][] = {
    {WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL },
    {WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL,WALL,WALL,WALL,WALL,WALL },
    {WALL,PATH,PATH,PATH,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,PATH,PATH,WALL,PATH,WALL,WALL,PATH,WALL,WALL,WALL,WALL,WALL,WALL },
    {WALL,PATH,WALL,PATH,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,PATH,WALL,PATH,WALL,PATH,PATH,WALL,WALL,WALL,WALL,WALL,WALL },
    {WALL,PATH,WALL,PATH,WALL,WALL,PATH,PATH,PATH,PATH,PATH,PATH,WALL,PATH,PATH,PATH,WALL,PATH,WALL,WALL,WALL,WALL,WALL,WALL,WALL },
    {WALL,PATH,WALL,PATH,PATH,PATH,PATH,WALL,WALL,WALL,WALL,PATH,PATH,PATH,WALL,PATH,PATH,PATH,WALL,WALL,WALL,WALL,WALL,WALL,WALL },
    {WALL,PATH,WALL,PATH,PATH,WALL,PATH,PATH,WALL,WALL,WALL,WALL,PATH,PATH,WALL,WALL,PATH,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL },
    {WALL,PATH,WALL,PATH,PATH,WALL,WALL,PATH,WALL,PATH,PATH,PATH,PATH,SECR,WALL,WALL,PATH,WALL,WALL,PATH,PATH,PATH,WALL,WALL,WALL },
    {WALL,PATH,PATH,PATH,WALL,WALL,WALL,PATH,WALL,PATH,WALL,WALL,WALL,SECR,WALL,WALL,PATH,PATH,PATH,PATH,WALL,PATH,WALL,WALL,WALL },
    {WALL,WALL,PATH,WALL,WALL,WALL,WALL,PATH,WALL,PATH,WALL,WALL,WALL,SECR,WALL,WALL,PATH,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL },
    {WALL,WALL,PATH,PATH,PATH,PATH,WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL },
    {WALL,PATH,PATH,WALL,WALL,PATH,WALL,PATH,WALL,WALL,PATH,WALL,WALL,WALL,WALL,WALL,SECR,WALL,WALL,WALL,WALL,PATH,PATH,WALL,WALL },
    {WALL,PATH,WALL,WALL,WALL,PATH,WALL,PATH,WALL,WALL,PATH,WALL,WALL,WALL,WALL,WALL,SECR,WALL,PATH,PATH,PATH,PATH,PATH,WALL,WALL },
    {WALL,PATH,WALL,WALL,WALL,PATH,WALL,PATH,WALL,WALL,PATH,WALL,WALL,WALL,WALL,WALL,SECR,WALL,PATH,WALL,WALL,WALL,PATH,WALL,WALL },
    {WALL,PATH,WALL,WALL,WALL,PATH,WALL,PATH,WALL,WALL,PATH,WALL,WALL,WALL,PATH,PATH,PATH,WALL,PATH,WALL,WALL,WALL,PATH,WALL,WALL },
    {WALL,PATH,WALL,WALL,WALL,PATH,PATH,PATH,WALL,WALL,PATH,PATH,WALL,PATH,PATH,WALL,PATH,PATH,PATH,WALL,WALL,WALL,PATH,WALL,WALL },
    {WALL,PATH,PATH,SECR,SECR,SECR,WALL,PATH,WALL,WALL,WALL,PATH,WALL,PATH,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL,PATH,PATH,WALL },
    {WALL,PATH,PATH,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL,PATH,WALL,PATH,WALL,WALL,WALL,WALL,PATH,SECR,SECR,SECR,SECR,PATH,WALL },
    {WALL,PATH,PATH,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL,PATH,PATH,PATH,WALL,WALL,WALL,PATH,PATH,PATH,PATH,WALL,WALL,PATH,WALL },
    {WALL,PATH,WALL,WALL,WALL,WALL,WALL,PATH,WALL,WALL,SECR,SECR,WALL,WALL,WALL,WALL,WALL,PATH,WALL,WALL,PATH,WALL,WALL,PATH,WALL },
    {WALL,PATH,PATH,WALL,WALL,WALL,WALL,PATH,PATH,PATH,PATH,WALL,WALL,WALL,WALL,WALL,WALL,PATH,WALL,WALL,PATH,WALL,PATH,PATH,WALL },
    {WALL,WALL,PATH,WALL,WALL,WALL,PATH,PATH,WALL,WALL,PATH,WALL,WALL,WALL,WALL,WALL,WALL,PATH,WALL,WALL,PATH,PATH,PATH,WALL,WALL },
    {WALL,WALL,PATH,PATH,WALL,PATH,PATH,PATH,WALL,WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL,WALL,WALL,PATH,PATH,WALL,WALL },
    {WALL,WALL,WALL,PATH,PATH,PATH,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL },
    {WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL }
        };

//initialize instances
    int numloot = 3;
    int numnpcs = 5;
    Character npc[] = new Character[numnpcs];
    Character user = new Character();
    Treasure loot[] = new Treasure[numloot];
    
    Portal port1 = new Portal();
    Portal port2 = new Portal();
    
//portal variebles
    int portTimer;
    boolean portHit;
//tech variables
    boolean gameover;
    int timeCount;
    boolean gamestart;
//secret pass variables
    boolean secretPass;
    int passTimer;
//scores variables
    int score;
    int highscore;

//theme color
    Color theme = Color.cyan;
    Color contrast = Color.BLACK;
    Color crazy = new Color(0,0,0);
    int crazedred;
    int redosc;
    int crazedblue;
    int blueosc;
    int crazedgreen;
    int greenosc;
    
    static Maze frame1;

    public static void main(String[] args) {
        frame1 = new Maze();
        frame1.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
    }

    public Maze() {

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.BUTTON1 == e.getButton()) {
                    //left button
                }
//if right click
                if (e.BUTTON3 == e.getButton()) {
                    //right button
                    reset();
                }
                repaint();
            }
        });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        repaint();
      }
    });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {
        repaint();
      }
    });

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
//if keys pressed
                if (e.VK_RIGHT == e.getKeyCode())
                {
                    user.setcolumnDir(1);
                    user.setrowDir(0);
                    gamestart=true;
                }
                if (e.VK_LEFT == e.getKeyCode())
                {
                    user.setcolumnDir(-1);
                    user.setrowDir(0);
                    gamestart=true;
                }
                if (e.VK_UP == e.getKeyCode())
                {
                    user.setrowDir(-1);
                    user.setcolumnDir(0);
                    gamestart=true;
                }
                if (e.VK_DOWN == e.getKeyCode())
                {
                    user.setrowDir(1);
                    user.setcolumnDir(0);
                    gamestart=true;
                }
                if (e.VK_SPACE == e.getKeyCode() && user.getValue()>1)
                {
                //if portal stepped on
                    if(user.getcurrentColumn() == port1.getcurrentColumn() && 
                       user.getcurrentRow() == port1.getcurrentRow() && portHit == false && user.getValue()>2) 
                    {
                        user.setcurrentRow(port2.getcurrentRow());
                        user.setcurrentColumn(port2.getcurrentColumn());
                        portHit = true;
                        user.setValue(user.getValue() -3);
                    }
                    else if(user.getcurrentColumn() == port2.getcurrentColumn() && 
                            user.getcurrentRow() == port2.getcurrentRow() && portHit == false && user.getValue()>2) 
                    {
                        user.setcurrentRow(port1.getcurrentRow());
                        user.setcurrentColumn(port1.getcurrentColumn());
                        portHit = true;
                        user.setValue(user.getValue() -3);
                    }
                //if no portal is stepped on
                    else if(user.getcurrentColumn() != port1.getcurrentColumn() && 
                            user.getcurrentRow() != port1.getcurrentRow() && user.getcurrentColumn() 
                            != port2.getcurrentColumn() && user.getcurrentRow() != port2.getcurrentRow())
                    {
                         user.setValue(user.getValue() -2);
                         secretPass=true;  
                    }
                }
                repaint();
            }
        });
        init();
        start();
    }




    Thread relaxer;
////////////////////////////////////////////////////////////////////////////
    public void init() {
        requestFocus();
    }
////////////////////////////////////////////////////////////////////////////
    public void destroy() {
    }
////////////////////////////////////////////////////////////////////////////
    public void paint(Graphics gOld) {
        if (image == null || xsize != getSize().width || ysize != getSize().height) {
            xsize = getSize().width;
            ysize = getSize().height;
            image = createImage(xsize, ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }

//fill background
        g.setColor(theme);

        g.fillRect(0, 0, xsize, ysize);

        int x[] = {getX(0), getX(getWidth2()), getX(getWidth2()), getX(0), getX(0)};
        int y[] = {getY(0), getY(0), getY(getHeight2()), getY(getHeight2()), getY(0)};
//fill border
        g.setColor(Color.white);
        g.fillPolygon(x, y, 4);
// draw border
        g.setColor(Color.red);
        g.drawPolyline(x, y, 5);

        if (animateFirstTime) {
            gOld.drawImage(image, 0, 0, null);
            return;
        }

        g.setColor(contrast);
//horizontal lines
        for (int zi=1;zi<numRows;zi++)
        {
            g.drawLine(getX(0) ,getY(0)+zi*getHeight2()/numRows ,
            getX(getWidth2()) ,getY(0)+zi*getHeight2()/numRows );
        }
//vertical lines
        for (int zi=1;zi<numColumns;zi++)
        {
            g.drawLine(getX(0)+zi*getWidth2()/numColumns ,getY(0) ,
            getX(0)+zi*getWidth2()/numColumns,getY(getHeight2())  );
        }
// draw walls
        for (int zrow=0;zrow<numRows;zrow++)
        {
            for (int zcolumn=0;zcolumn<numColumns;zcolumn++)
            {
                if (board[zrow][zcolumn] == WALL || board[zrow][zcolumn]==SECR && secretPass == false)
                {
                    g.setColor(Color.gray);
                    g.fillRect(getX(0)+zcolumn*getWidth2()/numColumns,
                    getY(0)+zrow*getHeight2()/numRows,
                    getWidth2()/numColumns,
                    getHeight2()/numRows);
                }
//draw secret passes
                 if (board[zrow][zcolumn]==SECR && secretPass == true)
                {
                    g.setColor(Color.cyan);
                    g.fillRect(getX(0)+zcolumn*getWidth2()/numColumns,
                    getY(0)+zrow*getHeight2()/numRows,
                    getWidth2()/numColumns,
                    getHeight2()/numRows);
                }
                  if (board[zrow][zcolumn]==SECR && secretPass == false)
                {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(getX(0)+zcolumn*getWidth2()/numColumns,
                    getY(0)+zrow*getHeight2()/numRows,
                    getWidth2()/numColumns,
                    getHeight2()/numRows);
                }
            }
        }
// draw treasure
          for (int index=0;index<numloot;index++)
          {
            if(loot[index].getValue() == 3)  
            g.setColor(Color.YELLOW);
            if(loot[index].getValue() <3)  
            g.setColor(Color.orange);
            g.fillRect(getX(0)+loot[index].getcurrentColumn()*getWidth2()/numColumns,
            getY(0)+loot[index].getcurrentRow()*getHeight2()/numRows,
            getWidth2()/numColumns,
            getHeight2()/numRows);
          }
// draw portals
          g.setColor(contrast);
          g.fillRect(getX(0)+port1.getcurrentColumn()*getWidth2()/numColumns,
          getY(0)+port1.getcurrentRow()*getHeight2()/numRows,
          getWidth2()/numColumns,
          getHeight2()/numRows);   
          g.fillRect(getX(0)+port2.getcurrentColumn()*getWidth2()/numColumns,
          getY(0)+port2.getcurrentRow()*getHeight2()/numRows,
          getWidth2()/numColumns,
          getHeight2()/numRows);     
          
// draw player
          g.setColor(crazy);
          g.fillRect(getX(0)+user.getcurrentColumn()*getWidth2()/numColumns,
          getY(0)+user.getcurrentRow()*getHeight2()/numRows,
          getWidth2()/numColumns,
          getHeight2()/numRows);
          g.setColor(Color.yellow);
          g.fillRect(getX(5)+user.getcurrentColumn()*getWidth2()/numColumns,
          getY(5)+user.getcurrentRow()*getHeight2()/numRows,
          getWidth2()/numColumns -10,
          getHeight2()/numRows - 10);
          
          g.setColor(contrast);
          g.setFont(new Font("Impact",Font.PLAIN,10));
          g.drawString(""+user.getValue(),getX(0)+user.getcurrentColumn()*getWidth2()/numColumns +10
           ,getY(0)+user.getcurrentRow()*getHeight2()/numRows +16); 
          
          
 // draw npcs         
          for (int index=0;index<numnpcs;index++)
          {
            g.setColor(npc[index].getColor());
            g.fillRect(getX(0)+npc[index].getcurrentColumn()*getWidth2()/numColumns,
            getY(0)+npc[index].getcurrentRow()*getHeight2()/numRows,
            getWidth2()/numColumns,
            getHeight2()/numRows);
            
            g.setColor(Color.BLACK);
            g.setFont(new Font("Impact",Font.PLAIN,10));
            g.drawString(npc[index].getname(),getX(0)+npc[index].getcurrentColumn()*getWidth2()/numColumns +4
             ,getY(0)+npc[index].getcurrentRow()*getHeight2()/numRows +12); 
          }
          
// draw writing          
          if(gameover)
          {
            g.setColor(theme); 
            g.setFont(new Font("Goudy Stout",Font.PLAIN,50));
            g.drawString("Game over!", 25,400); 
          }
            g.setColor(contrast); 
            g.setFont(new Font("Goudy Stout",Font.PLAIN,20));
            g.drawString("Score: "+score, 25,50); 
            
            g.setColor(contrast); 
            g.setFont(new Font("Goudy Stout",Font.PLAIN,20));
            g.drawString("Highscore: "+highscore, 320,50); 
            
 //time bar          
            if(portHit)
            {
            g.setColor(contrast);
            g.fillRect(getX(0),
            getY(0),
            getWidth2()-375-portTimer,
            getHeight2()/numRows);
            }
            
        gOld.drawImage(image, 0, 0, null);
    }
////////////////////////////////////////////////////////////////////////////
// needed for     implement runnable
    public void run() {
        while (true) {
            animate();
            repaint();
            double seconds = 0.04;    //time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {
            }
        }
    }
/////////////////////////////////////////////////////////////////////////
    public void reset() {   
        gameover = false;
//randomize player position
        boolean playerset = false;
        while(playerset == false)        
        {
        user.setcurrentRow((int)(Math.random()* numRows));
        user.setcurrentColumn((int)(Math.random()* numColumns));
        if(board[user.getcurrentRow()][user.getcurrentColumn()] == PATH)
        playerset = true;
        }
//randomize portal positions
        boolean portalset = false;
        while(portalset == false)        
        {
        port1.setcurrentRow((int)(Math.random()* numRows));
        port1.setcurrentColumn((int)(Math.random()* numColumns));
        port2.setcurrentRow((int)(Math.random()* numRows));
        port2.setcurrentColumn((int)(Math.random()* numColumns));
        if(board[port1.getcurrentRow()][port1.getcurrentColumn()] == PATH &&
           board[port2.getcurrentRow()][port2.getcurrentColumn()] == PATH)
        portalset = true;
        }
//randomize npcs position        
        for (int index=0;index<numnpcs;index++)
        {
            npc[index] = new Character();
            
            boolean npcset = false;
            while(npcset == false)        
            {
            npc[index].setcurrentRow((int)(Math.random()* numRows));
            npc[index].setcurrentColumn((int)(Math.random()* numColumns));
            if(board[npc[index].getcurrentRow()][npc[index].getcurrentColumn()] == PATH &&
               npc[index].getcurrentRow()-user.getcurrentRow() > 5 && npc[index].getcurrentColumn()-user.getcurrentColumn() > 5
               || board[npc[index].getcurrentRow()][npc[index].getcurrentColumn()] == PATH &&
               npc[index].getcurrentRow()-user.getcurrentRow() < -5 && npc[index].getcurrentColumn()-user.getcurrentColumn() > 5
               || board[npc[index].getcurrentRow()][npc[index].getcurrentColumn()] == PATH &&
               npc[index].getcurrentRow()-user.getcurrentRow() > 5 && npc[index].getcurrentColumn()-user.getcurrentColumn() > -5
               || board[npc[index].getcurrentRow()][npc[index].getcurrentColumn()] == PATH &&
               npc[index].getcurrentRow()-user.getcurrentRow() < -5 && npc[index].getcurrentColumn()-user.getcurrentColumn() > -5)
            npcset = true;
            }
  //randomize npc color,speed,and direction          
            npc[index].setnpcDir((int)(Math.random()*4));
            npc[index].setrowDir(1);
            npc[index].setcolumnDir(0);
            npc[index].setspeed((int)(Math.random()*3 +5));
            int red = (int)(Math.random()*254);
            int blue = (int)(Math.random()*254);
            int green = (int)(Math.random()*254);
            Color npccolor = new Color(red,blue,green);
            npc[index].setColor(npccolor);
        }
        
 //randomize treasure position       
        for (int index=0;index<numloot;index++)
        {
            loot[index] = new Treasure();
            
            boolean lootset = false;
            while(lootset == false)        
            {
            loot[index].setcurrentRow((int)(Math.random()* numRows));
            loot[index].setcurrentColumn((int)(Math.random()* numColumns));
            if(board[loot[index].getcurrentRow()][loot[index].getcurrentColumn()] == PATH)
            lootset = true;
            }
 //randomize treasure value           
            loot[index].setValue((int)(Math.random()* 3 +1));
        }
        
 //tech variables       
        timeCount = 0;
        user.setValue(2);
        
        portTimer = 0;
        portHit = false;
        secretPass = false;
        gameover = false;
        score = 0;
        gamestart = false;
        
//set npc names        
         npc[0].setname("Bigs");
         npc[1].setname("Jojo");
         npc[2].setname("Tymy");
         npc[3].setname("Jaks");
         npc[4].setname("Mac");
        
 //randomize user color
         crazedred = (int)(Math.random()*254);
         crazedblue = (int)(Math.random()*254);
         crazedgreen = (int)(Math.random()*254);
         redosc=(int)(Math.random()+1);
         if(redosc==1)
            redosc=-2;
         blueosc=(int)(Math.random()+1);
         if(blueosc==1)
            blueosc=-2;
         greenosc=(int)(Math.random()+1);
         if(greenosc==1)
            greenosc=-2;
    }
/////////////////////////////////////////////////////////////////////////
    public void animate() {

        if (animateFirstTime) {
            animateFirstTime = false;
            if (xsize != getSize().width || ysize != getSize().height) {
                xsize = getSize().width;
                ysize = getSize().height;
            }
            reset();
             
        }
        if(gameover || gamestart == false)
            return;
        
//do user colors        
        if(crazedred<20)
            redosc=2;
        if(crazedred>230)
            redosc=-2;
        if(crazedblue<20)
            blueosc=2;
        if(crazedblue>230)
            blueosc=-2;
        if(crazedgreen<20)
            greenosc=2;
        if(crazedgreen>230)
            greenosc=-2;
        crazedred += redosc;
        crazedblue += blueosc;
        crazedgreen += greenosc;
        crazy = new Color(crazedred,crazedblue,crazedgreen);
        
//time count         
        timeCount++;
        
 //start the secret pass timer
        if(secretPass)
            passTimer++;
 //start the portal timer
        if(portHit)
            portTimer++;
        
 //AI for npcs       
        for (int index=0;index<numnpcs;index++)
        {     
            if(timeCount % npc[index].getspeed() == npc[index].getspeed() -1)
            {
                    if(npc[index].getcurrentRow()+npc[index].getrowDir()>=user.getcurrentRow()+user.getrowDir() && 
                       npc[index].getcurrentColumn()+npc[index].getcolumnDir()<=user.getcurrentColumn()+user.getcolumnDir())
                    {
                        //0
                        if(board[npc[index].getcurrentRow()-1][npc[index].getcurrentColumn()] == PATH && npc[index].getnpcDir() != 2)
                            npc[index].setnpcDir(0);
                        else if(board[npc[index].getcurrentRow()][npc[index].getcurrentColumn()+1] == PATH && npc[index].getnpcDir() != 3)
                            npc[index].setnpcDir(1);    
                        else if(board[npc[index].getcurrentRow()][npc[index].getcurrentColumn()-1] == PATH && npc[index].getnpcDir() != 1)
                            npc[index].setnpcDir(3);  
                        else if(board[npc[index].getcurrentRow()+1][npc[index].getcurrentColumn()] == PATH && npc[index].getnpcDir() != 0)
                            npc[index].setnpcDir(2);  
                    }
                    else if(npc[index].getcurrentRow()+npc[index].getrowDir()<user.getcurrentRow()+user.getrowDir() && 
                            npc[index].getcurrentColumn()+npc[index].getcolumnDir()<=user.getcurrentColumn()+user.getcolumnDir())
                    {
                        //1
                        if(board[npc[index].getcurrentRow()+1][npc[index].getcurrentColumn()] == PATH && npc[index].getnpcDir() != 0)
                            npc[index].setnpcDir(2);  
                        else if(board[npc[index].getcurrentRow()][npc[index].getcurrentColumn()+1] == PATH && npc[index].getnpcDir() != 3)
                            npc[index].setnpcDir(1); 
                        else if(board[npc[index].getcurrentRow()][npc[index].getcurrentColumn()-1] == PATH && npc[index].getnpcDir() != 1)
                            npc[index].setnpcDir(3); 
                        else if(board[npc[index].getcurrentRow()-1][npc[index].getcurrentColumn()] == PATH && npc[index].getnpcDir() != 2)
                            npc[index].setnpcDir(0);  
                    }
                    else if(npc[index].getcurrentRow()+npc[index].getrowDir()<user.getcurrentRow()+user.getrowDir() && 
                            npc[index].getcurrentColumn()+npc[index].getcolumnDir()>user.getcurrentColumn()+user.getcolumnDir())
                    {
                        //2
                        if(board[npc[index].getcurrentRow()][npc[index].getcurrentColumn()-1] == PATH && npc[index].getnpcDir() != 1)
                            npc[index].setnpcDir(3);
                        else if(board[npc[index].getcurrentRow()+1][npc[index].getcurrentColumn()] == PATH && npc[index].getnpcDir() != 0)
                            npc[index].setnpcDir(2);
                        else if(board[npc[index].getcurrentRow()-1][npc[index].getcurrentColumn()] == PATH && npc[index].getnpcDir() != 2)
                            npc[index].setnpcDir(0);
                        else if(board[npc[index].getcurrentRow()][npc[index].getcurrentColumn()+1] == PATH && npc[index].getnpcDir() != 3)
                            npc[index].setnpcDir(1);     

                    }
                    else if(npc[index].getcurrentRow()+npc[index].getrowDir()>=user.getcurrentRow()+user.getrowDir() && 
                            npc[index].getcurrentColumn()+npc[index].getcolumnDir()>user.getcurrentColumn()+user.getcolumnDir())
                    {
                        //3
                        if(board[npc[index].getcurrentRow()-1][npc[index].getcurrentColumn()] == PATH && npc[index].getnpcDir() != 2)
                            npc[index].setnpcDir(0);
                        else if(board[npc[index].getcurrentRow()][npc[index].getcurrentColumn()-1] == PATH && npc[index].getnpcDir() != 1)
                            npc[index].setnpcDir(3); 
                        else if(board[npc[index].getcurrentRow()][npc[index].getcurrentColumn()+1] == PATH && npc[index].getnpcDir() != 3)
                            npc[index].setnpcDir(1); 
                        else if(board[npc[index].getcurrentRow()+1][npc[index].getcurrentColumn()] == PATH && npc[index].getnpcDir() != 0)
                            npc[index].setnpcDir(2);  
                    }

                if (npc[index].getnpcDir()==0)
                {
                    npc[index].setrowDir(-1);
                    npc[index].setcolumnDir(0);
                }
                else if (npc[index].getnpcDir()==1)
                {
                    npc[index].setrowDir(0);
                    npc[index].setcolumnDir(1);
                }
                else if (npc[index].getnpcDir()==2)
                {
                    npc[index].setrowDir(1);
                    npc[index].setcolumnDir(0);
                }
                else if (npc[index].getnpcDir()==3)
                {
                    npc[index].setrowDir(0);
                    npc[index].setcolumnDir(-1);
                }

//move npcs     
                if(npc[index].getcurrentRow()+npc[index].getrowDir() >= numRows)
                    npc[index].setcurrentRow(-1);
                else if(npc[index].getcurrentRow()+npc[index].getrowDir() < 0)
                    npc[index].setcurrentRow(numRows);
                else if(npc[index].getcurrentColumn()+npc[index].getcolumnDir() >= numColumns)
                    npc[index].setcurrentColumn(-1);
                else if(npc[index].getcurrentColumn()+npc[index].getcolumnDir() < 0)
                    npc[index].setcurrentColumn(numRows);
                
                if(board[npc[index].getcurrentRow()+npc[index].getrowDir()][npc[index].getcurrentColumn()+npc[index].getcolumnDir()] == PATH)
                {
                    npc[index].setcurrentRow(npc[index].getcurrentRow()+npc[index].getrowDir());
                    npc[index].setcurrentColumn(npc[index].getcurrentColumn()+npc[index].getcolumnDir());
                }
            }
        }
        
        
//move player        
        if(board[user.getcurrentRow()+user.getrowDir()][user.getcurrentColumn()+user.getcolumnDir()] == PATH 
           || board[user.getcurrentRow()+user.getrowDir()][user.getcurrentColumn()+user.getcolumnDir()] == SECR && secretPass == true)
        {
            user.setcurrentRow(user.getcurrentRow()+user.getrowDir());
            user.setcurrentColumn(user.getcurrentColumn()+user.getcolumnDir());
        }
        user.setrowDir(0);
        user.setcolumnDir(0);

//if gameover code
        for (int index=0;index<numnpcs;index++)
        { 
           if(user.getcurrentColumn() == npc[index].getcurrentColumn() && user.getcurrentRow() == npc[index].getcurrentRow()) 
           {
               gameover = true;
               score+=user.getValue()*10;
           }
        } 

//if treasure is picked up
        for (int index=0;index<numloot;index++)
        { 
           if(user.getcurrentColumn() == loot[index].getcurrentColumn() && user.getcurrentRow() == loot[index].getcurrentRow()) 
           {
                boolean lootset = false;
                while(lootset == false)        
                {
                loot[index].setcurrentRow((int)(Math.random()* numRows));
                loot[index].setcurrentColumn((int)(Math.random()* numColumns));
                if(board[loot[index].getcurrentRow()][loot[index].getcurrentColumn()] == PATH)
                lootset = true;
                }
                int lootsies = user.getValue()+loot[index].getValue();
                user.setValue(lootsies);
                
                loot[index].setValue((int)(Math.random()* 3 +1));
           }
        } 

//close secret passes after a second or so 
        if(passTimer>30)
        {
            passTimer = 0;
            secretPass=false;
        }
        
//open portals again after 10 seconds
        if(portTimer>250)
        {
            portTimer = 0;
            portHit=false;
        }
//score and highscore setting
        if(timeCount%5==5-1)
            score++;
         if(score>=highscore)
             highscore = score;
    }

////////////////////////////////////////////////////////////////////////////
    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }
////////////////////////////////////////////////////////////////////////////
    public void stop() {
        if (relaxer.isAlive()) {
            relaxer.stop();
        }
        relaxer = null;
    }
/////////////////////////////////////////////////////////////////////////
    public int getX(int x) {
        return (x+SIDE_BORDER);
    }
    public int getY(int y) {
        return (y + TOP_BORDER + YTITLE);
    }
    public int getWidth2() {
        return (xsize - SIDE_BORDER*2);
    }
    public int getHeight2() {
        return (ysize - (TOP_BORDER + YTITLE) - BOTTOM_BORDER);
    }
}


//class for npcs
    class Character{
        private int currentColumn;
        private int currentRow;
        private int rowDir;
        private int columnDir;             
        private int npcDir;
        private Color color;
        private String name;
        private int speed;
        private int value;
        
    //speed    
        void setspeed(int _speed)
        {
            speed = _speed;
        }
        int getspeed()
        {
            return(speed);
        }
    //name    
        void setname(String _name)
        {
            name = _name;
        }
        String getname()
        {
            return(name);
        }
    //currentColumn        
        void setcurrentColumn(int _column)
        {
            currentColumn = _column;
        }
        int getcurrentColumn()
        {
            return(currentColumn);
        }
    //currentRow
        void setcurrentRow(int _row)
        {
            currentRow = _row;
        }
        int getcurrentRow()
        {
            return(currentRow);
        }
    //rowDir
        void setrowDir(int _rowdir)
        {
            rowDir = _rowdir;
        }
        int getrowDir()
        {
            return(rowDir);
        }
    //columnDir
        void setcolumnDir(int _columndir)
        {
            columnDir = _columndir;
        }
        int getcolumnDir()
        {
            return(columnDir);
        }
    //npcDir
        void setnpcDir(int _npcdir)
        {
            npcDir = _npcdir;
        }
        int getnpcDir()
        {
            return(npcDir);
        }
    //Color
        void setColor(Color _color)
        {
            color = _color;
        }
        Color getColor()
        {
            return(color);
        }
    //Value
        void setValue(int _value)
        {
            value = _value;
        }
        int getValue()
        {
            return(value);
        }
    }


//class for treasure
    class Treasure{
        private int currentColumn;
        private int currentRow;
        private int value;
        
    //currentRow
        void setcurrentRow(int _row)
        {
            currentRow = _row;
        } 
        int getcurrentRow()
        {
            return(currentRow);
        }
    //currentColumn
        void setcurrentColumn(int _column)
        {
            currentColumn = _column;
        }     
        int getcurrentColumn()
        {
            return(currentColumn);
        }
    //Value
        void setValue(int _value)
        {
            value = _value;
        }     
        int getValue()
        {
            return(value);
        }
    }
//class for portals
    class Portal{
        private int currentColumn;
        private int currentRow;
        
    //currentRow
        void setcurrentRow(int _row)
        {
            currentRow = _row;
        } 
        int getcurrentRow()
        {
            return(currentRow);
        }
    //currentColumn
        void setcurrentColumn(int _column)
        {
            currentColumn = _column;
        }     
        int getcurrentColumn()
        {
            return(currentColumn);
        }
    }