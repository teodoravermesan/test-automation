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
   - Collection: `postman/api-tests.json`
   - Environment: `postman/environment.json`
3. Set variables:
   - `baseUrl`: `http://localhost:3000/api`
4. Run collection using Collection Runner.

### ⚙️ Run via Newman CLI

```bash
cd postman
newman run api-tests.json -e environment.json
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

## 🛠️ Optional CI Integration

You can integrate tests with CI tools like GitHub Actions, Jenkins, or GitLab CI:

- API tests via Newman in CLI:
  ```bash
  newman run postman/api-tests.json -e postman/environment.json
  ```

---
