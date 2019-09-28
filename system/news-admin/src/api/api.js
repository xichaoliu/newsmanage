import axios from './index';

export function loginByUsername(usrname, password) {
  const data = {
    usrname,
    password
  }
  return axios({
    url: '/api/login',
    method: 'post',
    data
  })
}