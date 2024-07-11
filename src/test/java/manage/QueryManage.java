package manage;

public class QueryManage {
    //bunlara kimse ulasamasın diye private hazırlıyoruz. Fakat diğer classda kullanmamız gerek. Kullanabilmemiz için Getter olusturuyoruz. O public'tir
    private String query01 = "SELECT user_id FROM u168183796_qaloantec.deposits WHERE amount between 100 and 500;";
    private String query02="SELECT name FROM u168183796_qaloantec.cron_schedules Limit 2;";
    private String query03="SELECT firstname, lastname FROM users WHERE id=11 and country_code!='TR';";
    private  String query04 ="SELECT user_id, group_concat(browser, ' - ', os ) as browser_os FROM user_logins GROUP BY user_id;";
    private String updatequery05 =  "UPDATE users SET mobile= 33344333 where username like '%e_';";
    private String preparedquery05 =  "UPDATE users SET mobile= ? where username like ?;";
    private String preparedQuery06 = "INSERT INTO admin_password_resets (id,email,token,status) VALUES (?,?,?,?);";
    private String preparedQuery07 = "Update admin_notifications Set is_read = ? Where id = ?";
    private String preparedQuery08Insert = "insert into update_logs (id,version,update_log,created_at) values(?,?,?,?)";
    private String preparedQuery08Update = "UPDATE update_logs set update_log = ? Where version = ? and id = ?";
    private String preparedQuery09Delete= "DELETE FROM u168183796_qaloantec.update_logs WHERE id=?";
    private String preparedQuery10Insert= "INSERT INTO support_attachments (id, support_message_id, attachment, created_at) VALUES(?, ?, ?, ?)";
    private String preparedQuery10Delete= "Delete from u168183796_qaloantec.support_attachments where support_message_id = ?";


    //  ------------------------GETTER-----------------------
    public String getQuery01() {return query01;}
    public String getQuery02() {return query02;}
    public String getQuery03() {return query03;}
    public String getQuery04() {return query04;}
    public String getUpdatequery05() {return updatequery05;}
    public String getPreparedquery05() {return preparedquery05;}
    public String getPreparedQuery06() {return preparedQuery06;}
    public String getPreparedQuery07() {return preparedQuery07;}
    public String getPreparedQuery08Update() {return preparedQuery08Update;}
    public String getPreparedQuery08Insert() {return preparedQuery08Insert;}
    public String getPreparedQuery09Delete() {return preparedQuery09Delete;}
    public String getPreparedQuery10Insert() {return preparedQuery10Insert;}
    public String getPreparedQuery10Delete() {return preparedQuery10Delete;}
}
