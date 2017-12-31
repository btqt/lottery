/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lotterypkg;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PHIVH
 */
public class LocationPairExtractor {

    LotteryTable table;
    
    public LocationPairExtractor(LotteryTable table) {
        this.table = table;
    }
    
    public List<LocationPair> getListLocationPair() {
        List<LocationPair> results = new ArrayList<LocationPair>();
        List<String> listGiai = table.getAllListGiai();
        
//        List<char> 
        for(int i = 0; i < listGiai.size(); ++i) {
            listGiai.get(i).toCharArray();
        }
        
        return results; 
   }
    
    public static void main(String[] args) {
        
    }
}
