import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}
const routes = [{
    path: "/parent",
    name: "Parent",
    component: () =>
        import("../views/Parent.vue"),
    redirect: "/parent/home",
    children: [{
        path: '403',
        name: 'NoAuth',
        meta: { name: '无权限' },
        component: () =>
            import('../views/parent/403.vue')
    },
    {
        path: 'home',
        name: 'Phome',
        meta: { name: '系统首页' },
        component: () =>
            import('../views/parent/Home.vue'),
        redirect: "/parent/activity"
    },
    {
        path: "courses",
        name: "Courses",
        component: () =>
            import("../views/parent/course/Courses.vue"),
        meta: { name: "课程信息" }

    },
    {
        path: "coursedetail",
        name: "CourseDetail",
        component: () =>
            import("../views/parent/course/CourseDetail.vue"),
        meta: { name: "课程信息" }

    },
    {
        path: "entertainment-management",
        name: "Entertainment-management",
        component: () =>
            import("../views/parent/user/Entertainment-management.vue"),
        meta: { name: "文娱管理" },
        children: [



        ]
    },
    {
        path: "surveys",
        name: "Surveys",
        component: () =>
            import("../views/parent/survey/Surveys.vue"),
        meta: { name: "问卷调查" }
    },
    {
        path: "surveyfeedback",
        name: "SurveyFeedback",
        component: () =>
            import("../views/parent/survey/SurveyFeedback.vue"),
        meta: { name: "反馈意见" }

    },
    {
        path: "surveyfeedbackdetail",
        name: "SurveyFeedbackDetail",
        component: () =>
            import("../views/parent/survey/SurveyFeedbackDetail.vue"),
        meta: { name: "反馈意见" }

    },
    {
        path: "leaveRequest",
        name: "LeaveRequest",
        component: () => import("../views/parent/leavequest/LeaveRequest.vue"),
        meta: { name: "班级管理" }
    },
    {
        path: "leaveRequestPage",
        name: "LeaveRequestPage",
        component: () => import("../views/parent/leavequest/LeaveRequestPage.vue"),
        meta: { name: "请假申请" }
    },
    {
        path: "activity",
        name: "Activity",
        component: () => import("../views/parent/entertainment-management/Activity.vue"),
        meta: { name: "活动信息推荐" },
        children: [

        ]
    },
    {
        path: "activityform",
        name: "ActivityFrom",
        component: () => import("../views/parent/entertainment-management/ActivityForm.vue"),
        meta: { name: "活动信息" },
    },
    {
        path: "dishform",
        name: "DishForm",
        component: () => import("../views/parent/entertainment-management/dish/DishForm.vue"),
        meta: { name: "每日菜谱" },
    },
    {
        path: "dishdetail",
        name: "DishDetail",
        component: () => import("../views/parent/entertainment-management/dish/DishDetail.vue"),
        meta: { name: "菜谱详细" },
    },
    {
        path: "surveydetail",
        name: "SurveyDetail",
        component: () => import("../views/parent/survey/SurveyDetail.vue"),
        meta: { name: "问卷详细" },
    },

    {
        path: "surveyfeedbackpage",
        name: "SurveyFeedbackPage",
        component: () => import("../views/parent/survey/SurveyFeedbackPage.vue"),
        meta: { name: "问卷反馈" },
    },
    {
        path: "activityRegistrationPage",
        name: "ActivityRegistrationPage",
        component: () => import("../views/parent/entertainment-management/ActivityRegistrationPage.vue"),
        meta: { name: "活动报名 " }
    },
    {
        path: "activity-item",
        name: "Activity-item",
        component: () => import("../views/parent/entertainment-management/activity/ActivityItem.vue"),
        meta: { name: "活动详细" }
    },
   {
        path: "user",
        name: "UserCenter",
        component: () => import("../views/parent/UserCenter.vue"),
        redirect: "/parent/user/profile",
        children: [
            {
                path: "profile",
                name: "UserProfile",
                component: () => import("../views/parent/user/Profile.vue"),
                meta: { name: "个人中心" }
            },
            {
                path: "change-password",
                name: "ChangePassword",
                component: () => import("../views/parent/user/ChangePassword.vue"),
                meta: { name: "修改密码" }
            },
            {
                path: 'communication-management',
                name: 'CommunicationManagement',
                component: () =>
                    import("../views/parent/user/Communication-management.vue"),
                meta: { name: "沟通管理" }
            },
            {
                path: 'student-management',
                name: 'StudentManagement',
                component: () =>
                    import("../views/parent/user/Student-management.vue"),
                meta: { name: "学生管理" }
            },
            {
                path: 'my-favorites-management',
                name: 'MyFavoritesManagement',
                component: () =>
                    import("../views/parent/user/MyFavoritesManagement.vue"),
                meta: { name: "我的收藏管理" }
            },
        ]
    },

    ]
},

{
    path: '/login',
    name: 'Login',
    meta: { name: '登录' },
    component: () =>
        import('../views/Login.vue')
}, {
    path: '/registerS',
    name: 'RegisterS',
    meta: { name: '学生注册' },
    component: () =>
        import('../views/RegisterS.vue')
}, {
    path: '/registerP',
    name: 'RegisterP',
    meta: { name: '家长注册' },
    component: () =>
        import('../views/RegisterP.vue')
}, {
    path: '*',
    name: 'NotFound',
    meta: { name: '无法访问' },
    component: () =>
        import('../views/404.vue')
},



]
// })

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) => {
    const user = JSON.parse(localStorage.getItem("xm-parent") || '{}');
    if (to.name === 'Login' || to.name === 'RegisterP'|| to.name === 'RegisterS') {
        next()
    } else if (!user) {
        next({ name: 'Login' })
    } else if (user.sid) {
        if (to.matched.some(record => record.name.startsWith('P'))) {
            next();
        } else {
            next({ name: 'Phome' })
        }
    } else {
        next({ name: 'Login' })
    }
})

export default router