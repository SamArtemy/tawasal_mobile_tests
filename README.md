# Локальный запуск:
### Установить:
* [Allure](https://docs.qameta.io/allure/#_commandline) - Для генерации отчета
* [Maven](https://maven.apache.org/download.cgi) - Менеджмент зависимостей
* [JDK11](https://openjdk.java.net/projects/jdk/11/) - OpenJDK 11
* [Sqlite3](https://www.sqlite.org/download.html) - SQL database engine
* [Appium](http://appium.io/) - Appium server

### Перед тестами:
1. Поднять Appium server, с включенной перезаписью сессии `--session-override`
2. Поднять эмуляторы, селеноиды, подключить реальные девайсы в общем подготовить окружение в котором будут исполнятся тесты

### Запуск тестов с дефолтным тестовым набором 

`mvn clean test`

### Запуск тестов с конкретным тестовым набором

`mvn test -DdefaultSuite="YourSuiteName.xml"`

или с указанием пути от корня проекта

`mvn test -Dsuite="src/test/resources/suites/YourSuiteName.xml"`

### Генерация отчета после прохождения тестов

`allure serve target/allure-results`

В конце будет что-то вроде:

![pic](https://docs.qameta.io/allure/images/tab_overview.png)

---

## Как конфигурировать тестовые наборы:

* [TestNG](https://testng.org/doc/documentation-main.html#testng-xml) - Документация для testng.xml

Хранятся в src/test/resources/suites/

В thread-count указывается количество потоков

В parallel способ распареллеливания, менять нельзя т.к есть тесты зависимые внутри класса

## Как использовать Appium inspector

* [Appium Inspector](https://developers.perfectomobile.com/display/PD/Appium+Inspector) - Документация Appium Inspector

Капабилити хранятся в Utils.PlatformConfig и в src/test/resources/capabilities