package manage;

public class QueryManage {
    //bunlara kimse ulasamasın diye private hazırlıyoruz. Fakat diğer classda kullanmamız gerek. Kullanabilmemiz için Getter olusturuyoruz. O public'tir
    private String query01 = "SELECT user_id FROM u168183796_qaloantec.deposits WHERE amount between 100 and 500;";
    private String query02="SELECT name FROM u168183796_qaloantec.cron_schedules Limit 2;";
    private String query03="SELECT firstname, lastname FROM users WHERE id=11 and country_code!='TR';";
    private  String query04 ="SELECT user_id, group_concat(browser, ' - ', os ) as browser_os FROM user_logins GROUP BY user_id;";
    private String updatequery05 =  "UPDATE users SET mobile= 33344333 where username like '%e_';";


    //  ------------------------GETTER-----------------------
    public String getQuery01() {return query01;}
    public String getQuery02() {return query02;}
    public String getQuery03() {return query03;}
    public String getQuery04() {return query04;}
    public String getUpdatequery05() {return updatequery05;}

}
