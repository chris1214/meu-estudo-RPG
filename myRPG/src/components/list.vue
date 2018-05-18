<script>
import renderImagem from './RenderImagem'
import imgResponsiva from './imgResponsiva'
export default{
  data(){
      return{
         activeIndex: '2',
         fotos: [],
         imageUrl: '',
         upload: {
          title: '',
          url: ''
         },
         httpMesa: 'http://localhost:3000/mesas',
         filtro: '',
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
    button(){
      this.$http.post(`${this.httpMesa}`, this.upload).then(
        response => {
          upload: {
            title: '';
            url: ''
          }
          this.myGet();
        }, error => {
          this.$message.error('Erro');
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
    }
  },
  created(){
    this.myGet();
  },
  components:{
    renderImagem,
    imgResponsiva
  },
}




</script>
<template>
  <div>
    <el-menu class="myMenu" :default-active="activeIndex" mode="horizontal">
      <el-menu-item class="myDisableImg" disabled index="1">
        <div class="containerIMG">
          <img class="img" src="../img/Runa-Ansuz.png">
        </div>
      </el-menu-item>
      <el-menu-item index="2">Procurar por mesa</el-menu-item>
      <el-menu-item index="3">Criar Mesa</el-menu-item>
      <el-menu-item index="4">Minhas mesas</el-menu-item>
      <el-menu-item index="5">Mesas que já participo</el-menu-item>
      <el-menu-item class="pullRight" index="7">Perfil</el-menu-item>
      <span class="pullRight">
        <el-row>
          <el-col :span="24">
            <el-input
              type="text"
              placeholder="Filtro"
              prefix-icon="el-icon-search"
              v-model="filtro">
            </el-input>
          </el-col>
        </el-row>
      </span>
    </el-menu>
      <div class="background-IMG">
      </div>
    <b-container fluid class="padding-top">
          <el-row :gutter="20">
            <div v-for="foto in filterFotos">
              <el-col :span="4">
                <renderImagem
                  :label="foto.title"
                  height="height: 200px;"
                  :url="foto.url"
                  :alt="foto.title"
                />
              </el-col>
            </div>
          </el-row>
          <el-row>
            <el-col :span="4">
              <button @click="button()">Name</button>
              <span style="margin-top: 11px;" class="pullRight">
            </span>
            </el-col>
            <el-col :span="4">
              <span>Upload</span>
              <el-input v-model="upload.title"/>
              <el-input v-model="upload.url"/>
            </el-col>
          </el-row>
        </b-container>
  </div>
</template>

<style>
.myMenu {
  position: absolute;
  top: 0;
  width: 100%;
  z-index: 100;
}
.el-col {
  margin-bottom: 20px
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
.pullRight {
  float: right !important;
}
.containerIMG {
  max-height: 60px;
  max-width: 60px;
  height: 60px;
}
.img {
  height: 100%;
}
.myDisableImg {
  opacity: 1 !important;
}

.padding-top {
  padding-top: 50px;
  margin-top: 25px;
}
.background-IMG {
  background-image: url('../img/kingCastle.jpg');
  height: 100vh;
  position: fixed;
  width: 100%;
  top: 0;
}
</style>

