/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lotterypkg;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author PHIVH
 */
public class LotteryDataHTMLParser {
    List<LotteryTable> parseData(String fileName) throws IOException, ParseException {
        List<LotteryTable> listTable = new ArrayList<LotteryTable>();

        File input = new File(fileName);
        Document doc = Jsoup.parse(input, "UTF-8");

        Elements tableKQList = doc.select("table#result_tab_mb");

        for (Element tableKQ : tableKQList) {
            LotteryTable table = new LotteryTable();

            String date = tableKQ.select("thead > tr > td:eq(0)").first().text().split("8")[0] + 8;
//            String date = tableKQ.select("thead > tr > td:eq(0)").first().text().split("KTTG")[0];

            String giaiDB = tableKQ.select("tbody > tr:eq(0) > td:eq(1)").text();

            String giaiNhat = tableKQ.select("tbody > tr:eq(1) > td:eq(1)").text();

            String giaiNhi1 = tableKQ.select("tbody > tr:eq(2) > td:eq(1)").text();
            String giaiNhi2 = tableKQ.select("tbody > tr:eq(2) > td:eq(2)").text();

            String giaiBa1 = tableKQ.select("tbody > tr:eq(3) > td:eq(1)").text();
            String giaiBa2 = tableKQ.select("tbody > tr:eq(3) > td:eq(2)").text();
            String giaiBa3 = tableKQ.select("tbody > tr:eq(3) > td:eq(3)").text();
            String giaiBa4 = tableKQ.select("tbody > tr:eq(4) > td:eq(1)").text();
            String giaiBa5 = tableKQ.select("tbody > tr:eq(4) > td:eq(2)").text();
            String giaiBa6 = tableKQ.select("tbody > tr:eq(4) > td:eq(3)").text();

            String giaiBon1 = tableKQ.select("tbody > tr:eq(5) > td:eq(1)").text();
            String giaiBon2 = tableKQ.select("tbody > tr:eq(5) > td:eq(2)").text();
            String giaiBon3 = tableKQ.select("tbody > tr:eq(5) > td:eq(3)").text();
            String giaiBon4 = tableKQ.select("tbody > tr:eq(5) > td:eq(4)").text();

            String giaiNam1 = tableKQ.select("tbody > tr:eq(6) > td:eq(1)").text();
            String giaiNam2 = tableKQ.select("tbody > tr:eq(6) > td:eq(2)").text();
            String giaiNam3 = tableKQ.select("tbody > tr:eq(6) > td:eq(3)").text();
            String giaiNam4 = tableKQ.select("tbody > tr:eq(7) > td:eq(1)").text();
            String giaiNam5 = tableKQ.select("tbody > tr:eq(7) > td:eq(2)").text();
            String giaiNam6 = tableKQ.select("tbody > tr:eq(7) > td:eq(3)").text();

            String giaiSau1 = tableKQ.select("tbody > tr:eq(8) > td:eq(1)").text();
            String giaiSau2 = tableKQ.select("tbody > tr:eq(8) > td:eq(2)").text();
            String giaiSau3 = tableKQ.select("tbody > tr:eq(8) > td:eq(3)").text();

            String giaiBay1 = tableKQ.select("tbody > tr:eq(9) > td:eq(1)").text();
            String giaiBay2 = tableKQ.select("tbody > tr:eq(9) > td:eq(2)").text();
            String giaiBay3 = tableKQ.select("tbody > tr:eq(9) > td:eq(3)").text();
            String giaiBay4 = tableKQ.select("tbody > tr:eq(9) > td:eq(4)").text();

            table.themGiai(LotteryTable.Giai.GIAI_DB, giaiDB);
            table.themGiai(LotteryTable.Giai.GIAI_NHAT, giaiNhat);
            table.themGiai(LotteryTable.Giai.GIAI_NHI, giaiNhi1);
            table.themGiai(LotteryTable.Giai.GIAI_NHI, giaiNhi2);

            table.themGiai(LotteryTable.Giai.GIAI_BA, giaiBa1);
            table.themGiai(LotteryTable.Giai.GIAI_BA, giaiBa2);
            table.themGiai(LotteryTable.Giai.GIAI_BA, giaiBa3);
            table.themGiai(LotteryTable.Giai.GIAI_BA, giaiBa4);
            table.themGiai(LotteryTable.Giai.GIAI_BA, giaiBa5);
            table.themGiai(LotteryTable.Giai.GIAI_BA, giaiBa6);

            table.themGiai(LotteryTable.Giai.GIAI_BON, giaiBon1);
            table.themGiai(LotteryTable.Giai.GIAI_BON, giaiBon2);
            table.themGiai(LotteryTable.Giai.GIAI_BON, giaiBon3);
            table.themGiai(LotteryTable.Giai.GIAI_BON, giaiBon4);

            table.themGiai(LotteryTable.Giai.GIAI_NAM, giaiNam1);
            table.themGiai(LotteryTable.Giai.GIAI_NAM, giaiNam2);
            table.themGiai(LotteryTable.Giai.GIAI_NAM, giaiNam3);
            table.themGiai(LotteryTable.Giai.GIAI_NAM, giaiNam4);
            table.themGiai(LotteryTable.Giai.GIAI_NAM, giaiNam5);
            table.themGiai(LotteryTable.Giai.GIAI_NAM, giaiNam6);

            table.themGiai(LotteryTable.Giai.GIAI_SAU, giaiSau1);
            table.themGiai(LotteryTable.Giai.GIAI_SAU, giaiSau2);
            table.themGiai(LotteryTable.Giai.GIAI_SAU, giaiSau3);

            table.themGiai(LotteryTable.Giai.GIAI_BAY, giaiBay1);
            table.themGiai(LotteryTable.Giai.GIAI_BAY, giaiBay2);
            table.themGiai(LotteryTable.Giai.GIAI_BAY, giaiBay3);
            table.themGiai(LotteryTable.Giai.GIAI_BAY, giaiBay4);

            String[] dateSplit = date.split(" ");
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            table.setDate(df.parse(dateSplit[dateSplit.length - 1]));
            listTable.add(table);
        }

        return listTable;
    }
    
    public static void main(String[] args) {
        try {
            new LotteryDataHTMLParser().parseData("test.html");
        } catch (IOException ex) {
            Logger.getLogger(LotteryDataHTMLParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(LotteryDataHTMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
