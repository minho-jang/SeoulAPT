<script>
import { Bar } from 'vue-chartjs';

export default {
  extends: Bar,
  props: ['chartdata'],
  data() {
    return {
      options: null,
    };
  },
  mounted() {
    this.options = {
      responsive: true,
      maintainAspectRatio: false, // default value. false일 경우 포함된 div의 크기에 맞춰서 그려짐.
      scales: {
        yAxes: [
          {
            ticks: {
              beginAtZero: true,
            },
          },
        ],
        xAxes: [
          {
            display: false, //this will remove all the x-axis grid lines
          },
        ],
      },
      legend: {
        display: false,
      },
    };
    this.renderChart(this.chartdata, this.options);
  },
};
</script>
