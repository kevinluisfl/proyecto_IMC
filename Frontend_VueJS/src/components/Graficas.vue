<template>
        <div class="container animate__animated animate__fadeIn">
        <h3>Gráfica</h3>
        <div class="row">
            <div id="chart_col" class="col-md-8"></div>
            <div id="chart_pie" class="col-md-4"></div>
             <div id="piechart" style="width: 450px; height: 400px;"></div>
        </div>
    </div>
</template>

<script>
  
export default{
    name:'Graficas',
    props: {
            datos: Object,
        },
    mounted () {
        console.log(this.datos)
        },
    // data(){
    //     return{
    //         dataimc :[
    //             ['IMC-UNAB', 'Mujeres(XX)', 'Hombres(XY)'],
    //             ['Peso bajo', this.datos.pbmujeres, this.datos.pbhombres],
    //             ['Peso normal', this.datos.pnmujeres, this.datos.pnhombres],
    //             ['Sobrepeso', this.datos.spmujeres, this.datos.sphombres],
    //             ],
    //             options : {
    //                 title: ' IMC POR GÉNERO ',
    //                 colors: ['#F18B16', '#0D6EFD'],
    //                 hAxis: {minValue: 0},
    //                 },
    //         dataporcen : [
    //             ['Mujeres(XX)',this.datos.cantmujeres],
    //             ['Hombres(XY)',this.datos.canthombres],
    //         ]
    //     }
    // },
}
</script>

<style scoped>

</style>
