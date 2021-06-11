<template>
  <div class="login">
    <login-form />
  </div>
</template>

<script>
import LoginForm from '../components/User/LoginForm.vue';
export default {
  components: { LoginForm },
};
</script>

<style scoped>
.login {
  width: 500px;
  margin: 100px auto;
  margin-bottom: 100px;
}
</style>
