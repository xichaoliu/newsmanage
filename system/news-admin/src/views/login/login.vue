<template>
  <div class="login-container">
    <el-form ref="loginForm" :model="form" class="login-form" auto-complete="on">
      <el-form-item prop="username">
        <el-input
          v-model="form.usrname"
          name="username"
        ></el-input>
      </el-form-item>
      <el-form-item prop="passowrd">
        <el-input
          :type="'password'"
          v-model="form.password"
          name="password"
        ></el-input>
      </el-form-item>
      <el-button :loading="loading" type="primary" @click.native.prevent="handleLogin">Login</el-button>
    </el-form>
  </div>
</template>
<script>
import ApiService from '@/api/api.js'
export default {
  name: 'Login',
  data() {
    return {
      form: {
        usrname: '',
        password: ''
      },
      loading: false
    }
  },
  methods: {
    handleLogin() {
    ApiService.loginByUsername(this.form.usrname, this.form.password)
      .then((res)=> {
        if (res.data.code == 0) {
          this.$router.push('home');
        }
        console.log('登录成功', res)
      })
      .catch((err) => {
        console.log('错误', err);
      })
    }
  }
}
</script>
<style lang='scss' scoped >
  $bg:#283443;
  $light_gray:#eee;
  $cursor: #fff;
.login-container {
     min-height: 100%;
    width: 100%;
    background-color: #2d3a4b;
    overflow: hidden;
    .login-form {
      position: relative;
      width: 520px;
      max-width: 100%;
      padding: 160px 35px 0;
      margin: 0 auto;
      overflow: hidden;
    }
}

.login-container {
    .el-input {
      display: inline-block;
      height: 47px;
      width: 85%;
      input {
        background: transparent;
        border: 0px;
        -webkit-appearance: none;
        border-radius: 0px;
        padding: 12px 5px 12px 15px;
        color: $light_gray;
        height: 47px;
        caret-color: $cursor;
        &:-webkit-autofill {
          -webkit-box-shadow: 0 0 0px 1000px $bg inset !important;
          -webkit-text-fill-color: $cursor !important;
        }
      }
    }
    .el-form-item {
      border: 1px solid rgba(255, 255, 255, 0.1);
      background: rgba(0, 0, 0, 0.1);
      border-radius: 5px;
      color: #454545;
    }
  }
</style>