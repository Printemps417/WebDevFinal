import request from '@/utils/request'
import axios from 'axios'

const AccessTokenKey = 'ACCESS_TOKEN'
const RefreshTokenKey = 'REFRESH_TOKEN'

export function getAccessToken () {
  return localStorage.getItem(AccessTokenKey)
}

export function setToken (token) {
  localStorage.setItem(AccessTokenKey, token)
}
export function setAccessToken (token) {
  window.localStorage.setItem(AccessTokenKey, token)
}

export function setRefreshToken (token) {
  window.localStorage.setItem(RefreshTokenKey, token)
}

export function removeToken () {
  localStorage.removeItem(AccessTokenKey)
}

export function getRefreshToken () {
  return localStorage.getItem(RefreshTokenKey)
}

export function removeRefreshToken () {
  localStorage.removeItem(RefreshTokenKey)
}
export function ReloadToken () {
  const headers = {
    REFRESHTOKEN: 'Bearer ' + getRefreshToken()
  }
  return axios.get('/proxy/accounts/reloadtoken', { headers })
    .then(res => {
      console.log(res.data.data)
      setAccessToken(res.data.data.accessToken)
      setRefreshToken(res.data.data.refreshToken)
      console.log("刷新token成功！")
    })
}
export function refreshToken () {
  const headers = {
    REFRESHTOKEN: 'Bearer:' + getRefreshToken()
  }
  const refreshToken = getRefreshToken()
  console.log(headers)
  // let data = {
  //   'REFRESHTOKEN': "Bearer:" + refreshToken
  // }
  return axios.get('/proxy/accounts/reloadtoken', { headers })
    .then((res) => {
      console.log(res)
      setAccessToken(AccessTokenKey, res.data.data.accessToken)
      setRefreshToken(RefreshTokenKey, res.data.data.refreshToken)
      console.log("刷新成功！")
    })
    .catch((e) => {
      console.log("刷新过程出错")
      console.log(e)
    })
}