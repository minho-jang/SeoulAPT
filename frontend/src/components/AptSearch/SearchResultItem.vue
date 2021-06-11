<template>
  <b-list-group-item href="#" @click="onClickAptItem" :class="{ 'active-item': isClick }">
    <div>{{ apt.aptName }}</div>
    <small>
      {{ buildKoreanNum(apt.dealAmount) }}
      ({{ apt.dealYear + '.' + apt.dealMonth + '.' + apt.dealDay }})
    </small>
  </b-list-group-item>
</template>

<script>
import { mapState } from 'vuex';

export default {
  props: ['apt'],
  data() {
    return {
      isClick: false,
    };
  },
  computed: {
    ...mapState(['clickedApt']),
  },
  watch: {
    clickedApt() {
      this.isClick = this.apt.aptName == this.clickedApt.aptName;
    },
  },
  methods: {
    onClickAptItem() {
      this.$emit('clickItem', this.apt);
    },
    buildKoreanNum(num) {
      num = num.split(',').join('');
      let result = '',
        man = '';

      if (num.length >= 4) {
        man = num.substring(num.length - 4);
        num = num.substring(0, num.length - 4);
      }
      if (Number(num) != 0) {
        result = result.concat(num, '억 ');
      }
      if (Number(man) != 0) {
        result = result.concat(man, '만');
      }

      return result;
    },
  },
  filters: {
    numComma(val) {
      val = val.split(',').join('') + '0000';
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    },
  },
};
</script>

<style>
.active-item {
  background-color: #cfd8dc !important;
}
</style>
