<template>
  <a-layout class="layout" >
    <a-layout-sider
      class="sidebar"
      :trigger="null"
      :collapsible="true"
      :width="280"
    >
      <SidebarMenu />
    </a-layout-sider>
    <a-layout-content
      :style="{
          margin: '24px 16px',
          padding: '24px',
          background: '#fff',
          height: 'calc(100vh - 64px - 64px)',
          overflow: 'auto'
        }"
    >
      <router-view />
    </a-layout-content>
    </a-layout>
</template>

<script>
  import SidebarMenu from "@/components/Sidebar";
    export default {
        name: "Home",
      components:{
          SidebarMenu
      }
    }
</script>

<style lang="scss" scoped>
  .layout {
    min-height: 100vh;
    margin-left: 280px;
    transition: margin-left 0.2s;

    &.collapsed {
      margin-left: 80px;
      .logo {
        font-size: 16px;
      }
    }
  }

  .sidebar {
    height: 100vh;
    overflow: hidden;
    position: fixed;
    left: 0;
  }
</style>
