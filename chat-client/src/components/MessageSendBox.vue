<script setup >
import { reactive } from "vue";

  const emits = defineEmits([ 'send-msg' ]);
  const message = reactive({
    text : '',
    id : 0,
    user : {}
  });

  const  handleSendMessage = ( e ) => {
      e.preventDefault();
      emits('send-msg', message );
      message.text = '';
  }

</script>

<template>
  <div class="card-footer w-100" >
    <form @submit="handleSendMessage($event)"  class="w-100 ">
      <div class="d-flex gap-2">
        <input type="text" v-model="message.text" class="form-control" placeholder="Type message" />
        <button :disabled="message.text === ''" class="btn btn-sm btn-primary px-5 fw-bold">Send</button>
      </div>
    </form>
  </div>
</template>