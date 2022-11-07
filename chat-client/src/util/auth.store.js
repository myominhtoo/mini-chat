const isAuth = () => {
    return JSON.parse(localStorage.getItem('user')) != null ? true : false ;
}

const userStore = () => {
    return JSON.parse(localStorage.getItem('user'));
}

const saveUser = ( user = {} ) => {
    localStorage.setItem( 'user' , JSON.stringify(user) );
}

const removeUser = () => {
    localStorage.removeItem('user');
}

export  { isAuth , userStore , saveUser , removeUser  } ;