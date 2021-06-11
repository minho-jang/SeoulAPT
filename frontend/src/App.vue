<template>
  <div id="app">
    <header-bar />
    <router-view />
    <footer-bar />
  </div>
</template>

<script>
import FooterBar from './components/common/FooterBar.vue';
import HeaderBar from './components/common/HeaderBar.vue';

export default {
  components: { HeaderBar, FooterBar },
};
</script>

<style></style>
