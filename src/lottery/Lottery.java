/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lottery;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import lottery.Table;

/**
 *
 * @author PHIVH
 */
public class Lottery {

    List<Table> parseData(String fileName) throws IOException, ParseException {
        List<Table> listTable = new ArrayList<Table>();

        File input = new File(fileName);
        Document doc = Jsoup.parse(input, "UTF-8");

        Elements tableKQList = doc.select("table#result_tab_mb");

        for (Element tableKQ : tableKQList) {
            Table table = new Table();

            String date = tableKQ.select("thead > tr > td").first().text();

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

            table.themGiai(Table.Giai.GIAI_DB, giaiDB);
            table.themGiai(Table.Giai.GIAI_NHAT, giaiNhat);
            table.themGiai(Table.Giai.GIAI_NHI, giaiNhi1);
            table.themGiai(Table.Giai.GIAI_NHI, giaiNhi2);

            table.themGiai(Table.Giai.GIAI_BA, giaiBa1);
            table.themGiai(Table.Giai.GIAI_BA, giaiBa2);
            table.themGiai(Table.Giai.GIAI_BA, giaiBa3);
            table.themGiai(Table.Giai.GIAI_BA, giaiBa4);
            table.themGiai(Table.Giai.GIAI_BA, giaiBa5);
            table.themGiai(Table.Giai.GIAI_BA, giaiBa6);

            table.themGiai(Table.Giai.GIAI_BON, giaiBon1);
            table.themGiai(Table.Giai.GIAI_BON, giaiBon2);
            table.themGiai(Table.Giai.GIAI_BON, giaiBon3);
            table.themGiai(Table.Giai.GIAI_BON, giaiBon4);

            table.themGiai(Table.Giai.GIAI_NAM, giaiNam1);
            table.themGiai(Table.Giai.GIAI_NAM, giaiNam2);
            table.themGiai(Table.Giai.GIAI_NAM, giaiNam3);
            table.themGiai(Table.Giai.GIAI_NAM, giaiNam4);
            table.themGiai(Table.Giai.GIAI_NAM, giaiNam5);
            table.themGiai(Table.Giai.GIAI_NAM, giaiNam6);

            table.themGiai(Table.Giai.GIAI_SAU, giaiSau1);
            table.themGiai(Table.Giai.GIAI_SAU, giaiSau2);
            table.themGiai(Table.Giai.GIAI_SAU, giaiSau3);

            table.themGiai(Table.Giai.GIAI_BAY, giaiBay1);
            table.themGiai(Table.Giai.GIAI_BAY, giaiBay2);
            table.themGiai(Table.Giai.GIAI_BAY, giaiBay3);
            table.themGiai(Table.Giai.GIAI_BAY, giaiBay4);

            String[] dateSplit = date.split(" ");
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            table.setDate(df.parse(dateSplit[dateSplit.length - 1]));
            listTable.add(table);
        }

        return listTable;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        try {

            Lottery lottery = new Lottery();
            List<Table> listTable = lottery.parseData("test.html");

            for (Table table : listTable) {
                System.out.println(table.getDate());
                System.out.println(table.giaiDB());
                System.out.println(table.giaiNhat());
            }
            
//            File input = new File("test.html");
//            Document doc = Jsoup.parse(input, "UTF-8");
//            Element divKq = doc.select("table#result_tab_mb").first();
//            String date = divKq.select("thead > tr > td").first().text();
//
//            String giaiDB = divKq.select("tbody > tr:eq(0) > td:eq(1)").text();
//
//            String giaiNhat = divKq.select("tbody > tr:eq(1) > td:eq(1)").text();
//
//            String giaiNhi1 = divKq.select("tbody > tr:eq(2) > td:eq(1)").text();
//            String giaiNhi2 = divKq.select("tbody > tr:eq(2) > td:eq(2)").text();
//
//            String giaiBa1 = divKq.select("tbody > tr:eq(3) > td:eq(1)").text();
//            String giaiBa2 = divKq.select("tbody > tr:eq(3) > td:eq(2)").text();
//            String giaiBa3 = divKq.select("tbody > tr:eq(3) > td:eq(3)").text();
//            String giaiBa4 = divKq.select("tbody > tr:eq(4) > td:eq(1)").text();
//            String giaiBa5 = divKq.select("tbody > tr:eq(4) > td:eq(2)").text();
//            String giaiBa6 = divKq.select("tbody > tr:eq(4) > td:eq(3)").text();
//
//            String giaiBon1 = divKq.select("tbody > tr:eq(5) > td:eq(1)").text();
//            String giaiBon2 = divKq.select("tbody > tr:eq(5) > td:eq(2)").text();
//            String giaiBon3 = divKq.select("tbody > tr:eq(5) > td:eq(3)").text();
//            String giaiBon4 = divKq.select("tbody > tr:eq(5) > td:eq(4)").text();
//
//            String giaiNam1 = divKq.select("tbody > tr:eq(6) > td:eq(1)").text();
//            String giaiNam2 = divKq.select("tbody > tr:eq(6) > td:eq(2)").text();
//            String giaiNam3 = divKq.select("tbody > tr:eq(6) > td:eq(3)").text();
//            String giaiNam4 = divKq.select("tbody > tr:eq(7) > td:eq(1)").text();
//            String giaiNam5 = divKq.select("tbody > tr:eq(7) > td:eq(2)").text();
//            String giaiNam6 = divKq.select("tbody > tr:eq(7) > td:eq(3)").text();
//
//            String giaiSau1 = divKq.select("tbody > tr:eq(8) > td:eq(1)").text();
//            String giaiSau2 = divKq.select("tbody > tr:eq(8) > td:eq(2)").text();
//            String giaiSau3 = divKq.select("tbody > tr:eq(8) > td:eq(3)").text();
//
//            String giaiBay1 = divKq.select("tbody > tr:eq(9) > td:eq(1)").text();
//            String giaiBay2 = divKq.select("tbody > tr:eq(9) > td:eq(2)").text();
//            String giaiBay3 = divKq.select("tbody > tr:eq(9) > td:eq(3)").text();
//            String giaiBay4 = divKq.select("tbody > tr:eq(9) > td:eq(4)").text();
//
//            String[] dateSplit = date.split(" ");
//            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//            
////            table.setDate(df.parse(dateSplit[dateSplit.length - 1]));
//
//            System.out.println(df.parse(dateSplit[dateSplit.length - 1]).toString());
//            System.out.println(giaiDB);
//            System.out.println(giaiNhat);
//            System.out.format("%s %s\n", giaiNhi1, giaiNhi2);
//            System.out.format("%s %s %s %s %s %s\n", giaiBa1, giaiBa2, giaiBa3, giaiBa4, giaiBa5, giaiBa6);
//            System.out.format("%s %s %s %s\n", giaiBon1, giaiBon2, giaiBon3, giaiBon4);
//            System.out.format("%s %s %s %s %s %s\n", giaiNam1, giaiNam2, giaiNam3, giaiNam4, giaiNam5, giaiNam6);
//            System.out.format("%s %s %s %s\n", giaiBay1, giaiBay2, giaiBay3, giaiBay4);
//
//            System.out.println(divKq);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
