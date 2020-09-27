<template>
    <div class="about">
      {{msg}}
    </div>
</template>

<script>
  export default {
    name: 'About',
    data: () => {
      return {
        msg: ' 스프링과 Vue 커넥팅 시험입니다.'
      }
    }
  }
</script>

<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #35495E;
  }
</style>
