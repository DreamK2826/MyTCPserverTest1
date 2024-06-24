import java.util.Date;

/**
 * 用于生成表格的数据
 */

public class TableData {


    //用于储存采集点信息
    private String name;
    private String pos1;
    private String pos2;
    private Date dateNow;
    //用于储存AP强度信息
    private String AP01;
    private String AP02;
    private String AP03;
    private String AP04;
    private String AP05;
    private String AP06;
    private String AP07;
    private String AP08;
    private String AP09;
    private String AP10;
    private String AP11;
    private String AP12;
    private String AP13;
    private String AP14;

    public TableData() {}
    public TableData(String name, String pos1, String pos2, Date date, String AP01, String AP02, String AP03, String AP04, String AP05, String AP06, String AP07, String AP08, String AP09, String AP10, String AP11, String AP12, String AP13, String AP14) {
        this.name = name;
        this.pos1 = pos1;
        this.pos2 = pos2;
        dateNow = date;
        this.AP01 = AP01;
        this.AP02 = AP02;
        this.AP03 = AP03;
        this.AP04 = AP04;
        this.AP05 = AP05;
        this.AP06 = AP06;
        this.AP07 = AP07;
        this.AP08 = AP08;
        this.AP09 = AP09;
        this.AP10 = AP10;
        this.AP11 = AP11;
        this.AP12 = AP12;
        this.AP13 = AP13;
        this.AP14 = AP14;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPos1() {
        return pos1;
    }

    public void setPos1(String pos1) {
        this.pos1 = pos1;
    }

    public String getPos2() {
        return pos2;
    }

    public void setPos2(String pos2) {
        this.pos2 = pos2;
    }

    public Date getDateNow() {
        return dateNow;
    }

    public void setDateNow(Date dateNow) {
        this.dateNow = dateNow;
    }

    public String getAP01() {
        return AP01;
    }

    public void setAP01(String AP01) {
        this.AP01 = AP01;
    }

    public String getAP02() {
        return AP02;
    }

    public void setAP02(String AP02) {
        this.AP02 = AP02;
    }

    public String getAP03() {
        return AP03;
    }

    public void setAP03(String AP03) {
        this.AP03 = AP03;
    }

    public String getAP04() {
        return AP04;
    }

    public void setAP04(String AP04) {
        this.AP04 = AP04;
    }

    public String getAP05() {
        return AP05;
    }

    public void setAP05(String AP05) {
        this.AP05 = AP05;
    }

    public String getAP06() {
        return AP06;
    }

    public void setAP06(String AP06) {
        this.AP06 = AP06;
    }

    public String getAP07() {
        return AP07;
    }

    public void setAP07(String AP07) {
        this.AP07 = AP07;
    }

    public String getAP08() {
        return AP08;
    }

    public void setAP08(String AP08) {
        this.AP08 = AP08;
    }

    public String getAP09() {
        return AP09;
    }

    public void setAP09(String AP09) {
        this.AP09 = AP09;
    }

    public String getAP10() {
        return AP10;
    }

    public void setAP10(String AP10) {
        this.AP10 = AP10;
    }

    public String getAP11() {
        return AP11;
    }

    public void setAP11(String AP11) {
        this.AP11 = AP11;
    }

    public String getAP12() {
        return AP12;
    }

    public void setAP12(String AP12) {
        this.AP12 = AP12;
    }

    public String getAP13() {
        return AP13;
    }

    public void setAP13(String AP13) {
        this.AP13 = AP13;
    }

    public String getAP14() {
        return AP14;
    }

    public void setAP14(String AP14) {
        this.AP14 = AP14;
    }
}
