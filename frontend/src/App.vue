<template>
  <div class="background-image-class">
    <div id="app">
      <router-view />
    </div>
  </div>
</template>
<style>
#app {
  margin-left: 10%;
  margin-right: 10%;
  background-color: white;
  height: auto;
}
.background-image-class{
  height: 100%;
  background-image: url('assets/imgs/bg5.jpg');
}
</style>
