import {createRouter, createWebHistory} from "vue-router";

const router = createRouter({
    history : createWebHistory(import.meta.env.BASE_URL),
    routes : [
        {
            path : '/',
            name : 'root',
            component : () => import('../views/main/HomeView.vue')
        },
        {
            path : '/register',
            name : 'register',
            component : () => import('../views/auth/RegisterView.vue')
        },
        {
            path : '/login',
            name :'login',
            component : () => import('../views/auth/LoginView.vue')
        }
    ]
});

export default router;