<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
  #app {
    min-height: 100vh;
  }
  pre {
    background: #eee;
    padding: 1em;
    margin: 1em 0 !important;

    border: 1px solid #ddd;
  }
  .ant-input {
    font-family: monospace !important;
  }
</style>
