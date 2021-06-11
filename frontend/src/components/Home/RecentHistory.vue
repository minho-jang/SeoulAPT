<template>
  <div>
    <div
      class="d-flex justify-content-center pb-5 searchbar-bg-color flex-wrap"
      v-if="getUser && userHistory && userHistory.length > 0"
    >
      <span class="mr-4"><small>최근 검색 기록 (최대 5개)</small></span>
      <div
        class="d-inline-flex bg-color-white rounded pl-2 mr-3 hover-history align-items-center mb-1"
        v-for="(item, index) in userHistory"
        :key="index"
        @click="onClickMoveSearch(item)"
      >
        <span class="mr-1">{{ item.sido }} {{ item.gugun }}</span>
        <b-button
          class="p-0 hover-danger"
          variant="light"
          size="sm"
          @click.stop="onClickDelete(item)"
        >
          <b-icon icon="x"></b-icon>
        </b-button>
      </div>
    </div>
    <div v-else class="py-5 searchbar-bg-color"></div>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from 'vuex';
import { deleteHistory } from '../../api/user.js';

export default {
  computed: {
    ...mapState(['userHistory']),
    ...mapGetters(['getUser']),
  },
  created() {
    if (this.getUser && this.getUser.userid) {
      this.fetchUserHistory(this.getUser.userid);
    }
  },
  methods: {
    ...mapActions(['fetchUserHistory']),
    onClickDelete(history) {
      deleteHistory(history.historyid)
        .then(() => {
          this.fetchUserHistory(this.getUser.userid);
        })
        .catch((err) => {
          console.error(err);
        });
    },
    onClickMoveSearch(keyword) {
      this.$emit('clickHistory', keyword.sido, keyword.gugun);
    },
  },
};
</script>

<style scoped>
.bg-color-lightgoldenrodyellow {
  background: lightgoldenrodyellow;
}
.hover-history:hover {
  background-color: rgb(185, 248, 69);
  cursor: pointer;
}
.hover-danger:hover {
  background-color: #dc3545;
  cursor: pointer;
}
.searchbar-bg-color {
  background-color: #e8d767;
}
.bg-color-white {
  background-color: white;
}
</style>
