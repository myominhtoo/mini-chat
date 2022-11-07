<script setup >

  const emits = defineEmits([ 'send-msg' ]);

  const  handleSendMessage = ( e ) => {
      e.preventDefault();
      emits('send-msg','hello world');
  }

</script>

<template>
  <div class="card-footer w-100" >
    <form @submit="handleSendMessage($event)"  class="w-100 ">
      <div class="d-flex gap-2">
        <input type="text" class="form-control" placeholder="Type message" />
        <button class="btn btn-sm btn-primary px-5 fw-bold">Send</button>
      </div>
    </form>
  </div>
</template>