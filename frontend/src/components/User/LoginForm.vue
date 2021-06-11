<template>
  <div>
    <h3 class="mb-3">로그인</h3>
    <b-form @submit="onSubmit">
      <b-form-group id="input-group-1" label="ID" label-for="input-1">
        <b-form-input
          id="input-1"
          v-model.trim="userid"
          type="text"
          placeholder="아이디를 입력해주세요"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Password" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model.trim="userpwd"
          type="password"
          placeholder="비밀번호를 입력해주세요"
          required
        ></b-form-input>
      </b-form-group>
      <div class="text-center">
        <b-button type="submit">로그인</b-button>
      </div>
    </b-form>
  </div>
</template>

<script>
import { login } from '../../api/user.js';
import { mapMutations } from 'vuex';

export default {
  data() {
    return {
      userid: '',
      userpwd: '',
    };
  },
  methods: {
    ...mapMutations(['SET_USER']),
    onSubmit(event) {
      event.preventDefault();
      login(this.userid, this.userpwd)
        .then((res) => {
          if (res.status == 200) {
            this.SET_USER(res.data);
            this.$router.push('/');
          } else {
            alert('아이디 혹은 비밀번호가 일치하지 않습니다.');
          }
        })
        .catch((err) => {
          console.error(err);
        });
    },
  },
};
</script>

<style scoped>
.btn {
  margin-top: 25px;
  width: 100%;
  height: 40px;
  background-color: #fabc66;
  border: none;
  color: sienna;
}
.btn:hover {
  background-color: sandybrown;
  color: sienna;
}
</style>
