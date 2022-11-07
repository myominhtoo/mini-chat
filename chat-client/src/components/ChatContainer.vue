<script setup  >
 import Message from "@/components/Message.vue";

 const props  = defineProps({
  messages : {
    type : Array,
    default : []
  }
 });

</script>

<template>
  <div  class="w-100 p-0 card-body" id="chat-container" >
    <Message v-for="message of messages" :message="message" />
  </div>
</template>