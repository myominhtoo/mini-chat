<script setup >
import {ref, reactive, onMounted, onUpdated} from 'vue';
import ChatContainer from '@/components/ChatContainer.vue';
import MessageSendBox from "@/components/MessageSendBox.vue";
import Loading from '@/components/Loading.vue';
import axios from 'axios';
import SockJs from 'sockjs-client/dist/sockjs.min.js';
import { over } from 'stompjs';
import sendNotiUrl from '@/assets/msg-noti.mp3';
import { isAuth, removeUser , userStore } from '@/util/auth.store';
import {useRouter} from "vue-router";

  const messages = ref([]);
  const status = reactive({
   isFetchingMessages : false,
  });

  const sendNotiRef = ref(null);

  let stompClient = null;
  const router = useRouter();
  const user = userStore();

  const handleSend = ( msg ) => {
      msg.user = { id : user.id };
      stompClient.send( '/app/send-message' , {} , JSON.stringify(msg) );
      setTimeout(() => {
         moveScrollToBotton();
      } , 100 );
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

  const handleLogout = () => {
    swal({
     text : 'Are you sure to logout!',
     icon : 'warning',
     buttons : [ 'No' , 'Yes']
    }).then( isYes => {
      if(isYes){
        removeUser();
        router.push('/login?success=true');
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
    if( newMessage.user.id != user.id ) sendNotiRef.value.play();
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

  const moveScrollToBotton = () => {
    const chatContainer = document.getElementById('chat-container');
    chatContainer.scrollTo({
     top : chatContainer.scrollHeight + 100,
     behavior : 'smooth'
    })
  }

  onMounted( () => {
    if( ! isAuth() ){
     router.push( '/login?msg=Please log in to continue!' );
     return;
    }
    fetchMessages();
    connectToSocket();
    sendNotiRef.value.src = sendNotiUrl;
  });

  onUpdated(() => {
    setTimeout(() => {
     moveScrollToBotton();
    } , 100 )
  })

</script>

<template>
  <main id="main" class="card shadow-sm p-3" >
    <header class="card-header my-2 d-flex justify-content-between">
      <h4 class="text-start text-muted">Chat App</h4>
      <button @click="handleLogout()"  class="btn btn-sm btn-danger outline-none border-none">Logout</button>
    </header>
    <audio class="d-none" ref="sendNotiRef" controls ></audio>
    <Loading :show="status.isFetchingMessages" />
    <ChatContainer v-if="!status.isFetchingMessages" :messages="messages" />
    <MessageSendBox @send-msg="handleSend($event)"  />
  </main>
</template>