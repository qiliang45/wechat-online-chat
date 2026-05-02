# 在线聊天应用

一个类似微信的在线聊天应用，包含添加好友、创建群聊和实时消息功能。

## 技术栈

### 前端
- Vue.js 3
- Vue Router
- Axios（HTTP客户端）
- Stomp.js + SockJS（WebSocket）

### 后端
- Spring Boot 3.2
- Spring Security
- MyBatis-Plus
- WebSocket（STOMP）

### 数据库
- MySQL

## 项目结构

```
wechatjava/
├── backend/              # Spring Boot 后端
│   ├── src/main/java/com/example/wechat/
│   │   ├── controller/   # REST控制器
│   │   ├── service/      # 业务逻辑
│   │   ├── mapper/       # MyBatis Mapper
│   │   ├── entity/       # 数据实体
│   │   ├── dto/          # 数据传输对象
│   │   ├── config/       # 配置类
│   │   └── util/         # 工具类
│   ├── src/main/resources/
│   │   ├── application.yml
│   │   └── schema.sql
│   └── pom.xml
├── frontend/             # Vue 前端
│   ├── src/
│   │   ├── views/        # 页面组件
│   │   ├── router/       # 路由配置
│   │   ├── utils/        # 工具类
│   │   ├── App.vue
│   │   └── main.js
│   ├── index.html
│   ├── package.json
│   └── vite.config.js
├── start.bat
└── README.md
```

## 功能列表

- 用户注册：手机号 + 用户名 + 密码
- 用户登录：手机号 + 密码
- 添加好友：按手机号或用户名搜索
- 好友请求：接收或拒绝请求
- 创建群聊：从好友列表选择成员创建群
- 发送消息：文本消息和图片消息
- 实时聊天：基于WebSocket的即时通讯

## 快速开始

### 1. 环境要求

- JDK 21+
- Maven 3.8+
- Node.js 18+
- MySQL 8.0+

### 2. 数据库配置

创建数据库：
```sql
CREATE DATABASE wechat_clone CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

修改 `backend/src/main/resources/application.yml` 中的数据库密码：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/wechat_clone
    username: your_username
    password: your_password
```

### 3. 安装依赖

前端：
```bash
cd frontend
npm install
```

### 4. 启动应用

后端：
```bash
cd backend
mvn spring-boot:run
```

前端：
```bash
cd frontend
npm run dev
```

### 5. 访问地址

- 前端：http://localhost:5173
- 后端API：http://localhost:8080

## API接口

### 认证
- POST /api/auth/register - 注册
- POST /api/auth/login - 登录

### 用户
- GET /api/users/{id} - 获取用户信息
- GET /api/users/search?keyword=xxx - 搜索用户
- GET /api/users/friends?userId=xxx - 获取好友列表

### 好友
- POST /api/friends/request - 发送好友请求
- POST /api/friends/request/{id}/accept - 同意请求
- POST /api/friends/request/{id}/reject - 拒绝请求
- GET /api/friends/requests - 获取待处理请求

### 群聊
- POST /api/groups - 创建群聊
- GET /api/groups?userId=xxx - 获取用户的群聊列表
- GET /api/groups/{id} - 获取群聊信息

### 消息
- POST /api/messages - 发送消息
- GET /api/messages/friend?userId=xxx&friendId=xxx - 获取好友消息
- GET /api/messages/group/{id} - 获取群聊消息

### 文件
- POST /api/files/upload - 上传文件

## WebSocket

连接地址：`ws://localhost:8080/ws`

订阅主题：
- `/topic/user/{userId}` - 用户消息
- `/topic/group/{groupId}` - 群聊消息

消息格式：
```javascript
// 示例
{
  senderId: 1,
  receiverId: 2,      // 私聊时使用
  groupId: null,      // 群聊时使用
  content: "你好",
  type: 0             // 0:文本, 1:图片
}
```

## 许可证

MIT
