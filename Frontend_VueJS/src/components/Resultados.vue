<template>
    <div class="container">

    <div class="row justify-content-end">
        <div class="col-6">
            <div class="row justify-content-end">
                <div class="col-7">
                    <!-- Esto esta comentado porque ahora se muestra directamente la tabla -->
                    <!-- <a v-if="mostrar" v-on:click="cargarPersonas" class="btn btn-success" data-bs-toggle="collapse" href="#collapseResultados" role="button" aria-expanded="true" aria-controls="collapseResultados">
                        Ver Resultados <i class="fa fa-angle-double-down" aria-hidden="true"></i>
                    </a>
                    <a v-else v-on:click="ocultarPersonas" class="btn btn-orange" data-bs-toggle="collapse" href="#collapseResultados" role="button" aria-expanded="true" aria-controls="collapseResultados">
                        Ocultar Resultados <i class="fa fa-angle-double-up" aria-hidden="true"></i>
                    </a> -->
                </div>
            </div>
        </div>
        <div class="col-6">
            <div class="row justify-content-end">
                <div class="col-7">
                    <a v-if="grafica"  v-on:click="verGraficas" class="btn btn-success" data-bs-toggle="collapse" href="#collapseGrafica" role="button" aria-expanded="false" aria-controls="collapseGrafica">
                    Ver Gráfica </a>
                    <a v-else v-on:click="verGraficas" class="btn btn-orange" data-bs-toggle="collapse" href="#collapseGrafica" role="button" aria-expanded="false" aria-controls="collapseGrafica">
                    Ocultar Gráfica </a>
                </div>
            </div>
        </div>
        <div class="collapse" id="collapseGrafica">
            <Graficas :datos="datos" />
        </div>
    </div>

        <!-- <div class="collapse" id="collapseResultados"> -->
            <h3>Registros</h3>
            <table  class="table table-striped table-hover">
                <tr>
                    <th>Nombre</th>
                    <th>Peso (Kg)</th>
                    <th>Talla (Mt)</th>
                    <th>IMC</th>
                    <th style="text-align:center;">Condición</th>
                    <th>Recomendaciones</th>
                    <th>Accion</th>
                </tr>
                <tr v-for="persona in personas" :key="persona.id" class="animate__animated animate__fadeInDown">
                    <td>{{persona.nombre}} {{persona.apellido}}</td>
                    <td>{{persona.peso}} </td>
                    <td>{{persona.talla}}</td>
                    <td>{{persona.imc}}</td>
                    <td  align="center">{{persona.condicion}}</td>
                    <td>{{persona.recomendacion}}</td>
                    <td>
                        <!-- <button v-on:click="editarPersona(persona.id)">Editar</button> -->
                        <button v-if="persona.id>1" type="button" class="btn btn-outline-danger" v-on:click="eliminarPersona(persona.id)"><i class="fa fa-trash-o fa-lg"></i> Eliminar</button></td>
                </tr>
            </table>
        <!-- </div> -->

    </div>
</template>

<script>
import Graficas from './Graficas.vue'

    export default{
        name: 'Resultados',
        props: {
            token: String,
        },
        components:{
            Graficas,
        },
        ///////otra forma con array function para return implicito
        data: ()=>({
            personas: [],
            mostrar: true,
            grafica: true,
            hombres: [],
            mujeres: [],
            datos: {
                canthombres: 0,
                pbhombres: 0,
                pnhombres: 0,
                sphombres: 0,
                cantmujeres: 0,
                pbmujeres: 0,
                pnmujeres: 0,
                spmujeres: 0
            },
        }),
        mounted () {
           this.cargarPersonas();
            // this.mostrar = !this.mostrar;

        },
        methods: {
            async cargarPersonas(){
                const requestOptions={
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                        "Authorization": this.token,
                    }
                };
                // estado boton
                this.mostrar = !this.mostrar;
                // peticion al backend
                const respuesta = await fetch("http://localhost:8082/api/personas", requestOptions)
                this.personas = await respuesta.json();
                console.log(this.personas);
                // separando por género
                this.hombres = this.personas.filter(item => item.genero == 'M');
                this.mujeres = this.personas.filter(item => item.genero == 'F');
                //cantidad por genero
                this.datos.canthombres = this.hombres.length;
                this.datos.cantmujeres = this.mujeres.length;
                // cantidad peso bajo cada género
                this.datos.pbhombres = this.hombres.filter(item => item.condicion == "Peso bajo").length;
                this.datos.pbmujeres = this.mujeres.filter(item => item.condicion == "Peso bajo").length;
                // cantidad peso normal cada género
                this.datos.pnhombres = this.hombres.filter(item => item.condicion == "Peso normal").length;
                this.datos.pnmujeres = this.mujeres.filter(item => item.condicion == "Peso normal").length;
                // cantidad sobrepeso cada género
                this.datos.sphombres = this.hombres.filter(item => item.condicion == "Sobrepeso").length;
                this.datos.spmujeres = this.mujeres.filter(item => item.condicion == "Sobrepeso").length;

            },
            async eliminarPersona(id){
                const requestOptions={
                    method: "DELETE",
                    headers: {
                        "Content-Type": "application/json",
                        "Authorization": this.token,
                    }
                };
               await fetch(`http://localhost:8082/api/personas/${id}`, requestOptions)
                ////actualizar la tabla para que desaparezca el eliminado
                this.cargarPersonas();
            },
            ocultarPersonas(){
                // estado boton
                this.mostrar = !this.mostrar;
            },
            verGraficas(){
                if(this.personas.length == 0){
                    this.cargarPersonas();
                    this.mostrar = !this.mostrar;
                }
                this.grafica = !this.grafica;
            }
        },
    }
</script>

<style scoped>
    
</style>
