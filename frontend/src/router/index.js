import { createRouter, createWebHistory } from 'vue-router'
import RecipeListView from '../views/RecipeList/RecipeListView.vue'
import RecipeDetailView from '../views/RecipeDetail/RecipeDetailView.vue'
import LandingPageView from '../views/LandingPage/LandingPageView.vue'

const routes = [
  {
    path: '/',
    name: 'recipe-list',
    component: RecipeListView,
  },
  {
    path: '/recipe/:id',
    name: 'recipe-detail',
    component: RecipeDetailView,
  },
  {
    path: '/home',
    name: 'landing-page',
    component: LandingPageView,
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
