<script setup  >

 const props  = defineProps({
  messages : {
    type : Array,
    default : []
  }
 });

</script>

<template>
  <div class="w-100 p-2 card-body" id="chat-container" >
    <small>

    </small>
  </div>
</template>