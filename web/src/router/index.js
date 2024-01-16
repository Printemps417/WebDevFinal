import Vue from 'vue'
import VueRouter from 'vue-router'

import studentManage from '../views/Admin/studentManage/index'
import addStudent from "@/views/Admin/studentManage/addStudent";
import studentList from "@/views/Admin/studentManage/studentList";
import editorStudent from "@/views/Admin/studentManage/editorStudent";
import teacherManage from "@/views/Admin/teacherManage/index"
import addTeacher from "@/views/Admin/teacherManage/addTeacher";
import editorTeacher from "@/views/Admin/teacherManage/editorTeacher";
import courseManage from "@/views/Admin/courseManage/index";
import addCourse from "@/views/Admin/courseManage/addCourse";
import queryStudent from "@/views/Admin/studentManage/queryStudent";
import queryTeacher from "@/views/Admin/teacherManage/queryTeacher";
import editorCourse from "@/views/Admin/courseManage/editorCourse";
import courseList from "@/views/Admin/courseManage/courseList";
import queryCourse from "@/views/Admin/courseManage/queryCourse";
import selectCourseManage from "@/views/Admin/selectCourseManage/index";
import selectlessonList  from "@/views/Admin/selectCourseManage/selectlessonList";
// import queryCourseTeacher from "@/views/Admin/selectCourseManage/queryCourseTeacher";
import queryGradeCourse from "@/views/Admin/gradeCourseManage/queryGradeCourse";
import editorGradeCourse from "@/views/Admin/gradeCourseManage/editorGradeCourse";

/* Layout */
import Layout from '@/layout'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: Layout, // 修改为 Layout 组件
    children: [
      {
        path: '',
        component: () => import( '../views/HomeView.vue'),
        name: 'home'
      }
    ]
  },
  {
    path: '/student',
    component: ()=> import( '../views/student/student'), 
    redirect:'/student/studenthome',
    children: [
      {
        path: '/student/studenthome',
        component: () => import( '../views/student/studenthome'),
        name: 'studenthome'
      },
      {
        path: '/student/selectlist',
        component: () => import( '../views/student/selectcourselist'),
        name: 'selectcourselis'
      },
      {
        path: '/student/querylist',
        component: () => import( '../views/student/querySelectedCourse'),
        name: 'querySelectedCourse'
      },

    ]
  },

  {
    // admin 的路由
    path: '/admin',
    name: 'admin',
    // redirect: '/adminHome',
    component: ()=> import( '../views/Admin/admin'),
    meta: {requireAuth: true},
    children: [
      // {
      //   path: '/adminHome',
      //   name: 'Hi! admin',
      //   component: adminHome,
      //   // meta: {requireAuth: true},
      //   children: [
      //     {
      //       path: '/adminHome',
      //       name: 'admin 主页',
      //       component: adminHome,
      //       // meta: {requireAuth: true},
      //     }
      //   ]
      // },
      {
        path: '/studentManage',
        name: '学生管理',
        component: studentManage,
        // meta: {requireAuth: true},
        children: [
          {
            path: '/addStudent',
            name: '添加学生',
            component: addStudent,
            // meta: {requireAuth: true}
          },
          {
            path: '/studentList',
            name: '学生列表',
            component: studentList,
            // meta: {requireAuth: true},
          },
          {
            path: '/editorStudent',
            name: '编辑学生',
            component: editorStudent,
            // meta: {requireAuth: true}
          },
          {
            path: '/queryStudent',
            name: '搜索',
            component: queryStudent,
            // meta: {requireAuth: true},
            children: [
              {
                path: '/queryStudent/studentList',
                component: studentList,
                // meta: {requireAuth: true}
              }
            ]
          }
        ]
      },
      {
        path: '/teacherManage',
        name: '教师管理',
        component: teacherManage,
        // meta: {requireAuth: true},
        children: [
          {
            path: '/addTeacher',
            name: '添加教师',
            component: addTeacher,
            // meta: {requireAuth: true}
          },
          {
            path: '/queryTeacher',
            name: '教师列表',
            component: queryTeacher,
            // meta: {requireAuth: true},
            children: [
            ]
          },
          {
            path: '/editorTeacher',
            name: '编辑教师',
            component: editorTeacher,
            // meta: {requireAuth: true}
          },
        ]
      },
      {
        path: '/courseManage',
        name: '课程管理',
        component: courseManage,
        // meta: {requireAuth: true},
        children: [
          {
            path: '/addCourse',
            name: '添加课程',
            component: addCourse,
            // meta: {requireAuth: true}
          },
          {
            path: '/queryCourse',
            name: '搜索课程',
            component: queryCourse,
            // meta: {requireAuth: true},
            children: [
              {
                path: '/courseList',
                name: '课程列表',
                component: courseList,
                // meta: {requireAuth: true}
              },
            ]
          },
          {
            path: '/editorCourse',
            name: '编辑课程',
            component: editorCourse,
            // meta: {requireAuth: true}
          },
        ]
      },
      {
        path: '/selectCourseManage',
        name: '开课表管理',
        component: selectCourseManage,
        // meta: {requireAuth: true},
        children: [
          {
            path: '/selectlessonlist',
            name: '开课管理',
            component: selectlessonList,
            // meta: {requireAuth: true},
          },
        ]
      },
    ]
  },
  // {
  //   path: '/rbac',
  //   component: Layout,
  //   children: [{
  //       path: 'user/list',
  //       component:() => import( '../views/User/List.vue')
  //     },
  //     {
  //       path: 'user/add',
  //       component:() => import( '../views/User/Add.vue')
  //     },
  //     {
  //       path: 'role/list',
  //       component:() => import( '../views/Role/List.vue')
  //     },
  //     {
  //       path: 'role/add',
  //       component:() => import( '../views/Role/Add.vue')
  //     },
  //     {
  //       path: 'permission/list',
  //       component:() => import( '../views/Permission/List.vue')
  //     }
  //   ]
  // },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/Login.vue')
  },
  // {
  //   path: '/student',
  //   name: 'student',
  //   component: student,
  //   redirect: '/studentHome',
  //   meta: {requireAuth: true},
  //   children: [
  //     {
  //       path: '/student',
  //       name: 'hi! student',
  //       component: studentHome,
  //       meta: {requireAuth: true},
  //       children: [
  //         {
  //           path: '/studentHome',
  //           name: '学生主页',
  //           component: studentHome,
  //           meta: {requireAuth: true},
  //         },
  //       ],
  //     },
  //     {
  //       path: '/updateInfo',
  //       name: '学生编辑',
  //       component: updateInfo,
  //       meta: {requireAuth: true},
  //       children: [
  //         {
  //           path: '/updateInfoHome',
  //           name: '编辑学生信息',
  //           component: updateInfo,
  //           meta: {requireAuth: true}
  //         }
  //       ]
  //     },
  //     {
  //       path: '/studentSelectCourseManage',
  //       name: '选课管理',
  //       component: studentSelectCourseManage,
  //       meta: {requireAuth: true},
  //       children: [
  //         {
  //           path: '/studentSelectCourse',
  //           name: '选课',
  //           component: selectCourse,
  //           meta: {requireAuth: true}
  //         },
  //         {
  //           path: '/querySelectedCourse',
  //           name: '查询课表',
  //           component: querySelectedCourse,
  //           meta: {requireAuth: true}
  //         }
  //       ]
  //     },
  //     {
  //       path: '/courseGrade',
  //       name: '学生成绩管理',
  //       component: studentCourseGrade,
  //       meta: {requireAuth: true},
  //       children: [
  //         {
  //           path: '/queryCourseGrade',
  //           name: '成绩查询',
  //           component: queryCourseGrade,
  //           meta: {requireAuth: true}
  //         },
  //       ]
  //     }
  //   ]
  // }

]

// 防止连续点击多次路由报错
let routerPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return routerPush.call(this, location).catch(err => err)
}

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
