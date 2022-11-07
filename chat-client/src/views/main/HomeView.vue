<script setup >
import {ref, reactive, onMounted} from 'vue';
import ChatContainer from '@/components/ChatContainer.vue';
import MessageSendBox from "@/components/MessageSendBox.vue";
import Loading from '@/components/Loading.vue';
import axios from 'axios';
import SockJs from 'sockjs-client/dist/sockjs.min.js';
import { over } from 'stompjs';
import sendNotiUrl from '@/assets/msg-noti.mp3';

  const messages = ref([]);
  const status = reactive({
   isFetchingMessages : false,
  });

  const sendNotiRef = ref(null);

  let stompClient = null;

  const handleSend = ( msg ) => {
      msg.user = { id : 1 };
      stompClient.send( '/app/send-message' , {} , JSON.stringify(msg) );
  }

  const fetchMessages =  async () => {
    status.isFetchingMessages = true;
    await axios.get( 'http://localhost:8080/api/v1/messages' )
    .then( res => {
     status.isFetchingMessages = false;
      if(res.status == 200){
        console.log(res.data);
        messages.value = res.data;
      }
    });
  }

  const whenConnected = () => {
    listenMessages();
  }

  const whenError = () => {
    console.log('error');
  }

  const whenMessageReceived = ( payload ) => {
    const newMessage = JSON.parse(payload.body);
    sendNotiRef.value.play();
    messages.value.push(newMessage);
  }

  const listenMessages = () => {
    stompClient.subscribe( '/chat/messages' , whenMessageReceived );
  }

  const connectToSocket = () => {
    const socket = new SockJs('http://localhost:8080/socket');
    stompClient = over(socket);
    stompClient.connect( {} , whenConnected, whenError );
  }

  onMounted( () => {
    fetchMessages();
    connectToSocket();
    sendNotiRef.value.src = sendNotiUrl;
  });

</script>

<template>
  <main id="main" class="card shadow-sm p-3" >
    <header class="card-header my-2 ">
      <h4 class="text-start">Chat App</h4>
    </header>
    <audio class="d-none" ref="sendNotiRef" controls ></audio>
    <Loading :show="status.isFetchingMessages" />
    <ChatContainer v-if="!status.isFetchingMessages" :messages="messages" />
    <MessageSendBox @send-msg="handleSend($event)"  />
  </main>
</template>