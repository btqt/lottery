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
import lotterypkg.LotteryTable.Giai;

/**
 *
 * @author PHIVH
 */
public class TblSKQDAO extends TableDAO {

    public TblSKQDAO() {
        setTableName("tbl_so_ket_qua");
        
        date = new java.util.Date();
        giaiDB = "";
        giaiNhat = "";
        giaiNhi = "";
        giaiBa = "";
        giaiBon = "";
        giaiNam = "";
        giaiSau = "";
        giaiBay = "";
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
    
    public void insert(LotteryTable table) {
        try {
            date = table.getDate();
            giaiDB = table.giaiDB();
            giaiNhat = table.giaiNhat();
            
            List<String> listGiaiNhi = table.getGiai(Giai.GIAI_NHI);            
            for(String entry : listGiaiNhi)
                giaiNhi = giaiNhi + entry + ";";
            
            List<String> listGiaiBa = table.getGiai(Giai.GIAI_BA);            
            for(String entry : listGiaiBa)
                giaiBa = giaiBa + entry + ";";
            
            List<String> listGiaiBon = table.getGiai(Giai.GIAI_BON);            
            for(String entry : listGiaiBon)
                giaiBon = giaiBon + entry + ";";
            
            List<String> listGiaiNam = table.getGiai(Giai.GIAI_NAM);            
            for(String entry : listGiaiNam)
                giaiNam = giaiNam + entry + ";";
            
            List<String> listGiaiSau = table.getGiai(Giai.GIAI_SAU);            
            for(String entry : listGiaiSau)
                giaiSau = giaiSau + entry + ";";
            
            List<String> listGiaiBay = table.getGiai(Giai.GIAI_BAY);            
            for(String entry : listGiaiBay)
                giaiBay = giaiBay + entry + ";";
                        
            save();
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
                table.themGiai(Giai.GIAI_DB, giaiDB);
                table.themGiai(Giai.GIAI_NHAT, giaiNhat);
                
                String[] arrGiaiNhi = giaiNhi.split(";");
                table.themGiai(Giai.GIAI_NHI, arrGiaiNhi[0]);
                table.themGiai(Giai.GIAI_NHI, arrGiaiNhi[1]);
                
                String[] arrGiaiBa = giaiBa.split(";");
                table.themGiai(Giai.GIAI_BA, arrGiaiBa[0]);
                table.themGiai(Giai.GIAI_BA, arrGiaiBa[1]);
                table.themGiai(Giai.GIAI_BA, arrGiaiBa[2]);
                table.themGiai(Giai.GIAI_BA, arrGiaiBa[3]);
                table.themGiai(Giai.GIAI_BA, arrGiaiBa[4]);
                table.themGiai(Giai.GIAI_BA, arrGiaiBa[5]);
                
                String[] arrGiaiBon = giaiBon.split(";");
                table.themGiai(Giai.GIAI_BON, arrGiaiBon[0]);
                table.themGiai(Giai.GIAI_BON, arrGiaiBon[1]);
                table.themGiai(Giai.GIAI_BON, arrGiaiBon[2]);
                table.themGiai(Giai.GIAI_BON, arrGiaiBon[3]);
                
                String[] arrGiaiNam = giaiNam.split(";");
                table.themGiai(Giai.GIAI_NAM, arrGiaiNam[0]);
                table.themGiai(Giai.GIAI_NAM, arrGiaiNam[1]);
                table.themGiai(Giai.GIAI_NAM, arrGiaiNam[2]);
                table.themGiai(Giai.GIAI_NAM, arrGiaiNam[3]);
                table.themGiai(Giai.GIAI_NAM, arrGiaiNam[4]);
                table.themGiai(Giai.GIAI_NAM, arrGiaiNam[5]);
                
                String[] arrGiaiSau = giaiSau.split(";");
                table.themGiai(Giai.GIAI_SAU, arrGiaiSau[0]);
                table.themGiai(Giai.GIAI_SAU, arrGiaiSau[1]);
                table.themGiai(Giai.GIAI_SAU, arrGiaiSau[2]);
                
                String[] arrGiaiBay = giaiBay.split(";");
                table.themGiai(Giai.GIAI_BAY, arrGiaiBay[0]);
                table.themGiai(Giai.GIAI_BAY, arrGiaiBay[1]);
                table.themGiai(Giai.GIAI_BAY, arrGiaiBay[2]);
                table.themGiai(Giai.GIAI_BAY, arrGiaiBay[3]);
                
                results.add(table);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return results;
    }
    
    public java.util.Date date;
    public String giaiDB;
    public String giaiNhat;
    public String giaiNhi;
    public String giaiBa;
    public String giaiBon;
    public String giaiNam;
    public String giaiSau;
    public String giaiBay;    
}
