import Vue from 'vue'
import Router from 'vue-router'
import Home from "@/components/Home"
import Hw21 from "@/components/hw2/hw2-1"
import Hw71 from "@/components/hw7/hw7-1"
import Hw72 from "@/components/hw7/hw7-2"
import Hw73 from "@/components/hw7/hw7-3"


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/home',
      name: 'Home',
      component: Home,
      children:[
        {
          path: 'hw7',
          component: Hw71,
          children:[
            {
              path:'1',
              component: Hw71
            },
            {
              path:'2',
              component: Hw72
            },
            {
              path:'3',
              component: Hw73
            }
          ]
        },
        {
          path: 'hw2',
          component: Hw21,
          children:[
            {
              path:'1',
              component: Hw21
            }
          ]
        }
      ]

    },
    {
      path: '/',
      redirect: '/home',
    }

  ]
})
