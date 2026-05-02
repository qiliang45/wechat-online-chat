# MySQL安装指南

## 下载MySQL

1. 访问：https://dev.mysql.com/downloads/mysql/
2. 选择：Windows (x86, 64-bit), MSI Installer
3. 点击 Download

## 安装MySQL

1. 双击下载的 `.msi` 文件
2. 选择 **Full** 安装类型
3. 设置root密码（请记住！）
4. 完成安装

## 配置MySQL

### 类型和网络步骤
1. Config Type：**Development Computer**
2. Port：3306（保持默认）
3. 勾选：? Show advanced options

### 账户和角色步骤
1. 设置MySQL Root密码（例如：`Wechat123!`）
2. 点击 Next

### 应用配置
1. 点击 Execute 应用设置
2. 等待安装完成

## 启动MySQL服务

MySQL服务应该自动启动。如果没有：

```bash
net start MySQL
```

## 创建数据库

打开MySQL命令行客户端或任何MySQL客户端，运行：

```sql
CREATE DATABASE wechat_clone CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

## 配置应用

编辑 `backend/src/main/resources/application.yml`：

```yaml
spring:
  datasource:
    password: your_mysql_password  # 修改为你的密码
```

## 常见问题

### 'mysql' 无法识别
如果 `mysql` 命令找不到，使用完整路径：
```
"C:\Program Files\MySQL\MySQL Server 9.7\bin\mysql.exe" -u root -p
```

### 端口3306已被占用
检查哪个进程在使用3306端口：
```bash
netstat -ano | findstr :3306
```

### 访问被拒绝错误
请确保使用的是安装时设置的正确root密码。
