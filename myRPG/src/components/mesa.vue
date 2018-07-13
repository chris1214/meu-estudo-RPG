<script>
import {httpPlayer, httpMesa, httpMesasList, httpPlayersList,httpChatMesaList, httpMesasUpdate, httpPlayersSave} from '../http'
export default{
  props: ['mesaId', 'title', 'name', 'id'],
    data(){
      return{
        mesas: [],
        mesa: [],
        player: [],
        activeName: 'first',
        voltar: `/inicio/${this.name}/${this.id}`,
        chat: {
          userText: '',
          text: '',
          user: this.name,
          userID: this.id,
          mesaID: this.mesaId
        },
        myChat:[],
        value1: null,
        url: '',
        urlYoutube: '',
        play: '',
        players: [],
        newPlayers: {
          user: this.name,
          mesaId: this.mesaId,
        },
        mesaTrue: false,
        playersTrue: false,
        httpMesa: httpMesa,
        httpPlayer: httpPlayer
      }
  },
  components:{
  },
  methods: {
    getAll(http, mesa, id = ''){
      this.$http.get(`${http}${id}`)
        .then(
          response => {
            this[mesa] = response.body;
            console.log(this.players)
          }, error => {
            console.log('Error')
          }
        )
    },
    getMesa() {
      this.getAll(`${this.httpMesa}`, 'mesa', `/${this.mesaId}`);
      this.getAll(`${httpChatMesaList}`, 'myChat', `/${this.mesaId}`);
      this.getAll(`${this.httpPlayer}`, 'players', `/${this.mesaId}`);
    },
    enviar(total, players){

      if(players <= 0){
        this.$message.error('Desculpenos, porem a mesa esta lotada');
      }else {
        this.$http.put(`${httpMesasUpdate}/${this.mesaId}`, this.mesa).then(
          response => {
            this.mesa = response.body;
          }, error => {
            console.log('Error')
          }
        )
        .then(
          function () {
            this.$http.post(`${httpPlayersSave}`, this.newPlayers).then(
              response => {
                this.newPlayers = {
                  user: this.name,
                  mesaId: this.mesaId,
                };
                this.getAll(`${this.httpPlayer}`, 'players', `/${this.mesaId}`);
              }, error => {
                console.log('Error')
              }
            )
        }
      );
      }
    },
    totalDeVagas(){
      var total = 1;
      var players;
      for(var i = 0; i <= this.mesa.vagas; i++){
        players = i
      }
      if(players == 0) {
        players = 1
      }
      this.enviar(total, players)
    },
    entrarNaMesa(){
      this.getAll(`${this.httpPlayer}`, 'players', `${this.mesaId}`);
      this.totalDeVagas();
    },
    submitChat(){
      var texto = this.chat.userText;
      var textYoutube = texto.split('play')[0];
      var urlYoutube = texto.split('play')[1];
      var puse = texto.split('!')[1];
      var textTr = texto.split('d')[0];
      var dado = texto.split('d')[1];
      var numeroDado = Number(dado);
      var text = this.chat.text = this.chat.userText;

      if(puse == "exite"){
        this.play = ''
      }
      if(textYoutube == "!"){
        console.log(urlYoutube);
        this.chama(urlYoutube)
      }
      if (textTr == '/r '){
        if(typeof numeroDado === "number"){
          var resultado = Math.round(Math.random() * dado)
          this.chat.text = '/r d' + dado + ' = ' + resultado
        }
      }

      this.$http.post(`${httpChatMesaList}`, this.chat).then(
        response => {
          this.getAll(`${httpChatMesaList}`, 'myChat', `?mesaID=${this.mesaId}`);
          this.chat = {
            userText: '',
            text: '',
            user: this.name,
            userID: this.id,
            mesaID: this.mesaId
          }
        }, error => {
          console.log('Error')
        }
      )
    },
    getLink(url) {
        var regExp = /^.*(youtu.be\/|v\/|u\/\w\/|embed\/|watch\?v=|\&v=)([^#\&\?]*).*/;
        var match = url.match(regExp);

        if (match && match[2].length == 11) {
            return match[2];
        } else {
            return 'error';
        }
    },
    chama(urlYoutube){
      var videoId = this.getLink(urlYoutube);
      this.url = 'https://www.youtube.com/embed/'+videoId+'?autoplay=1';
      console.log(this.url);
      this.play = this.url
    }
  },
  watch: {
  },
  created(){
    this.getMesa();
  }
}
</script>
<template>
  <div>
    <div class="img">
    </div>
    <b-container>
      <b-row style="margin-top: 2%;">
        <b-col md="12">
          <b-card :title="mesa.title">
            <el-tabs>
              <el-tab-pane label="Descrição da mesa">
                <el-row>
                  <el-col :span="12">
                    <el-row :gutter="40">
                      <el-col>
                        <p><b>Mestre: </b>{{mesa.mestre}}</p>
                      </el-col>
                      <el-col>
                        <p><b>Faixa Etaria: </b>{{mesa.faixaEtaria}}</p>
                      </el-col>
                      <el-col>
                        <p><b>Sistema: </b>{{mesa.tipoDoSistema}}</p>
                      </el-col>
                      <el-col>
                        <p><b>Tipo da Mesa: </b>{{mesa.valueTiposDeMesas}}</p>
                      </el-col>
                      <el-col>
                        <p><b>Players: </b>
                          <span v-for="player in players">
                              {{player.user}},
                          </span>
                        </p>
                      </el-col>
                      <el-col>
                        <p><b>Vagas: </b>{{mesa.vagas}} de {{mesa.playersMax}}</p>
                      </el-col>
                    </el-row>
                  </el-col>
                  <el-col :span="12" class="containerImgPrincipal">
                    <p><b>Mapa Principal: </b></p>
                    <img class="imgPrincipal" :src="mesa.mapaPrincipal">
                  </el-col>
                </el-row>
                <el-row>
                  <el-col>
                    <p><b>Descrição da mesa:</b></p>
                  </el-col>
                  <el-col>
                    <p>{{mesa.descricao}}</p>
                  </el-col>
                </el-row>
              </el-tab-pane>
              <el-tab-pane label="Ficha">
                <el-button @click="entrarNaMesa()">Pedido para jogo</el-button>
              </el-tab-pane>
              <el-tab-pane label="Chat Geral">
                <b-row>
                  <b-col md="2">
                    <el-collapse class="myChats">
                      <el-collapse-item title="Chat-de-Voz">
                        <el-row style="padding: 5px;">
                          <el-col :span="24" class="list-friends">
                              <img width="35" height="35" src="http://cs625730.vk.me/v625730358/1126a/qEjM1AnybRA.jpg">
                              <p>{{name}}</p>
                          </el-col>
                          <el-col :span="24" class="list-friends">
                              <img width="35" height="35" src="http://cs625730.vk.me/v625730358/1126a/qEjM1AnybRA.jpg">
                              <p>{{name}}</p>
                          </el-col>
                          <el-col :span="24" class="list-friends">
                              <img width="35" height="35" src="http://cs625730.vk.me/v625730358/1126a/qEjM1AnybRA.jpg">
                              <p>{{name}}</p>
                          </el-col>
                        </el-row>
                      </el-collapse-item>
                      <el-collapse-item title="Feedback" class="myFeedback">
                        <div class="block">
                          <span class="demonstration">Default</span>
                          <el-rate v-model="value1"></el-rate>
                        </div>
                      </el-collapse-item>
                      <el-collapse-item title="Efficiency">

                      </el-collapse-item>
                      <el-collapse-item title="Bot-commands">
                        <p style="color: black"><b>Rolar dado = </b>/r d20 (Troque o 20 pela quantidade de faces do dado)</p>
                        <p style="color: black"><b>!play (url do video) = </b>toca musica do Youtube</p>
                        <p style="color: black"><b>!exite = </b>tira a musica</p>
                      </el-collapse-item>
                    </el-collapse>
                  </b-col>
                  <b-col md="10">
                    <b-card class="cardChat" title="Bate-papo">
                      <div v-for="chat in myChat">
                        <p>
                          <b>{{chat.user}}: </b>
                          {{chat.text}}
                        </p>
                      </div>
                    </b-card>
                    <el-form>
                      <el-row>
                        <el-col :span="22">
                          <el-form-item>
                            <el-input class="myInputSubmit" type="text" v-model="chat.userText"></el-input>
                          </el-form-item>
                        </el-col>
                        <el-col :span="2">
                          <el-form-item>
                            <el-button class="myButtonSubmit" native-type="submit" @click="submitChat" type="primary" size="mini">Enviar</el-button>
                          </el-form-item>
                        </el-col>
                      </el-row>
                    </el-form>

                  </b-col>
                </b-row>
              </el-tab-pane>
              <el-tab-pane label="Assistir" v-show="true">
              </el-tab-pane>
            </el-tabs>
            <router-link :to="voltar">
              <el-button size="mini">Voltar</el-button>
            </router-link>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
    <iframe id="musica" class="myYoutubeVideo" v-show="true" width="420" height="315"
            :src="play"  frameborder="0" allow="autoplay; encrypted-media" allowfullscreen>
    </iframe >
  </div>
</template>
<style>
.list-friends img{
  border-radius: 50%;
  border: 3px solid #696c75;
}
.list-friends p{
  color: black;
  margin-top: 1em;
  margin-left: 1em;
}
.list-friends{
  display: flex;
}
.myChats .el-collapse-item__header{
  color: #ffffff;
  background-color: #0000003b;
}
.myFeedback .el-collapse-item__content{
  color: #ffffff;
  background-color: black !important;
}
.myChats .el-collapse-item__content{
  color: #ffffff;
  background-color: #0000003b;
}
.myInputSubmit input.el-input__inner{
  height: 60px;
  border-bottom-right-radius: 0;
  border-top-right-radius: 0;
  border-top-left-radius: 0;
}
</style>
<style scoped>
.myYoutubeVideo {
    opacity: 0;
    position: absolute;
    left: 0;
    top: 0;
    z-index: -10000;
}
.myButtonSubmit {
  height: 60px;
  border-bottom-left-radius: 0px;
  border-top-left-radius: 0px;
  border-top-right-radius: 0px;
  width: 100%;
}
.char {
  width: 39px;
  height: 35px;
  border-radius: 100px;
  margin: 5px;
}
.cardChat {
  overflow: auto;
  min-height: 70vh;
  max-height: 70vh;
  background-color: #fffcfc4d;
  border-bottom-left-radius: 0;
  border-bottom-right-radius: 0;
}
.card-title {
   text-align: center;
}
.img{
  background-image: url('../img/IlhaFuturista.jpg');
  height: 100vh;
  position: fixed;
  width: 100%;
  top: 0;
  background-repeat:no-repeat;
}
.containerImgPrincipal {
  max-height: 100%;
  max-width: 100%;
}
.imgPrincipal {
  height: 100%;
  width: 100%;
  border: solid 1px yellow;
}
</style>
