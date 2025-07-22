# 🔍 Automation Testing – UI (Selenium) & API (Postman)

This repository includes automated test suites for a web application built with a React frontend and Node.js backend. It covers:

- ✅ UI Functional Testing using **Selenium WebDriver + Java + TestNG**
- ✅ API Testing using **Postman** and **Newman CLI**
- 📄 Includes test cases, reports, screenshots, and instructions

---

## 📁 Project Structure

```
.
├── ui-automation/                # Selenium UI tests
│   ├── pom.xml
│   ├── src/test/java/...
│   └── README.md                # UI-specific instructions
│
├── postman/                     # Postman API tests
│   ├── ui-api-tests.postman_collection.json
│   ├── environment.json
│   └── README.md                # API-specific instructions
│
└── README.md                    # Combined documentation
```

---

## 🧪 UI Automation – Selenium + Java

### 📌 Prerequisites

- Java 8+
- Maven
- Chrome browser
- ChromeDriver (via WebDriverManager)

### ⚙️ Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/teodoravermesan/test-automation.git
   cd ui-automation
   ```

2. Edit the config:
   - `src/test/resources/config.properties`:
     ```properties
     baseUrl=http://localhost:3000
     username=testuser
     password=testpass
     ```

3. Run the tests:
   ```bash
   mvn clean test
   ```

### 📂 Output

- 📄 Test report: `test-output/ExtentReport.html`
- 🖼️ Screenshots (on failure): `test-output/screenshots/`

Open the `ExtentReport.html` file in a browser to review results with logs and screenshots.

### ✅ UI Tests Included

- Login (valid & invalid credentials)
- Create new item
- Edit item
- Delete item
- Assert presence of items in UI

---

## 🔗 API Automation – Postman

### 📌 Prerequisites

- Postman
- (Optional) Newman CLI:
  ```bash
  npm install -g newman
  ```

### 🚀 Run via Postman GUI

1. Open Postman.
2. Import files:
   - Collection: `postman/ui-api-tests.postman_collection.json`
   - Environment: `postman/environment.json`
3. Set variables:
   - `baseUrl`: `http://localhost:3000/api`
4. Run collection using Collection Runner.

### ⚙️ Run via Newman CLI

```bash
cd postman
newman run ui-api-tests.postman_collection.json -e environment.json
```

### ✅ API Test Cases

- **POST /login** – valid & invalid credentials
- **GET /items** – with/without auth
- **POST /items** – valid/invalid payload
- **PUT /items/:id** – update with valid/invalid data
- **DELETE /items/:id** – delete with valid/invalid ID, missing auth

### 🔐 Auth Token Handling

- `POST /login` stores the JWT using:
  ```js
  pm.environment.set("authToken", pm.response.json().token);
  ```
- Used in future requests as:
  ```
  Authorization: Bearer {{authToken}}
  ```

---

## 📄 Test Plan Summary

| Area       | Coverage                                          |
|------------|---------------------------------------------------|
| UI Tests   | Login, Create, Edit, Delete item, UI Assertions   |
| API Tests  | Authentication, CRUD operations, Error handling   |
| Types      | Positive, Negative, Functional, Regression        |
| Tools      | Selenium, TestNG, Maven, Postman, Newman          |

---

## 🧱 Assumptions & Notes

- App must be running locally:
  - React frontend on `http://localhost:3000`
  - Node.js backend on `http://localhost:3000/api` or similar
- Test user must exist or be created in DB
- UI tests rely on stable selectors
- Screenshots are only saved for failed UI tests

---

## 🛠️ Optional CI Integration

You can integrate tests with CI tools like GitHub Actions, Jenkins, or GitLab CI:

- UI tests via Maven build step
- API tests via Newman in CLI:
  ```bash
  newman run postman/ui-api-tests.postman_collection.json -e postman/environment.json
  ```
