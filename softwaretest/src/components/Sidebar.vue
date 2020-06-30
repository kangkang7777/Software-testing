<template>
  <div class="menu-container">
    <a-menu
      theme="dark"
      mode="inline"
      @click="handleMenuClick"
      :defaultOpenKeys="defaultOpenKeys"
      v-model="selectedKeys"
    >
      <a-sub-menu v-for="menu in menuConfig" :key="'/home/' + menu.path">
        <span slot="title">
          <a-icon :type="menu.icon" />
          <span>{{ menu.name }}</span>
        </span>
        <a-menu-item
          v-for="submenu in menu.children"
          :key="`/home/${menu.path}/${submenu.path}`"
          :title="submenu.name"
        >{{ submenu.name }}</a-menu-item
        >
      </a-sub-menu>
    </a-menu>
  </div>
</template>

<script>
  export default {
    name: "SidebarMenu",
    data() {
      return {
        selectedKeys: [this.$route.path],
        defaultOpenKeys: [
          this.$route.path
            .split("/")
            .slice(0, -1)
            .join("/")
        ],
        menuConfig: [
          {
            name: "作业1",
            path: "hw1",
            children: [
              { name: "三角形问题边界值法", path: "1" },
              { name: "三角形问题等价类法", path: "2" },
                { name: "万年历问题边界值法", path: "3" },
                { name: "万年历问题等价类法", path: "4" },
            ]
          },
          {
            name: "作业2",
            path: "hw2",
            children: [
              { name: "边界值法", path: "1" }
            ]
          },
          {
            name: "作业7",
            path:"hw7",
            children: [
              { name: "边界值法", path: "1" },
              { name: "等价类法", path: "2" },
              {name: "决策表法", path:"3"}
            ]
          },
          {
            name: "作业8",
            path: "hw8",
            children: [
              { name: "白盒测试", path: "1" }
            ]
          }
        ]
      };
    },
    computed: {
      path() {
        return this.$route.path;
      }
    },
    watch: {
      path(v) {
        this.selectedKeys = [v];
      }
    },
    methods: {
      handleMenuClick(e) {
        this.$router.push(e.key);
      }
    }
  };
</script>

<style scoped>
  .menu-container {
    height: 100%;
    overflow: auto;
  }
</style>

