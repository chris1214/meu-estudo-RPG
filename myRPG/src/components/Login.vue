<script>
import {httpUsersList, httpGetUsuario} from '../http'
export default {
  data () {
    return {
      httpUsers: httpUsersList,
      httpUsuario: httpGetUsuario,
      users: [],
      values: {
        email: '',
        password: '',
      },
    }
  },
  methods: {
    getAll(myHttp, resposta){
      this.$http.get(`${myHttp}`).then(
        response => {
          this[resposta] = response.body;
        }, error => {
          console.log('Error')
        }
      )
    },
    myGet(){
      this.getAll(`${this.httpUsers}`, 'users')
    },
    login(){
      if(this.values.email && this.values.password){
        var error = true;
        this.$http.get(`${this.httpUsuario}?email=${this.values.email} && ?password=${this.values.password}`).then(
          response => {
            console.log(`${this.httpUsuario}?email=${this.values.email} && ?password=${this.values.password}`);
            this.users = response.body
          }, error => {
            console.log('error')
          }
        )
        /*for(var i = 0; i <= this.users.length -1; i++){
          if(this.values.email == this.users[i].email && this.values.password == this.users[i].password){
            this.$router.push('/inicio/' + this.users[i].name + '/' + this.users[i].id);
            error = false;
          }
        }*/

        if (error) {
          this.$message.error('Campo E-mail ou Senha incorretos');
        }

      }else {
         this.$message.error('Campo E-mail ou Senha vazio');
      }
    },
  },
  created(){
    this.myGet();
  }
}

</script>
<template>
  <div>
    <div class="background-IMG" />
    <b-container>
      <b-row style="margin-top: 10%;">
        <b-col md="6" offset-md="3">
          <b-card title="Login"
                  class="mb-2">
            <div>
              <el-form ref="form" label-position="top">
                <el-form-item label="Email">
                  <el-input v-model="values.email"></el-input>
                </el-form-item>
                <el-form-item label="Senha">
                  <el-input @enter.13="submit" v-model="values.password" type="password"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-row>
                    <el-col :span="12">
                      <router-link to="/create">Cadastre-se</router-link>
                    </el-col>
                    <el-col :span="12" class="align-right">
                      <router-link to="/">Esqueci a senha</router-link>
                    </el-col>
                  </el-row>
                </el-form-item>
                <el-form-item>
                  <el-button native-type="submit" @click.prevent="login" type="primary" size="mini">
                    Login
                  </el-button>
                </el-form-item>
              </el-form>
            </div>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
<style scoped>
.card-title {
   text-align: center;
}
.background-IMG {
  background-image: url('../img/Ilha.jpg');
  height: 100vh;
  position: fixed;
  width: 100%;
  top: 0;
  background-repeat:no-repeat;
}
</style>
