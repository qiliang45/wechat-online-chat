---
noteId: "323b8ad1463511f19e210b84e7e0c4ac"
tags: []

---

# MySQL��װָ��

## ����MySQL

1. ���ʣ�https://dev.mysql.com/downloads/mysql/
2. ѡ��Windows (x86, 64-bit), MSI Installer
3. ��� Download

## ��װMySQL

1. ˫�����ص� .msi �ļ�
2. ѡ�� Full ��װ����
3. ����root���루���ס��
4. ��ɰ�װ

## ����MySQL

### ���ͺ����粽��
1. Config Type��Development Computer
2. Port��3306������Ĭ�ϣ�
3. ��ѡ��Show advanced options

### �˻��ͽ�ɫ����
1. ����MySQL Root���루���磺Wechat123!��
2. ��� Next

### Ӧ������
1. ��� Execute Ӧ������
2. �ȴ���װ���

## ����MySQL����

MySQL����Ӧ���Զ����������û�У�

```
net start MySQL
```

## �������ݿ�

��MySQL�����пͻ��˻��κ�MySQL�ͻ��ˣ����У�

```sql
CREATE DATABASE wechat_clone CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

## ����Ӧ��

�༭ backend/src/main/resources/application.yml��

```yaml
spring:
  datasource:
    password: your_mysql_password  # �޸�Ϊ�������
```

## ��������

### mysql �޷�ʶ��
��� mysql �����Ҳ�����ʹ������·����
```
C:\Program Files\MySQL\MySQL Server 9.7\bin\mysql.exe -u root -p
```

### �˿�3306�ѱ�ռ��
����ĸ�������ʹ��3306�˿ڣ�
```
netstat -ano | findstr :3306
```

### ���ʱ��ܾ�����
��ȷ��ʹ�õ��ǰ�װʱ���õ���ȷroot���롣
