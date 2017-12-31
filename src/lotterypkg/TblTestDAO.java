/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lotterypkg;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author PHIVH
 */
public class TblTestDAO extends TableDAO {

    public java.util.Date date;
    public String giaiDB;
    public String giaiNhat;
    public String giaiNhi;
    public String giaiBa;
    public String giaiBon;
    public String giaiNam;
    public String giaiSau;
    public String giaiBay;  
    
    public TblTestDAO() {
        setTableName("tbl_test");
    }

    @Override
    public void save() {
        PreparedStatement stmt = null;
        try {
            stmt = DBConnector.getInstance().getConnection().prepareStatement("INSERT INTO " + getTableName() + " (date,giai_db,giai_nhat,giai_nhi,giai_ba,giai_bon,giai_nam,giai_sau,giai_bay) "
                                                                             + "VALUES(?,?,?,?,?,?,?,?,?)");
            stmt.setObject(1, date);
            stmt.setString(2, giaiDB);
            stmt.setString(3, giaiNhat);
            stmt.setString(4, giaiNhi);
            stmt.setString(5, giaiBa);
            stmt.setString(6, giaiBon);
            stmt.setString(7, giaiNam);
            stmt.setString(8, giaiSau);
            stmt.setString(9, giaiBay);
            
            stmt.execute();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<LotteryTable> selectAll() {
        List<LotteryTable> results = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = DBConnector.getInstance().getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + getTableName() + " ORDER BY date");
            
            while (rs.next()) {
                LotteryTable table = new LotteryTable();
                date = (Date) rs.getObject("date");
                giaiDB = rs.getString("giai_db");
                giaiNhat = rs.getString("giai_nhat");
                giaiNhi = rs.getString("giai_nhi");
                giaiBa = rs.getString("giai_ba");
                giaiBon = rs.getString("giai_bon");
                giaiNam = rs.getString("giai_nam");
                giaiSau = rs.getString("giai_sau");
                giaiBay = rs.getString("giai_bay");
                
                table.setDate(date);
                table.themGiai(LotteryTable.Giai.GIAI_DB, giaiDB);
                table.themGiai(LotteryTable.Giai.GIAI_NHAT, giaiNhat);
                
                String[] arrGiaiNhi = giaiNhi.split(";");
                table.themGiai(LotteryTable.Giai.GIAI_NHI, arrGiaiNhi[0]);
                table.themGiai(LotteryTable.Giai.GIAI_NHI, arrGiaiNhi[1]);
                
                String[] arrGiaiBa = giaiBa.split(";");
                table.themGiai(LotteryTable.Giai.GIAI_BA, arrGiaiBa[0]);
                table.themGiai(LotteryTable.Giai.GIAI_BA, arrGiaiBa[1]);
                table.themGiai(LotteryTable.Giai.GIAI_BA, arrGiaiBa[2]);
                table.themGiai(LotteryTable.Giai.GIAI_BA, arrGiaiBa[3]);
                table.themGiai(LotteryTable.Giai.GIAI_BA, arrGiaiBa[4]);
                table.themGiai(LotteryTable.Giai.GIAI_BA, arrGiaiBa[5]);
                
                String[] arrGiaiBon = giaiBon.split(";");
                table.themGiai(LotteryTable.Giai.GIAI_BON, arrGiaiBon[0]);
                table.themGiai(LotteryTable.Giai.GIAI_BON, arrGiaiBon[1]);
                table.themGiai(LotteryTable.Giai.GIAI_BON, arrGiaiBon[2]);
                table.themGiai(LotteryTable.Giai.GIAI_BON, arrGiaiBon[3]);
                
                String[] arrGiaiNam = giaiNam.split(";");
                table.themGiai(LotteryTable.Giai.GIAI_NAM, arrGiaiNam[0]);
                table.themGiai(LotteryTable.Giai.GIAI_NAM, arrGiaiNam[1]);
                table.themGiai(LotteryTable.Giai.GIAI_NAM, arrGiaiNam[2]);
                table.themGiai(LotteryTable.Giai.GIAI_NAM, arrGiaiNam[3]);
                table.themGiai(LotteryTable.Giai.GIAI_NAM, arrGiaiNam[4]);
                table.themGiai(LotteryTable.Giai.GIAI_NAM, arrGiaiNam[5]);
                
                String[] arrGiaiSau = giaiSau.split(";");
                table.themGiai(LotteryTable.Giai.GIAI_SAU, arrGiaiSau[0]);
                table.themGiai(LotteryTable.Giai.GIAI_SAU, arrGiaiSau[1]);
                table.themGiai(LotteryTable.Giai.GIAI_SAU, arrGiaiSau[2]);
                
                String[] arrGiaiBay = giaiBay.split(";");
                table.themGiai(LotteryTable.Giai.GIAI_BAY, arrGiaiBay[0]);
                table.themGiai(LotteryTable.Giai.GIAI_BAY, arrGiaiBay[1]);
                table.themGiai(LotteryTable.Giai.GIAI_BAY, arrGiaiBay[2]);
                table.themGiai(LotteryTable.Giai.GIAI_BAY, arrGiaiBay[3]);
                
                results.add(table);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return results;
    }    
}
