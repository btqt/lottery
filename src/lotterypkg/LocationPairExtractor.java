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

        System.out.println(tableCharacterList.size());
        return results;
    }

    public static void main(String[] args) {
        DBConnector.getInstance().connectDB();
        LotteryTable table = new TblSKQDAO().selectById(1);

        List<LotteryTable> tables = new TblSKQDAO().selectAll();

        for (LotteryTable t : tables) {
            List<LocationPair> l = new LocationPairExtractor(t).getListLocationPair();
            System.out.println("Count: " + l.size());
            for (LocationPair p : l) {
                System.out.println(p.getFirst() + " " + p.getSecond() + " " + p.getNumber());
            }
        }
    }
}
