---
noteId: "23e29960463511f19e210b84e7e0c4ac"
tags: []

---

# ��������Ӧ��

һ������΢�ŵ���������Ӧ�ã��������Ӻ��ѡ�����Ⱥ�ĺ�ʵʱ��Ϣ���ܡ�

## ����ջ

### ǰ��
- Vue.js 3
- Vue Router
- Axios��HTTP�ͻ��ˣ�
- Stomp.js + SockJS��WebSocket��

### ���
- Spring Boot 3.2
- Spring Security
- MyBatis-Plus
- WebSocket��STOMP��

### ���ݿ�
- MySQL

## ��Ŀ�ṹ

```
wechatjava/
������ backend/              # Spring Boot ���
��   ������ src/main/java/com/example/wechat/
��   ��   ������ controller/   # REST������
��   ��   ������ service/      # ҵ���߼�
��   ��   ������ mapper/       # MyBatis Mapper
��   ��   ������ entity/       # ����ʵ��
��   ��   ������ dto/          # ���ݴ������
��   ��   ������ config/       # ������
��   ��   ������ util/         # ������
��   ������ src/main/resources/
��   ��   ������ application.yml
��   ��   ������ schema.sql
��   ������ pom.xml
������ frontend/             # Vue ǰ��
��   ������ src/
��   ��   ������ views/        # ҳ�����
��   ��   ������ router/       # ·������
��   ��   ������ utils/        # ������
��   ��   ������ App.vue
��   ��   ������ main.js
��   ������ index.html
��   ������ package.json
��   ������ vite.config.js
������ start.bat
������ README.md
```

## �����б�

- �û�ע�᣺�ֻ��� + �û��� + ����
- �û���¼���ֻ��� + ����
- ���Ӻ��ѣ����ֻ��Ż��û�������
- �������󣺽��ջ�ܾ�����
- ����Ⱥ�ģ��Ӻ����б�ѡ���Ա����Ⱥ
- ������Ϣ���ı���Ϣ��ͼƬ��Ϣ
- ʵʱ���죺����WebSocket�ļ�ʱͨѶ

## ���ٿ�ʼ

### 1. ����Ҫ��

- JDK 21+
- Maven 3.8+
- Node.js 18+
- MySQL 8.0+

### 2. ���ݿ�����

�������ݿ⣺
```sql
CREATE DATABASE wechat_clone CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

�޸����ݿ����룺
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/wechat_clone
    username: your_username
    password: your_password
```

### 3. ��װ����

ǰ�ˣ�
```bash
cd frontend
npm install
```

### 4. ����Ӧ��

��ˣ�
```bash
cd backend
mvn spring-boot:run
```

ǰ�ˣ�
```bash
cd frontend
npm run dev
```

### 5. ���ʵ�ַ

- ǰ�ˣ�http://localhost:5173
- ���API��http://localhost:8080

## API�ӿ�

### ��֤
- POST /api/auth/register - ע��
- POST /api/auth/login - ��¼

### �û�
- GET /api/users/{id} - ��ȡ�û���Ϣ
- GET /api/users/search?keyword=xxx - �����û�
- GET /api/users/friends?userId=xxx - ��ȡ�����б�

### ����
- POST /api/friends/request - ���ͺ�������
- POST /api/friends/request/{id}/accept - ͬ������
- POST /api/friends/request/{id}/reject - �ܾ�����
- GET /api/friends/requests - ��ȡ����������

### Ⱥ��
- POST /api/groups - ����Ⱥ��
- GET /api/groups?userId=xxx - ��ȡ�û���Ⱥ���б�
- GET /api/groups/{id} - ��ȡȺ����Ϣ

### ��Ϣ
- POST /api/messages - ������Ϣ
- GET /api/messages/friend?userId=xxx&friendId=xxx - ��ȡ������Ϣ
- GET /api/messages/group/{id} - ��ȡȺ����Ϣ

### �ļ�
- POST /api/files/upload - �ϴ��ļ�

## WebSocket

���ӵ�ַ��ws://localhost:8080/ws

�������⣺
- /topic/user/{userId} - �û���Ϣ
- /topic/group/{groupId} - Ⱥ����Ϣ

��Ϣ��ʽ��
```javascript
// ʾ��
{
  senderId: 1,
  receiverId: 2,      // ˽��ʱʹ��
  groupId: null,      // Ⱥ��ʱʹ��
  content: "���",
  type: 0             // 0:�ı�, 1:ͼƬ
}
```

## ����֤

MIT
