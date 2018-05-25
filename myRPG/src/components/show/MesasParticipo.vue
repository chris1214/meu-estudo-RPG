<script>
import renderImagem from '../utils/RenderImagem'
export default{
  props: ['id', 'name'],
  data(){
      return{
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
    getAll(myHttp, resposta, id){
      this.$http.get(`${myHttp}${id}`).then(
        response => {
          this[resposta] = response.body;
        }, error => {
          console.log('Error')
        }
      )
    },
    myGet(){
      this.getAll(`${this.httpPlayers}`, 'myPlayers', `?userId=${this.id}`)
        /*this.getAll(`${this.httpMesas}`, 'mesas', `?id=${}`)*/
    }
  },
  watch: {
    myPlayers: function(value){
    var player = value.length
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
        <p v-for="item in mesas">{{item}}</p><br><br>
        <br><br>
        <p v-for="item in myPlayers">{{item}}</p><br><br>
        <div v-for="mesa in mesas">
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
