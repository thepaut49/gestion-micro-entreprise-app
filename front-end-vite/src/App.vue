<template>
  <div id="app">
    <app-nav />
    <router-view class="page" />
  </div>
</template>

<script>
import AppNav from "./components/AppNav.vue";

export default {
  components: { AppNav },
};
</script>

<style>
@import "./assets/styles/global.css";
.page {
  min-height: calc(100vh - 56px);
  margin-left: 0.5em;
  margin-right: 0.5em;
  height: 100%;
}
</style>
