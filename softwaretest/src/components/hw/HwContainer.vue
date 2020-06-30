<template>
  <div class="hw-container">
    <div class="left">
        <HwContent :content="content" />
    </div>
    <div class="divider"></div>
    <div class="right">
      <HwContent :content="content2" />

          <a-button type="primary" html-type="submit" @click="handleSubmit">
            提交
          </a-button>
    </div>
  </div>
</template>

<script>
  import Axios from "axios";
  import HwContent from "@/components/hw/HwContent"
  export default {
    components: { HwContent},
    props: {
      HwName: {
        type: String,
        required: true
      },
      method:{
        type: String
      },
      api:{
        type: String
      }
    },
    data() {
      return {
        content:this.getContent(),
        content2:this.getContent2()
      };
    },
    computed: {

    },
    // init data

    methods: {
      getContent(){
        Axios.get("/static/"+ this.HwName +".md")
          .then(response => {
            this.content = response.data;
            return response.data;
          })
          .catch(e => {
            message.error("获取实验说明失败：" + e.message).then();
          });
      },
      getContent2(){
        Axios.get("/static/"+ this.method +".md")
          .then(response => {
            this.content2 = response.data;
            return response.data;
          })
          .catch(e => {
            message.error("获取测试用例失败：" + e.message).then();
          });
      },
      async handleSubmit(e) {
        e.preventDefault();

        try {
          const response = await Axios.get(this.api);
          if (response.status === 200) {
            if (response.data.length > 0) {
              const h = this.$createElement;
              this.$info({
                title: `结果`,
                width: 800,
                content: h("div", {}, [h("pre", response.data)]),
                onOk() {
                  // do nothing
                }
              });
            }
          } else {
            this.$message.warn("已提交，但服务器响应超时").then();
          }
        } catch (error) {
          this.$message.error("发生错误：" + error.message).then();
        } finally {
        }
      }
    }


  };
</script>

<style lang="scss" scoped>
  .hw-container {
    display: flex;
    justify-content: center;
    position: relative;

    height: 100%;

    .left,
    .right {
      flex: 1 1 0;
      overflow: auto;
    }

    .divider {
      position: relative;
      top: 0;
      bottom: 0;
      width: 2px;
      background: #ddd;
      margin: 0 10px;
    }
  }
</style>
