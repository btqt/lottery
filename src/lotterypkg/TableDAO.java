/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lotterypkg;

import java.util.List;

/**
 *
 * @author PHIVH
 */
public abstract class TableDAO {
    private String tableName;

    public abstract void save();
    public abstract List<LotteryTable> selectAll();
    
    /**
     * @return the tableName
     */
    public final String getTableName() {
        return tableName;
    }

    /**
     * @param tableName the tableName to set
     */
    public final void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
