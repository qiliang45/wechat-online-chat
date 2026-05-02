<template>
  <div class="register-container">
    <div class="register-box">
      <h1 class="title">&#x7528;&#x6237;&#x6CE8;&#x518C;</h1>
      <form @submit.prevent="handleRegister">
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
          <label>&#x7528;&#x6237;&#x540D;</label>
          <input
            v-model="form.username"
            type="text"
            placeholder="&#x8BF7;&#x8F93;&#x5165;&#x7528;&#x6237;&#x540D;"
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
        <button type="submit" class="register-btn">&#x6CE8;&#x518C;</button>
      </form>
      <p class="login-link">
        &#x5DF2;&#x6709;&#x8D26;&#x53F7;&#xFF1F;<a href="/login"
          >&#x7ACB;&#x5373;&#x767B;&#x5F55;</a
        >
      </p>
    </div>
  </div>
</template>

<script setup>
import { reactive } from "vue";
import axios from "../utils/axios";
import { useRouter } from "vue-router";

const router = useRouter();
const form = reactive({
  phone: "",
  username: "",
  password: "",
});

const handleRegister = async () => {
  try {
    await axios.post("/auth/register", form);
    alert(String.fromCodePoint(0x6ce8, 0x518c, 0x6210, 0x529f, 0xff01));
    router.push("/login");
  } catch (error) {
    alert(
      error.response?.data?.error ||
        String.fromCodePoint(0x6ce8, 0x518c, 0x5931, 0x8d25),
    );
  }
};
</script>

<style scoped>
.register-container {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.register-box {
  background: white;
  padding: 40px;
  border-radius: 12px;
  box-shadow: 0 10px 40px rgba(0, 0, 0, 0.1);
  width: 350px;
}

.title {
  text-align: center;
  font-size: 28px;
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

.register-btn {
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

.register-btn:hover {
  background: #06ad56;
}

.login-link {
  text-align: center;
  margin-top: 20px;
  color: #666;
}

.login-link a {
  color: #07c160;
  text-decoration: none;
}

.login-link a:hover {
  text-decoration: underline;
}
</style>
