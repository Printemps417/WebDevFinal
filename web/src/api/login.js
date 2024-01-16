import request from '@/utils/request'
import {getAccessToken, getReFreshToken} from '@/utils/auth'
// 用户登录
export function login(username, password,type) {
  const data = {
    username,
    password,
    type
  }
  return request({
    url: '/accounts/login',
    method: 'post',
    data: data
  })
}
export function logout(){

  const params = new URLSearchParams();
  params.append('accessToken', getAccessToken());  
  params.append('refreshToken', getReFreshToken()); 

  return request({
    url:'/logout',
    method:'post',
    data:params,
    headers:{'Content-Type':"application/x-www-form-urlencoded"}
  })
}

// 获取用户信息
export function getInfo() {
  return request({
    url: '/accounts/getinfo',
    method: 'get'
  })
}
