---
noteId: "396075f1463511f19e210b84e7e0c4ac"
tags: []

---

# ��ϸ��װָ��

## ������װ����

### ����1����װJava��Maven

���� JAVA_MAVEN_SETUP.md �е�˵�����в���

### ����2����װMySQL

���� MYSQL_SETUP.md �е�˵�����в���

### ����3���������ݿ�

���ӵ�MySQL���������ݿ⣺
```sql
CREATE DATABASE wechat_clone CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### ����4���������ݿ�����

�༭ backend/src/main/resources/application.yml��
```yaml
spring:
  datasource:
    password: your_mysql_password
```

### ����5����װǰ������

```bash
cd frontend
npm install
```

### ����6������Ӧ��

������ˣ�
```bash
cd backend
mvn spring-boot:run
```

����ǰ�ˣ�
```bash
cd frontend
npm run dev
```

### ����7������Ӧ��

����������ʣ�http://localhost:5173

## ��Ҫ��װ·��

| ��� | ·�� |
|------|------|
| JDK 21 | C:\Program Files\Eclipse Adoptium\jdk-21.0.11.10-hotspot |
| Maven | C:\Program Files\Apache\maven |
| MySQL | C:\Program Files\MySQL\MySQL Server 9.7 |

## ������������

### Windows PowerShell����ʱ����������

```powershell
���û���
$env:JAVA_HOME='C:\Program Files\Eclipse Adoptium\jdk-21.0.11.10-hotspot'
$env:Path="$env:JAVA_HOME\bin;C:\Program Files\Apache\maven\bin;$env:Path"

�������
cd backend
mvn spring-boot:run

����ǰ�ˣ��´��ڣ�
cd frontend
npm install
npm run dev
```

## ��֤��װ

```bash
���Java
java -version

���Maven
mvn -version

���MySQL����
mysql -u root -p -e "SHOW DATABASES;"
```

## Ĭ������

- ��˶˿ڣ�8080
- ǰ�˶˿ڣ�5173
- MySQL�˿ڣ�3306
- WebSocket��ws://localhost:8080/ws
