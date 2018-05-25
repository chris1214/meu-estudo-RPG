<script>
export default{
  props: ['mesaId', 'title', 'name', 'id'],
    data(){
      return{
        mesa: [],
        httpMesa: 'http://localhost:3000/mesas',
        httpChatMesa: 'http://localhost:3000/chatMesa',
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
        play: ''
      }
  },
  components:{
  },
  methods: {
    getAll(http, mesa, id){
      this.$http.get(`${http}/${id}`).then(
        response => {
          this[mesa] = response.body;
        }, error => {
          console.log('Error')
        }
      )
    },
    getMesa() {
      this.getAll(`${this.httpMesa}`, 'mesa', `${this.mesaId}`);
      this.getAll(`${this.httpChatMesa}`, 'myChat', `?mesaID=${this.mesaId}`);
    },
    enviar(total, players){
      var user = this.name;
      var id = this.id;
      var myPush = this.mesa.players;

      console.log(total)
      console.log(players)

      if(players >= this.mesa.playersMax){
        this.$message.error('Desculpenos, porem a mesa esta lotada');
      }else {

        myPush.push({'user': user, 'id': id});
        this.mesa.vagas = this.mesa.vagas - total;

        this.$http.put(`${this.httpMesa}/${this.mesaId}`, this.mesa).then(
          response => {
            this.mesa = response.body;
          }, error => {
            console.log('Error')
          }
        )
      }
    },
    totalDeVagas(){
      var total = 1;
      var players;
      for(var i = 0; i <= this.mesa.players.length; i++){
        players = i
      }
      if(players == 0) {
        players = 1
      }
      this.enviar(total, players)
    },
    entrarNaMesa(){
      this.totalDeVagas();
    },
    submitChat(){
      var texto = this.chat.userText
      var textYoutube = texto.split('play')[0];
      var urlYoutube = texto.split('play')[1];
      var puse = texto.split('!')[1];
      var textTr = texto.split('d')[0];
      var dado = texto.split('d')[1];
      var numeroDado = Number(dado)
      var text = this.chat.text = this.chat.userText;

      if(puse == "pause"){
        this.play = ''
      }
      if(textYoutube == "!"){
        this.chama(urlYoutube)
      }
      if (textTr == '/r '){
        if(typeof numeroDado === "number"){
          var resultado = Math.round(Math.random() * dado)
          this.chat.text = '/r d' + dado + ' = ' + resultado
        }
      }

      this.$http.post(`${this.httpChatMesa}`, this.chat).then(
        response => {
          this.getAll(`${this.httpChatMesa}`, 'myChat', `?mesaID=${this.mesaId}`);
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
    getId(url) {
        var regExp = /^.*(youtu.be\/|v\/|u\/\w\/|embed\/|watch\?v=|\&v=)([^#\&\?]*).*/;
        var match = url.match(regExp);

        if (match && match[2].length == 11) {
            return match[2];
        } else {
            return 'error';
        }
    },
    chama(urlYoutube){
      var videoId = this.getId(urlYoutube);
      this.url = 'https://www.youtube.com/v/' + videoId + '?autoplay=1';
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
        <b-col md="11" offset-md="1">
          <b-card :title="mesa.title"
                  class="mb-2">
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
                          <span v-for="player in mesa.players">
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
              <el-tab-pane label="Chat de bate-papo">
                <b-row>
                  <b-col md="2">
                    <el-collapse class="myChats">
                      <el-collapse-item title="Chat-de-Voz">
                        <el-row>
                          <el-col :span="8">
                            <img class="char" src="../img/charElfa.jpg">
                          </el-col>
                          <el-col :span="8">
                            <img class="char" src="../img/charElfa.jpg">
                          </el-col>
                          <el-col :span="8">
                            <img class="char" src="../img/charElfa.jpg">
                          </el-col>
                          <el-col :span="8">
                            <img class="char" src="../img/charElfa.jpg">
                          </el-col>
                          <el-col :span="8">
                            <img class="char" src="../img/charElfa.jpg">
                          </el-col>
                          <el-col :span="8">
                            <img class="char" src="../img/charElfa.jpg">
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
            </el-tabs>
            <router-link :to="voltar">
              <el-button size="mini">Voltar</el-button>
            </router-link>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
    <object class="myYoutubeVideo" v-show="true" width="420" height="315"
            :data="play">
    </object>
  </div>
</template>
<style>
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
}



</style>
