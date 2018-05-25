<script>
import renderImagem from '../utils/RenderImagem'
export default{
  props: ['id', 'name'],
  data(){
      return{
        mesas: [],
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
      this.getAll(`${this.httpMesas}`, 'mesas', `?idMestre=${this.id}`)
    },
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
        <div v-for="foto in mesas">
          <el-col :xs="12" :sm="6" :md="6" :lg="4" :xl="4" @click="enter()">
            <renderImagem
              :id="id"
              :name="name"
              :router="foto.id"
              :mestre="foto.mestre"
              :faixaEtaria="foto.faixaEtaria"
              :label="foto.title"
              height="height: 200px;"
              :url="foto.url"
              :alt="foto.title"
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
