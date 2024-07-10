package manage;

public class QueryManage {
    //bunlara kimse ulasamasın diye private hazırlıyoruz. Fakat diğer classda kullanmamız gerek. Kullanabilmemiz için Getter olusturuyoruz. O public'tir
    private String query01 = "SELECT user_id FROM u168183796_qaloantec.deposits WHERE amount between 100 and 500;";
    private String query02="SELECT name FROM u168183796_qaloantec.cron_schedules Limit 2;";
    private String query03="SELECT firstname, lastname FROM users WHERE id=11 and country_code!='TR';";



    //  ------------------------GETTER-----------------------
    public String getQuery01() {return query01;}
    public String getQuery02() {return query02;}
    public String getQuery03() {return query03;}

}
