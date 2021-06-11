<template>
  <b-modal
    v-model="showModal"
    @hidden="onHidden"
    :title="getModalTitle"
    scrollable
    ok-only
    size="xl"
  >
    <apt-deal-detail :apt="apt" :params="params" />
  </b-modal>
</template>

<script>
import AptDealDetail from './AptDealDetail.vue';

export default {
  components: { AptDealDetail },
  props: ['apt', 'params'],
  data() {
    return {
      showModal: false,
    };
  },
  computed: {
    getModalTitle() {
      if (this.apt) {
        return '[' + this.apt.aptName + '] 거래정보';
      } else {
        return '거래정보';
      }
    },
  },
  watch: {
    apt(val) {
      if (val) {
        this.showModal = true;
      } else {
        this.showModal = false;
      }
    },
  },
  methods: {
    onHidden() {
      this.$emit('hidden');
    },
  },
};
</script>

<style></style>
