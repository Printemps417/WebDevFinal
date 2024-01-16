import request from '@/utils/request'

export function getstudentlist() {
    const url="/student/"
    return request({
      url: url,
      method: 'get'
    })
}

export function getstudentbyid(id) {
    const url="/student/"+id
    return request({
      url: url,
      method: 'get',
    })
  }

  export function addstudent(data) {
    const url="/student/"
    return request({
      url: url,
      method: 'post',
      data:data
    })
  }

  export function updatestudent(data) {
    const url="/student/"
    return request({
      url: url,
      method: 'put',
      data:data
    })
  }
  
  export function deletestudent(id) {
    const url="/student/"+id
    return request({
      url: url,
      method: 'delete'
    })
  }
  