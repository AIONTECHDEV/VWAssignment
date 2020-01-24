<template>
    <div id="login">
        
        <div class="container">
            <h3 class="text-center text-dark">Login</h3>
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <h3 class="text-center text-info"></h3>
                    <div id="login-box" class="col-md-12">
                        
                        <form id="login-form" class="form" action="" method="post" >
                            
                            <div class="form-group">
                                <label for="username" class="text-dark">Username:</label><br>
								 <input type="text" name="username" id="username" class="form-control" v-model="$v.input.username.$model" v-on:blur="validateUser()"  placeholder="Username" :maxlength="max" :minlength="min"/>
                                 <p class="error" v-if="!$v.input.username.strongUSer"> user name  need  be min 8 to max 20 digits long, not empty.</p>
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-dark">Password:</label><br>
								<input type="password" name="password" id="password" class="form-control" v-model="$v.input.password.$model" placeholder="Password" :maxlength="max" :minlength="min" />
                                <p class="error" v-if="!$v.input.password.strongPassword"> Strong passwords need to have a letter, a number, a special character, no sequence of numbers and be min 8 to max 20 digits long.</p>
                            </div>
                            <div class="form-group col text-center">
								<button :disabled="!$v.input.username.strongUSer && !$v.input.password.strongPassword"  type="button"   class="btn btn-dark btn-lg " v-on:click="login()">Enter</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { required,minLength,maxLength} from "vuelidate/lib/validators";
    export default {
        name: 'Login',
        data() {
            return {
                max: 20,
                min:8,
                input: {
                    username: "",
                    password: ""
                   
                }
            }
        },
        validations: {
            input:{
                username:{required,minLength:minLength(1),maxLength:maxLength(8),strongUSer(username) {
                    return (
                    /[a-z]/.test(username) && // checks for a-z
                    ///[0-9]/.test(username) && // checks for 0-9
                    //[!\"\$%&/]/.test(username) && //checks for specials chars 
                    username.length >= 8
                    );
                }},
                password:{required,minLength:minLength(1),maxLength:maxLength(8),strongPassword(password) {
                    return ( 
                    /[a-z]/.test(password) && // checks for a-z
                    /[0-9]/.test(password) && // checks for 0-9
                    //[!\"\$%&/]/.test(password) && //checks for specials chars 
                    /\W|_/.test(password)  &&// checks for special char
                    password.length >= 8
                    );
                }}
            }
        },
        methods: {
            login() {
                if(this.input.username != "" && this.input.password != "") {
                    if(this.input.username == this.$parent.mockAccount.username && this.input.password == this.$parent.mockAccount.password) {
                        this.$emit("authenticated", true);
                        this.$router.replace({ name: "home" });
                    } else {
                        this.$alert("The username and / or password is incorrect.","","error","");                    
                        console.log("The username and / or password is incorrect");
                    }
                } else {
                   this.$alert("A username and password must be present.","","error","");               
                   console.log("A username and password must be present");
                }
            },
            isDisabled() {
            if(this.input.username != "" && this.input.password != ""){
                return true;
            }else{
                return  false;
            } 
            },
            validateUser(){
            }
        }
    }
</script>

<style scoped>

 #login {
    font-family: "Poppins", sans-serif;
    width: 500px;
    border: 1px solid #CCCCCC;
    background-color: #FFFFFF;
    margin: auto;
    margin-top: 200px;
    padding: 20px;
    border-radius: 50px;
  }

a {
  color: #92badd;
  display:inline-block;
  text-decoration: none;
  font-weight: 400;
}

h1 {
  text-align: center;
  font-size: 16px;
  font-weight: 600;
  text-transform: uppercase;
  display:inline-block;
  margin: 40px 8px 10px 8px; 
  color: #cccccc;
}
.error {
  color: red;
  width:100%;
}
</style>