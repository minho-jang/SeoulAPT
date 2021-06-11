<template>
  <div class="headerbar-bg-color">
    <b-navbar toggleable="lg" type="light">
      <b-navbar-brand href="/">
        <img src="@/assets/seoulapt_logo.png" alt="logo" width="100px" />
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="/qna">QnA</b-nav-item>
          <b-nav-item href="/about">About</b-nav-item>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto" v-if="getUser && getUser.userid">
          <b-nav-item-dropdown right>
            <!-- Using 'button-content' slot -->
            <template #button-content>
              <em class="mr-2">{{ getUser.userid }}</em>
            </template>
            <b-dropdown-item href="/user/profile">Profile</b-dropdown-item>
            <b-dropdown-item @click="onClickLogOut">Logout</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto" v-else>
          <b-nav-item href="/user/login">Login</b-nav-item>
          <b-nav-item href="/user/join">Join</b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapGetters, mapMutations } from 'vuex';

export default {
  computed: {
    ...mapGetters(['getUser']),
  },
  methods: {
    ...mapMutations(['SET_USER']),
    onClickLogOut() {
      this.SET_USER(null);
      alert('로그아웃 되었습니다.');
      if (this.$route.path !== '/') {
        this.$router.push('/');
      } else {
        this.$router.go();
      }
    },
  },
};
</script>

<style scoped>
.headerbar-bg-color {
  background-color: #fabc66;
}
</style>
