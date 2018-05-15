<script>
export default{
    data(){
        return{
          dados: {
          httpUsers: 'http://localhost:3000/users',
            desejoMeTornar: [
              {
                label: 'Mestre',
                value: 'mestre',
              },
              {
                label: 'Player',
                value: 'player',
              },
              {
                label: 'Ambos',
                value: 'ambos',
              }
            ],
            minhaExperienciaComRpg: [
              {
                label: 'Alta pois já joguei inumeros sistemas',
                value: 'alta',
              },
              {
                label: 'Media já joguei RPG antes mas não tenho conhecimento sobre sistemas',
                value: 'media',
              },
              {
                label: 'Baixa nunca joguei RPG antes mas gostaria de aprender',
                value: 'baixa',
              }
            ],
          },
          resultados: {
            name: '',
            email: '',
            password: '',
            dataNascimento: '',
            nivelDeMestragem: 0,
            nivelDePlayer: 0,
            tags: {
              desejo: '',
              experiencia: '',
              user: '',
              nivel: ''
            },
            experienciaDescricao: '',
          },
          confirmePassowrd: '',
          regra: {
            email: [{ required: false, type: 'email', message: 'E-mail é obrigatório' }],
            user: [{ required: false, message: 'Nome de Usuário é obrigatório', trigger: 'change' }],
            password: [{ required: false, message: 'Senha é obrigatório', trigger: 'blur' }],
            confirmePassowrd: [{ required: false, message: 'Confirmação de senha é obrigatório', trigger: 'blur' }],
            dataNascimento: [{ required: false, message: 'Data de Nascimento é obrigatório', trigger: 'change' }],
            desejo: [{ required: false, message: 'E-mail é obrigatório', trigger: 'change' }],
            experiencia: [{ required: false, message: 'E-mail é obrigatório', trigger: 'change' }],
          }
        }
    },
    components:{
    },
    methods: {
      pushAll(){
        this.$http.post(`http://localhost:3000/users/`, this.resultados).then(
          response => {
            this.$router.push('/')
          }, error => {
            console.log("Error");
          }
        )
      },
      create(){
        let email = this.resultados.email;
        let name = this.resultados.name;
        let password = this.resultados.password;
        let confirmePassowrd = this.confirmePassowrd;
        let dataNascimento = this.resultados.dataNascimento;
        let desejo = this.resultados.tags.desejo;
        let experiencia = this.resultados.tags.experiencia;
        let experienciaDescricao = this.resultados.experienciaDescricao;


        if (desejo == 'mestre'){

          this.resultados.tags.user = 'Mestre';

        }else if (desejo == 'player'){

          this.resultados.tags.user = 'Player';

        }else if (desejo == 'ambos'){

          this.resultados.tags.user = 'Ambos';

        }

        if(this.resultados.tags.user == 'Mestre'){
          if (experiencia == 'alta' || experiencia == 'media'){

            this.resultados.nivelDeMestragem = 1;
            this.resultados.tags.nivel = 'Mestre';

          }else if (experiencia == 'baixa'){

            this.resultados.nivelDeMestragem = 1;
            this.resultados.tags.nivel = 'Aspirante a Mestre Aprendiz';

          }
        }

        if(this.resultados.tags.user == 'Player'){
          if (experiencia == 'alta' || experiencia == 'media'){

            this.resultados.nivelDePlayer = 1;
            this.resultados.tags.nivel = 'Player';

          }else if (experiencia == 'baixa'){

            this.resultados.nivelDePlayer = 1;
            this.resultados.tags.nivel = 'Player Iniciante';

          }
        }

        if(this.resultados.tags.user == 'Ambos'){
          if (experiencia == 'alta' || experiencia == 'media'){

            this.resultados.nivelDeMestragem = 1;
            this.resultados.nivelDePlayer = 1;
            this.resultados.tags.nivel = 'Player e Mestre';

          }else if (experiencia == 'baixa'){

            this.resultados.nivelDeMestragem = 1;
            this.resultados.nivelDePlayer = 1;
            this.resultados.tags.nivel = 'Player e Mestre Iniciante';

          }
        }
        if (email && name && password && confirmePassowrd == password && dataNascimento && desejo && experiencia){
          console.log("True")
          this.pushAll();
        }else{
          console.log("OPS")
        }
      }
    }
}

</script>
<template>
  <div>
    <b-container>
      <b-row style="margin-top: 10%;">
        <b-col md="8" offset-md="2">
          <b-card title="Create"
                  class="mb-2">
            {{resultados.email}}
            <div>
              <el-form label-position="top" :rules="regra">
                <el-row :gutter="20">
                  <el-col :span="12">
                    <el-form-item label="Email:">
                      <el-input type="email" v-model="resultados.email"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="Nome de usuário:" prop="user">
                      <el-input v-model="resultados.name" type="text"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row :gutter="20">
                  <el-col :span="8">
                    <el-form-item label="Senha:" prop="password">
                      <el-input v-model="resultados.password" type="password"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="Confirmação de Senha:" prop="confirmePassowrd">
                      <el-input v-model="confirmePassowrd" type="password"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="Data de Nascimento:" prop="dataNascimento">
                      <div class="block">
                        <el-date-picker
                          v-model="resultados.dataNascimento"
                          type="date">
                        </el-date-picker>
                      </div>
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-row :gutter="20">
                  <el-col :span="12">
                    <el-form-item label="Desejo me tornar um:" prop="desejo">
                      <el-select v-model="resultados.tags.desejo">
                        <el-option
                          v-for="item in dados.desejoMeTornar"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="Minha experiencia com RPG é: " prop="experiencia">
                      <el-select v-model="resultados.tags.experiencia">
                        <el-option
                          v-for="item in dados.minhaExperienciaComRpg"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row :gutter="20">
                  <el-col :span="24">
                    <el-form-item label="Sua Experiencia com RPG">
                      <el-input
                        type="textarea"
                        :rows="4"
                        v-model="resultados.experienciaDescricao">
                      </el-input>
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-form-item>
                  <el-row>
                    <el-col :span="12">
                      <el-button @click="create()" type="primary" size="mini">
                        Cadastre-se
                      </el-button>
                    </el-col>
                    <el-col class="align-right" :span="12">
                      <router-link to="/">
                        <el-button type="defalt" size="mini">
                          Voltar
                        </el-button>
                      </router-link>
                    </el-col>
                  </el-row>
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
