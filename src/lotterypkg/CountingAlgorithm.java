/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lotterypkg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author PHIVH
 */
public class CountingAlgorithm {
    List<LotteryTable> tables;

    public CountingAlgorithm(List<LotteryTable> tables) {
        this.tables = tables;
    }
    
    public Map<Integer, Integer> calculate() {
        Map<Integer,Integer> resultMap = new HashMap<Integer, Integer>();
        List<Pair<LotteryTable, LocationPair>> listPairLotTable2Location = new ArrayList<Pair<LotteryTable, LocationPair>>();
        
        int column = 3263
; 
        while(column < 3264/*11449*/) {
            for(int row = 0; row < tables.size(); ++row) {
                LotteryTable table = tables.get(row);
                LocationPair lp = table.listLocationPairs().get(column);
                listPairLotTable2Location.add(new Pair<LotteryTable,LocationPair>(table, lp));
            }
            ++column;
            // TODO: counting here
            int index = 0;
            if(listPairLotTable2Location.size() > 0) {
                Pair<LotteryTable, LocationPair> p = listPairLotTable2Location.get(0);
                System.out.println("Vi tri: " + p.getRight().getFirst() + "-" + p.getRight().getSecond());
            }
                
            while (index < listPairLotTable2Location.size() - 1) {
                Pair<LotteryTable, LocationPair> p = listPairLotTable2Location.get(index);
                System.out.println("Current: " + p.getLeft().getDate() + " " + p.getRight().getNumber());
                int limit = 0;
                
                int i = index + 1;
                for(; i < listPairLotTable2Location.size(); ++i) {
                    if(!listPairLotTable2Location.get(i).getLeft().checkNumberIsExistInTable(p.getRight().getNumber())) {
                        break;
                    }
                    ++ limit;
                    if(limit == 1) {
                        System.out.println("lotterypkg.CountingAlgorithm.calculate(): " + listPairLotTable2Location.get(i).getLeft().getDate() + " Bridge-head here .................................");
                    } else System.out.println("lotterypkg.CountingAlgorithm.calculate(): " + listPairLotTable2Location.get(i).getLeft().getDate() + " Bridge continue ..................................");
                }
                
                if(limit >= 2) {// cau dai 2 ngay tro len -> insert vao map
                    if(resultMap.containsKey(limit))
                        resultMap.put(limit, resultMap.get(limit) + 1);
                    else resultMap.put(limit, 1);
                }
                
                if(limit >= 2) 
                    index = i - 1;
                else index ++;
            }
        }
        
        return resultMap;
    }
    
    public static void main(String[] args) {
        DBConnector.getInstance().connectDB();

        List<LotteryTable> tables = new TblSKQDAO().selectAll();

        CountingAlgorithm ca = new CountingAlgorithm(tables);
        
        Map<Integer, Integer> m = ca.calculate();
        
        for(Map.Entry<Integer,Integer> entry : m.entrySet()) {
            System.out.println("Cau " + entry.getKey() + ": " + entry.getValue());
        }
        
//        List<LotteryTable> list1table = new ArrayList<LotteryTable>();
//        list1table.add(new TblSKQDAO().selectById(1556));
//        
//        new LotteryDataExcelExporter().exportTables(list1table);
    }
}
