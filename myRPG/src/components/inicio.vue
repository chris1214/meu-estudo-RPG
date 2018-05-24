<script>
import list from './show/list';
import criarMesa from './show/criarMesa';
export default{
  props: ['id', 'name'],
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
        },
        options: [
          {
            value: 'Option1',
            label: 'Option1'
          }, {
            value: 'Option2',
            label: 'Option2'
          }, {
            value: 'Option3',
            label: 'Option3'
          }, {
            value: 'Option4',
            label: 'Option4'
          }, {
            value: 'Option5',
            label: 'Option5'
          }
        ],
        value5: [],
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
    },
  },
  components:{
    list,
    criarMesa,
  },
}

</script>
<template>
  <div>
    <div>
      <el-row :gutter="20" class="myMenu">
        <el-col :xs="24" :sm="18" :md="16" :lg="14" :xl="12">
          <el-menu :default-active="activeIndex" mode="horizontal">
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
        </el-col>
        <el-col :xs="12" :sm="12" :md="6" :lg="4" :xl="4" style="margin-top: 10px;">
          <span :myShow="conteudo.list">
            <el-input
              type="text"
              placeholder="Filtro"
              prefix-icon="el-icon-search"
              v-model="filtro">
            </el-input>
          </span>
        </el-col>
        <el-col :xs="12" :sm="12" :md="6" :lg="6" :xl="8" style="margin-top: 10px; right: 0; position: relative">

        </el-col>
      </el-row>
    </div>
    <div class="background-IMG"/>
    <div class="margin-top">
      <list :name="name" :id="id" :filtro="filtro" title="Mesas" v-show="conteudo.list" :myShow="conteudo.list"/>
      <criarMesa :name="name" :id="id" v-show="conteudo.criarMesa"/>
    </div>
  </div>
</template>
<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
  }
.el-menu--horizontal {
  border-bottom: none;
}
.margin-top {
  margin-top: 65px;
}
.myMenu {
  background: white;
  position: absolute;
  top: 0;
  width: 100%;
  z-index: 100;
  margin-left: 0 !important;
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
  background-repeat:no-repeat;
}



</style>
