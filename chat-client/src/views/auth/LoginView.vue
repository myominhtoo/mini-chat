<script setup >
import { reactive } from "vue";
import axios from "axios";
import swal from "sweetalert";
import {useRouter} from "vue-router";
import { saveUser } from '@/util/auth.store';

  const user = reactive({
   email : '',
   password : ''
  });

  const status = reactive({
   error : '',
   isLoading : false,
  });

  const router = useRouter();

  const handleUserLogin = async ( e ) => {
        e.preventDefault();
        status.error = '';
        status.isLoading = true;
        await axios.get(`http://localhost:8080/api/v1/users?email=${user.email}`)
        .then( res => {
          status.isLoading = false;
          if( res.data && res.data.password === user.password  ){
            swal({
             text : 'Successfully Logged In!',
             icon : 'success'
            }).then( () => {
              router.push(  '/?success=true' );
              saveUser( res.data );
            });
          }else{
            status.error = "Invalid email or password!"
          }
        });
  }

</script>

<template>
  <div class="card p-4">
    <h4 class="text-center">Login Account</h4>
    <form @submit="handleUserLogin($event)"  class="form w-100">
      <div class="form-group my-3">
        <label for="email">Email</label>
        <input type="email" v-model="user.email" id="email" class="form-control my-1"  placeholder="Enter email" required />
        <small v-if="status.error" class="text-danger">{{ status.error }}</small>
      </div>
      <div class="form-group my-3">
        <label for="password">Password</label>
        <input type="password" v-model="user.password" id="password" class="form-control my-1" placeholder="Enter passsword" autocomplete="" minlength="6" required />
        <small v-if="status.error" class="text-danger">{{ status.error }}</small>
      </div>
      <div class="form-group my-3 text-center">
        <button type="submit" class="btn btn-sm btn-primary w-100" :disabled="status.isLoading" >{{ status.isLoading ? 'Logging In...' : 'Login'  }}</button>
        <router-link class="text-danger" to="/register">Register Account?</router-link>
      </div>
    </form>
  </div>
</template>