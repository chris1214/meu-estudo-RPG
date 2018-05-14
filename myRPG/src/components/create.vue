<script>
export default{
    data(){
        return{
          dados: {
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
            desejo: '',
            experiencia: '',
            experienciaDescricao: ''
          },
        }
    },
    components:{
    },
    methods: {
      
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
              <el-form label-position="top">
                <el-row :gutter="20">
                  <el-col :span="12">
                    <el-form-item label="Email:">
                      <el-input></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="Nome de usuário:">
                      <el-input></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row :gutter="20">
                  <el-col :span="8">
                    <el-form-item label="Senha:">
                      <el-input type="password"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="Confirmação de Senha:">
                      <el-input type="password"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="Data de Nascimento:">
                      <div class="block">
                        <el-date-picker
                          type="date">
                        </el-date-picker>
                      </div>
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-row :gutter="20">
                  <el-col :span="12">
                    <el-form-item label="Desejo me tornar um:">
                      <el-select v-model="resultados.desejo">
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
                    <el-form-item label="Minha experiencia com RPG é: ">
                      <el-select v-model="resultados.experiencia">
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
                      <router-link to="/">
                        <el-button type="primary" size="mini">
                          Cadastre-se
                        </el-button>
                      </router-link>
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
