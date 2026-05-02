<template>
  <div class="login-container">
    <div class="login-box">
      <h1 class="title">&#x5728;&#x7EBF;&#x804A;&#x5929;</h1>
      <form @submit.prevent="handleLogin">
        <div class="form-group">
          <label>&#x624B;&#x673A;&#x53F7;</label>
          <input
            v-model="form.phone"
            type="tel"
            placeholder="&#x8BF7;&#x8F93;&#x5165;&#x624B;&#x673A;&#x53F7;"
            class="form-input"
          />
        </div>
        <div class="form-group">
          <label>&#x5BC6;&#x7801;</label>
          <input
            v-model="form.password"
            type="password"
            placeholder="&#x8BF7;&#x8F93;&#x5165;&#x5BC6;&#x7801;"
            class="form-input"
          />
        </div>
        <button type="submit" class="login-btn">&#x767B;&#x5F55;</button>
      </form>
      <p class="register-link">
        &#x8FD8;&#x6CA1;&#x6709;&#x8D26;&#x53F7;&#xFF1F;<a href="/register"
          >&#x7ACB;&#x5373;&#x6CE8;&#x518C;</a
        >
      </p>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from "vue";
import axios from "../utils/axios";
import { useRouter } from "vue-router";

const router = useRouter();
const form = reactive({
  phone: "",
  password: "",
});

const handleLogin = async () => {
  try {
    const response = await axios.post("/auth/login", form);
    localStorage.setItem("token", response.token);
    localStorage.setItem("user", JSON.stringify(response.user));
    router.push("/");
  } catch (error) {
    alert(
      error.response?.data?.error ||
        String.fromCodePoint(0x767b, 0x5f55, 0x5931, 0x8d25),
    );
  }
};
</script>

<style scoped>
.login-container {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.login-box {
  background: white;
  padding: 40px;
  border-radius: 12px;
  box-shadow: 0 10px 40px rgba(0, 0, 0, 0.1);
  width: 350px;
}

.title {
  text-align: center;
  font-size: 32px;
  color: #07c160;
  margin-bottom: 30px;
}

.form-group {
  margin-bottom: 20px;
}

.form-group label {
  display: block;
  margin-bottom: 8px;
  font-weight: 500;
  color: #333;
}

.form-input {
  width: 100%;
  padding: 12px 15px;
  border: 1px solid #ddd;
  border-radius: 8px;
  font-size: 14px;
  transition: border-color 0.3s;
}

.form-input:focus {
  outline: none;
  border-color: #07c160;
}

.login-btn {
  width: 100%;
  padding: 14px;
  background: #07c160;
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  cursor: pointer;
  margin-top: 10px;
  transition: background 0.3s;
}

.login-btn:hover {
  background: #06ad56;
}

.register-link {
  text-align: center;
  margin-top: 20px;
  color: #666;
}

.register-link a {
  color: #07c160;
  text-decoration: none;
}

.register-link a:hover {
  text-decoration: underline;
}
</style>
