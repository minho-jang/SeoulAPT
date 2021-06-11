<template>
  <b-container class="mb-5">
    <qna-header />
    <b-row>
      <b-col>
        <b-form-group label-for="question-input" :invalid-feedback="invalidFeedback" :state="state">
          <b-form-input
            id="question-input"
            v-model="question"
            :state="state"
            trim
            placeholder="질문을 입력해주세요"
            class="shadow-sm"
          ></b-form-input>
        </b-form-group>

        <b-form-textarea
          id="textarea"
          v-model="content"
          placeholder="질문에 대한 자세한 내용을 적어주세요 :)"
          rows="10"
          max-rows="16"
          class="shadow"
        ></b-form-textarea>
      </b-col>
    </b-row>
    <b-row class="my-3">
      <b-col class="text-center">
        <b-button variant="primary" @click="onClickModify" class="mr-3">저장</b-button>
        <b-button variant="secondary" @click="onClickToList">목록</b-button>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import QnaHeader from './QnaHeader.vue';
import { modifyQna } from '../../api/qna.js';
import { mapState, mapGetters, mapMutations } from 'vuex';

export default {
  components: { QnaHeader },
  computed: {
    ...mapState(['qnaDetail']),
    ...mapGetters(['getUser']),
    state() {
      return this.question.length >= 2;
    },
    invalidFeedback() {
      return '최소 2글자 이상 입력해야 합니다.';
    },
  },
  created() {
    this.question = this.qnaDetail.qnatitle;
    this.content = this.qnaDetail.qnacontent;
  },
  data() {
    return {
      question: '',
      content: '',
    };
  },
  methods: {
    ...mapMutations(['SET_QNA_DETAIL']),
    onClickToList() {
      this.$router.push('/qna');
    },
    onClickModify() {
      if (this.checkValidation()) {
        const param = {
          qnaboardid: this.qnaDetail.qnaboardid,
          qnacontent: this.content,
          qnatitle: this.question,
        };
        modifyQna(param)
          .then(({ data }) => {
            console.log(data);
            alert('질문이 수정되었습니다.');
            this.SET_QNA_DETAIL({ ...param, userid: this.getUser.userid });
            this.$router.push(`/qna/${this.qnaDetail.qnaboardid}`);
          })
          .catch((err) => {
            console.error(err);
          });
      }
    },
    checkValidation() {
      if (!this.question || this.question.length < 2) {
        alert('질문을 확인해주세요');
        return false;
      }
      if (!this.content || this.content.length == 0) {
        alert('질문에 대한 자세한 내용을 입력해주세요');
        return false;
      }

      return true;
    },
  },
};
</script>

<style></style>
