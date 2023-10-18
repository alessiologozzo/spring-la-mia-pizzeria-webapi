<template>
<div>
    <Navbar />
    <div class="bg_light">
        <div>
            <label for="name">Nome</label>
            <input type="text" v-model="pizza.name" id="name" name="name">
        </div>

        <div>
            <label for="price">Prezzo</label>
            <input type="text" v-model="pizza.price" id="price" name="price">
        </div>

        <div @click="updatePizza()" class="btn btn-primary">Invia</div>
    </div>
</div>
</template>

<script setup>
import { ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios'
import Navbar from '../components/Navbar.vue';

const route = useRoute()
const router = useRouter()
const pizza = ref({name: "", price: 0})


getPizza()

function getPizza() {
    axios.get("http://localhost:8080/api/pizzas/" + route.params.id).
    then(res => {
        pizza.value.name = res.data.name
        pizza.value.price = res.data.price
        console.log(pizza.value)
    })
    .catch(err => console.log(err))
}

function updatePizza() {
    axios.put("http://localhost:8080/api/pizzas/" + route.params.id, pizza.value).then(res => console.log(res)).catch(res => console.log(res))
    router.push("/")
}
</script>

<style lang="scss" scoped>

</style>