<template>
    <div class="container pt-5 py-4">
        <h1 class="text-center py-4">Pizze</h1>

        <router-link to="/create" class="btn btn-primary">Aggiungi pizza</router-link>

        <div class="d-flex justify-content-end">
            <div class="d-flex flex-column gap-1 bg-light p-4 mb-5">
                <label for="name">Cerca per nome</label>
                <input type="text" v-model="pizzaNameFilter" id="name" placeholder="Cerca per nome...">
                <div @click="getPizzas()" class="btn btn-primary mt-2">Filtra</div>
            </div>
        </div>
        <div v-if="pizzas != null" class="table-responsive">
            <table class="table table-dark table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Prezzo</th>
                        <th>Azioni</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="pizza in pizzas" :key="pizza.id">
                        <td>{{ pizza.name }}</td>
                        <td>{{ pizza.price }} </td>
                        <td>
                            <div class="d-flex justify-content-center gap-2">
                                <router-link :to="{path: '/edit/' + pizza.id}" class="d-inline-block edit-icon pe-2">
                                    <i class="fa-solid fa-pen-to-square"></i>
                                </router-link>

                                <div class="d-inline-block trash-icon pe-2">
                                    <i @click="deletePizza(pizza.id)" class="fa-solid fa-trash"></i>
                                </div>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div v-else>
            <h5>Non ci sono pizze</h5>
        </div>

    </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from "axios";

const pizzas = ref(null)
const pizzaNameFilter = ref("")

getPizzas()


function getPizzas() {
    axios.get(pizzaNameFilter.value != "" ? "http://localhost:8080/api/pizzas?name=" + pizzaNameFilter.value : "http://localhost:8080/api/pizzas").then(res => pizzas.value = res.data).catch(err => console.log(err))
}

function deletePizza(id) {
    console.log("sono qui!" + id);
    axios.delete("http://localhost:8080/api/pizzas/" + id).then(res => {
        console.log(res)
        getPizzas()
    }).catch(res => console.log(res))
}
</script>

<style lang="scss" scoped>
.trash-icon {
	font-size: 1.2rem;
	color: orangered;
	transition: all 300ms;
	cursor: pointer;
}

.trash-icon:hover {
	transform: scale(1.1);
	color: darkorange;
}

.edit-icon {
	font-size: 1.2rem;
	color: greenyellow;
	transition: all 300ms;
	cursor: pointer;
}

.edit-icon:hover {
	transform: scale(1.1);
	color: lightgreen;
}
</style>