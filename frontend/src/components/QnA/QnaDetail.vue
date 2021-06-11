<template>
  <b-container>
    <b-row>
      <b-col>
        <qna-header />
      </b-col>
    </b-row>

    <b-row class="mb-2 shadow-sm">
      <b-col cols="2" class="text-center py-2 qna-detail-header">제목</b-col>
      <b-col class="py-2">{{ qnaDetail.qnatitle }}</b-col>
    </b-row>

    <b-row class="mb-2 shadow-sm">
      <b-col cols="2" class="text-center py-2 qna-detail-header">작성자</b-col>
      <b-col cols="4" class="py-2">{{ qnaDetail.userid }}</b-col>
      <b-col cols="2" class="text-center py-2 qna-detail-header">작성일</b-col>
      <b-col cols="4" class="py-2">{{ qnaDetail.qna_timestamp }}</b-col>
    </b-row>

    <b-row class="mt-2 mb-4 px-2 pb-5 pt-4 shadow">
      <b-col>
        <span class="white-space-pre"> {{ qnaDetail.qnacontent }}</span>
      </b-col>
    </b-row>

    <b-row class="mb-5">
      <b-col class="text-center">
        <span v-if="getUser && getUser.userid && getUser.userid == qnaDetail.userid">
          <b-button class="mr-2" variant="info" @click="onClickModify">수정</b-button>
          <b-button class="mr-2" variant="danger" @click="onClickDelete">삭제</b-button>
        </span>
        <b-button variant="secondary" @click="onClickToList">목록</b-button>
      </b-col>
    </b-row>

    <b-row>
      <b-col class="p-0">
        <qna-comments :qnaboardid="$route.params.id" />
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import QnaHeader from './QnaHeader.vue';
import { mapState, mapGetters, mapMutations } from 'vuex';
import QnaComments from './QnaComments.vue';
import { getQnaDetail, deleteQna } from '../../api/qna.js';

export default {
  components: { QnaHeader, QnaComments },
  computed: {
    ...mapState(['qnaDetail']),
    ...mapGetters(['getUser']),
  },
  created() {
    if (!this.qnaDetail.qnatitle || !this.qnaDetail.userid) {
      getQnaDetail(this.$route.params.id)
        .then(({ data }) => {
          this.SET_QNA_DETAIL(data);
        })
        .catch((err) => {
          console.error(err);
        });
    }
  },
  methods: {
    ...mapMutations(['SET_QNA_DETAIL']),
    onClickToList() {
      this.$router.push('/qna');
    },
    onClickModify() {
      this.$router.push(`/qna/${this.qnaDetail.qnaboardid}/modify`);
    },
    onClickDelete() {
      if (confirm('삭제하시겠습니까?')) {
        deleteQna(this.qnaDetail.qnaboardid)
          .then(() => {
            this.SET_QNA_DETAIL(null);
            alert('삭제되었습니다.');
            this.$router.push('/qna');
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
.qna-detail-header {
  background-color: #c9d7f5;
  font-weight: 550;
  font-size: 1.1em;
}
.white-space-pre {
  white-space: pre-wrap;
  word-wrap: break-word;
}
</style>
