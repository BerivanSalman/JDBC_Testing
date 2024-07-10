package stepDefinitions;

import io.cucumber.java.en.Given;
import manage.QueryManage;
import org.junit.Assert;
import utilities.JDBCReusableMethods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {

    QueryManage queryManage = new QueryManage(); //obje olusturduk
    String query;
    ResultSet resultSet;
    int rowCount;

    @Given("The database connection is created.")
    public void the_database_connection_is_created() {
        JDBCReusableMethods.getConnection();
        //ConfigProperties'ten istediği bilgileri alır database e gider bağlantı kurar ve bağlantıyı dönmüş olur
    }
    @Given("Query01 is prepared and executed.")
    public void is_prepared_and_executed(String string) throws SQLException {
        query = queryManage.getQuery01(); // query mizi query manage dosyasından aldık
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
}
