<script>
import {httpMesasList, httpPlayersList} from '../../http'
import renderImagem from '../utils/RenderImagem'
export default{
  props: ['id', 'name'],
  data(){
      return{
        minhasMesas: [],
        mesas: [],
        myPlayers: [],
        httpPlayers: httpPlayersList,
        httpMesas: httpMesasList
      }
  },
  components:{
    renderImagem
  },
  methods: {
    myGet(){
      this.$http.get(`${this.httpPlayers}?userId=${this.id}`).then(
        response => {
          this.myPlayers = response.body;
        }, error => {
          console.log("Error")
        }
      ).then(
        function () {
          var minhasMesas = this.myPlayers.length;
          for (var i = 0; i < minhasMesas;i++){
            this.$http.get(`${this.httpMesas}?id=${this.myPlayers[i].mesaId}`).then(
              response => {
                this.mesas.push({'mesa': response.body});
                var x = this.mesas.length;
                this.minhasMesas.push(this.mesas[x-1].mesa[0]);
              }, error => {
                console.log("Error")
              }
            );
          }
        }
      );
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
        <div v-for="mesa in minhasMesas">
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
