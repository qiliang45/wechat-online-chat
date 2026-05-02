# Detailed Setup Guide

## Complete Installation Steps

### Step 1: Install Java and Maven

Follow the instructions in `JAVA_MAVEN_SETUP.md`

### Step 2: Install MySQL

Follow the instructions in `MYSQL_SETUP.md`

### Step 3: Create Database

Connect to MySQL and create the database:
```sql
CREATE DATABASE wechat_clone CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### Step 4: Configure Database Password

Edit `backend/src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    password: your_mysql_password
```

### Step 5: Install Frontend Dependencies

```bash
cd frontend
npm install
```

### Step 6: Start the Application

**Start Backend:**
```bash
cd backend
mvn spring-boot:run
```

**Start Frontend:**
```bash
cd frontend
npm run dev
```

### Step 7: Access the Application

Open browser: http://localhost:5173

---

## Important Installation Paths

| Component | Path |
|-----------|------|
| JDK 21 | C:\Program Files\Eclipse Adoptium\jdk-21.0.11.10-hotspot |
| Maven | C:\Program Files\Apache\maven |
| MySQL | C:\Program Files\MySQL\MySQL Server 9.7 |

---

## Quick Start Commands

### Windows PowerShell (with temporary environment)

```powershell
# Set environment
$env:JAVA_HOME='C:\Program Files\Eclipse Adoptium\jdk-21.0.11.10-hotspot'
$env:Path="$env:JAVA_HOME\bin;C:\Program Files\Apache\maven\bin;$env:Path"

# Start backend
cd backend
mvn spring-boot:run

# Start frontend (new window)
cd frontend
npm install
npm run dev
```

---

## Verify Installation

```bash
# Check Java
java -version

# Check Maven
mvn -version

# Check MySQL connection
mysql -u root -p -e "SHOW DATABASES;"
```

---

## Default Configuration

- Backend Port: 8080
- Frontend Port: 5173
- MySQL Port: 3306
- WebSocket: ws://localhost:8080/ws
