import Vue from 'vue'
import Router from 'vue-router'
import Home from "@/components/Home"
import Hw11 from "@/components/hw1/hw1-1"
import Hw12 from "@/components/hw1/hw1-2"
import Hw13 from "@/components/hw1/hw1-3"
import Hw14 from "@/components/hw1/hw1-4"
import Hw21 from "@/components/hw2/hw2-1"
import Hw71 from "@/components/hw7/hw7-1"
import Hw72 from "@/components/hw7/hw7-2"
import Hw73 from "@/components/hw7/hw7-3"
import Hw81 from "@/components/hw8/hw8-1"


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/home',
      name: 'Home',
      component: Home,
      children:[
        {
          path: 'hw1/1',
          component: Hw11,
        },
        {
          path: 'hw1/2',
          component: Hw12,
        },
        {
          path: 'hw1/3',
          component: Hw13,
        },
        {
          path: 'hw1/4',
          component: Hw14,
        },
        {
          path: 'hw2/1',
          component: Hw21
        },
        {
          path: 'hw7/1',
          component: Hw71
        },
        {
          path: 'hw7/2',
          component: Hw72
        },
        {
          path: 'hw7/3',
          component: Hw73
        },
        {
          path: 'hw8/1',
          component: Hw81
        }
      ]

    },
    {
      path: '/',
      redirect: '/home/hw1/1',
    }

  ]
})
