<template>
   <div class="card p-4">
      <h4 class="text-center">Register Account</h4>
      <form class="form w-100">
        <div class="form-group my-3">
          <label for="username">Username</label>
          <input type="text" id="username" class="form-control my-1" placeholder="Enter username"   required autofocus />
        </div>
        <div class="form-group my-3">
          <label for="email">Email</label>
          <input type="email" id="email" class="form-control my-1" placeholder="Enter email"  />
        </div>
        <div class="form-group my-3">
          <label for="password">Password</label>
          <input type="password" id="password" class="form-control my-1" placeholder="Enter passsword" minlength="6" />
        </div>
        <div class="form-group my-3 text-center">
          <button class="btn btn-sm btn-primary w-100">Register</button>
          <router-link class="text-danger" to="/login">Already Had?</router-link>
        </div>
      </form>
   </div>
</template>