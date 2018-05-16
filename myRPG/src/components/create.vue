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
            email: [
              { required: true, type: 'email', message: 'E-mail é obrigatório' }
            ],
            name: [
              { required: true, message: 'Nome de Usuário é obrigatório'},
              {min: 3, max: 50, message: 'No minimo 3 caracteres e no maximo 50', trigger: 'blur'}
            ],
            password: [
              { required: true, message: 'Senha e obrigatório'},
              {min: 5, max: 50, message: 'No minimo 5 caracteres e no maximo 50', trigger: 'blur'}
            ],
            confirmePassowrd: [{ required: true, message: 'Confirmação de senha é obrigatório'}],
            dataNascimento: [{ required: true, type: 'date', message: 'Data de Nascimento é obrigatório'}],
            tags: {
              desejo: [{ required: true, message: 'Desejo é obrigatório', trigger: 'change'}],
              experiencia: [{ required: true, message: 'Experiencia é obrigatório', trigger: 'change'}],
            },
          },
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
        let confirmePassowrd = this.resultados.confirmePassowrd;
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
        if (email && name && password && dataNascimento && desejo && experiencia){
          if(confirmePassowrd == password){
            this.$message({
              message: 'Novo Usuário criado com sucesso!',
              type: 'success'
            });
            this.pushAll();
          }else{
            this.$message.error('campo Senha e Confirmação de Senha devem ser iguais');
          }
        }else{
        console.log(email)
        console.log(name)
        console.log(password)
        console.log(confirmePassowrd)
        console.log(dataNascimento)
        console.log(desejo)
        console.log(experiencia)
          this.$message.error('Todos os campos com (*) são obrigatórios');
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
            <div>
              <el-form label-position="top" :model="resultados" :rules="regra">
                <el-row :gutter="20">
                  <el-col :span="12">
                    <el-form-item label="Email:" prop="email" :rules="regra.email">
                      <el-input type="email" v-model.email="resultados.email"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="Nome de usuário:" prop="name">
                      <el-input v-model="resultados.name" type="text"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row :gutter="20">
                  <el-col :span="8">
                    <el-form-item label="Senha:"  prop="password">
                      <el-input v-model.password="resultados.password" type="password"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="Confirmação de Senha:" prop="confirmePassowrd">
                      <el-input v-model.password="resultados.confirmePassowrd" type="password"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="Data de Nascimento:" prop="dataNascimento">
                      <div class="block">
                        <el-date-picker
                          v-model.date="resultados.dataNascimento"
                          type="date">
                        </el-date-picker>
                      </div>
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-row :gutter="20">
                  <el-col :span="12">
                    <el-form-item label="Desejo me tornar um:" prop="tags.desejo">
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
                    <el-form-item label="Minha experiencia com RPG é: " prop="tags.experiencia">
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
