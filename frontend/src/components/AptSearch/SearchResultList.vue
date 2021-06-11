<template>
  <div>
    <div class="text-center pt-2 mb-2">
      <span class="search-keyword mb-2">"{{ params.sido }} {{ params.gugun }}"</span>의
      <br />검색결과 <small>({{ apts.length }} 개)</small>
      <b-form-input
        class="mt-1"
        size="sm"
        placeholder="아파트 이름 검색"
        v-model="searchKeyInList"
      ></b-form-input>
      <div class="d-flex align-items-end">
        <div class="flex-1">
          <b-form-input class="mt-1" type="number" size="sm" v-model="lower"></b-form-input>
        </div>
        <small class="mx-1"><b>만원</b> 이상</small>
        <div class="flex-1">
          <b-form-input class="mt-1" type="number" size="sm" v-model="upper"></b-form-input>
        </div>
        <small class="mx-1"><b>만원</b> 이하</small>
      </div>
    </div>

    <b-card no-body>
      <b-card-body id="scroller" class="scroll-list">
        <div v-if="isLoading" class="text-center pt-10em">
          <b-spinner label="Loading..."></b-spinner>
        </div>
        <b-list-group v-else>
          <search-result-item
            v-for="(apt, idx) in apts"
            :key="idx"
            :apt="apt"
            v-show="searchFilter(apt)"
            @clickItem="onClickItem"
          />
        </b-list-group>
      </b-card-body>
    </b-card>
  </div>
</template>

<script>
import SearchResultItem from '../AptSearch/SearchResultItem.vue';
import { mapState, mapMutations, mapActions } from 'vuex';

export default {
  props: ['params'],
  components: { SearchResultItem },
  data() {
    return {
      searchKeyInList: '',
      upper: '',
      lower: '',
      isLoading: false,
    };
  },
  computed: {
    ...mapState(['apts']),
  },
  methods: {
    ...mapMutations(['SET_CLICKED_APT']),
    ...mapActions(['fetchApts']),
    onClickItem(apt) {
      this.SET_CLICKED_APT(apt);
    },
    searchFilter(apt) {
      if (
        (this.searchKeyInList && !apt.aptName.includes(this.searchKeyInList)) ||
        (this.upper && parseInt(apt.dealAmount.replaceAll(',', '')) > this.upper) ||
        (this.lower && parseInt(apt.dealAmount.replaceAll(',', '')) < this.lower)
      ) {
        return false;
      }
      return true;
    },
  },
  async created() {
    this.isLoading = true;

    await this.fetchApts(this.params.gugun);

    this.isLoading = false;
  },
};
</script>

<style scoped>
.search-keyword {
  font-weight: 600;
  display: inline;
  box-shadow: inset 0 -10px 0 #d9fcdb;
}
.scroll-list {
  position: relative;
  height: 75vh;
  overflow-y: scroll;
  padding: 0;
}
.flex-1 {
  flex: 1;
}
.pt-10em {
  padding-top: 10em;
}
@media (max-width: 768px) {
  .scroll-list {
    height: 30vh;
  }
}
</style>
