Feature: SELECT QUERY EXECUTE
    Background:
    * The database connection is created.
  @query01
  Scenario: Database içindeki "deposits" toblosunda "amount" değeri 100$ ile 500$ arasında olan user_id’leri doğrulayınız.
    * Query01 is prepared and executed.
    * ResultSet01 is processed.
    * The database connection is closed.
@query02
    Scenario: Database içindeki "cron_schedules" tablosunda ilk 2 kaydın "name" bilgisini doğrulayınız
      * Query02 is prepared and executed.
      * ResultSet02 is processed.
      * The database connection is closed.
@query03
  Scenario: Database üzerinde "users" tablosunda "country_code=TR" olmayan
   ve "id=11" olan datanın "firstname" ve "lastname" bilgilerini doğrulayınız
  . * Query03 is prepared and executed.
    * ResultSet03 is processed.
    * The database connection is closed.
  @query04
Scenario: user_logins tablosunda user_id lere gore sisteme giris yapilan
browser ve os leri gruplayip ekrana yazdiriniz
    * Query04 is prepared and executed
    * ResultSet04 is proceed.
    * The database connection is closed.
    @query05
    Scenario: