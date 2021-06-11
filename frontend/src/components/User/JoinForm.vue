<template>
  <div>
    <h3 class="mb-3">회원가입</h3>
    <b-form @submit="onSubmit">
      <b-form-group label-for="input-1">
        <template #label>ID<span class="color-red">*</span></template>
        <b-form-input
          id="input-1"
          v-model.trim="userid"
          type="text"
          placeholder="아이디를 입력해주세요"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group label-for="input-2">
        <template #label>Password<span class="color-red">*</span></template>
        <b-form-input
          id="input-2"
          v-model.trim="userpwd"
          type="password"
          placeholder="비밀번호를 입력해주세요"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group label-for="input-2_">
        <template #label>Password Check<span class="color-red">*</span></template>
        <b-form-input
          id="input-2_"
          v-model.trim="userpwd_"
          type="password"
          placeholder="동일한 비밀번호를 한 번 더 입력해주세요"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group label-for="input-3">
        <template #label>Name<span class="color-red">*</span></template>
        <b-form-input
          id="input-3"
          v-model.trim="username"
          type="text"
          placeholder="예) 홍길동"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group label-for="input-4">
        <template #label>Email<span class="color-red">*</span></template>
        <b-form-input
          id="input-4"
          v-model.trim="useremail"
          type="email"
          placeholder="예) seoulapt@example.com"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group label="Address" label-for="input-5">
        <b-form-input
          id="input-5"
          v-model.trim="useraddress"
          type="text"
          placeholder="예) 경기도 의왕시 오전동"
        ></b-form-input>
      </b-form-group>

      <div class="btn-div">
        <b-button type="submit">가입하기</b-button>
      </div>
    </b-form>
  </div>
</template>

<script>
import { join } from '../../api/user.js';

export default {
  data() {
    return {
      userid: '',
      userpwd: '',
      userpwd_: '',
      username: '',
      useremail: '',
      useraddress: '',
    };
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      if (this.userpwd !== this.userpwd_) {
        alert('비밀번호가 일치하지 않습니다.');
        return;
      }

      const user = {
        userid: this.userid,
        userpwd: this.userpwd,
        name: this.username,
        email: this.useremail,
        address: this.useraddress,
      };
      join(user)
        .then(({ data, status }) => {
          if (status == 200) {
            console.log(data);
            alert('회원가입이 완료되었습니다.');
            this.$router.push('/');
          } else {
            alert('중복된 아이디 입니다.');
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
.btn-div {
  text-align: center;
}
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
.color-red {
  color: red;
}
</style>
