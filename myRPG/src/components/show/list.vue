<script>
import renderImagem from '../utils/RenderImagem'
export default{
  props: ['filtro', 'title', 'myShow', "name","id"],
  data(){
      return{
         fotos: [],
         imageUrl: '',
         httpMesa: 'http://localhost:8080/mesas/list',
         httpUser: 'http://localhost:8080/user/list',
         user: [],
         renderImg: true,
         img: 'https://image.freepik.com/icones-gratis/simbolo-de-cadeado_318-50592.jpg',
         faixaEtariaUser: ''
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
    userOn(){
      this.$http.get(`${this.httpUser}/${this.id}`).then(
        response => {
          this.user = response.body
        }, error => {
          console.log("usuario invalido")
        }
      )
    }
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
    user: function (resultado){
      var user = resultado.dataNascimento;
      var userDate = new Date(user);

      var newDate = new Date();
      var faixaEtaria;
      var idade =  Math.floor(Math.ceil(Math.abs(userDate.getTime() - newDate.getTime()) / (1000 * 3600 * 24)) / 365.25);
      if (idade < 10){

        faixaEtaria = '9'

      } else if(idade >= 10 && idade <= 11){

         faixaEtaria = '10'

      } else if(idade >= 12 && idade <= 13){

         faixaEtaria = '12'

      } else if(idade >= 14 && idade <= 15){

         faixaEtaria = '14'

      } else if(idade >= 16 && idade <= 17){

         faixaEtaria = '16'

      } else if(idade >= 18){

         faixaEtaria = '18'

      }
      this.faixaEtariaUser = faixaEtaria;
    },
  },
  created(){
    this.myGet();
    this.userOn();
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
          <el-col :xs="12" :sm="6" :md="6" :lg="4" :xl="4" @click="enter()">
            <renderImagem
              :faixaEtariaUser="faixaEtariaUser"
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
.el-col {
  margin-bottom: 20px
}
</style>

