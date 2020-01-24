<template>
    <div id="transfer">
        <div id="container" style="margin-left: 2rem;margin-right: 2rem;">
            <div class="row mt-3">
                <div class="col-md-5">
                    <div class="card">
                        <div class="card-body">
                            <h2 class="card-title">Create new transfer</h2>
                            <div>
                                <div class="form-group">
                                    <label for="accountSelect">Select origin account</label>
                                    <select class="custom-select" name="accountSelect" id="accountSelect">
                                        <option selected>*****5243 - $100.00</option>
                                        <option >*****5244 - $200.00</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="destAccount">Destination account</label>
                                    <input type="text" id="destAccount" name="destAccount" class="form-control" v-model="cleanAccount" :maxlength="max">
                                    
                                </div>
                                <div class="form-group">
                                    <label for="amount">Amount</label>
                                    <input type="text" id="amount" name="amount" class="form-control" v-model="cleanAmount" v-on:keyUp="validateText()">
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="d-flex ml-auto float-right">
                                            <div >
                                                <button class="btn btn-primary" style="width: 110px;"   v-on:click="updateData()">Transfer</button>
                                            </div>
                                            <div >  
                                                <button class="btn btn-light" style="width: 110px;" v-on:click="cancelData()">Cancel</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-7 mh-100">
                    <highcharts class="chart" :options="chartOptions"></highcharts>
                </div>
            </div>
            <div class="row mt-4">
                <div class="col-md-12">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">Origin Account</th>
                                <th scope="col">Detination account</th>
                                <th scope="col">Transfer date</th>
                                <th scope="col">Amount</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>****1234</td>
                                <td>12345678</td>
                                <td>01/May/19</td>
                                <td>$1400</td>
                            </tr>
                            <tr>
                                <td>****1234</td>
                                <td>12345678</td>
                                <td>21/Jun/19</td>
                                <td>$1500</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="row mt-4">
                <div class="col-md-12">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">Origin Account</th>
                                <th scope="col">Detination account</th>
                                <th scope="col">Transfer date</th>
                                <th scope="col">Amount</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>****5678</td>
                                <td>87654321</td>
                                <td>01/May/19</td>
                                <td>€20</td>
                            </tr>
                            <tr>
                                <td>****5678</td>
                                <td>87654321</td>
                                <td>21/Jun/19</td>
                                <td>€45</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
import {maxLength} from "vuelidate/lib/validators";

export default {
  name: "Transfer",
  data() {
    return {
       max: 8,
       cleanAmount:'',
       cleanAccount:'',
      updateArgs: [true, true, { duration: 1000 }],
      chartOptions: {
        chart: {
          plotBackgroundColor: null,
          plotBorderWidth: null,
          plotShadow: false,
          type: "pie"
        },
        title: {
          text: "Balance"
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
            name: "account",
            colorByPoint: true,
            data: [
              {
                name: "12345678",
                y: 7655.54,
                sliced: true,
                selected: true
              },
              {
                name: "987654321",
                y: 5243.54
              },
              {
                name: "807654321",
                y: 524.54
              },
              {
                name: "817654321",
                y: 4234.33
              },
              {
                name: "87654321",
                y: 434.18
              },
              {
                name: "87654322",
                y: 1233.34
              },
              {
                name: "87653333",
                y: 1222.6
              },
              {
                name: "87654344",
                y: 154.2
              },
              {
                name: "876543332",
                y: 290.61
              }
            ]
          }
        ]
      }
    };
  },
  validations: {          
                destAccount:{maxLength:maxLength(8)}           
  },
  methods: {
      updateData() {
          if(this.cleanAmount < 100000){
          this.$alert("succesful transaction.","","success","");
          }else{
              this.$alert("Amount to be transfer is less than 100000.","","error","");
          }

      },
      cancelData(){
          this.cleanAmount='';
          this.cleanAccount='';
      },
      validateText(){
          
      }   
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
};
</script>

<style scoped>
    #secure {
        background-color: #FFFFFF;
        border: 1px solid #CCCCCC;
        padding: 20px;
        margin-top: 10px;
    }
</style>