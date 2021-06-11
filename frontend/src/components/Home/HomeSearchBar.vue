<template>
  <div class="py-10 searchbar-bg-color">
    <b-container>
      <b-row>
        <b-col class="text-center mb-5 searchbar-text">검색하고자 하는 지역을 선택해주세요!</b-col>
      </b-row>
      <b-row align-h="center" class="mb-3">
        <b-col class="text-center sm-width" sm="3"
          ><b-form-select
            class="shadow"
            v-model="sidoSelected"
            :options="sidoOptions"
          ></b-form-select
        ></b-col>
        <b-col class="text-center sm-width" sm="3"
          ><b-form-select
            class="shadow"
            v-model="gugunSelected"
            :options="gugunOptions"
          ></b-form-select
        ></b-col>
        <b-col class="text-center" sm="2"
          ><b-button class="shadow" variant="primary" @click="onClickSearch">검색</b-button></b-col
        >
      </b-row>
      <b-row>
        <b-col>
          <recent-history @clickHistory="onClickHistory" />
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import { addHistory } from '../../api/user.js';
import RecentHistory from './RecentHistory.vue';

export default {
  components: {
    RecentHistory,
  },
  data() {
    return {
      sidoSelected: null,
      sidoOptions: [
        { value: null, text: '시/도' },
        { value: '서울특별시', text: '서울특별시' },
      ],
      gugunSelected: null,
      gugunOptions: [
        { value: null, text: '구/군' },
        { value: '종로구', text: '종로구' },
        { value: '중구', text: '중구' },
        { value: '용산구', text: '용산구' },
        { value: '성동구', text: '성동구' },
        { value: '광진구', text: '광진구' },
        { value: '동대문구', text: '동대문구' },
        { value: '중랑구', text: '중랑구' },
        { value: '성북구', text: '성북구' },
        { value: '강북구', text: '강북구' },
        { value: '도봉구', text: '도봉구' },
        { value: '노원구', text: '노원구' },
        { value: '은평구', text: '은평구' },
        { value: '서대문구', text: '서대문구' },
        { value: '마포구', text: '마포구' },
        { value: '양천구', text: '양천구' },
        { value: '강서구', text: '강서구' },
        { value: '구로구', text: '구로구' },
        { value: '금천구', text: '금천구' },
        { value: '영등포구', text: '영등포구' },
        { value: '동작구', text: '동작구' },
        { value: '관악구', text: '관악구' },
        { value: '서초구', text: '서초구' },
        { value: '강남구', text: '강남구' },
        { value: '송파구', text: '송파구' },
        { value: '강동구', text: '강동구' },
      ],
    };
  },
  computed: {
    ...mapGetters(['getUser']),
  },
  methods: {
    onClickSearch() {
      if (!this.validationCheck()) {
        return;
      }

      // user history
      if (this.getUser && this.getUser.userid) {
        addHistory(this.gugunSelected, this.getUser.userid).catch((err) => {
          console.error(err);
        });
      }

      // move search page
      const param = {
        sido: this.sidoSelected,
        gugun: this.gugunSelected,
      };
      this.$router.push({ path: '/search', query: param });
    },
    validationCheck() {
      if (this.sidoSelected == null || this.gugunSelected == null) {
        alert('지역을 선택해주세요');
        return false;
      }

      return true;
    },
    onClickHistory(sido, gugun) {
      const param = {
        sido,
        gugun,
      };

      // user history
      if (this.getUser && this.getUser.userid) {
        addHistory(gugun, this.getUser.userid).catch((err) => {
          console.error(err);
        });
      }
      this.$router.push({ path: '/search', query: param });
    },
  },
};
</script>

<style scoped>
.py-10 {
  padding-bottom: 2rem;
  padding-top: 7rem;
}
.searchbar-bg-color {
  background-color: #e8d767;
}
.font-color-lightblack {
  color: #212529;
}
.searchbar-text {
  font-size: 4em;
}
@media (max-width: 1200px) {
  .searchbar-text {
    font-size: 4em;
  }
}
@media (max-width: 992px) {
  .searchbar-text {
    font-size: 3em;
  }
}
@media (max-width: 768px) {
  .searchbar-text {
    font-size: 2em;
  }
}
@media (max-width: 576px) {
  .searchbar-text {
    font-size: 2em;
  }
  .sm-width {
    width: 40%;
    margin-bottom: 1rem;
  }
  .py-10 {
    padding-bottom: 2rem;
    padding-top: 3rem;
  }
}
</style>
