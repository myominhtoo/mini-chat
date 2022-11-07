<script setup >
import COLORS from '../data/colors';
import formatDate from "@/util/formatDate";

  const props = defineProps({
   message : {
    id : 0,
    text : '',
    sentDate : '',
    user : { id : 0 , username : '' }
   }
  });

  const getBackground = () => {
    return COLORS[props.message.user.username.length > 4 ?  props.message.user.username.length - 3 : props.message.user.username.length - 1  ];
  }

  const [ day , time ] = props.message.sentDate.split(' ');

</script>

<template>
 <div id='message-container' class="d-flex flex-column gap-2 my-3">
   <div id='info' class='d-flex justify-content-start gap-3 text-muted px-1' >
    <small class="fw-bold text-capitalize">{{ message.user.username  }}</small>
    <small style="font-size:13px;">{{ formatDate( day , time ) }}</small>
   </div>
   <div id='body' class="d-flex justify-content-start align-items-center gap-2">
      <div id='icon' class='text-light' :style="'background:'+getBackground()+' !important;'" >
       <span>{{ message.user.username.charAt(0).toUpperCase() }}</span>
      </div>
      <div id='message'>
        <span class="text-muted">{{ message.text }}</span>
      </div>
   </div>
 </div>
</template>