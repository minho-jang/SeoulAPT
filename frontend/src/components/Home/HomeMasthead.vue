<template>
  <b-overlay :show="true" class="overlay-div">
    <img src="@/assets/masthead.jpg" alt="masthead-bg-image" width="100%" />
    <template #overlay>
      <div class="text-center">
        <p class="m-0 masthead-text">
          <strong>Welcome to Seoul APT</strong> <br /><br />
          <span class="line-height">
            서울특별시 <span class="highlight">아파트 매매 거래 정보</span>를 한 눈에!<br />
            <span class="hightlight-2" @click="onClickMoveQna">QnA 게시판</span>에 궁금한 것은 질문!
            알고 있는 것은 답글!
          </span>
        </p>
      </div>
    </template>
  </b-overlay>
</template>

<script>
export default {
  methods: {
    onClickMoveQna() {
      this.$router.push('/qna');
    },
  },
};
</script>

<style>
.highlight {
  font-weight: 600;
  display: inline;
  box-shadow: inset 0 -10px 0 #ace669;
}
.hightlight-2 {
  text-decoration: underline;
  text-underline-position: under;
  text-decoration-style: wavy;
  text-decoration-thickness: from-font;
  cursor: pointer;
}
.line-height {
  line-height: 2;
}
.overlay-div > div > div:nth-child(2) {
  width: 75%;
}
.masthead-text {
  font-size: 2em;
}
@media (max-width: 1200px) {
  .masthead-text {
    font-size: 2em;
  }
}
@media (max-width: 992px) {
  .masthead-text {
    font-size: 1.3em;
  }
}
@media (max-width: 768px) {
  .masthead-text {
    font-size: 1em;
  }
}
@media (max-width: 576px) {
  .masthead-text {
    font-size: 0.8em;
  }
  .line-height {
    line-height: 1;
  }
  .overlay-div {
    padding-top: 3rem;
  }
  .overlay-div > div > div:nth-child(2) {
    width: 100%;
  }
}
</style>
