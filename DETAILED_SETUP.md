# 详细安装指南

## 完整安装步骤

### 步骤1：安装Java和Maven

按照 `JAVA_MAVEN_SETUP.md` 中的说明进行操作

### 步骤2：安装MySQL

按照 `MYSQL_SETUP.md` 中的说明进行操作

### 步骤3：创建数据库

连接到MySQL并创建数据库：
```sql
CREATE DATABASE wechat_clone CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 步骤4：配置数据库密码

编辑 `backend/src/main/resources/application.yml`：
```yaml
spring:
  datasource:
    password: your_mysql_password
```

### 步骤5：安装前端依赖

```bash
cd frontend
npm install
```

### 步骤6：启动应用

启动后端：
```bash
cd backend
mvn spring-boot:run
```

启动前端：
```bash
cd frontend
npm run dev
```

### 步骤7：访问应用

打开浏览器访问：http://localhost:5173

---

## 重要安装路径

| 组件 | 路径 |
|------|------|
| JDK 21 | C:\Program Files\Eclipse Adoptium\jdk-21.0.11.10-hotspot |
| Maven | C:\Program Files\Apache\maven |
| MySQL | C:\Program Files\MySQL\MySQL Server 9.7 |

---

## 快速启动命令

### Windows PowerShell（临时环境变量）

```powershell
# 设置环境
$env:JAVA_HOME='C:\Program Files\Eclipse Adoptium\jdk-21.0.11.10-hotspot'
$env:Path="$env:JAVA_HOME\bin;C:\Program Files\Apache\maven\bin;$env:Path"

# 启动后端
cd backend
mvn spring-boot:run

# 启动前端（新窗口）
cd frontend
npm install
npm run dev
```

---

## 验证安装

```bash
# 检查Java
java -version

# 检查Maven
mvn -version

# 检查MySQL连接
mysql -u root -p -e "SHOW DATABASES;"
```

---

## 默认配置

- 后端端口：8080
- 前端端口：5173
- MySQL端口：3306
- WebSocket：ws://localhost:8080/ws
