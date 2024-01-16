import request from '@/utils/request'

export function getteacherlist() {
    const url="/teachers/list"
    return request({
      url: url,
      method: 'get'
    })
}

export function getteacherbyid(id) {
    const url="/teachers/"+id
    return request({
      url: url,
      method: 'get',
    })
  }

  export function addteacher(data) {
    const url="/teachers/add"
    console.log(data)
    return request({
      url: url,
      method: 'post',
      data:data
    })
  }

  export function updateteacher(data) {
    const url="/teachers/update"
    return request({
      url: url,
      method: 'put',
      data:data
    })
  }
  
  export function deleteteacher(id) {
    const url="/teachers/delete/"+id
    return request({
      url: url,
      method: 'delete'
    })
  }
  