<script>
import renderImagem from '../utils/RenderImagem'
export default{
  props: ['filtro', 'title', 'myShow'],
  data(){
      return{
         fotos: [],
         imageUrl: '',
         httpMesa: 'http://localhost:3000/mesas',
      }
  },
  methods: {
    getAll(myHttp, resposta){
      this.$http.get(`${myHttp}`).then(
        response => {
          this[resposta] = response.body;
        }, error => {
          console.log('Error')
        }
      )
    },
    myGet(){
      this.getAll(`${this.httpMesa}`, 'fotos');
    },
  },
  computed: {
    filterFotos (){
      if(this.filtro){
        let filtro = new RegExp(this.filtro.trim(), 'i');
        return this.fotos.filter(foto => filtro.test(foto.title));
      }else{
        return this.fotos;
      }
    },
  },
  watch:{
    myShow: function (value){
      if(value = true){
        this.myGet();
      }
    },
  },
  created(){
    this.myGet();
  },
  components:{
    renderImagem,
  },
}
</script>
<template>
  <div>
    <b-container fluid class="padding-top">
      <el-row>
        <el-col>
          <h1 style="text-align: center; color: white">{{ title }}</h1>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <div v-for="foto in filterFotos">
          <el-col  :xs="12" :sm="6" :md="6" :lg="4" :xl="4">
            <renderImagem
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
.el-col {
  margin-bottom: 20px
}
</style>

