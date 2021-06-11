<template>
  <div>
    <home-masthead />
    <home-search-bar />
    <!-- <div class="py-5 searchbar-bg-color"></div> -->
  </div>
</template>

<script>
import HomeMasthead from '../components/Home/HomeMasthead.vue';
import HomeSearchBar from '../components/Home/HomeSearchBar.vue';

export default {
  components: { HomeMasthead, HomeSearchBar },
};
</script>

<style>
.searchbar-bg-color {
  background-color: #e8d767;
}
</style>
