<template>
  <div class="my-10">
    <b-container>
      <b-row class="mb-2">
        <b-col>
          <h3>내 정보</h3>
        </b-col>
      </b-row>
      <b-row class="border-bottom">
        <b-col cols="2" class="font-size-larger font-weight-bold py-2 pr-5 text-center"
          >아이디</b-col
        >
        <b-col class="font-size-larger py-2">{{ userinfo.userid }}</b-col>
      </b-row>
      <b-row class="border-bottom">
        <b-col cols="2" class="font-size-larger font-weight-bold py-2 pr-5 text-center"
          >비밀번호</b-col
        >
        <b-col v-if="type == 'profile'" class="font-size-larger py-2">{{ userinfo.userpwd }}</b-col>
        <b-col v-if="type == 'modify'">
          <div class="my-2">
            <b-form-input
              type="password"
              placeholder="수정할 비밀번호를 입력해주세요"
              v-model="userinfo.userpwd"
            />
          </div>
          <div class="mb-2">
            <b-form-input
              type="password"
              placeholder="비밀번호를 한 번 더 입력해주세요"
              v-model="updatedPwd"
            />
          </div>
        </b-col>
      </b-row>

      <b-row class="border-bottom">
        <b-col cols="2" class="font-size-larger font-weight-bold py-2 pr-5 text-center">이름</b-col>
        <b-col v-if="type == 'profile'" class="font-size-larger py-2">{{ userinfo.name }}</b-col>
        <b-col v-if="type == 'modify'">
          <div class="my-2">
            <b-form-input
              type="text"
              placeholder="수정할 이름을 입력해주세요"
              v-model="userinfo.name"
            />
          </div>
        </b-col>
      </b-row>

      <b-row class="border-bottom">
        <b-col cols="2" class="font-size-larger font-weight-bold py-2 pr-5 text-center"
          >이메일</b-col
        >
        <b-col v-if="type == 'profile'" class="font-size-larger py-2">{{ userinfo.email }}</b-col>
        <b-col v-if="type == 'modify'">
          <div class="my-2">
            <b-form-input
              type="text"
              placeholder="수정할 이메일을 입력해주세요"
              v-model="userinfo.email"
            />
          </div>
        </b-col>
      </b-row>

      <b-row class="mb-3 border-bottom">
        <b-col cols="2" class="font-size-larger font-weight-bold py-2 pr-5 text-center">주소</b-col>
        <b-col v-if="type == 'profile'" class="font-size-larger py-2">{{ userinfo.address }}</b-col>
        <b-col v-if="type == 'modify'">
          <div class="my-2">
            <b-form-input
              type="text"
              placeholder="수정할 주소를 입력해주세요"
              v-model="userinfo.address"
            />
          </div>
        </b-col>
      </b-row>

      <b-row>
        <b-col class="text-right" v-if="type == 'profile'">
          <b-button size="sm" class="mr-3" variant="info" @click="onClickModify">수정</b-button>
          <b-button size="sm" variant="danger" @click="onClickDelete">탈퇴</b-button>
        </b-col>
        <b-col class="text-right" v-if="type == 'modify'">
          <b-button size="sm" class="mr-3" variant="info" @click="onClickConfirm">확인</b-button>
          <b-button size="sm" variant="secondary" @click="onClickCancel">취소</b-button>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { modifyUser, deleteUser } from '../../api/user.js';
import { mapGetters, mapMutations } from 'vuex';

export default {
  props: {
    type: { type: String },
  },
  data() {
    return {
      userinfo: {
        userid: 'userid',
        userpwd: 'userpwd',
        name: 'name',
        email: 'email',
        address: 'address',
      },
      updatedPwd: '',
    };
  },
  created() {
    if (!this.getUser) {
      alert('올바르지 않은 접근입니다.');
      this.$router.push('/');
    }
    this.userinfo = this.getUser;
  },
  computed: {
    ...mapGetters(['getUser']),
  },
  methods: {
    ...mapMutations(['SET_USER']),
    onClickModify() {
      this.$router.push('/user/modify');
    },
    onClickConfirm() {
      if (this.userinfo.userpwd !== this.updatedPwd) {
        alert('비밀번호가 일치하지 않습니다.');
        return;
      }

      modifyUser(this.userinfo)
        .then(({ status }) => {
          if (status == 200) {
            this.$router.push({ path: '/user/profile' });
          } else {
            alert(`에러가 발생했습니다. 다시 시도해주세요. (HTTP Status ${status})`);
          }
        })
        .catch((err) => {
          console.error(err);
        });
    },
    onClickCancel() {
      this.$router.push({ path: '/user/profile' });
    },
    onClickDelete() {
      if (confirm('회원을 탈퇴하시겠습니까?')) {
        deleteUser(this.userinfo.userid)
          .then(({ status }) => {
            if (status == 200) {
              this.SET_USER(null);
              alert('회원탈퇴가 완료되었습니다.');
              this.$router.push('/');
            } else {
              alert(`에러가 발생했습니다. 다시 시도해주세요. (HTTP Status ${status})`);
            }
          })
          .catch((err) => {
            console.error(err);
          });
      }
    },
  },
};
</script>

<style scoped>
.font-size-larger {
  font-size: larger;
}
.my-10 {
  margin-top: 6em;
  margin-bottom: 6em;
}
</style>
