/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lottery;

import java.util.List;

/**
 *
 * @author PHIVH
 */
public class Lottery {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            DBConnector.getInstance().connectDB();

            MainGui mainGui = new MainGui();
            mainGui.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}
