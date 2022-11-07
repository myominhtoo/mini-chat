<script setup >
import {ref, reactive, onMounted} from 'vue';
import ChatContainer from '@/components/ChatContainer.vue';
import MessageSendBox from "@/components/MessageSendBox.vue";
import Loading from '@/components/Loading.vue';
import axios from 'axios';

  const messages = ref(['hi']);
  const status = reactive({
   isFetchingMessages : false,
  });

  const handleSend = ( msg ) => {
      console.log(msg);
  }

  const fetchMessages =  async () => {
    status.isFetchingMessages = true;
    await axios.get( 'http://localhost:8080/api/v1/messages' )
    .then( res => {
     status.isFetchingMessages = false;
      if(res.status == 200){
        messages.value = res.data;
      }
    });
  }

  onMounted( () => {
    fetchMessages();
  });

</script>

<template>
  <main id="main" class="card shadow-sm p-3" >
    <header class="card-header my-2 ">
      <h4 class="text-start">Chat App</h4>
    </header>
    <Loading :show="status.isFetchingMessages" />
    <ChatContainer v-if="!status.isFetchingMessages" :messages="messages" />
    <MessageSendBox @send-msg="handleSend($event)"  />
  </main>
</template>