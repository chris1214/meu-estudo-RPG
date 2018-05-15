<script>
export default{
    data(){
        return{
           activeIndex: '2',
        }
    },
    components:{
    },
}

</script>
<template>
  <div>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal">
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
      <span style="margin-top: 11px;" class="pullRight">
        <el-input
          placeholder="Filtro"
          prefix-icon="el-icon-search">
        </el-input>
      </span>
    </el-menu>
    <div class="background-IMG">
      <b-container fluid class="padding-top">
        <b-row>
          <b-col>col</b-col>
          <b-col>col</b-col>
          <b-col>col</b-col>
          <b-col>col</b-col>
          <b-col>col</b-col>
          <b-col>col</b-col>
        </b-row>
      </b-container>
    </div>
  </div>
</template>
<style>
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
  padding-top: 50px
}
.background-IMG {
  background-image: url('../img/kingCastle.jpg');
  height: 100vh;
}

</style>
