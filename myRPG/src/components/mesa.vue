<script>
export default{
  props: ['mesaId', 'title', 'name', 'id'],
    data(){
      return{
        mesa: [],
        httpMesa: 'http://localhost:3000/mesas',
        activeName: 'first',
        voltar: `/inicio/${this.name}/${this.id}`
      }
  },
  components:{
  },
  methods: {
    getAll(){
      this.$http.get(`${this.httpMesa}/${this.mesaId}`).then(
        response => {
          this.mesa = response.body;
        }, error => {
          console.log('Error')
        }
      )
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

    }
  },
  created(){
    this.getAll();
  }
}


</script>
<template>
  <div>
    <div class="img">
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
                        <el-collapse-item title="Feedback">

                        </el-collapse-item>
                        <el-collapse-item title="Efficiency">

                        </el-collapse-item>
                        <el-collapse-item title="Controllability">

                        </el-collapse-item>
                      </el-collapse>
                    </b-col>
                    <b-col md="10">
                      <b-card class="cardChat" title="Bate-papo">
                        <p><b>{{name}}: </b> Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusantium amet
                          atque consectetur culpa cum deleniti, ducimus earum est, et nesciunt nostrum nulla
                          perspiciatis praesentium ratione recusandae rem tenetur voluptatibus voluptatum?</p>
                        <p><b>{{name}}: </b> Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusantium amet
                          atque consectetur culpa cum deleniti, ducimus earum est, et nesciunt nostrum nulla
                          perspiciatis praesentium ratione recusandae rem tenetur voluptatibus voluptatum?</p>
                        <p><b>{{name}}: </b> Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusantium amet
                          atque consectetur culpa cum deleniti, ducimus earum est, et nesciunt nostrum nulla
                          perspiciatis praesentium ratione recusandae rem tenetur voluptatibus voluptatum?</p>
                        <p><b>{{name}}: </b> Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusantium amet
                          atque consectetur culpa cum deleniti, ducimus earum est, et nesciunt nostrum nulla
                          perspiciatis praesentium ratione recusandae rem tenetur voluptatibus voluptatum?</p>
                        <p><b>{{name}}: </b> Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusantium amet
                          atque consectetur culpa cum deleniti, ducimus earum est, et nesciunt nostrum nulla
                          perspiciatis praesentium ratione recusandae rem tenetur voluptatibus voluptatum?</p>
                        <p><b>{{name}}: </b> Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusantium amet
                          atque consectetur culpa cum deleniti, ducimus earum est, et nesciunt nostrum nulla
                          perspiciatis praesentium ratione recusandae rem tenetur voluptatibus voluptatum?</p>
                        <p><b>{{name}}: </b> Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusantium amet
                          atque consectetur culpa cum deleniti, ducimus earum est, et nesciunt nostrum nulla
                          perspiciatis praesentium ratione recusandae rem tenetur voluptatibus voluptatum?</p>
                        <p><b>{{name}}: </b> Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusantium amet
                          atque consectetur culpa cum deleniti, ducimus earum est, et nesciunt nostrum nulla
                          perspiciatis praesentium ratione recusandae rem tenetur voluptatibus voluptatum?</p>
                        <p><b>{{name}}: </b> Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusantium amet
                          atque consectetur culpa cum deleniti, ducimus earum est, et nesciunt nostrum nulla
                          perspiciatis praesentium ratione recusandae rem tenetur voluptatibus voluptatum?</p>
                        <p><b>{{name}}: </b> Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusantium amet
                          atque consectetur culpa cum deleniti, ducimus earum est, et nesciunt nostrum nulla
                          perspiciatis praesentium ratione recusandae rem tenetur voluptatibus voluptatum?</p>
                      </b-card>
                      <el-input type="textarea"></el-input>
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

    </div>
  </div>
</template>
<style>
.myChats .el-collapse-item__header{
  color: #ffffff;
  background-color: #0000003b;
}
.myChats .el-collapse-item__content{
  color: #ffffff;
  background-color: #0000003b;
}

</style>
<style scoped>
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
