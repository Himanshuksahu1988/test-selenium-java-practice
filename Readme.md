# Selenium Java TestNG Practice Framework

This repository contains Selenium WebDriver automation practice using Java, Maven, TestNG, and IntelliJ IDEA.

---

# Tech Stack

- Java 17
- Selenium WebDriver 4
- TestNG
- Maven
- IntelliJ IDEA
- Git & GitHub

---

# Project Setup Journey

## Step 1: Created Empty GitHub Repository

Repository:

https://github.com/Himanshuksahu1988/test-selenium-java-practice

Cloned locally:

```bash
git clone https://github.com/Himanshuksahu1988/test-selenium-java-practice.git
```

Output:

```text
warning: You appear to have cloned an empty repository.
```

This is expected because the repository was newly created and contained no files.

---

## Step 2: Opened Project in IntelliJ IDEA

Opened:

```text
D:\testAutomation\test-selenium-java-practice
```

---

## Step 3: Created Maven Project Structure

```text
test-selenium-java-practice
│
├── src
│   ├── main
│   │   └── java
│   │
│   └── test
│       └── java
│           └── tests
│               └── GoogleTest.java
│
├── pom.xml
├── testng.xml
├── README.md
└── .gitignore
```

---

## Step 4: Added Maven Dependencies

Dependencies added:

- Selenium Java
- TestNG

pom.xml contains:

```xml
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
</dependency>

<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
</dependency>
```

---

## Step 5: Created First Selenium Test

File:

```text
src/test/java/tests/GoogleTest.java
```

Code:

```java
package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class GoogleTest {

    @Test
    public void launchGoogle() {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com");

        System.out.println(driver.getTitle());

        driver.quit();
    }
}
```

---

## Step 6: Created TestNG Suite

File:

```text
testng.xml
```

Example:

```xml
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">

<suite name="Smoke Suite">

    <test name="Google Test">

        <classes>
            <class name="tests.GoogleTest"/>
        </classes>

    </test>

</suite>
```

---

## Step 7: IntelliJ Configuration Issue

Observed:

```text
pom.xml (Unknown)
```

Problem:

- IntelliJ did not recognize the project as a Maven project.
- TestNG Run option was not visible.

Resolution:

1. Added Maven support.
2. Reloaded Maven project.
3. Verified JDK configuration.
4. Confirmed TestNG dependencies were loaded.

After fixing:

- Run option became visible.
- TestNG test execution worked successfully.

---

## Step 8: Execute Tests

### Run from IntelliJ

Option 1:

- Open GoogleTest.java
- Click Green Run Icon

Option 2:

- Right-click Test Class
- Select Run

### Run Using Maven

```bash
mvn test
```

---

## Step 9: Git Commands Used

Check status:

```bash
git status
```

Stage files:

```bash
git add .
```

Commit changes:

```bash
git commit -m "Initial Selenium Framework Setup"
```

Push code:

```bash
git push origin main
```

---

# Current Learning Progress

Completed:

- GitHub Repository Creation
- Git Clone
- IntelliJ Setup
- Maven Setup
- Selenium Dependency Configuration
- TestNG Setup
- First Selenium Test
- Test Execution in IntelliJ
- Git Push Workflow

---

# Next Steps

Planned Framework Enhancements:

- BaseTest
- Driver Factory
- Page Object Model (POM)
- Utility Classes
- Config Reader
- Extent Reports
- Listeners
- Jenkins Integration
- Docker Execution
- AWS Integration
- Selenium Grid

---

# Interview Notes

### Difference Between git add, git commit and git push

| Command | Purpose |
|----------|----------|
| git add | Moves changes to staging area |
| git commit | Creates local repository snapshot |
| git push | Uploads commits to GitHub |

### Selenium Framework Components

- Maven
- TestNG
- WebDriver
- Page Object Model
- Reporting
- CI/CD Integration

---

Author: Himanshu K