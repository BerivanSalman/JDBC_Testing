package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import manage.QueryManage;
import org.junit.Assert;
import utilities.JDBCReusableMethods;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {

    QueryManage queryManage = new QueryManage(); //obje olusturduk
    String query;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    int rowCount;
    Faker faker= new Faker();

    int id;
    String version;
    String updateLog;
    int supportMessageID;
    int support_message_id;
    String attachment;


    @Given("The database connection is created.")
    public void the_database_connection_is_created() {
        JDBCReusableMethods.getConnection();
        //ConfigProperties'ten istediği bilgileri alır database e gider bağlantı kurar ve bağlantıyı dönmüş olur
    }
    @Given("Query01 is prepared and executed.")
    public void is_prepared_and_executed(String string) throws SQLException {
        query = queryManage.getQuery01(); // query mizi query manage file ından aldık
        resultSet = JDBCReusableMethods.getStatement().executeQuery(query); // bu cağırdıgımız queryi execute ederiz ve bu bir Select sorgusu oldugu için resultSet dondurur.
        //Update, delete vs sorgusu olsaydı 'int ' yazardik buraya
    }
    @Given("ResultSet01 is processed.")
    public void are_processed(String string) throws SQLException {
        //doğrulama yapariz burda
        resultSet.next(); //donen resultSetin basına koydum ben bunu
        int actualUserId = resultSet.getInt("user_id"); //user_id columndaki int degeri getirir
        //resultsetle databaseden donen bilgiyi actual olarak aldık
        //Burasi bana user_id columndaki 1. satırdaki veriyi getirir
        int expectedUserId = 1;
        Assert.assertEquals(expectedUserId,actualUserId);

    }
    @Given("The database connection is closed.")
    public void the_database_connection_is_closed() {
    JDBCReusableMethods.closeConnection();
    }

    // ----------------- query02------------

    @Given("Query02 is prepared and executed.")
    public void query02_is_prepared_and_executed() throws SQLException {
        query = queryManage.getQuery02();
        resultSet = JDBCReusableMethods.getStatement().executeQuery(query);

    }
    @Given("ResultSet02 is processed.")
    public void result_set02_is_processed() throws SQLException {
        //2 tane kayit dondugu için listle yaparız
        List<String> isimler = new ArrayList<>(); //String deger dondugu için string yazdık
        while(resultSet.next()) {
            String isim = resultSet.getString("name");
            isimler.add(isim);
            // isimler listesine 62. satırdan gelen ismi ekle. Bitene kadar while calısır
        }
        List<String> expectedName = new ArrayList<>();
        expectedName.add("5 Minutes");
        expectedName.add("10 Minutes");

        for(int i = 0; i < isimler.size(); i++){
            assertEquals(expectedName.get(i),isimler.get(i));
            System.out.println(i +". index dogrulandi");
            /*
            List<String> isimler = new ArrayList<>(); ile, veritabanı sorgusundan dönen isimleri saklamak için bir liste oluşturulur.
            while (resultSet.next()) { ... } döngüsü, resultSet içindeki her satırı okur ve name sütunundaki değerleri isimler listesine ekler.
            List<String> expectedName = new ArrayList<>(); ile beklenen isimleri saklamak için bir liste oluşturulur.
           expectedName.add("5 Minutes"); ve expectedName.add("10 Minutes"); ile, bu listeye iki beklenen isim eklenir.
           for (int i = 0; i < isimler.size(); i++) { ... } döngüsü, isimler listesindeki her bir öğeyi dolaşır.
           assertEquals(expectedName.get(i), isimler.get(i)); ile, her bir isimler öğesi ilgili expectedName öğesiyle karşılaştırılır.
           Eğer değerler eşit değilse, test başarısız olur.
           System.out.println(i + ". index doğrulandı"); ile, her doğrulama adımının geçtiği konsola yazdırılır.
             */
        }
    }
    // ----------------------query03---------------
    @Given("Query03 is prepared and executed.")
    public void query03_is_prepared_and_executed() throws SQLException {
        query = queryManage.getQuery03();
        resultSet = JDBCReusableMethods.getStatement().executeQuery(query);
    }
    @Given("ResultSet03 is processed.")
    public void result_set03_is_processed() throws SQLException {
        String expectedName= "Mehmet Genç";
        resultSet.next();
        String actualName = resultSet.getString("firstname")+ " " + resultSet.getString("lastname");

        assertEquals(expectedName,actualName);
        System.out.println("expected= " + expectedName);
        System.out.println("actual= " + actualName);

    }
    // ------------------query04-----------------
    @Given("Query04 is prepared and executed")
    public void query04_is_prepared_and_executed() throws SQLException {
        query = queryManage.getQuery04();
       resultSet =JDBCReusableMethods.getStatement().executeQuery(query);
    }
    @Given("ResultSet04 is proceed.")
    public void result_set04_is_proceed() throws SQLException {
        // sonuçları yazdırmamız lazım
        while(resultSet.next()){
            String kullanici_id = resultSet.getString("user_id");
            String browserOS = resultSet.getString("browser_os");

            System.out.println("kullanici_id " + kullanici_id);
            System.out.println("Browser & OS " + browserOS);
    }
    }
    // ----------------------QUERY(05) (statement ile) ---------------------------
    @Given("Update Query05 is prepared and executed.")
    public void query05_is_prepared_and_executed() throws SQLException {
        query = queryManage.getUpdatequery05();
         rowCount = JDBCReusableMethods.updateQuery(query);
        // rowCount yapılan degisiklikten kac satir etkilenmis ona bakarız

    }
    @Given("ResultSet05 is processed.")
    public void result_set05_is_processed() throws Exception {
        int expectedRowCount = 18;
        assertEquals(expectedRowCount, rowCount);
        //yukarda row counta atamıstık ya actual degeri icinde tutuyor zaten

    }
    // -----------------------QUERY(05) (Prepared Statement)--------
    @Given("Update preparedQuery05 is prepared and executed.")
    public void update_query06_is_prepared_and_executed() throws SQLException {
        query = queryManage.getPreparedquery05();
        preparedStatement = JDBCReusableMethods.getConnection().prepareStatement(query);
        // sen bu prepared statementi query ile birlikte calistir. PreparedStatement, SQL sorgularını önceden derleyip saklar
        // ve bu sayede performansı artırır
        // ve SQL enjeksiyon saldırılarına karşı güvenliği artırır.
        preparedStatement.setInt(1,4444444); // querydeki 1. soru işareti için
        preparedStatement.setString(2, "%e_"); // querydeki 2. soru işareti için
        //yukarda hazırladık aşagıda da preparedStatement.executeUpdate(); ile queryimizi göndereceğiz
        rowCount = preparedStatement.executeUpdate();
    }
    @Given("preparedResultSet05 is processed.")
    public void result_set06_is_processed() {
        assertEquals(18, rowCount);
    }
    // -----------------------QUERY(06) (Prepared Statement)--------
    @Given("Update preparedQuery06 is prepared and executed.")
    public void update_prepared_query06_is_prepared_and_executed() throws SQLException {
      query = queryManage.getPreparedQuery06();
      preparedStatement = JDBCReusableMethods.getConnection().prepareStatement(query);
        // INSERT INTO admin_password_resets (id,email,token,status) VALUES (?,?,?,?);
        preparedStatement.setInt(1,1090);
        preparedStatement.setString(2,"email53@gmail.com");
        preparedStatement.setString(3,"128478");
        preparedStatement.setInt(4,1);
        rowCount = preparedStatement.executeUpdate(); // yukardaki değişikliği yaptıktan sonra queryi gonderiyoruz
        // ve sonuc olarak bize dönen etkilenen satır sayisini rowCounta atarız
    }
    @Given("preparedResultSet06 is processed.")
    public void prepared_result_set06_is_processed() {
        assertEquals(1,rowCount);
    }
    // -----------------------QUERY(07) (Prepared Statement)--------
    @Given("Update preparedQuery07 is prepared and executed.")
    public void update_prepared_query07_is_prepared_and_executed() throws SQLException {
        query = queryManage.getPreparedQuery07();
        preparedStatement = JDBCReusableMethods.getConnection().prepareStatement(query);
        // "Update admin_notifications Set is_read = ? Where id = ?";
        preparedStatement.setInt(1,1);
        preparedStatement.setInt(2,773);
        rowCount = preparedStatement.executeUpdate();

    }
    @Given("preparedResultSet07 is processed.")
    public void prepared_result_set07_is_processed() {
        assertEquals(1,rowCount);
    }
    // -----------------------QUERY(08) (Prepared Statement)--------
    @Given("Update_logs tablosuna insert query hazirlanir ve calistirilir.")
    public void update_logs_tablosuna_insert_query_hazirlanir_ve_calistirilir() throws SQLException {
        //private String preparedQuery08Insert = "insert into update_logs (id,version,update_log,created_at) values(?,?,?,?)";
        //Faker dan rastgele degerler ürettirdik cünkü herkes ayni parojede çalışırken sorun olmasin diye.
        // Herkes aynı anda aynİ degeri değistirip silerse sorun olur
        query= queryManage.getPreparedQuery08Insert();
        preparedStatement = JDBCReusableMethods.getConnection().prepareStatement(query);
        id = faker.number().numberBetween(450,550);
        version = faker.options().option("Windows 10", "MacOs Ventura", "LinUx");
        updateLog = faker.lorem().sentence(1); // insert deger

        preparedStatement.setInt(1,id);
        preparedStatement.setString(2,version);
        preparedStatement.setString(3,updateLog);
        preparedStatement.setDate(4, Date.valueOf(LocalDate.now()));
        rowCount = preparedStatement.executeUpdate();

        System.out.println("updateLOG: "+ updateLog);
        System.out.println("version: "+ version);
        System.out.println("id: "+ id);
        int flag=0;
        if (rowCount > 0) {
            flag++;
        }
        rowCount = 0;
        assertEquals(1, flag);
    }

    @Given("update_logs tablosuna insert edilen datanin update log degeri degistirilir")
    public void update_logs_tablosuna_insert_edilen_datanin_update_log_degeri_degistirilir() throws SQLException {
        // private String preparedQuery08Update = "UPDATE update_logs set update_log = ? Where version = ? and id = ?";
        query= queryManage.getPreparedQuery08Update();
        String updatelogYeni = "yeni log"; // faker dan gelen insert degerini yeni log olarak degistirmek istiyorum.

        preparedStatement = JDBCReusableMethods.getConnection().prepareStatement(query);
        preparedStatement.setString(1,updatelogYeni);
        preparedStatement.setString(2,version);
        preparedStatement.setInt(3,id);
        rowCount = preparedStatement.executeUpdate();

        System.out.println("id: "+id);
    }
    @Given("update log degerinin degistigi dogrulanir")
    public void update_log_degerinin_degistigi_dogrulanir() {
        assertEquals(1,rowCount);
    }
    // -----------------------QUERY(09) (Prepared Statement)--------
    @Given("update_logs tablosuna insert edilen data silinir.")
    public void update_logs_tablosuna_insert_edilen_data_silinir() throws SQLException {
        query = queryManage.getPreparedQuery09Delete();
        preparedStatement = JDBCReusableMethods.getConnection().prepareStatement(query);
        preparedStatement.setInt(1,id);
        rowCount = preparedStatement.executeUpdate();
        System.out.println("silinen id; "+ id);
    }
    @Given("Satirin silindigi dogrulanir")
    public void satirin_silindigi_dogrulanir() {
        assertEquals(1,rowCount);
    }
    // -----------------------QUERY(10) (Prepared Statement)--------
    @Given("support_attachments tablosuna insert query hazirlanir ve calistirilir.")
    public void support_attachments_tablosuna_insert_query_hazirlanir_ve_calistirilir() throws SQLException {
        //  private String preparedQuery10Insert= "INSERT INTO support_attachments (id, support_message_id, attachment, created_at) VALUES(?, ?, ?, ?)";
        query = queryManage.getPreparedQuery10Insert();
        preparedStatement = JDBCReusableMethods.getConnection().prepareStatement(query);
        id = faker.number().numberBetween(50,500);
        support_message_id = faker.number().numberBetween(100,400);



        preparedStatement.setInt(1,id);
        preparedStatement.setInt(2,support_message_id);
        preparedStatement.setString(3,"658401a61409c1703149990.png");
        preparedStatement.setDate(4, Date.valueOf(LocalDate.now()));
        rowCount = preparedStatement.executeUpdate();
        System.out.println("id:  "+ id);
        System.out.println("supportMessageID:  "+ supportMessageID);

    }
    @Given("support_attachments tablosuna insert edilen data silinir.")
    public void support_attachments_tablosuna_insert_edilen_data_silinir() throws SQLException {
        // private String preparedQuery10Delete= "Delete from u168183796_qaloantec.support_attachments where support_message_id = ?";
        query = queryManage.getPreparedQuery10Delete();
        preparedStatement = JDBCReusableMethods.getConnection().prepareStatement(query);
        preparedStatement.setInt(1,support_message_id);
        rowCount = preparedStatement.executeUpdate();
        System.out.println("silinen datanin support message id'si : " + supportMessageID);
    }
}
