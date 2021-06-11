<template>
  <b-container fluid class="mx-1 my-3">
    <b-row>
      <b-col cols="12" md="9" order-md="2" class="mh-520px">
        <map-apt @clickMarker="onClickMarker" :params="this.$route.query" />
      </b-col>
      <b-col cols="12" md="3" order-md="1">
        <search-result-list :params="this.$route.query" />
      </b-col>
    </b-row>
    <apt-deal-modal :apt="apt" @hidden="hideModal" :params="this.$route.query" />
  </b-container>
</template>

<script>
import MapApt from '../components/AptSearch/MapApt.vue';
import SearchResultList from '../components/AptSearch/SearchResultList.vue';
import AptDealModal from '../components/AptSearch/AptDealModal.vue';

export default {
  components: { SearchResultList, MapApt, AptDealModal },
  data() {
    return {
      showModal: false,
      apt: null,
    };
  },
  methods: {
    onClickMarker(item) {
      this.showModal = true;
      this.apt = item;
    },
    hideModal() {
      this.showModal = false;
      this.apt = null;
    },
  },
};
</script>

<style>
.mh-520px {
  min-height: 520px;
}
</style>
