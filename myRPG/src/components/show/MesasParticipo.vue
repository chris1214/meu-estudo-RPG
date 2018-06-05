<script>
import renderImagem from '../utils/RenderImagem'
export default{
  props: ['id', 'name'],
  data(){
      return{
        minhaMesa: [],
        mesas: [],
        myPlayers: [],
        httpPlayers: 'http://localhost:3000/players',
        httpMesas: 'http://localhost:3000/mesas'
      }
  },
  components:{
    renderImagem
  },
  methods: {
    /*getAll(myHttp, resposta, id){
      this.$http.get(`${myHttp}${id}`).then(
        response => {
          this[resposta] = response.body;
        }, error => {
          console.log('Error')
        }
      ).then(
        function(){
          var player = this.myPlayers.length;
          console.log(player)
          if(player){
            for(var i = 0; i < player; i++){
              this.$http.get(`${this.httpMesas}?id=${value[i].mesaId}`).then(
                response => {
                  this.mesas.push(response.body);
                }, error => {
                  console.log('Error')
                }
              )
            }
          }
        }
      )
    },*/
    myGet(){
      this.$http.get(`${this.httpPlayers}?userId=${this.id}`).then(
        response => {
          this.myPlayers = response.body;
        }, error => {
          console.log("Error")
        }
      ).then(
        function () {
          var mesas = this.myPlayers.length;
          console.log(mesas);
          for (var i = 0; i < mesas; i++){
            console.log(this.myPlayers[i].mesaId);
            this.$http.get(`${this.httpMesas}?id=${this.myPlayers[i].mesaId}`).then(
              response => {
                this.mesa = response.body;
                this.minhaMesa.push( response.body);
                console.log(this.minhaMesa)
              }, error => {
                console.log("Error")
              }
            )
          }
        }
      );
      console.log(this.mesa)
    }
  },
  created(){
    this.myGet();
  }
}
</script>
<template>
  <div>
    <b-container fluid class="padding-top">
      <el-row :gutter="20">
        <p v-for="item in minhaMesa">{{item}}</p><br><br>
        <p v-for="item in myPlayers">{{item}}</p><br><br>
        <div v-for="mesa in minhaMesa">
          <el-col :xs="12" :sm="6" :md="6" :lg="4" :xl="4" @click="enter()">
            <renderImagem
              :id="id"
              :name="name"
              :router="mesa.id"
              :mestre="mesa.mestre"
              :faixaEtaria="mesa.faixaEtaria"
              :label="mesa.title"
              height="height: 200px;"
              :url="mesa.url"
              :alt="mesa.title"
            />
          </el-col>
        </div>
      </el-row>
    </b-container>
  </div>
</template>
<style scoped>
.padding-top {
  padding-top: 50px;
  margin-top: 25px;
}
.el-col {
  margin-bottom: 20px
}
</style>
