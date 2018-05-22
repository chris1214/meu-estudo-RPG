<script>
export default{
  props: ['mesaId', 'title', 'name', 'id'],
    data(){
      return{
        mesa: [],
        httpMesa: 'http://localhost:3000/mesas',
        activeName: 'first'
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
    entrarNaMesa(){
      var user = this.name;
      var id = this.id;
      var myPush = this.mesa.players;

      myPush.push({'user': user, 'id': id})
      this.$http.put(`${this.httpMesa}/${this.mesaId}`, this.mesa).then(
        response => {
          this.mesa = response.body;
        }, error => {
          console.log('Error')
        }
      )
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
        <b-row style="margin-top: 10%;">
          <b-col md="8" offset-md="2">
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
                          <p><b>Vagas: </b>Tanto de {{mesa.playersMax}}</p>
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
                <el-tab-pane label="Role">Role</el-tab-pane>
                <el-tab-pane label="Task">Task</el-tab-pane>
              </el-tabs>
            </b-card>
          </b-col>
        </b-row>
      </b-container>

    </div>
  </div>
</template>
<style scoped>

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
