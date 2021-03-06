import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8080/';
// axios.defaults.withCredentials = true;

// 添加请求拦截器
axios.interceptors.request.use(function (config) {
  // 在发送请求之前做些什么
  console.log('请求拦截');
  return config;
}, function (error) {
  // 对请求错误做些什么
  return Promise.reject(error);
});

// 添加响应拦截器
axios.interceptors.response.use(function (response) {
  // 对响应数据做点什么
  console.log('响应拦截', response);
  let url = response.config.url;
  if (url.split('/').pop() === 'login') {
    if (response.data.code == 0) {
      localStorage.setItem('token', response.data.token);
    }
  }
  return response;
}, function (error) {
  // 对响应错误做点什么
  return Promise.reject(error);
});

export default axios;