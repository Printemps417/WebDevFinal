import request from '@/utils/request'

export function getselectlist() {
    return request({
      url: 'lessons/list',
      method: 'get'
    })
  }

export function selectlesson(id) {
    const url="/lessons/select/"+id
    return request({
      url: url,
      method: 'get'
    })
}

export function addlesson(data) {
    return request({
      url: 'lessons/add',
      method: 'post',
      data:data
    })
  }

  export function updatelesson(data) {

    return request({
      url: 'lessons/update',
      method: 'put',
      data:data
    })
  }
  
  export function deletelesson(id) {
    const url="/lessons/delete/"+id
    return request({
      url: url,
      method: 'delete'
    })
  }
  