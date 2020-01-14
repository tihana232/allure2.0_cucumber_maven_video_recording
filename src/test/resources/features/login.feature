@1
Feature: LoginFeature feature

  @ORPHAN
  Scenario Outline: SuccessfulLogin
    Given User on Login page
    When Enters UserName as <email/telephone> and Password as <password>
    Then Message displayed Login Successfully
    
    Examples:
      | email/telephone  | password      |
      | 12345678         | qwert12345    |
      | 33333333         | paswword33333 |
      | 44444444         | paswword44444 |
