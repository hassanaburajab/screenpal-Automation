# Screencast Web Application — Automated Test Suite

Selenium + TestNG automation project for [ScreenPal](https://screenpal.com), covering authentication, profile management, and video upload functionality.

---

## Project Structure

```
src/
├── base/
├──---- main/
│   │   └── BaseTest.java      # Shared setup: driver, wait, login, logout
│   ├── LoginPage.java         # Login page actions
│   ├── UploadPage.java        # Upload page actions
│   └── ProfilePage.java       # Profile page actions
└── test/
    ├── AuthFlowTest.java      # Login / logout / invalid credentials
    ├── UploadTest.java        # Video upload, delete, share
    └── ProfileTest.java       # View and update profile
```

---

## Test Coverage

### Module 1 — Authentication (`AuthFlowTest`)
| # | Test Case | Expected Result |
|---|-----------|----------------|
| 1 | Login with valid credentials | User is logged in successfully |
| 2 | Logout | User is redirected to screenpal.com |
| 3 | Login with invalid password | Error message is displayed |

### Module 2 — Profile Management (`ProfileTest`)
| # | Test Case | Expected Result |
|---|-----------|----------------|
| 1 | Open profile page | Profile page loads successfully |
| 2 | Update display name | Success message contains "update" |

### Module 3 — Video Upload (`UploadTest`)
| # | Test Case | Expected Result |
|---|-----------|----------------|
| 1 | Upload valid MP4 video | Video appears in library |
| 2 | Upload unsupported file type | Error message is displayed |
| 3 | Delete uploaded video | Video is removed from library |
| 4 | Share uploaded video | Share link is returned |

---

## Tech Stack

| Tool | Version |
|------|---------|
| Language | Java 21 |
| Browser | Google Chrome |
| Automation | Selenium WebDriver 4.20.0 |
| Framework | TestNG 7.11.0 |
| IDE | Eclipse |
| Build Tool | Maven |
| OS | Windows 10 |

---

## Prerequisites

- Java 21+
- Maven
- Google Chrome + matching ChromeDriver on PATH

# Clone the repo
git clone(https://github.com/hassanaburajab/screenpal-Automation)
cd screencast-tests

## Configuration

Update credentials in `BaseTest.java` before running:

## Architecture

All test classes extend `BaseTest`, which handles the shared lifecycle:

BaseTest
│   @BeforeClass  setup()    → launches ChromeDriver
│   login()                  → navigates and authenticates
│   logout()                 → opens profile menu and signs out
│   @AfterClass   tearDown() → quits the browser
│
├── AuthFlowTest   (no extra setup needed)
├── UploadTest     (adds UploadPage)
└── ProfileTest    (adds ProfilePage)

## Deliverables

- `Test_Plan.docx` — scope, strategy, environment, entry/exit criteria
- `Test_Cases.xlsx` — detailed test cases per module
- `test_report_.docx` — execution results and defect summary
