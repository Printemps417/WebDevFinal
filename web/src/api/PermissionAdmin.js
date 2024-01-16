import request from '@/utils/request'

export function getUserPermission(){
    return JSON.parse(localStorage.getItem("userInfo")).user_permission
}

export function AllPermissions(){
    return request({
        url: '/permission/getAllPermissions',
        method: 'get'
      })
}