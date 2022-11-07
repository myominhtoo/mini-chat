<script setup >
 defineProps({
  show : false,
 });
</script>

<template>
  <div v-if="show" id="loading" class="text-muted mx-auto  p-5 d-flex  justify-content-center align-items-center">
    <h6>Loading Messages...</h6>
  </div>
</template>