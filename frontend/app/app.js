import Vue from 'nativescript-vue'
import Home from './pages/Home'
import Theme from '@nativescript/theme'

Theme.setMode(Theme.Light)

let __DEV__ = true

// Prints Vue logs when --env.production is *NOT* set while building
Vue.config.silent = !__DEV__

new Vue({
  render: (h) => h('frame', [h(Home)]),
}).$start()
