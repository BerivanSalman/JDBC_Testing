Feature: Update , Delete, Insert QUERY

  Background:
    * The database connection is created.
@query05
    Scenario: "users" tablosunda sondan bir önceki harfi e olan
    "usernamelerin" "mobile" numarasını update ediniz.

      * Update Query05 is prepared and executed.
      * ResultSet05 is processed.
      * The database connection is closed.