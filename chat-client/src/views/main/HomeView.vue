<script setup >
import { ref } from 'vue';
import ChatContainer from '@/components/ChatContainer.vue';
import MessageSendBox from "@/components/MessageSendBox.vue";

  const messages = ref(['hi']);

  const handleSend = ( msg ) => {
      console.log(msg);
  }

</script>

<template>
  <main id="main" class="card shadow-sm p-3" >
    <header class="card-header my-2 ">
      <h4 class="text-start">Chat App</h4>
    </header>
    <ChatContainer :messages="messages" />
    <MessageSendBox @send-msg="handleSend($event)"  />
  </main>
</template>