const app = Vue.createApp({
    data(){
        return {
            cards: [],
            debitC: [],
            creditC: [],
            cardColor: "",
            cardType: "",
        }
    },
    created(){ 
        const params = new URLSearchParams(window.location.search);
        const id = params.get('id'); 
        axios.get("/api/clients/current")
        .then(res => {
            this.cards = res.data.cards,
            this.debitC = [...res.data.cards.filter(when => when.cardType == "DEBIT")];
            this.creditC = [...res.data.cards.filter(when => when.cardType == "CREDIT")]; 
            console.log(this.cards)
        })
        .catch(error => console.log(error));
    },
    methods:{
            createCard(){
                axios.post('/api/clients/current/cards', "cardType="+this.cardType+"&cardColor="+this.cardColor)
                .then(()=> swal("Card created successfully", {
                    icon: "success",
                  })
                .then(() => window.location.href="/web/cards.html"))
        },
            logout(){
                axios.post('/api/logout').then(response => window.location.href="/web/index.html")
            },
    },
});
app.mount("#app");