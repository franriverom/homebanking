const app = Vue.createApp({
    data(){
        return {
            names: "",
            cardsData: [],
            creditC: [],
            debitC: [],
            cardToDelete: "",
        }
    },
    created(){ 
        const params = new URLSearchParams(window.location.search);
        const id = params.get('id'); 
        axios.get("/api/clients/current")
        .then(res => {
            this.debitC = [...res.data.cards.filter(when => when.cardType == "DEBIT")];
            this.creditC = [...res.data.cards.filter(when => when.cardType == "CREDIT")]; 
            this.names = res.data.firstName;
            this.cardsData = res.data.cards;
            console.log(this.cardsData)
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
            cardColor(color) {
                return color.toLowerCase()
            },
            deleteCard(){
                axios.delete('/api/clients/current/cards/'+this.cardToDelete)
                .then(()=> swal("Card deleted successfully", {
                    icon: "success",
                  })
                .then(() => location.reload()))
            },
    },
});
app.mount("#app");