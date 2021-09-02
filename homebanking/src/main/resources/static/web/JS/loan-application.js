const app = Vue.createApp({
    data(){
        return {
            accounts: [],
            loans: [],
            amount: "",
            payments: "",
            toAccountNumber: "",
            selection: "",
            loanData: [],
            names: "",
            number: "",
            loan: "",
        }
    },
    created(){ 
        const params = new URLSearchParams(window.location.search);
        const id = params.get('id'); 
        axios.get("/api/clients/current")
        .then(res => {
            this.accounts = res.data.accounts,
            this.loans = res.data.loans,
            this.names = res.data.firstName,
            this.number = res.data.accounts[0].number,
            console.log(this.loans)
        })
        .catch(error => console.log(error)),

        axios.get("/api/loans")
        .then(res =>{
            this.loanData = res.data,
            console.log(this.loanData)
        })
        .catch(error => console.log(error))
    },
    methods:{
        currentBalance(){
            if (this.toAccountNumber != "") {
                return this.accounts.filter(when => when.number == this.toAccountNumber)[0].balance
            }
        },
        maxAmount() {
            if (this.loan != "") {
                return this.loanData.filter(when => when.name == this.loan)[0].amount
            }

        },
        paymentsByName() {
            if (this.loan != "") {
                return this.loanData.filter(when => when.name == this.loan)[0].payments
            }
        },
        amountToPay() {
            if(this.payments != ""){
                return this.amount / this.payments * 1.2
            }
        },
        applyLoan(){
            axios.post('/api/loans',{
                name: this.loan,
                amount:this.amount,
                payments:this.payments,
                toAccountNumber: this.toAccountNumber})
                .then(()=> swal("success applying to Loan", {
                    icon: "success",
                  })
                .then(() => window.location.href="/web/accounts.html"))
        },
        logout(){
            axios.post('/api/logout').then(response => window.location.href="/web/index.html")
        },
    },
    computed:{
        
    },
});
app.mount("#app");
