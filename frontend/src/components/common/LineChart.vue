<script>
import { Line } from 'vue-chartjs';

export default {
  extends: Line,
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
      },
      legend: {
        display: false,
      },
    };
    this.renderChart(this.chartdata, this.options);
  },
};
</script>
