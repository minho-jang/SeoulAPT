<template>
  <div class="text-center mt-5 mb-3">
    <span><h1 class="cursor-pointer d-inline-block" @click="onClickHeader">QnA</h1></span>
  </div>
</template>

<script>
export default {
  methods: {
    onClickHeader() {
      this.$router.push('/qna');
    },
  },
};
</script>

<style scoped>
.cursor-pointer {
  cursor: pointer;
}
</style>
