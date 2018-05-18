<script>
import list from './show/list'
import criarMesa from './show/criarMesa'
export default{
  data(){
      return{
        filtro: '',
        activeIndex: '2',
        menu: {
          procurarPorMesa: 'procurarPorMesa',
          criarMesa: 'criarMesa',
          minhasMesas: 'minhasMesas',
          MesasParticipo: 'MesasParticipo',
          perfil: 'perfil'
        },
        conteudo: {
          list: true,
          criarMesa: false,
        }

      }
  },
  methods: {
    myMenu(valor){
      if(valor == 'procurarPorMesa'){
        this.conteudo.list = true;
        this.conteudo.criarMesa = false;
      }
      if(valor == 'criarMesa'){
        this.conteudo.list = false;
        this.conteudo.criarMesa = true;
      }
        console.log(this.conteudo.list)
    }
  },
  components:{
    list,
    criarMesa
  },
}
</script>
<template>
  <div>
    <div>
      <el-menu class="myMenu" :default-active="activeIndex" mode="horizontal">
        <el-menu-item class="myDisableImg" disabled index="1">
          <div class="containerIMG">
            <img class="img" src="../img/Runa-Ansuz.png">
          </div>
        </el-menu-item>
        <el-menu-item index="2" @click="myMenu(menu.procurarPorMesa)">Procurar por mesa</el-menu-item>
        <el-menu-item index="3" @click="myMenu(menu.criarMesa)">Criar Mesa</el-menu-item>
        <el-menu-item index="4" @click="myMenu(menu.minhasMesas)">Minhas mesas</el-menu-item>
        <el-menu-item index="5" @click="myMenu(menu.MesasParticipo)">Mesas que já participo</el-menu-item>
        <el-menu-item index="6" @click="myMenu(menu.perfil)">Perfil</el-menu-item>
      </el-menu>
      <span class="myMenu" style="padding-top: 10px; width: 25.6667%;left: 45%;">
            <el-input
              type="text"
              placeholder="Filtro"
              prefix-icon="el-icon-search"
              v-model="filtro">
            </el-input>
      </span>
    </div>
    <div class="background-IMG">
    </div>
    <list :filtro="filtro" title="Mesas" v-show="conteudo.list" :myShow="conteudo.list"/>
    <criarMesa v-show="conteudo.criarMesa"/>
  </div>
</template>
<style scoped>
.myMenu {
  position: absolute;
  top: 0;
  width: 100%;
  z-index: 100;
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
