import request from '@/utils/request'


export function getAllStudentlessons() {
  const url="/studentlesson/"
  return request({
    url: url,
    method: 'get'
  })
}


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