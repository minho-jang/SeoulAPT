<template>
  <b-list-group class="mb-5">
    <div v-if="!comments || comments.length == 0" class="text-center my-3 p-5 bg-color-skyblue">
      <h3 class="m-0">댓글이 없습니다.</h3>
    </div>
    <b-list-group-item
      v-else
      class="flex-column align-items-start comment-item"
      v-for="(comment, index) in comments"
      :key="index"
    >
      <div class="d-flex w-100 justify-content-between comment-header">
        <span class="mb-1 font-weight-bold">{{ comment.userid }}</span>
        <small variant="dark">{{ comment.comment_timestamp }}</small>
      </div>

      <div v-if="modifyComment == index">
        <b-form-textarea v-model="comment.content" placeholder="댓글을 입력해주세요" class="mt-2">
        </b-form-textarea>
        <div class="text-right mt-2">
          <b-button
            size="sm"
            class="mr-2"
            variant="outline-info"
            @click="onClickModifyComment(comment)"
            >수정</b-button
          >
          <b-button size="sm" variant="outline-secondary" @click="onClickModifyCancel"
            >취소</b-button
          >
        </div>
      </div>

      <div v-else>
        <div class="p-2 white-space-pre">{{ comment.content }}</div>

        <div
          class="text-right"
          v-if="getUser && getUser.userid && getUser.userid == comment.userid"
        >
          <b-dropdown
            dropup
            right
            class="m-2 border-none"
            no-caret
            size="sm"
            variant="outline-warning"
            menu-class="w-100"
          >
            <template #button-content>
              <b-icon icon="pencil-square" variant="dark"></b-icon>
            </template>
            <b-dropdown-item @click="onClickModify(index)">수정</b-dropdown-item>
            <b-dropdown-item @click="onClickDeleteComment(comment.qnacommentid)"
              >삭제</b-dropdown-item
            >
          </b-dropdown>
        </div>
      </div>
    </b-list-group-item>

    <div class="mt-3 p-0 d-flex" v-if="getUser && getUser.userid">
      <div class="pr-3 w-100">
        <b-form-group
          :label="getUser.userid"
          label-for="comment-textarea"
          label-class="ml-2"
          class="mb-0"
        >
          <b-form-textarea
            id="comment-textarea"
            v-model.trim="content"
            placeholder="댓글을 입력해주세요"
            class="shadow"
          ></b-form-textarea>
        </b-form-group>
      </div>
      <div class="text-right mt-2 p-0">
        <b-button
          class="h-100 shadow-sm"
          size="sm"
          variant="outline-primary"
          @click="onClickAddComment"
          >등록</b-button
        >
      </div>
    </div>
  </b-list-group>
</template>

<script>
import { getCommentList, writeComment, modifyComment, deleteComment } from '../../api/comment.js';
import { mapGetters } from 'vuex';

export default {
  props: ['qnaboardid'],
  data() {
    return {
      comments: [],
      content: '',
      modifyComment: Number,
    };
  },
  computed: {
    ...mapGetters(['getUser']),
  },
  created() {
    this.fetchComment();
  },
  methods: {
    onClickModify(idx) {
      this.modifyComment = idx;
    },
    onClickModifyCancel() {
      this.modifyComment = undefined;
    },
    onClickModifyComment(modifiedComment) {
      modifyComment(modifiedComment)
        .then(({ data }) => {
          console.log(data);
          this.modifyComment = undefined;
        })
        .catch((err) => {
          console.error(err);
        });
    },
    onClickDeleteComment(id) {
      if (confirm('삭제하시겠습니까?')) {
        deleteComment(id)
          .then(() => {
            this.fetchComment();
          })
          .catch((err) => {
            console.error(err);
          });
      }
    },
    onClickAddComment() {
      if (!this.content) {
        return;
      }
      const param = {
        qnaboardid: this.qnaboardid,
        content: this.content,
        userid: this.getUser.userid,
      };
      writeComment(param)
        .then(({ data }) => {
          console.log(data);
          this.content = '';
          this.fetchComment();
        })
        .catch((err) => {
          console.error(err);
        });
    },
    fetchComment() {
      getCommentList(this.qnaboardid)
        .then(({ data }) => {
          this.comments = data;
        })
        .catch((err) => {
          console.error(err);
        });
    },
  },
};
</script>

<style scoped>
.comment-header {
  border-bottom: solid 1px #cacaca;
  padding-bottom: 0.25em;
  padding-left: 0.25em;
}
.comment-item {
  background-color: #eeeeee;
  border: none;
}
.white-space-pre {
  white-space: pre-wrap;
  word-wrap: break-word;
}
.bg-color-skyblue {
  background-color: #d8e7f0;
}
</style>
