/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lottery;

import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import lottery.DBConnector;

/**
 *
 * @author PHIVH
 */
public class LotteryTable {

    public LotteryTable() {
        this.mapGiai2Value = new HashMap<Giai, List<String> >();
        this.mapGiai2Value.put(Giai.GIAI_DB, new ArrayList<String>());
        this.mapGiai2Value.put(Giai.GIAI_NHAT, new ArrayList<String>());
        this.mapGiai2Value.put(Giai.GIAI_NHI, new ArrayList<String>());
        this.mapGiai2Value.put(Giai.GIAI_BA, new ArrayList<String>());
        this.mapGiai2Value.put(Giai.GIAI_BON, new ArrayList<String>());
        this.mapGiai2Value.put(Giai.GIAI_NAM, new ArrayList<String>());
        this.mapGiai2Value.put(Giai.GIAI_SAU, new ArrayList<String>());
        this.mapGiai2Value.put(Giai.GIAI_BAY, new ArrayList<String>());

        this.date = new Date();
    }

    public enum Giai {
        GIAI_DB,
        GIAI_NHAT,
        GIAI_NHI,
        GIAI_BA,
        GIAI_BON,
        GIAI_NAM,
        GIAI_SAU,
        GIAI_BAY
    };

    private Map<Giai, List<String> > mapGiai2Value;
    private Date date;

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    void themGiai(Giai giai, String value) {
        mapGiai2Value.get(giai).add(value);
    }

    String giaiDB() {
        return getGiai(Giai.GIAI_DB).get(0);
    }
    
    String giaiNhat() {
        return getGiai(Giai.GIAI_NHAT).get(0);
    }

    List<String> getGiai(Giai giai) {        
        for (Map.Entry<Giai, List<String> > entry : mapGiai2Value.entrySet())
            if(entry.getKey() == giai)
                return entry.getValue();
        return new ArrayList<String>();
    }
    
    List<String> getAllListGiai() {
        List<String> results = new ArrayList<String>();
        results.addAll(getGiai(Giai.GIAI_DB));
        results.addAll(getGiai(Giai.GIAI_NHAT));
        results.addAll(getGiai(Giai.GIAI_NHI));
        results.addAll(getGiai(Giai.GIAI_BA));
        results.addAll(getGiai(Giai.GIAI_BON));
        results.addAll(getGiai(Giai.GIAI_NAM));
        results.addAll(getGiai(Giai.GIAI_SAU));
        results.addAll(getGiai(Giai.GIAI_BAY));
        
        return results;
    }
    
    List<String> getAllListGiaiNormalized() {
        List<String> results = getAllListGiai();
        for(int i = 0; i < results.size(); i ++) {
            String string = (String)results.get(i);
            results.set(i, string.substring(string.length() - 2));
        }
        return results;
    }
    
    public void save() {
        try {
            new LotteryTableDAO().insert(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
