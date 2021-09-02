const app = Vue.createApp({
    data(){
        return {
            accounts: [],
            number: "",
            cardType: "",
            info: false,
            selection: "",
            fromAccountNumber: "",
            toAccountNumber: "",
            amount: "",
            description: "",
            type: "",
        }
    },
    created(){ 
        const params = new URLSearchParams(window.location.search);
        const id = params.get('id'); 
        axios.get("/api/clients/current")
        .then(res => {
            this.accounts = res.data.accounts,
            console.log(this.accounts)
        })
        .catch(error => console.log(error));
    },
    methods:{
            destination(){
            if(this.type == "thirdP"){
                return 
            }
        },
            transfer(){
                axios.post('/api/clients/current/transactions', "fromAccountNumber="+this.fromAccountNumber+
                "&toAccountNumber="+this.toAccountNumber+"&amount="+this.amount+"&description="+this.description)
                .then(()=> swal("succesful transaction", {
                    icon: "success",
                  })
                .then(() => window.location.href="/web/accounts.html"))
        },
            logout(){
                axios.post('/api/logout').then(response => window.location.href="/web/index.html")
            },
            
    },
});
app.mount("#app");
