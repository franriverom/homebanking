const app = Vue.createApp({
    data(){
        return {
            allInfo: [],
            client: "",
            accounts: [],
            error: true,
            info: false,
            accToDelete: "",
        }
    },
    created(){ 
        const params = new URLSearchParams(window.location.search);
        const id = params.get('id'); 
        axios.get("/api/clients/current")
        .then(res => {
            this.accounts = res.data.accounts;
            this.client = res.data.firstName;
            console.log(this.accounts)
        })
        .catch(error => console.log(error));
    },
    methods:{
            formatDate: function(date){
                return new Date(date).toLocaleDateString('en-gb');
            },  
            logout(){
                axios.post('/api/logout').then(response => window.location.href="/web/index.html")
            },
            createAcc(){
                axios.post('/api/clients/current/accounts',{headers:{'content-type':'application/x-www-form-urlencoded'}})
                .then(()=> swal("Account created successfully", {
                    icon: "success",
                  })
                  .then(() => location.reload()))
                .catch(error => this.error = !this.error);
            },
            deleteAcc(){
                axios.delete('/api/clients/current/accounts',{
                    number: this.accToDelete})
                    .then(()=> swal("success applying to Loan", {
                        icon: "success",
                      })
                    .then(() => window.location.href="/web/accounts.html"))
            },
        }
});
app.mount("#app");
