<template>
  <div class="main-container">
    <aside class="sidebar">
      <div class="sidebar-header">
        <h2>{{ String.fromCodePoint(0x5728, 0x7ebf, 0x804a, 0x5929) }}</h2>
        <button class="logout-btn" @click="handleLogout">
          {{ String.fromCodePoint(0x9000, 0x51fa) }}
        </button>
      </div>

      <div class="search-bar">
        <input
          v-model="searchKeyword"
          type="text"
          :placeholder="String.fromCodePoint(0x641c, 0x7d22, 0x2e, 0x2e, 0x2e)"
          class="search-input"
          @input="handleSearch"
        />
      </div>

      <div class="menu-tabs">
        <button
          v-for="tab in tabs"
          :key="tab.id"
          :class="['tab-btn', { active: activeTab === tab.id }]"
          @click="activeTab = tab.id"
        >
          {{ tab.name }}
        </button>
      </div>

      <div class="sidebar-actions">
        <button class="sidebar-action-btn" @click="showAddFriend = true">
          + {{ String.fromCodePoint(0x6dfb, 0x52a0, 0x597d, 0x53cb) }}
        </button>
        <button class="sidebar-action-btn" @click="showCreateGroup = true">
          + {{ String.fromCodePoint(0x521b, 0x5efa, 0x7fa4, 0x804a) }}
        </button>
      </div>

      <div class="chat-list">
        <div
          v-for="item in chatList"
          :key="item.id"
          :class="['chat-item', { active: selectedChat?.id === item.id }]"
          @click="selectChat(item)"
        >
          <div class="chat-avatar">
            {{ item.avatar || item.name.charAt(0) }}
          </div>
          <div class="chat-info">
            <div class="chat-name">{{ item.name }}</div>
            <div class="chat-preview">
              {{
                item.preview ||
                String.fromCodePoint(0x6682, 0x65e0, 0x6d88, 0x606f)
              }}
            </div>
          </div>
        </div>
      </div>
    </aside>

    <main class="chat-area" v-if="selectedChat">
      <div class="chat-header">
        <div class="chat-title">{{ selectedChat.name }}</div>
        <div class="chat-actions">
          <button class="action-btn" @click="showAddFriend = true">
            {{ String.fromCodePoint(0x6dfb, 0x52a0, 0x597d, 0x53cb) }}
          </button>
          <button class="action-btn" @click="showCreateGroup = true">
            {{ String.fromCodePoint(0x521b, 0x5efa, 0x7fa4, 0x804a) }}
          </button>
        </div>
      </div>

      <div class="message-list" ref="messageList">
        <div
          v-for="msg in messages"
          :key="msg.id"
          :class="['message-item', { self: msg.sender.id === currentUser.id }]"
        >
          <div class="message-avatar">{{ msg.sender.username.charAt(0) }}</div>
          <div class="message-content">
            <div v-if="msg.type === 0" class="text-message">
              {{ msg.content }}
            </div>
            <div v-else class="image-message">
              <img
                :src="msg.content"
                :alt="String.fromCodePoint(0x56fe, 0x7247, 0x6d88, 0x606f)"
              />
            </div>
          </div>
        </div>
      </div>

      <div class="input-area">
        <input
          type="file"
          accept="image/*"
          class="file-input"
          ref="fileInput"
          @change="handleFileSelect"
        />
        <button class="attach-btn" @click="triggerFileInput">+</button>
        <input
          v-model="inputMessage"
          type="text"
          :placeholder="
            String.fromCodePoint(
              0x8f93,
              0x5165,
              0x6d88,
              0x606f,
              0x2e,
              0x2e,
              0x2e,
            )
          "
          class="message-input"
          @keyup.enter="sendMessage"
        />
        <button class="send-btn" @click="sendMessage">
          {{ String.fromCodePoint(0x53d1, 0x9001) }}
        </button>
      </div>
    </main>

    <div class="empty-state" v-else>
      <div class="empty-icon">&#x2709;</div>
      <p>
        {{
          String.fromCodePoint(
            0x9009,
            0x62e9,
            0x4e00,
            0x4e2a,
            0x804a,
            0x5929,
            0x5f00,
            0x59cb,
            0x5bf9,
            0x8bdd,
          )
        }}
      </p>
    </div>

    <div
      class="modal-overlay"
      v-if="showAddFriend"
      @click="showAddFriend = false"
    >
      <div class="modal-content" @click.stop>
        <h3>{{ String.fromCodePoint(0x6dfb, 0x52a0, 0x597d, 0x53cb) }}</h3>
        <input
          v-model="addFriendKeyword"
          type="text"
          :placeholder="
            String.fromCodePoint(
              0x8f93,
              0x5165,
              0x624b,
              0x673a,
              0x53f7,
              0x6216,
              0x7528,
              0x6237,
              0x540d,
            )
          "
          class="search-input"
          @input="searchUsers"
        />
        <button class="search-btn" @click="searchUsers">
          {{ String.fromCodePoint(0x641c, 0x7d22) }}
        </button>
        <div class="search-results">
          <div
            v-if="searchResults.length === 0 && addFriendKeyword.trim()"
            class="no-results"
          >
            {{
              String.fromCodePoint(
                0x672a,
                0x627e,
                0x5230,
                0x76f8,
                0x5173,
                0x7528,
                0x6237,
              )
            }}
          </div>
          <div v-for="user in searchResults" :key="user.id" class="search-item">
            <span class="user-name">{{ user.username }}</span>
            <span class="user-phone">{{ user.phone }}</span>
            <button class="add-btn" @click="sendFriendRequest(user.id)">
              {{ String.fromCodePoint(0x6dfb, 0x52a0) }}
            </button>
          </div>
        </div>
        <button class="close-btn" @click="showAddFriend = false">
          {{ String.fromCodePoint(0x5173, 0x95ed) }}
        </button>
      </div>
    </div>

    <div
      class="modal-overlay"
      v-if="showCreateGroup"
      @click="showCreateGroup = false"
    >
      <div class="modal-content" @click.stop>
        <h3>{{ String.fromCodePoint(0x521b, 0x5efa, 0x7fa4, 0x804a) }}</h3>
        <input
          v-model="groupName"
          type="text"
          :placeholder="String.fromCodePoint(0x7fa4, 0x804a, 0x540d, 0x79f0)"
          class="search-input"
        />
        <div class="friend-list">
          <div
            v-for="friend in friends"
            :key="friend.id"
            :class="[
              'friend-item',
              { selected: selectedFriends.includes(friend.id) },
            ]"
            @click="toggleFriendSelection(friend.id)"
          >
            <input
              type="checkbox"
              :checked="selectedFriends.includes(friend.id)"
            />
            <span>{{ friend.username }}</span>
          </div>
        </div>
        <button class="create-btn" @click="createGroup">
          {{ String.fromCodePoint(0x521b, 0x5efa) }}
        </button>
        <button class="close-btn" @click="showCreateGroup = false">
          {{ String.fromCodePoint(0x5173, 0x95ed) }}
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, nextTick } from "vue";
import axios from "../utils/axios";
import { connect, sendMessage as socketSendMessage } from "../utils/socket";
import { useRouter } from "vue-router";

const router = useRouter();
const currentUser = ref(JSON.parse(localStorage.getItem("user")));
const activeTab = ref("chat");
const tabs = [
  { id: "chat", name: String.fromCodePoint(0x804a, 0x5929) },
  { id: "friends", name: String.fromCodePoint(0x901a, 0x8baf, 0x5f55) },
  { id: "groups", name: String.fromCodePoint(0x7fa4, 0x804a) },
];

const searchKeyword = ref("");
const chatList = ref([]);
const selectedChat = ref(null);
const messages = ref([]);
const inputMessage = ref("");
const messageList = ref(null);

const showAddFriend = ref(false);
const addFriendKeyword = ref("");
const searchResults = ref([]);

const showCreateGroup = ref(false);
const groupName = ref("");
const friends = ref([]);
const selectedFriends = ref([]);

const handleLogout = () => {
  localStorage.removeItem("token");
  localStorage.removeItem("user");
  router.push("/login");
};

const handleSearch = () => {
  loadChatList();
};

const loadChatList = async () => {
  try {
    if (activeTab.value === "chat") {
      chatList.value = [
        ...friends.value.map((f) => ({
          id: f.id,
          name: f.username,
          avatar: null,
          type: "friend",
        })),
        ...groups.value.map((g) => ({
          id: g.id,
          name: g.name,
          avatar: null,
          type: "group",
        })),
      ];
    } else if (activeTab.value === "friends") {
      const response = await axios.get(
        `/users/friends?userId=${currentUser.value.id}`,
      );
      friends.value = response;
      chatList.value = response.map((f) => ({
        id: f.id,
        name: f.username,
        avatar: null,
      }));
    } else if (activeTab.value === "groups") {
      const response = await axios.get(
        `/groups?userId=${currentUser.value.id}`,
      );
      groups.value = response;
      chatList.value = response.map((g) => ({
        id: g.id,
        name: g.name,
        avatar: null,
      }));
    }
  } catch (error) {
    console.error(
      String.fromCodePoint(0x52a0, 0x8f7d, 0x5217, 0x8868, 0x5931, 0x8d25),
      error,
    );
  }
};

const groups = ref([]);

const selectChat = async (item) => {
  selectedChat.value = item;
  messages.value = [];

  try {
    if (activeTab.value === "groups" || item.type === "group") {
      const response = await axios.get(`/messages/group/${item.id}`);
      messages.value = response;
    } else {
      const response = await axios.get(
        `/messages/friend?userId=${currentUser.value.id}&friendId=${item.id}`,
      );
      messages.value = response;
    }
    nextTick(() => {
      scrollToBottom();
    });
  } catch (error) {
    console.error(
      String.fromCodePoint(0x52a0, 0x8f7d, 0x6d88, 0x606f, 0x5931, 0x8d25),
      error,
    );
  }
};

const scrollToBottom = () => {
  if (messageList.value) {
    messageList.value.scrollTop = messageList.value.scrollHeight;
  }
};

const triggerFileInput = () => {
  document.querySelector(".file-input").click();
};

const handleFileSelect = async (event) => {
  const file = event.target.files[0];
  if (file) {
    const formData = new FormData();
    formData.append("file", file);

    try {
      const response = await axios.post("/files/upload", formData, {
        headers: { "Content-Type": "multipart/form-data" },
      });
      sendImageMessage(response);
    } catch (error) {
      console.error(
        String.fromCodePoint(0x4e0a, 0x4f20, 0x5931, 0x8d25),
        error,
      );
    }
  }
  event.target.value = "";
};

const sendMessage = () => {
  if (!inputMessage.value.trim() || !selectedChat.value) return;

  const message = {
    senderId: currentUser.value.id,
    content: inputMessage.value,
    type: 0,
  };

  if (selectedChat.value.type === "group" || activeTab.value === "groups") {
    message.groupId = selectedChat.value.id;
  } else {
    message.receiverId = selectedChat.value.id;
  }

  socketSendMessage(message);

  messages.value.push({
    id: Date.now(),
    sender: currentUser.value,
    content: inputMessage.value,
    type: 0,
    receiverId: message.receiverId,
    groupId: message.groupId,
  });

  inputMessage.value = "";
  nextTick(() => scrollToBottom());
};

const sendImageMessage = (imageUrl) => {
  if (!selectedChat.value) return;

  const message = {
    senderId: currentUser.value.id,
    content: imageUrl,
    type: 1,
  };

  if (selectedChat.value.type === "group" || activeTab.value === "groups") {
    message.groupId = selectedChat.value.id;
  } else {
    message.receiverId = selectedChat.value.id;
  }

  socketSendMessage(message);

  messages.value.push({
    id: Date.now(),
    sender: currentUser.value,
    content: imageUrl,
    type: 1,
    receiverId: message.receiverId,
    groupId: message.groupId,
  });

  nextTick(() => scrollToBottom());
};

const searchUsers = async () => {
  if (!addFriendKeyword.value.trim()) {
    searchResults.value = [];
    return;
  }

  try {
    console.log("Searching for:", addFriendKeyword.value);
    const response = await axios.get(
      `/users/search?keyword=${addFriendKeyword.value}`,
    );
    console.log("Search response:", response);
    searchResults.value = response.filter((u) => u.id !== currentUser.value.id);
    console.log("Filtered results:", searchResults.value);
  } catch (error) {
    console.error("Search error:", error);
    console.error("Error response:", error.response);
  }
};

const sendFriendRequest = async (userId) => {
  try {
    await axios.post(`/friends/request?userId=${currentUser.value.id}`, {
      targetUserId: userId,
    });
    alert(
      String.fromCodePoint(
        0x597d,
        0x53cb,
        0x8bf7,
        0x6c42,
        0x5df2,
        0x53d1,
        0x9001,
      ),
    );
    showAddFriend.value = false;
    addFriendKeyword.value = "";
  } catch (error) {
    alert(
      error.response?.data?.error ||
        String.fromCodePoint(0x53d1, 0x9001, 0x5931, 0x8d25),
    );
  }
};

const toggleFriendSelection = (friendId) => {
  const index = selectedFriends.value.indexOf(friendId);
  if (index > -1) {
    selectedFriends.value.splice(index, 1);
  } else {
    selectedFriends.value.push(friendId);
  }
};

const createGroup = async () => {
  if (!groupName.value.trim() || selectedFriends.value.length === 0) {
    alert(
      String.fromCodePoint(
        0x8bf7,
        0x8f93,
        0x5165,
        0x7fa4,
        0x540d,
        0x79f0,
        0x5e76,
        0x9009,
        0x62e9,
        0x6210,
        0x5458,
      ),
    );
    return;
  }

  try {
    await axios.post(`/groups?userId=${currentUser.value.id}`, {
      name: groupName.value,
      memberIds: selectedFriends.value,
    });
    alert(String.fromCodePoint(0x7fa4, 0x804a, 0x521b, 0x5efa, 0x6210, 0x529f));
    showCreateGroup.value = false;
    groupName.value = "";
    selectedFriends.value = [];
    loadChatList();
  } catch (error) {
    alert(
      error.response?.data?.error ||
        String.fromCodePoint(0x521b, 0x5efa, 0x5931, 0x8d25),
    );
  }
};

onMounted(() => {
  loadChatList();

  connect(currentUser.value.id, (message) => {
    messages.value.push(message);
    nextTick(() => scrollToBottom());
  });
});
</script>

<style scoped>
.main-container {
  display: flex;
  height: 100vh;
  background: #f5f5f5;
}

.sidebar {
  width: 350px;
  background: white;
  display: flex;
  flex-direction: column;
  border-right: 1px solid #e0e0e0;
}

.sidebar-header {
  padding: 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #e0e0e0;
}

.sidebar-header h2 {
  color: #07c160;
  font-size: 20px;
}

.logout-btn {
  padding: 6px 12px;
  background: #ff4d4f;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 12px;
}

.search-bar {
  padding: 15px;
}

.search-input {
  width: 100%;
  padding: 10px 15px;
  border: 1px solid #e0e0e0;
  border-radius: 20px;
  font-size: 14px;
  background: #f5f5f5;
}

.menu-tabs {
  display: flex;
  border-bottom: 1px solid #e0e0e0;
}

.tab-btn {
  flex: 1;
  padding: 12px;
  border: none;
  background: white;
  cursor: pointer;
  font-size: 14px;
  position: relative;
}

.tab-btn.active {
  color: #07c160;
}

.tab-btn.active::after {
  content: "";
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  width: 30px;
  height: 2px;
  background: #07c160;
}

.sidebar-actions {
  display: flex;
  gap: 10px;
  padding: 15px;
  border-bottom: 1px solid #e0e0e0;
}

.sidebar-action-btn {
  flex: 1;
  padding: 10px;
  background: #07c160;
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-size: 12px;
  transition: background 0.3s;
}

.sidebar-action-btn:hover {
  background: #06ad56;
}

.chat-list {
  flex: 1;
  overflow-y: auto;
}

.chat-item {
  display: flex;
  align-items: center;
  padding: 15px;
  cursor: pointer;
  border-bottom: 1px solid #f0f0f0;
  transition: background 0.2s;
}

.chat-item:hover {
  background: #f5f5f5;
}

.chat-item.active {
  background: #e8f5e9;
}

.chat-avatar {
  width: 48px;
  height: 48px;
  border-radius: 50%;
  background: #07c160;
  color: white;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 18px;
  margin-right: 12px;
}

.chat-info {
  flex: 1;
  overflow: hidden;
}

.chat-name {
  font-weight: 500;
  margin-bottom: 4px;
}

.chat-preview {
  font-size: 12px;
  color: #999;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.chat-area {
  flex: 1;
  display: flex;
  flex-direction: column;
  background: #e8e8e8;
}

.chat-header {
  padding: 15px 20px;
  background: white;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #e0e0e0;
}

.chat-title {
  font-size: 16px;
  font-weight: 500;
}

.action-btn {
  padding: 6px 12px;
  background: #07c160;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 12px;
  margin-left: 10px;
}

.message-list {
  flex: 1;
  padding: 20px;
  overflow-y: auto;
}

.message-item {
  display: flex;
  margin-bottom: 15px;
}

.message-item.self {
  flex-direction: row-reverse;
}

.message-item.self .message-content {
  background: #07c160;
  color: white;
}

.message-avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background: #ddd;
  color: white;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 14px;
  flex-shrink: 0;
}

.message-item.self .message-avatar {
  margin-left: 10px;
}

.message-item:not(.self) .message-avatar {
  margin-right: 10px;
}

.message-content {
  max-width: 70%;
  padding: 10px 15px;
  border-radius: 20px;
  background: white;
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
}

.text-message {
  word-break: break-all;
  font-size: 14px;
}

.image-message img {
  max-width: 200px;
  max-height: 200px;
  border-radius: 8px;
}

.input-area {
  padding: 15px;
  background: white;
  display: flex;
  align-items: center;
  gap: 10px;
}

.file-input {
  display: none;
}

.attach-btn {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  border: none;
  background: #f5f5f5;
  font-size: 20px;
  cursor: pointer;
}

.message-input {
  flex: 1;
  padding: 12px 15px;
  border: 1px solid #e0e0e0;
  border-radius: 20px;
  font-size: 14px;
}

.send-btn {
  padding: 10px 20px;
  background: #07c160;
  color: white;
  border: none;
  border-radius: 20px;
  cursor: pointer;
}

.empty-state {
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background: #e8e8e8;
}

.empty-icon {
  font-size: 60px;
  margin-bottom: 20px;
}

.empty-state p {
  color: #999;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
}

.modal-content {
  background: white;
  padding: 25px;
  border-radius: 12px;
  width: 400px;
  max-height: 80vh;
  overflow-y: auto;
}

.modal-content h3 {
  margin-bottom: 20px;
  color: #333;
}

.search-btn {
  width: 100%;
  padding: 10px;
  background: #07c160;
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  margin-top: 10px;
}

.no-results {
  text-align: center;
  padding: 20px;
  color: #999;
}

.search-results {
  margin-top: 15px;
}

.search-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  border-bottom: 1px solid #f0f0f0;
}

.user-name {
  font-weight: 500;
}

.user-phone {
  color: #999;
  font-size: 12px;
  margin-left: 10px;
}

.add-btn {
  padding: 4px 12px;
  background: #07c160;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 12px;
}

.friend-list {
  margin-top: 15px;
  max-height: 300px;
  overflow-y: auto;
}

.friend-item {
  display: flex;
  align-items: center;
  padding: 10px;
  border-bottom: 1px solid #f0f0f0;
  cursor: pointer;
}

.friend-item:hover {
  background: #f5f5f5;
}

.friend-item.selected {
  background: #e8f5e9;
}

.friend-item input {
  margin-right: 10px;
}

.create-btn {
  width: 100%;
  padding: 12px;
  background: #07c160;
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  margin-top: 20px;
}

.close-btn {
  width: 100%;
  padding: 12px;
  background: #f5f5f5;
  color: #333;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  margin-top: 10px;
}
</style>
