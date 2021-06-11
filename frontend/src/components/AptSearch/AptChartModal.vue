<template>
  <b-modal
    :title="'[' + apt.aptName + '] 거래정보 그래프'"
    v-model="showModal"
    @hidden="hideModal"
    scrollable
    ok-only
    size="lg"
    content-class="mh-70vh"
  >
    <div class="chart-div">
      <h5>[{{ apt.aptName }}] 시세 변화</h5>
      <apt-price-chart v-if="loaded" :chartdata="chartdataTrend" class="chart-component" />
    </div>
    <div class="chart-div mt-3">
      <h5>[{{ params.sido + ' ' + params.gugun }}] 아파트 매매가 분포</h5>
      <apt-deal-chart v-if="loaded" :chartdata="chartdataPrice" class="chart-component" />
    </div>
    <div class="chart-div mt-3">
      <h5>[{{ params.sido + ' ' + params.gugun }}] 아파트 면적 분포</h5>
      <apt-deal-chart v-if="loaded" :chartdata="chartdataArea" class="chart-component" />
    </div>
  </b-modal>
</template>

<script>
import AptDealChart from '../common/BarChart.vue';
import AptPriceChart from '../common/LineChart.vue';
import { getGugunAptDeals, getGugunAptArea, getAptPriceTrend } from '../../api/apt.js';

export default {
  props: ['show', 'apt', 'params'],
  components: { AptDealChart, AptPriceChart },
  data: () => ({
    showModal: false,
    loaded: false,
    chartdataPrice: null,
    dataPrice: null,
    chartdataArea: null,
    dataArea: null,
    chartdataTrend: null,
    dataTrend: null,
  }),
  computed: {
    colorizePrice() {
      return this.dataPrice.map((item) => {
        if (item.check == 'T') {
          return '#00802C';
        } else {
          return '#FACCCA';
        }
      });
    },
    colorizeArea() {
      return this.dataArea.map((item) => {
        if (item.check == 'T') {
          return '#00802C';
        } else {
          return '#FACCCA';
        }
      });
    },
  },
  async mounted() {
    this.loaded = false;

    try {
      const responsePrice = await getGugunAptDeals(
        this.params.gugun,
        this.apt.dong,
        this.apt.aptName
      );
      this.dataPrice = responsePrice.data;
      this.chartdataPrice = {
        labels: this.dataPrice.map((item) => item.dealAmount),
        datasets: [
          {
            backgroundColor: this.colorizePrice,
            data: this.dataPrice.map((item) => item.dealAmount),
          },
        ],
      };

      const responseArea = await getGugunAptArea(
        this.params.gugun,
        this.apt.dong,
        this.apt.aptName
      );
      this.dataArea = responseArea.data;
      this.chartdataArea = {
        labels: this.dataArea.map((item) => item.area),
        datasets: [
          {
            backgroundColor: this.colorizeArea,
            data: this.dataArea.map((item) => item.area),
          },
        ],
      };

      const responseTrend = await getAptPriceTrend(
        this.params.gugun,
        this.apt.dong,
        this.apt.aptName
      );
      this.dataTrend = responseTrend.data;
      this.chartdataTrend = {
        labels: this.dataTrend.map(
          (item) => item.dealYear + '-' + item.dealMonth + '-' + item.dealDay
        ),
        datasets: [
          {
            borderColor: '#69CC37',
            data: this.dataTrend.map((item) => item.dealAmount),
            tension: 0,
          },
        ],
      };
      this.loaded = true;
    } catch (err) {
      console.error(err);
    }
  },
  watch: {
    show(val) {
      this.showModal = val;
    },
  },
  methods: {
    hideModal() {
      this.$emit('hidden');
    },
  },
};
</script>

<style>
.chart-component {
  height: 30vh;
}
.mh-70vh {
  min-height: 70vh;
}
</style>
