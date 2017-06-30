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
import java.util.EnumMap;
import java.util.HashMap;

/**
 *
 * @author PHIVH
 */
public class Table {

    public Table() {
        this.mapGiai2Value = new HashMap<Giai, String>();
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

    Map<Giai, String> mapGiai2Value;
    Date date;

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    void themGiai(Giai giai, String value) {
        mapGiai2Value.put(giai, value);
    }

    String giaiDB() {
        return getGiai(Giai.GIAI_DB).get(0);
    }
    
    String giaiNhat() {
        return getGiai(Giai.GIAI_NHAT).get(0);
    }

    List<String> getGiai(Giai giai) {
        List results = new ArrayList();
        
        for (Map.Entry<Giai, String> entry : mapGiai2Value.entrySet())
            if(entry.getKey() == giai)
                results.add(entry.getValue());
        return results;
    }
}
