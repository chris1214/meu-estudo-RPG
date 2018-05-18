<script>
export default{
    data(){
        return{
          upload: {
            title: '',
            url: '',
          },
          httpMesa: 'http://localhost:3000/mesas',
          activeName: 'first',
          tiposDeSistema: [
            {tipo:  'Próprio', value: 'propio'},
            {tipo: 'Existente', value: 'existente'}
          ],
          tiposDeMesas: [
            {
              label: 'Medieval',
              value: 'Medieval'
            },
            {
              label: 'CyberPunck',
              value: 'CyberPunck'
            },
            {
              label: 'Pirata',
              value: 'Pirata'
            },
            {
              label: 'Horror',
              value: 'Horror'
            }
          ],
          resultados: {
            tipoDoSistema: '',
            valueTiposDeMesas: ''
          }
        }
    },
    components:{
    },
    methods: {
      create(){
        this.$http.post(`${this.httpMesa}`, this.upload).then(
          response => {
            upload: {
              title: '';
              url: ''
            }
          }, error => {
            this.$message.error('Erro');
          }
        )
      }
    }
}



</script>
<template>
  <div>
    <el-row style="margin-top: 8%">
      <el-col  :span="22" :offset="1">
        <b-card title="Crie sua Mesa"
                class="mb-2">
          <el-tabs v-model="activeName">
            <el-tab-pane label="Sistema" name="first">
              <el-form label-position="top">
                <el-row :gutter="20">
                  <el-col :span="4">
                    <el-form-item label="Tipo de Sistema">
                      <el-select v-model="resultados.tipoDoSistema" placeholder="Select">
                        <el-option
                          v-for="item in tiposDeSistema"
                          :key="item.value"
                          :label="item.tipo"
                          :value="item.value">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="4">
                    <el-form-item label="Tipo da Mesa">
                      <el-select v-model="resultados.valueTiposDeMesas" placeholder="Tipos de Mesa">
                        <el-option
                          v-for="item in tiposDeMesas"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="4">
                    <el-form-item label="Activity form">
                      <el-input ></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
              </el-form>
            </el-tab-pane>
            <el-tab-pane label="Config" name="second">Config</el-tab-pane>
            <el-tab-pane label="Role" name="third">Role</el-tab-pane>
            <el-tab-pane label="Task" name="fourth">Task</el-tab-pane>
          </el-tabs>
        </b-card>
      </el-col>
    </el-row>
  </div>
</template>
<style>
</style>
