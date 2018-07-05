<script>
  import upload from '../utils/uploadImg'
  import {httpMesasList, httpMesasSave} from '../../http'
  export default {
    props: ['id', 'name'],
    data() {
      return {
        httpMesa: httpMesasList,
        httpMesaSave: httpMesasSave,
        activeName: 'first',
        propio: false,
        existente: false,
        tiposDeSistema: [
          {tipo: 'Próprio', value: 'propio'},
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
          mestre: '',
          idMestre: '',
          playersMin: '',
          playersMax: '',
          mapaPrincipal: '',
          descricao: '',
          players: [],
          mapasSecundarios: [],
          newFile: {
            name: '',
            type: '',
            url: '',
            size: '',
            file:[]
          },
          vagas: ''
        },
        mapasSecundariosTitle: '',
        mapasSecundariosUrl: '',
        tipo: '',
        buttonProximo: false,
        mapas: true,
        regra: {
          faixaEtaria: [
            {required: true, message: 'O tipo do sistema é obrigatório', trigger: 'change'}
          ],
          title: [
            {required: true, message: 'Senha obrigatório'}
          ],
          url: [
              {required: true, message: 'Imagem principal da mesa é obrigatório'}
            ]
        },
        tipoDeImg: [
          {
            label: 'URL',
            value: 'url'
          },
          {
            label: 'Upload',
            value: 'upload'
          }
        ],
        tipoDeImgResultado: '',
        typeUrl: false,
        typeUpload: false,
      }
    },
    components: {
      upload
    },
    methods: {
      create() {
        this.resultados.mestre = this.name;
        this.resultados.idMestre = this.id;
        this.resultados.tipoDoSistema = this.tipo;
        this.resultados.vagas = this.playersMax;

        this.$http.post(`${this.httpMesaSave}`, this.resultados).then(
          response => {
            this.resultados = {
              tipoDoSistema: '',
              valueTiposDeMesas: '',
              faixaEtaria: '',
              title: '',
              mestre: '',
              idMestre: '',
              playersMin: '',
              playersMax: '',
              mapaPrincipal: '',
              descricao: '',
              players: [],
              mapasSecundarios: [],
              newFile: {
                newFile: '',
                type: '',
                url: '',
                size: '',
                file:[]
              }
            }
          }, error => {
            this.$message.error('Erro');
          }
        )
      },
      addMapa() {
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
      tipo: function (value) {
        console.log(value)
        if (value == 'propio') {
          this.propio = true,
            this.existente = false
        } else {
          this.propio = false,
            this.existente = true
        }
      },
      tipoDeImgResultado: function (value) {
        if (value == 'url') {
          this.typeUrl = true,
            this.typeUpload = false
        } else {
          this.typeUrl = false,
            this.typeUpload = true
        }
      },
      now: function(){
      }
    },
    computed: {
      now: function () {
        if (!this.resultados.faixaEtaria == '' && !this.resultados.title == '' && !this.resultados.newFile.url == '') {
          return this.buttonProximo = true;
        } else {
          return this.buttonProximo = false, this.mapas = true;

        }
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
                </el-row>
                <el-row :gutter="20">
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
                    <el-form-item prop="faixaEtaria" :rules="regra.faixaEtaria" label="Faixa etária da mesa">
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
                    <el-col :span="6">
                    <el-form-item label="Tipo de IMG para demonstração da mesa">
                      <el-select v-model="tipoDeImgResultado">
                        <el-option
                          v-for="item in tipoDeImg"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="4" v-show="typeUrl">
                    <el-form-item prop="newFile.url" :rules="regra.url" label="URL da Imagem para a mesa">
                      <el-input v-model="resultados.newFile.url"/>
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
                  <el-form>
                    <el-col :span="4" v-show="typeUpload">
                      <el-form-item prop="url" :rules="regra.url" label="Upload da Imagem para a mesa">
                        <upload :id="id" :name="name" :array="resultados.newFile"></upload>
                      </el-form-item>
                    </el-col>
                  </el-form>
                </el-row>
                <el-row :gutter="20">
                  <el-col :span="4">
                    <el-form-item label="Quantidade minima de players">
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
                  <el-button @click="proximo()">Próximo</el-button>
                </el-row>
              </el-form>
            </el-tab-pane>
            <el-tab-pane :disabled="mapas" label="Mapas" name="second">
              <el-form label-position="top">
                <el-row :gutter="20">
                  <el-col :span="6">
                    <el-form-item label="Mapa principal">
                      <el-input v-model="resultados.mapaPrincipal"/>
                    </el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="Titulo do Mapas secundarios">
                      <el-input v-model="mapasSecundariosTitle"/>
                    </el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="Imagem do Mapas secundarios">
                      <el-input v-model="mapasSecundariosUrl"/>
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
