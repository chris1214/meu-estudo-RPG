<script>
export default{
    props: ['id', 'name'],
    data(){
        return{
          httpMesa: 'http://localhost:3000/mesas',
          activeName: 'first',
          propio: false,
          existente: false,
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
          faixaEtaria: [
            {
              label: '+10',
              value: '10'
            },
            {
              label: '+12',
              value: '12'
            },
            {
              label: '+14',
              value: '14'
            },
            {
              label: '+16',
              value: '16'
            },
            {
              label: '+18',
              value: '18'
            }
          ],
          resultados: {
            tipoDoSistema: '',
            valueTiposDeMesas: '',
            faixaEtaria: '',
            title: '',
            url: '',
            mestre:'',
            idMestre: '',
            playersMin: '',
            playersMax: '',
            mapaPrincipal: '',
            descricao: '',
            players: [],
            mapasSecundarios: []
          },
          mapasSecundariosTitle: '',
          mapasSecundariosUrl: '',
          tipo: '',
          buttonProximo: false,
          mapas: true,
          regra: {
            faixaEtaria: [
              { required: true, message: 'O tipo do sistema é obrigatório',trigger: 'change' }
            ],
            title: [
              { required: true, message: 'Senha obrigatório'}
            ],
            url: [
              { required: true, message: 'Imagem principal da mesa é obrigatório'}
            ],
            playersMin: [
              { required: true, type: 'number', message: 'Somente numeros sao permitidos',},
              {min: 1, message: 'No minimo 1 player',trigger: 'blur'}
            ]
          }
        }
    },
    components:{
    },
    methods: {
      create(){
       this.resultados.mestre = this.name;
       this.resultados.idMestre = this.id;
       this.resultados.tipoDoSistema = this.tipo;

        this.$http.post(`${this.httpMesa}`, this.resultados).then(
          response => {
            this.resultados = {
              tipoDoSistema: '',
              valueTiposDeMesas: '',
              faixaEtaria: '',
              title: '',
              url: '',
              mestre:'',
              idMestre: '',
              playersMin: '',
              playersMax: '',
              mapaPrincipal: '',
              descricao: '',
              players: [],
              mapasSecundarios: []
            }
          }, error => {
            this.$message.error('Erro');
          }
        )
      },
      addMapa(){
        let title = this.mapasSecundariosTitle;
        let url = this.mapasSecundariosUrl;
        let myPush = this.resultados.mapasSecundarios;

        myPush.push({'title': title, 'url': url});

        this.mapasSecundariosTitle = '';
        this.mapasSecundariosUrl = '';
      },
      proximo() {
        this.mapas = false;
        this.activeName = 'second';
      }
    },
    watch: {
      tipo: function (value){
        if(value == 'propio'){
          this.propio = true,
          this.existente = false
        }else {
          this.propio = false,
          this.existente = true
        }
      },
      resultados: function (value) {
        console.log(valu.faixaEtariae);
        console.log(value.title);
        console.log(value.url);
        console.log(value.playersMin)
        console.log(value)
      }
    }
}
</script>
<template>
  <div>
    <el-row style="margin-top: 8%">
      <el-col :span="22" :offset="1">
        <b-card title="Crie sua Mesa"
                class="mb-2">
          {{mapas}}
          <el-tabs v-model="activeName">
            <el-tab-pane label="Sistema" name="first">
              <el-form label-position="top" :model="resultados" :rules="regra">
                <el-row :gutter="20">
                  <el-col :span="4">
                    <el-form-item label="Tipo de Sistema">
                      <el-select v-model="tipo" placeholder="Select">
                        <el-option
                          v-for="item in tiposDeSistema"
                          :key="item.value"
                          :label="item.tipo"
                          :value="item.value">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <span v-show="propio">
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
                    <el-form-item prop="faixaEtaria" :rules="regra.faixaEtaria"  label="Faixa etária da mesa">
                      <el-select v-model="resultados.faixaEtaria">
                        <el-option
                          v-for="item in faixaEtaria"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="4">
                    <el-form-item prop="title" :rules="regra.title" label="Titulo da Mesa">
                      <el-input v-model="resultados.title" type="text"/>
                    </el-form-item>
                  </el-col>
                  <el-col :span="4">
                    <el-form-item  prop="url" :rules="regra.url" label="URL da Imagem para a mesa">
                      <el-input v-model="resultados.url"/>
                    </el-form-item>
                  </el-col>
                  </span>
                  <span v-show="existente">
                  <el-col :span="4">
                    <el-form-item label="Sistema">
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
                    <el-form-item label="Faixa etária da mesa">
                      <el-select v-model="resultados.faixaEtaria">
                        <el-option
                          v-for="item in faixaEtaria"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="4">
                    <el-form-item label="Titulo da Mesa">
                      <el-input v-model="resultados.title" type="text"/>
                    </el-form-item>
                  </el-col>
                  <el-col :span="4">
                    <el-form-item label="URL da Imagem para a mesa">
                      <el-input v-model="resultados.url"/>
                    </el-form-item>
                  </el-col>
                  </span>
                </el-row>
                <el-row :gutter="20">
                  <el-col :span="4">
                    <el-form-item prop="playersMin" :rules="regra.playersMin" label="Quantidade minima de players">
                      <el-input v-model="resultados.playersMin"/>
                    </el-form-item>
                  </el-col>
                  <el-col :span="4">
                    <el-form-item label="Quantidade Maxima de players">
                      <el-input v-model="resultados.playersMax"/>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col>
                    <el-form-item label="Descrição da mesa">
                      <el-input type="textarea" v-model="resultados.descricao"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row v-show="buttonProximo">
                  <el-button @click="proximo()" >Próximo</el-button>
                </el-row>
              </el-form>
            </el-tab-pane>
            <el-tab-pane :disabled="mapas" label="Mapas" name="second">
              <el-form label-position="top">
                <el-row :gutter="20">
                  <el-col :span="6">
                    <el-form-item label="Mapa principal">
                      <el-input v-model="resultados.mapaPrincipal" />
                    </el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="Titulo do Mapas secundarios">
                      <el-input v-model="mapasSecundariosTitle" />
                    </el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="Imagem do Mapas secundarios">
                      <el-input v-model="mapasSecundariosUrl" />
                    </el-form-item>
                  </el-col>
                  <el-col :span="3">
                    <el-form-item label="Mapas secundarios">
                      <el-button @click="addMapa" size="mini">Add Mapa secundario</el-button>
                    </el-form-item>
                  </el-col>
                  <el-col :span="3">
                    <p v-for="mapa in resultados.mapasSecundarios">
                      <b>
                        {{mapa.title}}
                      </b>
                    </p>
                    </el-col>
                </el-row>
                <el-row>
                  <el-col>
                    <el-button @click="create()" size="mini">Criar</el-button>
                  </el-col>
                </el-row>
              </el-form>
            </el-tab-pane>
          </el-tabs>
        </b-card>
      </el-col>
    </el-row>
  </div>
</template>
<style>
</style>
