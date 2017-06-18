Feature: User

  As a admin of this system
  I should be able to get any user's info use user's name

  #noinspection NonAsciiCharacters
  Scenario Outline: Get user's info
    Given I use the user's name : <userName>
    When I request a user's info,is ROLE_ADMIN <isAdmin>
    Then I should get a response with HTTP status code : <status>
    And The response should contain the message: <containsMessage>
    Examples:
      |isAdmin | userName | status | containsMessage |
      |true    | admin    | 200    | admin           |
      |false   | admin    | 403    | 无权访问！       |
#      |true    | notExist | 404    | 这个页面不见了！  | because of https://github.com/spring-projects/spring-boot/issues/7321