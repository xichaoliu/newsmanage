import axios from './index';

const loginByUsername = (usrname, password) => {
  const data = {
    usrname,
    password
  }
  return axios({
    url: '/api/user',
    method: 'post',
    data
  })
}
const getUserList = () => {
  return axios({
    url:'/api/user',
    method: 'get'
  })
}

 const uploadFile = (data) => {
  return axios({
    url: '/demo/upload',
    method:'post',
    data: data
  })
}

export default {
  loginByUsername,
  getUserList,
  uploadFile
}