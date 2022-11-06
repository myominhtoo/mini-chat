<script setup >
import {reactive, ref} from 'vue';
import axios from "axios";
import {useRouter} from "vue-router";
import swal from 'sweetalert';

const user = reactive({
 username : '',
 email : '',
 password : ''
});

const isLoading = ref(false);
const router = useRouter();

const handleRegisterUser = async ( e ) => {
    e.preventDefault();
    isLoading.value = true;
    await axios.post( 'http://localhost:8080/api/v1/users', user  )
    .then( res => {

     isLoading.value = false;
     if( res.data.ok ) {
       swal({
        text : res.data.message,
        icon : 'success'
       }).then( () => {
        router.push( '/login?success=true' );
       });
     }else{
      swal({
       text : res.data.message,
       icon : 'warning'
      });
     }
    });
}

</script>

<template>
   <div class="card p-4">
      <h4 class="text-center">Register Account</h4>
      <form @submit="handleRegisterUser($event)" class="form w-100">
        <div class="form-group my-3">
          <label for="username">Username</label>
          <input type="text" v-model="user.username" id="username" class="form-control my-1" placeholder="Enter username"   required autofocus />
        </div>
        <div class="form-group my-3">
          <label for="email">Email</label>
          <input type="email" v-model="user.email" id="email" class="form-control my-1" placeholder="Enter email" required  />
        </div>
        <div class="form-group my-3">
          <label for="password">Password</label>
          <input type="password" v-model="user.password" id="password" class="form-control my-1" placeholder="Enter passsword" autocomplete="" required minlength="6" />
        </div>
        <div class="form-group my-3 text-center">
          <button class="btn btn-sm btn-primary w-100" :disabled="isLoading" >{{ isLoading ? 'Registering...' : 'Register' }}</button>
          <router-link class="text-danger" to="/login">Already Had?</router-link>
        </div>
      </form>
   </div>
</template>