<script setup >

  defineProps({
   message : {
    id : 0,
    text : '',
    sentDate : '',
    user : {}
   }
  });

</script>

<template>
 <h1>{{ message.text }}</h1>
</template>