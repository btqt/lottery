/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lotterypkg;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PHIVH
 */
public class LocationPairExtractor {

    private LotteryTable table;

    public LocationPairExtractor() {
    }

    public LocationPairExtractor(LotteryTable table) {
        this.table = table;
    }

    /**
     * @param table the table to set
     */
    public void setTable(LotteryTable table) {
        this.table = table;
    }
    
    public List<LocationPair> getListLocationPair() {
        List<LocationPair> results = new ArrayList<LocationPair>();
        List<String> tableCharacterList = new ArrayList<String>();

        List<String> listGiai = table.getAllListGiai();

        for (int i = 0; i < listGiai.size(); ++i) {
            char[] giaiArr = listGiai.get(i).toCharArray();
            for (int j = 0; j < giaiArr.length; ++j) {
                tableCharacterList.add(Character.toString(giaiArr[j]));
            }
        }

        for (int i = 0; i < tableCharacterList.size(); ++i) {
            for (int j = 0; j < tableCharacterList.size(); ++j) {
                String first = tableCharacterList.get(i);
                String second = tableCharacterList.get(j);
                results.add(new LocationPair(i, j, first + second));
            }
        }
        return results;
    }

    public static void main(String[] args) {
//        DBConnector.getInstance().connectDB();
//
//        List<LotteryTable> tables = new TblSKQDAO().selectAll();
//
//        LotteryTable table = new TblSKQDAO().selectById(1);
//        
//        List<LocationPair> locationPairs = new LocationPairExtractor(table).getListLocationPair();
//        
//        PreparedStatement stmt = null;
//        try {
//            for(int i = 0; i < locationPairs.size(); ++i) {
//                LocationPair lp = locationPairs.get(i);
//                stmt = DBConnector.getInstance().getConnection().prepareStatement("INSERT INTO tbl_location_pair_column (value) "
//                                                                                 + "VALUES(?)");
//                stmt.setObject(1, lp.getFirst() + "-" + lp.getSecond());
//
//                stmt.execute();
//            }
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
        
//        new LotteryDataExcelExporter().exportLocationPairs(tables);

//        LocationPairExtractor lpe = new LocationPairExtractor();
        
//        boolean endLoop = false;
        
//        while(!endLoop) {
//            List<String> listNumberWithSameLocationPair = new ArrayList<String>();
//            int currentLocationPair = 0;
//            for(int i = 0; i < tables.size(); ++i) {
//                lpe.setTable(tables.get(i));
//                String s = lpe.getListLocationPair().get(currentLocationPair).getNumber();
//                listNumberWithSameLocationPair.add(s);
//            }
//        }        
    }
}
