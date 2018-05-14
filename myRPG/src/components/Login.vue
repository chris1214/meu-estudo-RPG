<script>
export default {
  data () {
    return {
      httpUsers: 'http://localhost:3000/users',
      users: [],
      values: {
        email: '',
        password: '',
      }
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
        for(var i = 0; i < this.users.length; i++){
          if(this.values.email == this.users[i].email && this.values.password == this.users[i].password){
            console.log(this.users[i].name)
            this.$router.push('/list/' + this.users[i].name + '#' + this.users[i].id)
          }else{
            console.log('Error');
          }
        }
      }else {
        alert("False");
      }
    }
  },
  created(){
    this.myGet();
  }
}

</script>
<template>
  <div>
    <b-container>
      <b-row style="margin-top: 20%;">
        <b-col md="6" offset-md="3">
          <b-card title="Login"
                  class="mb-2">
            <div>
              <el-form label-position="top">
                <el-form-item label="Email">
                  <el-input v-model="values.email"></el-input>
                </el-form-item>
                <el-form-item label="Senha">
                  <el-input v-model="values.password" type="password"></el-input>
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
                  <el-button @click="login" type="primary" size="mini">
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

</style>
