<template>
  <div class="join">
    <join-form />
  </div>
</template>

<script>
import JoinForm from '../components/User/JoinForm.vue';
export default {
  components: { JoinForm },
};
</script>

<style scoped>
.join {
  width: 500px;
  margin: 100px auto;
  margin-bottom: 100px;
}
</style>
