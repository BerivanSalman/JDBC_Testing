Feature: Update , Delete, Insert QUERY

  Background:
    * The database connection is created.
@query05
    Scenario: "users" tablosunda sondan bir önceki harfi e olan
    "usernamelerin" "mobile" numarasını update ediniz.

      * Update Query05 is prepared and executed.
      * ResultSet05 is processed.
      * The database connection is closed.
  @query06
  Scenario: "users" tablosunda sondan bir önceki harfi e olan
  "usernamelerin" "mobile" numarasını update ediniz.

    * Update preparedQuery05 is prepared and executed.
    * preparedResultSet05 is processed.
    * The database connection is closed.
@query07
  Scenario: admin_password_resets tablosuna yeni (id,email,token,status,created_at datalarla)
  veri girisi yapiniz.
  * Update preparedQuery06 is prepared and executed.
  * preparedResultSet06 is processed.
  * The database connection is closed.
  @query08
  Scenario: admin_notifications tablosunda  "id=800" olan kullanıcının "is_read=0" olan bildirimlerini '1' Olarak Update edip doğrulayınız.
    * Update preparedQuery07 is prepared and executed.
    * preparedResultSet07 is processed.
    * The database connection is closed.
  @query09
  Scenario: "update_logs" tablosunda "version=? " ve "id=?" olan datanın "update_log"
  değerini update edip doğrulayınız.
    * Update_logs tablosuna insert query hazirlanir ve calistirilir.
    * update_logs tablosuna insert edilen datanin update log degeri degistirilir
    * update log degerinin degistigi dogrulanir
    * The database connection is closed.