<template>
    <div class="container animate__animated animate__fadeIn" >
<!-- buscador -->
    <div class="row justify-content-end">
        <div class="col-md-3">
            <div class="input-group input-group-sm ">
                <span class="input-group-text">Buscar por ID:</span>
                <input class="form-control form-control-sm" v-model="id" id="id" type="number">
                <button class="btn btn-orange" v-on:click="buscarPersona"><i class="fa fa-search" aria-hidden="true"></i>Buscar</button>
            </div>
        </div>
    </div><br>
<!-- fin buscador -->
        <!-- <p>{{token}}</p> -->
        <!-- formulario para capturar datos -->
        <form v-show="!search" class="animate__animated animate__zoomIn form-control">
        <h2>Ingresar datos</h2>
<!-- FILA 1 -->
        <div class="row">
<!-- nombre -->
            <div class="col-md-3">
                <div class="input-group input-group-sm mb-3">
                    <span class="input-group-text">Nombre:*</span>
                    <input class="form-control form-control-sm" v-model="persona.nombre" id="nombre" type="text" required><br>
                </div>
            </div>
<!-- apellido -->
            <div class="col-md-3">
                <div class="input-group input-group-sm mb-3">
                    <span class="input-group-text">Apellido:*</span>
                    <input class="form-control form-control-sm" v-model="persona.apellido" id="apellido" type="text" required><br>
                </div>
            </div>
<!-- tipo documento -->
            <div class="col-md-3">
                <!-- <label class="text-muted" for="tipoDocumento">Tipo Documento: </label> -->
                <select class="form-select form-select-sm"  id="tipoDocumento" v-model="persona.tipoDocumento" required>
                    <option value="CC" selected>CEDULA DE CIUDADANIA</option>
                    <option value="TI">TARJETA DE IDENTIDAD</option>
                    <option value="RC">REGISTRO CIVIL</option>
                    <option value="CE">CEDULA EXTRANJERIA</option>
                    <option value="OT">OTRO</option>
                </select><br>
            </div>
<!-- numero documento -->
            <div class="col-md-3">
                <div class="input-group input-group-sm mb-3">
                    <span class="input-group-text">Documento:*</span>
                    <input class="form-control form-control-sm" v-model="persona.numeroDocumento" id="documento" type="number" required><br>
                </div>
            </div>
        </div>
<!-- FILA 2 -->
    <div class="row">
<!-- peso -->
        <div class="col-md-2">
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text">Peso (Kg)*</span>
                <input class="form-control form-control-sm" v-model="persona.peso" id="peso" type="number" required><br>
            </div>
        </div>
<!-- talla -->
        <div class="col-md-2">
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text">Talla (Mt)*</span>
                <input class="form-control form-control-sm" v-model="persona.talla" id="talla" type="number" required><br>
            </div>
        </div>
<!-- perfil -->
        <div class="col-md-2">
            <!-- <label  class="text-muted" for="perfil">Perfil: </label> -->
                <select class="form-select form-select-sm" id="perfil" v-model="persona.perfil" required>
                    <option value="Administrativo">ADMINISTRATIVO</option>
                    <option value="Docente">DOCENTE</option>
                    <option value="Estudiante" selected>ESTUDIANTE</option>
                </select><br>
        </div>
<!-- género -->
        <div class="col-md-2">
            <!-- <label  class="text-muted" for="genero">Género: </label> -->
            <div >
                <input class="form-check-input" type="radio" id="femenino" name="genero" v-model="persona.genero" value="F" checked>
                <label  class="text-muted form-check-label" for="femenino">Femenino (XX)</label>
            </div>
            <div>
                <input class="form-check-input" type="radio" id="masculino" name="genero" v-model="persona.genero" value="M">
                <label  class="text-muted form-check-label" for="masculino">Masculino (XY)</label>
            </div><br>
        </div>
<!-- email -->
        <div class="col-md-4">
            <div class="form-floating mb-3">
                <input type="email" class="form-control form-control-sm" id="email" v-model="persona.email" placeholder="example@mail.com">
                <label class="text-muted" for="email">Email(opcional): </label>
            </div>
        </div>
    </div>
<!-- botones -->
            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                <button class="btn btn-success mb-3" v-on:click="crearPersona"><i class="fa fa-floppy-o" aria-hidden="true"></i> {{edit ? 'Actualizar' : 'Guardar'}}</button>
                <button class="btn btn-outline-secondary mb-3" v-on:click="limpiar"><i class="fa fa-window-close" aria-hidden="true"></i> Limpiar</button>
            </div>
<!-- mensajes -->
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div v-if="error" class="alert alert-danger" role="alert">
                            Debes rellenar todos los campos Obligatorios!
                        </div>
                        <div v-if="guardo" class="alert alert-success" role="alert">
                            La persona ha sido agregada correctamente!
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <!-- visualizar dato individual -->
        <div v-show="guardo" class="animate__animated animate__flipInX">
            <label for="resultado">Tu resultado:</label><br>
            <table class="table table-striped table-hover">
                <tr>
                    <th>Nombre</th>
                    <th>Peso (Kg)</th>
                    <th>Talla (Mt)</th>
                    <th>IMC</th>
                    <th style="text-align:center;">Condición</th>
                    <th>Recomendaciones</th>
                    <th>Acciones</th>
                    <th><button style="text-align:right;" class="btn-close" v-on:click="limpiar"></button></th>
                </tr>
                <tr>
                    <td>{{personaresultado.nombre}} {{personaresultado.apellido}}</td>
                    <td>{{personaresultado.peso}}</td>
                    <td>{{personaresultado.talla}}</td>
                    <td>{{personaresultado.imc}}</td>
                    <td align="center">{{personaresultado.condicion}}</td>
                    <td>{{personaresultado.recomendacion}}</td>
                    <td>
                        <button class="btn btn-outline-warning btn-orange" v-on:click="vereditar"><i class="fa fa-pencil fa-lg"></i>Editar</button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'Personas',
        props: {
            token: String,
        },
        data(){
            return{
                persona:{
                    nombre: "",
                    apellido: "",
                    tipoDocumento: "CC",
                    numeroDocumento: "",
                    presentacion: "",
                    admin: false,
                    email: "",
                    fechaRegistro: new Date(),
                    genero: "F",
                    peso: "",
                    talla: "",
                        imc: 0,
                    perfil: "Estudiante",
                        condicion: "",
                        recomendacion: ""
                },
                id: "",
                guardo: false,
                personaresultado:{},
                search: false,
                error: false,
                msgError:"",
                edit: false
            }
        },
        computed:{
            calculoimc(){
                return Number((this.persona.peso/(this.persona.talla**2)).toFixed(1))
            }
        },
        methods: {
            // crear nuevo registro
                async crearPersona(){
                    ////validaciones
                    if(this.persona.nombre =="" ){
                        this.error = true;
                        this.msgError = 'Escriba el nombre! (*)';
                        return;
                    }
                    if(this.persona.apellido =="" ){
                        this.error = true;
                        this.msgError = 'Escriba el apellido! (*)';
                        return;
                    }
                    if(this.persona.numeroDocumento ==""){
                        this.error = true;
                        this.msgError = 'Escriba el numero de documento! (*)';
                        return;
                    }

                    ////hago uso del computed para que solo cambie el resultado del calculo si cambian los valores de las variables
                    ////aunque no veo la diferencia por ahora
                    this.persona.imc = this.calculoimc;
                    if(this.persona.imc >= 16 && this.persona.imc < 18.5){
                        this.persona.condicion = "Peso bajo";
                        this.persona.recomendacion = "Debes alimentarte con más regularidad";
                    }else if(this.persona.imc >= 18.5 && this.persona.imc <= 25){
                        this.persona.condicion = "Peso normal";
                        this.persona.recomendacion = "Tu condicion es saludable, sigue asi!";
                    }else if(this.persona.imc > 25 && this.persona.imc <= 40 ){
                        this.persona.condicion = "Sobrepeso";
                        this.persona.recomendacion = "Alimentate mejor y haz deporte!";
                    }else{
                        this.persona.condicion = "El valor del IMC esta fuera de rango";
                        this.persona.recomendacion = "Ingresa correctamente los datos de Talla y Peso";
                    }

                const requestOptions = {
                    method: "POST",
                    body: JSON.stringify(this.persona),
                    headers: {
                        "Content-Type": "application/json",
                        "Authorization": this.token,
                }
                };
                const respuesta = await fetch("http://localhost:8082/api/personas", requestOptions)
                this.persona = await respuesta.json();
                ///una vez guardado quiero mostrar la tabla con el nuevo dato
                console.log(this.persona);
                this.personaresultado = this.persona;

                this.persona={
                    nombre: "",
                    apellido: "",
                    tipoDocumento: "CC",
                    numeroDocumento: "",
                    presentacion: "",
                    admin: false,
                    email: "",
                    fechaRegistro: new Date(),
                    genero: "F",
                    peso: "",
                    talla: "",
                    imc: 0,
                    perfil: "Estudiante",
                    condicion: "",
                    recomendacion: ""
                }
                this.id = "";
                this.guardo= true;
                this.search= false;
                this.error = false;
                this.edit=false;
            },
            // buscar un registro por id
            async buscarPersona(){
                ///si esta vacio el campo id, limpio campos y detengo con return
                if(this.id==""){
                    this.limpiar();
                    return;
                }
                const requestOptions = {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                        "Authorization": this.token,
                }
                };
                const respuesta = await fetch(`http://localhost:8082/api/personas/${this.id}`, requestOptions);
                this.persona = await respuesta.json();
                console.log(this.persona);
                this.personaresultado = this.persona;

                 this.guardo = true;
                 this.search = true;
                 this.error = false;
            },
            ////limpiar campos
            limpiar(){
                 this.persona={
                    nombre: "",
                    apellido: "",
                    tipoDocumento: "CC",
                    numeroDocumento: "",
                    presentacion: "",
                    admin: false,
                    email: "",
                    fechaRegistro: new Date(),
                    genero: "F",
                    peso: "",
                    talla: "",
                    imc: 0,
                    perfil: "Estudiante",
                    condicion: "",
                    recomendacion: ""
                }
                this.personaresultado = this.persona;

                this.id = "";
                this.guardo = false;
                this.search = false;
                this.error = false;
                this.edit = false;
            },
            vereditar(){
                this.guardo = false;
                this.search = false;
                this.error = false;
                this.edit = true;
            }
        }
    }
</script>

<style scoped>
  .btn-outline-warning{
      color: #F18B16;
      border-color: #F18B16;
  }
</style>
