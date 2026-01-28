# Test Automation Framework
This framework using Java, Selenium, TestNG, Maven, POM and Appium 

## Project Overview
This project implements automated end-to-end testing using:
* **Selenium WebDriver**: 4.15.0
* **Maven**: 3.8+
* **SLF4J + Logback**: 1.4.14
* **TestNG**: 7.11.0
* **Appium Java Client**: 9.0.0
* **Allure Report**: 2.21.0
---

## Prerequisites
Before running the tests, ensure you have installed:
* **JDK 11** or higher.
* **Apache Maven 3.8.x**.
* **Browsers**: Google Chrome, Mozilla Firefox
* **Appium Server**: For mobile tests
* **Xcode**: Open the iOS simulator
* **iOS App**: https://github.com/appium/ios-test-app
---

## Configuration
The framework supports multiple environments (Dev, Stage). You can manage environment-specific variables in:

* src/test/resources/config/dev.properties

* src/test/resources/config/stage.properties

## How to Run Tests

### 1. Run All Tests
To execute all scenarios found in the tests directory:
```bash
mvn clean test
```

### 2. Run Specific Test File
```bash
mvn test -Dtest=CreateChallengeE2ETest
```

### 3. Run Test and generate report with Allure
```bash
mvn clean test -Dtest=CreateChallengeE2ETest allure:serve
```

### 4. Run Test mobile
Before run test with mobile, we should start the Appium server, open the iOS simulator.
```bash
 mvn test -Dtest=ComputeSumTest allure:serve
```
