import { createRouter, createMemoryHistory } from 'vue-router';
import Test from './components/TestComponent.vue';

const routes = [
  { path: '/test', component: Test },
]

export const router = createRouter({
  history: createMemoryHistory(),
  routes,
})
