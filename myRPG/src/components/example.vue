<script>
import VueDragResize from 'vue-drag-resize';
export default{
  data(){
      return{
        width: 0,
        height: 0,
        top: 0,
        left: 0
      }
  },
  components:{
    VueDragResize
  },
  methods: {
    resize(newRect) {
        this.width = newRect.width;
        this.height = newRect.height;
        this.top = newRect.top;
        this.left = newRect.left;
    }
  }
}
</script>
<template>
  <div>
    <h2>Draggable Wrapper</h2>
    <VueDragResize :isActive="true" :w="200" :h="200" v-on:resizing="resize" v-on:dragging="resize">
      <h3>Hello World!</h3>
      <p>{{ top }} х {{ left }} </p>
      <p>{{ width }} х {{ height }}</p>
    </VueDragResize>
  </div>
</template>
<style>
</style>
