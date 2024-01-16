import request from '@/utils/request'



export function getselectlist() {
    return request({
      url: 'lessons/list',
      method: 'get'
    })
  }