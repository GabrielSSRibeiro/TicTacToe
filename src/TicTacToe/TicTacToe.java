/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Color;
import javax.swing.*;
import java.util.Random;

/**
 *
 * @author Gabriel Ribeiro
 */
public class TicTacToe {
    
    private String userName = "";
    private boolean vsAI = false;
    private boolean hasMoved = false;
    private boolean playerIsX = true;
    private final String[][] allMoves = new String[3][3];
    private final JButton[][] button = new JButton[3][3];
    JPanel panel = new JPanel(new GridLayout(3,3));       
    JFrame frame = new JFrame("Tic-Tac-Toe");
    private String previousMove = "";
    
//     saves the name of the user to be used in the panes     
    public void SetUserName(String newName){
        userName = newName;
    }
    
    public void ActivateAI(){
        vsAI = true;
    }

//    creates a JOptionPane with the options    
    public String JOpPane(String[] options){
        
//        custom text for the panes based on the first option        
        String text = "";
        switch (options[0]){
            case "Player VS AI":
                text = "Choose the game mode, " + userName + ":";
            break;
            case "YES!":
                text = "Play another one?";
            break;
        }
        
//        JOptionPane
        int choice = JOptionPane.showOptionDialog(null, text,
              "Choose an Option",
              JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options);

//     returns the chosen option        
        return options[choice];  
    } 
     
    public void SetUp() {
     
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.add(panel);        

//        creating the 9 buttons with 2-dimentional array
        for (int r = 0; r<3; r++){
            for (int c = 0; c<3; c++){
//                names the button for better reference
                button[r][c] = new JButton(""+r+c);
//                clear the all moves array
                allMoves[r][c] = ""+r+c; 
//                makes the text desappear, so the value won't show 
                button[r][c].setFont(new Font("Arial", Font.PLAIN, 1000));
                button[r][c].setBackground(Color.white);
                button[r][c].addActionListener(new ActionListener(){               
                    public void actionPerformed(ActionEvent e){
                        String buttonText = e.getActionCommand();  
                        PlayerMove(buttonText);
                    }
                });               
                panel.add(button[r][c]);
            }
        }
//        refresh JFrame to prevent bug
        SwingUtilities.updateComponentTreeUI(frame);
//        randomly decide who goes first and tell the player
        Random ran = new Random();  
        int x = 1 + ran.nextInt(100);
          if(x<51){
            JOptionPane.showMessageDialog(null, "You go first!");          
          }
          else{
            if(vsAI){
                JOptionPane.showMessageDialog(null, "I go first!");
                hasMoved = true;
                playerIsX = false;
                AIMove();
            }
            else{
                JOptionPane.showMessageDialog(null, "Player 2 goes first!");
                playerIsX = false;
            }
          }
    }
    
//     makes sure the X or O appear at the right place
    private void PlayerMove(String text){
       for (int r = 0; r<3; r++){
           for (int c = 0; c<3; c++){    
               if (text.equals(""+r+c)){
                   hasMoved = true;
//    makes sure the X or O value is correct
                   String value;
                   if(previousMove.equals("X")){
                       value = "O";
                   }
                   else{
                       value = "X";
                   }
                   button[r][c].setText(value);
                   allMoves[r][c] = value; 
                   previousMove = value;
                   button[r][c].setFont(new Font("Arial", Font.PLAIN, 100));                  
//                   checks if the game is done                 
                   EndOfGame();
//                   the AI makes a move
                   if(vsAI){  
                       AIMove();
                   }                   
               }
           }
       }     
    }   

    private void AIMove(){   
        Random ran = new Random();                       
        while(hasMoved){        
                        
             int x = ran.nextInt(3);
             int y = ran.nextInt(3); 
             
             if ((!(allMoves[x][y].equals("X"))) && (!(allMoves[x][y].equals("O")))){
                 hasMoved = false;
                 String value;
                 if(previousMove.equals("X")){
                     value = "O";
                 }
                 else{
                     value = "X";
                 }
                 button[x][y].setText(value);
                 allMoves[x][y] = value; 
                 previousMove = value;
                 button[x][y].setFont(new Font("Arial", Font.PLAIN, 100));
//                   checks if the game is done                 
                   EndOfGame();
             }             
        }        
    }
    
    public void GameMode(){
        String[] gameOptions = {"Player VS AI","Player VS Player"};
        String choice = JOpPane(gameOptions);
        if (choice.equals("Player VS AI")){
            ActivateAI();
        }
        SetUp();
    }
    
    private void EndOfGame(){
        
//Check all 8 possible win conditions and saves the winner     
        String XorO = "";
        if(((allMoves[0][0].equals(allMoves[0][1])) && (allMoves[0][0].equals(allMoves[0][2]))) ||
        ((allMoves[0][0].equals(allMoves[1][0])) && (allMoves[0][0].equals(allMoves[2][0])))){
            XorO = allMoves[0][0];
        }        
        else if(((allMoves[1][0].equals(allMoves[1][1])) && (allMoves[1][0].equals(allMoves[1][2]))) ||
        ((allMoves[0][1].equals(allMoves[1][1])) && (allMoves[0][1].equals(allMoves[2][1]))) ||
        (((allMoves[0][0].equals(allMoves[1][1])) && (allMoves[0][0].equals(allMoves[2][2]))) ||
        ((allMoves[2][0].equals(allMoves[1][1])) && (allMoves[2][0].equals(allMoves[0][2]))))){        
            XorO = allMoves[1][1];
        }     
        else if(((allMoves[2][0].equals(allMoves[2][1])) && (allMoves[2][0].equals(allMoves[2][2]))) ||
        ((allMoves[0][2].equals(allMoves[1][2])) && (allMoves[0][2].equals(allMoves[2][2])))){
            XorO = allMoves[2][2];
        }
        else if(((allMoves[0][0].equals("X")) || (allMoves[0][0].equals("O"))) &&
                ((allMoves[1][0].equals("X")) || (allMoves[1][0].equals("O"))) &&
                ((allMoves[0][1].equals("X")) || (allMoves[0][1].equals("O"))) &&
                ((allMoves[1][1].equals("X")) || (allMoves[1][1].equals("O"))) &&
                ((allMoves[2][0].equals("X")) || (allMoves[2][0].equals("O"))) &&
                ((allMoves[0][2].equals("X")) || (allMoves[0][2].equals("O"))) &&
                ((allMoves[2][1].equals("X")) || (allMoves[2][1].equals("O"))) &&
                ((allMoves[1][2].equals("X")) || (allMoves[1][2].equals("O"))) &&
                ((allMoves[2][2].equals("X")) || (allMoves[2][2].equals("O")))){
            XorO = "TIE";
        }
        
        if((!XorO.equals(""))){ 
//            prints the winner
            if (XorO.equals("TIE")){
                JOptionPane.showMessageDialog(null, "It's a tie!");            
            }
            else if((playerIsX && XorO.equals("X")) || ((!playerIsX) && XorO.equals("O"))){
                JOptionPane.showMessageDialog(null, "Congratulations " + userName + ", you win!");   
            }
            else{
                if(vsAI){
                    JOptionPane.showMessageDialog(null, "Pathetic...");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Player 2 wins!");
                }        
            }
//                closes the window and resets
            panel.removeAll();
            frame.dispose();
            playerIsX = true;
            vsAI = false;
            hasMoved = false;
            previousMove = "";
//        asks the user to play again
            String[] gameOptions = {"YES!","No..."};
            String choice = JOpPane(gameOptions);
            if (choice.equals("YES!")){  
                GameMode();
            }
            else{
                JOptionPane.showMessageDialog(null, "Until next time!");
            }
        }
    }    
}
