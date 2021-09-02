const app = Vue.createApp({
    data(){
        return {
            firstName: "",
            lastName: "",
            email: "",
            password: "",
            error: false,
        }
    },
    created(){ 
        
    },
    methods:{
        login(){
            axios.post('/api/login',"email="+this.email+"&password="+this.password,{headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response => window.location.href="/web/accounts.html")
            .catch(error => this.error = !this.error);
        },
        logout(){
            axios.post('/api/logout').then(response => window.location.href="/web/index.html")   
        },
        newAcc(){
            axios.post('/api/clients', "firstName="+this.firstName+"&lastName="+this.lastName+"&email="+this.email+"&password="+this.password)
            .then(()=>
            axios.post('/api/login',"email="+this.email+"&password="+this.password)
            .then(()=>
            axios.post('/api/clients/current/accounts'))
            .then(response => window.location.href="/web/accounts.html"))
        }
    }
});
app.mount("#app");