//********************************************************************************
// PantherID:  6210492
// CLASS: COP 2210 – 2019
// ASSIGNMENT 5
// DATE: 11/20/19
//********************************************************************************

/**
* Implements an application that simulates a Tic-Tac-Toe game. 
* The goal is to win against the machine
* @author  Gabriel Ribeiro
* @version 1.0
*/

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe;
import javax.swing.JOptionPane;
/**
 *
 * @author Gabriel Ribeiro
 */
public class TicTacToeTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here  

        TicTacToe game = new TicTacToe();   
        
        String name;  
        name  = JOptionPane.showInputDialog("Please enter the player's name:");
//       fixing the cancel option and "" value
        if(name == null || (name != null && ("".equals(name)))){
            name = "Dummy";
        }
        game.SetUserName(name);
        
        game.GameMode();
        
    }
}
