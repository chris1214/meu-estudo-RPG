<script>
export default{
  props: ['mesaId', 'title', 'faixaEtaria'],
    data(){
      return{
        mesa: [],
        httpMesa: 'http://localhost:3000/mesas',
        activeName: 'first'
      }
  },
  components:{
  },
  methods: {
    getAll(myHttp, resposta){
      this.$http.get(`${this.httpMesa}/${this.mesaId}`).then(
        response => {
          this.mesa = response.body;
        }, error => {
          console.log('Error')
        }
      )
    },
  },
  created(){
    this.getAll();
  }
}
</script>
<template>
  <div class="img">
    <b-container>
      <b-row style="margin-top: 10%;">
        <b-col md="8" offset-md="2">
          <b-card :title="mesa.title"
                  class="mb-2">
            <p>Desculpe nos, mas você ainda não tem idade para jogar essa mesa</p>

          </b-card>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
<style scoped>

.card-title {
   text-align: center;
}
.img{
  background-image: url('../../img/IlhaFuturista.jpg');
  height: 100vh;
  position: fixed;
  width: 100%;
  top: 0;
  background-repeat:no-repeat;
}

</style>
