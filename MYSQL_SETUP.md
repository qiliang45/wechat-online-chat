# MySQL Setup Guide

## Download MySQL

1. Visit: https://dev.mysql.com/downloads/mysql/
2. Select: Windows (x86, 64-bit), MSI Installer
3. Click Download

## Install MySQL

1. Double-click the downloaded `.msi` file
2. Choose **Full** installation type
3. Set root password (remember it!)
4. Complete installation

## Configure MySQL

### Type and Networking Step
1. Config Type: **Development Computer**
2. Port: 3306 (keep default)
3. Check: ? Show advanced options

### Accounts and Roles Step
1. Set MySQL Root Password (e.g., `Wechat123!`)
2. Click Next

### Apply Configuration
1. Click Execute to apply settings
2. Wait for installation to complete

## Start MySQL Service

MySQL service should start automatically. If not:

```bash
net start MySQL
```

## Create Database

Open MySQL Command Line Client or any MySQL client and run:

```sql
CREATE DATABASE wechat_clone CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

## Configure Application

Edit `backend/src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    password: your_mysql_password  # Change this
```

## Troubleshooting

### 'mysql' is not recognized
If `mysql` command is not found, use full path:
```
"C:\Program Files\MySQL\MySQL Server 9.7\bin\mysql.exe" -u root -p
```

### Port 3306 is already in use
Check what process is using port 3306:
```bash
netstat -ano | findstr :3306
```

### Access denied error
Make sure you're using the correct root password set during installation.
