<template>
  <div class="card p-4">
    <h4 class="text-center">Login Account</h4>
    <form class="form w-100">
      <div class="form-group my-3">
        <label for="email">Email</label>
        <input type="email" id="email" class="form-control my-1" placeholder="Enter email"/>
      </div>
      <div class="form-group my-3">
        <label for="password">Password</label>
        <input type="password" id="password" class="form-control my-1" placeholder="Enter passsword" minlength="6"/>
      </div>
      <div class="form-group my-3 text-center">
        <button class="btn btn-sm btn-primary w-100">Login</button>
        <router-link class="text-danger" to="/register">Register Account?</router-link>
      </div>
    </form>
  </div>
</template>