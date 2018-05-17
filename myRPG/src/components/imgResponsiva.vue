<script>
export default{
  props: ['url', 'titulo'],
  data(){
      return{
      }
  },
}
</script>
<template>
  <div>
    <img :src="url" :alt="titulo">
  </div>
</template>
<style scoped>
img {
  max-width: 100%;
  max-height: 100%;
}
</style>
