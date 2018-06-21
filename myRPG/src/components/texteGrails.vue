<script>
  export default {
    data() {
      return {
        myHttp: '/user/list',
        resposta: [],
        form: {
          nivelDePlayer: 3,
          name: 'chris',
          experienciaDescricao: 'media',
          password: '132456',
          nivelDeMestragem: 2,
          email: 'bla@hotmail.com',
          dataNascimento: '1998-11-17',
          tags: {
            desejo: 'mestre',
            experiencia: 'alta',
            user: 'ambos',
            nivel: 'baixo'
          },
        }
      }
    },
    methods: {
      get: function() {
        this.$http.get(`http://localhost:8080/user/list`)
          .then(response => {
            this.resposta = response.body;
          });
      },
      post() {
        this.$http.post(`http://localhost:8080/user/save`,this.form)
          .then(response => {
            console.log(this.form);
          });
      },
    },
    created() {
      this.get();
    }
  }
</script>

<template>
  <div>
    {{myHttp}}
    <br>
    {{resposta}}
    <p>http</p>
   <div v-for="item in resposta">
     <p>{{item.id}}</p>
     <p>{{item.nivelDePlayer}}</p>
     <p>{{item.name}}</p>
     <p>{{item.experienciaDescricao}}</p>
     <p>{{item.password}}</p>
     <p>{{item.nivelDeMestragem}}</p>
     <p>{{item.email}}</p>
     <p>{{item.dataNascimento}}</p>
   </div>
    <button @click="post()">Vai</button>
  </div>
</template>

<style scoped>

</style>
