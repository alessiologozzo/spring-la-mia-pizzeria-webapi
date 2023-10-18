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

        <div @click="createPizza()" class="btn btn-primary">Invia</div>
    </div>
</div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router';
import axios from 'axios'
import Navbar from '../components/Navbar.vue';

const router = useRouter()
const pizza = ref({name: "", price: 0})


function createPizza() {
    axios.post("http://localhost:8080/api/pizzas", pizza.value).then(res => console.log(res)).catch(res => console.log(res))
    router.push("/")
}
</script>

<style lang="scss" scoped>

</style>