const app = Vue.createApp({
    data(){
        return {
            names: "",
            esVisible: true,
            noVisible: false,
            loans: [],
            mensaje: false,
            first: [],
            names: [],
            number: [],
        }
    },
    created(){
        const params = new URLSearchParams(window.location.search);
        const id = params.get('id'); 
        axios.get("/api/clients/current")
        .then(res => {
            this.first = res.data.accounts[0].transactions,
            this.transactions = res.data.accounts,
            this.loans = res.data.loans;
            this.names = res.data.firstName;
            this.number = res.data.accounts[0].number;
            console.log(this.number)
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
    },  
});
app.mount("#app");