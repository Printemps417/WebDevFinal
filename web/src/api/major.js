import request from '@/utils/request'

export function getStudentLessonById(id) {
    const url="/studentlesson/"+id
    return request({
      url: url,
      method: 'get'
    })
}

export function deleteStudentLesson(sid,lid) {
    const url="/studentlesson/"+sid+"/"+lid
    return request({
      url: url,
      method: 'delete'
    })
}

export function getAllmajors() {
    const url="/majors/"
    return request({
      url: url,
      method: 'get'
    })
}

export function addmajor() {
    const url="/majors/add"
    const data={
        "majorName": "艺术建筑"

    }
    return request({
      url: url,
      method: 'post',
      data:data
    })
}

export function updatemajor() {
    const url="/majors/update"
    const data={
        "majorId": "14",
        "majorName": "艺术建筑考古"
      }
    return request({
      url: url,
      method: 'post',
      data:data
    })
}
