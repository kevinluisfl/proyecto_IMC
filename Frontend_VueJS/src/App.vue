<template>
  <div id="app" class="container">
    <!-- ---------------------------------------------------------- -->
    <!-- <HelloWorld 
      msg="Creador: Team mintic"
    /> -->
    <!-- <Pruebas /> -->
    <!-- ----------------------------------------------------------- -->
    <nav >
      <ul class="nav justify-content-end">
        <li class="nav-item"><img  alt="App logo" src="./assets/logounab.jpg" width="100" height="80"></li>
        <li class="nav-item"><h1>Cálculo IMC - UNAB</h1></li>
        <li class="nav-item"><button class="btn btn-orange" v-on:click="muestraRegistro">Registro <i class="fa fa-plus-square" aria-hidden="true"></i></button></li>
        <li class="nav-item"><button class="btn btn-success" v-on:click="muestraResultados">Resultados <i class="fa fa-list-alt" aria-hidden="true"></i></button></li>
      </ul>
    </nav>
    <hr>
    <div>
      <Personas :token="token" v-if="token && registro"/>
      <Resultados :token="token" v-if="token && resultados"/>
    </div>
  </div>
</template>

<script>
// import HelloWorld from './components/HelloWorld.vue'
// import Pruebas from './components/Pruebas.vue'
import Personas from './components/Personas.vue'
import Resultados from './components/Resultados.vue'

export default {
  name: 'App',
  data(){
    return{
      token: '',
      registro: true,
      resultados: false,
    }
  },
  components: {
    // HelloWorld,
    // Pruebas,
    Personas,
    Resultados,
  },
    async beforeCreate(){
    const requestOptions = {
      method: "POST",
      // body: JSON.stringify({"username":"admin", "password":"admin"}), ///para Django
      body: JSON.stringify({"id": 1}),  ///consultar token con java spring
      headers: {
        "Content-Type": "application/json"
      }
    };
    // fetch("http://localhost:8000/api/token/", requestOptions)  ////para Django
    fetch("http://localhost:8082/api/auth", requestOptions)
      .then(async response =>{
        const data = await response.json();
        // console.log(data)
        if(!response.ok){
          const error = (data && data.message) || response.status;
          return Promise.reject(error);
        }
        this.token = data.token;
        console.log(this.token)
      })
      .catch(error => {
        this.token = error;
        // this.token = '';
        console.error('Error al consultar el token', error);
      });
      console.log(this.token)
      ///una vez carga el componente quiero mostrar la tabla de datos
  },
  methods:{
    muestraRegistro(){
      this.registro = true;
      this.resultados = false;
    },
    muestraResultados(){
      this.registro = false;
      this.resultados = true;
    },
    },

}
</script>

<style>

h1,h2,h3{
    text-align: center;
    margin-bottom: 10px;
    padding: 18px;
}
nav ul li{
  padding-top: 18px;
  padding-inline: 10px;
}
.btn-orange{
  background-color: rgb(241, 139, 22);
  color: white;
}
.btn-orange:hover{
  background-color: rgb(230, 131, 19);
    color: white;
  }

/* #app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  margin-top: 10px;
} */


</style>
