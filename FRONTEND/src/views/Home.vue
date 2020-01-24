<template>
    <div id="home">
       
<div id="container">
		<div class="row mt-5">
          <div class="col-md-12">
            <blockquote class="blockquote text-center">
              <p class="h1" style="color:#9c9c9c;">Welcome to your online banking {{this.$parent.mockAccount.username}}</p>   
            </blockquote>
          </div>
    </div>
    <div class="row mt-2">
          <div class="col-md-12">
          </div>
    </div>     
    <div class="row mt-5" style="margin-right: 2rem;">
        
        <div class="col-md-4" >
          <div class="card" style="margin-left: 2rem;margin-right: 2rem;">
              <highcharts class="chart" :options="chartOptions"></highcharts>
					  <div class="card-body">
						<h2 class="card-title">Transactions History</h2>
						<p class="card-text text-justify">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id ellit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>						
					  </div>
					</div>
        </div>
				<div class="col-md-4">
              <div class="card" style="margin-right: 2rem;">
                  <img src="imagen" class="card-img-top  mx-auto d-bloroundedck" style="width: 320px;height: 200px;" alt="">
                  <div class="card-body">
                  <h2 class="card-title">Main Expenses</h2>
                  <p class="card-text text-justify">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut leo odio, imperdiet quis lacus sit amet, vulputate tristique elit. Ut vel malesuada urna. Phasellus est nunc, molestie tempor orci vitae, pulvinar posuere nibh. Nulla a ultricies quam. Pellentesque consectetur purus quis ipsum convallis pellentesque. Vestibulum et tristique erat. Donec scelerisque, nibh aliquam commodo tristique, purus nisi sollicitudin diam, non maximus leo leo vitae neque. Integer venenatis, lorem ac posuere dapibus, nibh tellus tincidunt mauris, at accumsan turpis diam nec magna.</p>
                 
                  </div>
              </div>
        </div> 
				<div class="col-md-4">
              <div class="card" style="margin-rigth: 2rem;">
                        <div class="card-body">
                            <h2 class="card-title">Current Balance</h2>
      
                                <div class="col-md-12">
                        <table class="table table-bordered">
                          <thead>
                            <tr>
                              <th scope="col">Account No.</th>
                              <th scope="col">Balance</th>
                              <th scope="col">Date of Latest Transfer</th>
                            </tr>
                          </thead>
                          <tbody>
                            <tr>
                              <td>****1234</td>
                              <td>$400.00</td>
                              <td>21/May/19</td>
                            </tr>
                            <tr>
                              <td>****5678</td>
                              <td>$30.00</td>
                              <td>10/Apr/19</td>
                            </tr>
                          </tbody>
                        </table>
								</div>
                        </div>
              </div>   
            </div> 				
        </div>
		
      </div>



    </div>
</template>

<script>

    export default {
        name: 'Home',
        data() {
            return {
              imagen:this.$parent.imagen,
              updateArgs: [true, true, { duration: 1000 }],
              chartOptions: {
                chart: {
                  plotBackgroundColor: null,
                  plotBorderWidth: null,
                  plotShadow: false,
                  type: "pie"
                },
                title: {
                  text: null
                },
                plotOptions: {
                  pie: {
                    allowPointSelect: true,
                    cursor: "pointer",
                    dataLabels: {
                      enabled: true,
                      format: "<b>{point.name}</b>: {point.percentage:.1f} %"
                    }
                  }
                },
                series: [
                  {
                    name: "Brands",
                    colorByPoint: true,
                    data: [
                      {
                        name: "Chrome",
                        y: 61.41,
                        sliced: true,
                        selected: true
                      },
                      {
                        name: "Internet Explorer",
                        y: 11.84
                      },
                      {
                        name: "Firefox",
                        y: 10.85
                      },
                      {
                        name: "Edge",
                        y: 4.67
                      },
                      {
                        name: "Safari",
                        y: 4.18
                      },
                      {
                        name: "Sogou Explorer",
                        y: 1.64
                      },
                      {
                        name: "Opera",
                        y: 1.6
                      },
                      {
                        name: "QQ",
                        y: 1.2
                      },
                      {
                        name: "Other",
                        y: 2.61
                      }
                    ]
                  }
                ]
              }
     };
    },
    watch: {
      title(newValue) {
        this.chartOptions.title.text = newValue;
      },
      points(newValue) {
        this.chartOptions.series[0].data = newValue;
      },
      chartType(newValue) {
        this.chartOptions.chart.type = newValue;
      }
    }
  }
</script>

<style scoped>
    #secure {
        background-color: #FFFFFF;
        border: 1px solid #CCCCCC;
        padding: 20px;
        margin-top: 10px;
    }
</style>