<script>
  {
    data: function () {
      return {
        serviceProviders: [],
        serviceProviderValue: "",
        services: [],
        client: [],
        clientValue: "",
        serviceValue: "",
        months: [
          {
            label: "Janeiro",
            value: 1
          },
          {
            label: "Fevereiro",
            value: 2
          },
          {
            label: "Março",
            value: 3
          },
          {
            label: "Abril",
            value: 4
          },
          {
            label: "Maio",
            value: 5
          },
          {
            label: "Junho",
            value: 6
          },
          {
            label: "Julho",
            value: 7
          },
          {
            label: "Agosto",
            value: 8
          },
          {
            label: "Setembro",
            value: 9
          },
          {
            label: "Outubro",
            value: 10
          },
          {
            label: "Novembro",
            value: 11
          },
          {
            label: "Dezembro",
            value: 12
          },
        ],
        currentMonths: [],
        monthValue: "",
        showServices: false,
        options: {
          chart: {
            type: 'column',
            backgroundColor: 'transparent'
          },
          title: {
            text: 'Quantidade de Chamados Abertos'
          },
          subtitle: {},
          xAxis: {
            categories: [],
            crosshair: true,
          },
          tooltip: {
            formatter: function () {
              shared: true
              return '<b>' + this.series.name + ', ' + this.point.category + '</b><br/>' +
                'Ordens de Serviço: ' + Highcharts.numberFormat(Math.abs(this.point.y), 0);
            }
          },
          yAxis: {
            title: {
              text: 'Ordens de Serviço'
            }
          },
          plotOptions: {
            column: {
              pointPadding: 0.2,
              borderWidth: 0
            },
            series: {
              dataLabels: {
                enabled: true,
              }
            }
          },
          series: []
        },
      }
    }
  ,
    methods: {
      doSearch: function () {

        this.$loading({fullscreen: true, loading: true});

        let instance = {
          serviceProvider: this.serviceProviderValue,
          service: this.serviceValue,
          month: this.monthValue,
        }

        let httpOptions = {
          method: 'post',
          url: this._global().url + "/api/wsi/QTT_SO_OPEN",
          data: JSON.stringify(instance)
        };

        axios(httpOptions).then(response => {
          let data = response.data;

          this.options.subtitle = data.subtitle;
          this.options.xAxis.categories = data.categories;
          this.options.series = data.series;

          this.$loading().close();
        });

      }
    ,
      loadServices: function () {

        this.$loading({fullscreen: true, loading: true});

        let instance = {
          serviceProvider: this.serviceProviderValue
        }

        let httpOptions = {
          method: 'post',
          url: this._global().url + "/api/wsi/LOAD_SP_C",
          data: JSON.stringify(instance)
        };

        axios(httpOptions).then(response => {
          let data = response.data;

          this.services = data.services;

          this.$loading().close();
        });

      }
    }
  ,
    mounted: function () {
      this.$loading({fullscreen: true, loading: true});
      axios.get(this._global().url + "/api/wsi/LOAD_SP_C")
        .then(response => {
          let data = response.data;

          this.serviceProviders = data.serviceProviders;

          this.$loading().close();
        })
        .then(() => {
          let date = new Date().getMonth();
          for (var i = 0; i <= date; i++) {
            let myPush = this.currentMonths;
            myPush.push(this.months[i]);
          }
        })
        .then(() => {
          this.doSearch()
        })
    }
  ,
    watch: {
      // sempre que a pergunta mudar, essa função será executada
      serviceProviderValue: function (value) {
        this.serviceValue = "";

        if (value == "") {
          this.showServices = false;
        } else {
          this.loadServices();
          this.showServices = true;
        }
      }
    }
  }
</script>

<template>
  <div>
    <div class="row">
      <div class="row">
        <div class="col-md-12">
          <div class="panel">
            <div class="panel-body">
               <el-form label-position="top">
        <div class="row">
          <div class="col-md-3">
            <el-form-item label="Prestador de Serviço">
              <el-select filterable clearable v-model="serviceProviderValue">
                <el-option
                  v-for="item in serviceProviders"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </div>
          <div class="col-md-3">
            <el-form-item label="Data de inicio">
              <el-select filterable clearable v-model="monthValue">
                <el-option
                  v-for="item in currentMonths"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </div>
          <div class="col-md-3">
            <el-form-item label="Data fim">
              <el-select filterable clearable v-model="monthValue">
                <el-option
                  v-for="item in currentMonths"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </div>
          <div class="col-md-3">
            <el-form-item label="contratante">
              <el-select filterable clearable v-model="clientValue">
                <el-option
                  v-for="item in client"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </div>
        </div>
        <div class="row">
          <div class="col-md-3" v-show="showServices">
            <el-form-item label="Serviço">
              <el-select filterable clearable v-model="serviceValue">
                <el-option
                  v-for="item in services"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </div>
          <div class="col-md-3">
            <el-button type="primary" size="small" native-type="submit" @click.prevent="doSearch()"
                       style="margin-bottom: 20px;">
              <i class="fa fa-undo"></i>
              Atualizar
            </el-button>
          </div>
        </div>
      </el-form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="row">
        <div class="col-md-12">
          <div class="panel">
            <div class="panel-body">
              <highcharts :options="options" ref="highcharts"></highcharts>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12">
          <div class="panel">
            <div class="panel-body">
              <div class="col-md-6">
                <highcharts :options="options2" ref="highcharts"></highcharts>
              </div>
              <div class="col-md-6">
                <highcharts :options="options3" ref="highcharts"></highcharts>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
</style>
