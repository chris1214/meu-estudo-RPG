<script>
export default {
    props: ["label", "src", "router", "height"],
    data() {
      return {
      }
    },
    methods: {
    },
    created() {
    }
  }
</script>


<template>
  <div slot="body">

    <!-- TODO - ajustar espaçamento do topo -->
        {{src.img}}
        {{src}}
    <img src="src">
    <img :src="src">
        <div class="meu-thumbex margin-10px-10px-30px" :body-style="{ padding: '0px' }" :style="height">
          <div class="meu-thumbnail">
              <img :src="src">
              <div class="margin-40-3">
                <span><strong>{{ label }}</strong></span>
              </div>
          </div>
        </div>

  </div>
</template>
<style scoped>
  .image {
    background-size: cover;
    width: 100%;
    display: block;
    height: 100%;
  }
  .div-img {
    height: 200px;
  }
  .padding-top-10px{
    padding-top: 15px;
    padding-bottom: 15px;
  }
  .fundo-transparente {
    background-color: rgba(4, 6, 9, 0.3);
    min-height: 290px;
  }
  .padding-30px-margintop-50px{
    padding: 30px;
    margin: 50px !important;
  }
  @media (min-width: 1200px) {
    .col-lg-2 {
      width: 25% !important;
    }
    @media (min-width: 1400px) {
      .col-lg-2 {
        width: 16.66667% !important;
      }
    }
  }

    .meu-thumbex {
        box-shadow: 5px 10px 40px 5px rgba(0, 0, 0, 0.5);
        flex: 1 1 0;
        overflow: hidden;
    }

    .meu-thumbnail {
        backface-visibility: hidden;
        height: 100%;
        min-width: 100%;
        opacity: 0.88;
        overflow: hidden;
        position: relative;
        transition: all 0.4s ease-out 0s;
    }
    .meu-thumbnail img {
        backface-visibility: hidden;
        height: 115%;
        left: 50%;
        position: absolute;
        top: 50%;
        transform: translate(-50%, -50%);
        z-index: 1;

        background-size: cover;
        max-width: 100%;
        width: 100%;
        display: block;
        max-height: 100%;
    }
    .meu-thumbnail:hover {
         backface-visibility: hidden;
         transform: scale(1.15, 1.15);
         opacity: 1;
    }

    span{
        position: absolute;
        z-index: 2;
        /*left: 0;
        right: 0;*/
        background: rgba(0,0,0,0.7);
        padding: 10px 18px;
        text-align: center;
        /*margin: 0 45px;
        font-size: 15px;*/
        color: white;
        font-weight: 300;
        letter-spacing: 0.2px;
        transition: all 0.3s ease-out;
        width: 100%;
        word-wrap: break-word;
    }

    .meu-thumbnail:hover span{
        opacity: 0;
    }

    .margin-10px {
      margin: 10px 10px 30px;
    }
    .margin-40-3 {
      display: inline-flex;
      left: 20%;
      right: 20%;
      position: absolute;
      top: 35%;
      bottom: 25%;
    }

</style>
