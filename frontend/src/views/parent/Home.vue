<template>    
  <div class="admin-dashboard">    
    <!-- 顶部导航和面包屑等 -->    
    
    <!-- 主要内容区域 -->    
    
  </div>    
</template>

<script>

export default {
  name: 'Home',
  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-parent') || '{}'),
      notices: [],
    };  
    }
}
</script>
<style scoped>
</style>
