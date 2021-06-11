<template>
  <b-container class="mb-5">
    <qna-header />
    <div class="text-right">
      <b-button variant="primary" class="mb-2 p-1" @click="onClickAddQna">
        <b-icon icon="plus"></b-icon>
      </b-button>
    </div>
    <div v-if="qnaItems.length == 0" class="text-center my-3 p-5 bg-color-skyblue">
      <h3 class="m-0">게시물이 없습니다.</h3>
    </div>
    <b-table
      v-else
      striped
      hover
      :items="qnaItems"
      :fields="fields"
      @row-clicked="onClickQna"
      tbody-tr-class="cursor-pointer"
    >
      <template #cell(No)="row">
        {{ row.index + 1 }}
      </template>
    </b-table>
  </b-container>
</template>

<script>
import QnaHeader from './QnaHeader.vue';
import { getQnaList } from '../../api/qna.js';
import { mapMutations } from 'vuex';

export default {
  components: { QnaHeader },
  data() {
    return {
      fields: [
        { key: 'qnaboardid', label: 'No', thClass: 'text-center', tdClass: 'text-center' },
        { key: 'qnatitle', label: '제목' },
        { key: 'userid', label: '작성자', thClass: 'text-center', tdClass: 'text-center' },
      ],
      qnaItems: [],
    };
  },
  created() {
    getQnaList()
      .then(({ data }) => {
        this.qnaItems = data;
      })
      .catch((err) => {
        console.error(err);
      });
  },
  methods: {
    ...mapMutations(['SET_QNA_DETAIL']),
    onClickQna(record) {
      this.SET_QNA_DETAIL(record);
      this.$router.push('/qna/' + record.qnaboardid);
    },
    onClickAddQna() {
      this.$router.push('/qna/write');
    },
  },
};
</script>

<style>
.cursor-pointer {
  cursor: pointer;
}
</style>

<style scoped>
.bg-color-skyblue {
  background-color: #d8e7f0;
}
</style>
