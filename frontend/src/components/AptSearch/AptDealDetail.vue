<template>
  <div>
    <b-container>
      <b-row class="mb-3">
        <b-col lg="6" class="roadview-wrapper">
          <div id="roadview"></div>
        </b-col>
        <b-col lg="6">
          <div class="mb-1 d-flex justify-content-between">
            <span class="h5"><strong>요약 정보</strong></span>
            <b-button size="sm" class="shadow" variant="success" @click="onClickChart">
              그래프로 보기
            </b-button>
          </div>
          <div class="mb-3">
            <span class="mb-1">최근거래가</span>
            <b-form-input
              :value="
                buildKoreanNum(dealAmountToNumber(recentDealAmount.dealAmount)) +
                ' (' +
                recentDealAmount.dealDate +
                ')'
              "
              readonly
            ></b-form-input>
          </div>
          <div class="mb-3">
            <span class="mb-1">최고가</span>
            <b-form-input
              :value="
                buildKoreanNum(dealAmountToNumber(maxDealAmount.dealAmount)) +
                ' (' +
                maxDealAmount.dealDate +
                ')'
              "
              readonly
            ></b-form-input>
          </div>
          <div class="mb-3">
            <span class="mb-1">최저가</span>
            <b-form-input
              :value="
                buildKoreanNum(dealAmountToNumber(minDealAmount.dealAmount)) +
                ' (' +
                minDealAmount.dealDate +
                ')'
              "
              readonly
            ></b-form-input>
          </div>
          <div class="mb-3">
            <span class="mb-1">평균가</span>
            <b-form-input v-model="avgDealAmount" readonly></b-form-input>
          </div>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-table
            sticky-header="true"
            :items="aptDealInfo"
            :fields="fields"
            :sort-by.sync="sortBy"
            :sort-desc.sync="sortDesc"
            sort-icon-left
          >
            <template #cell(No)="data">
              {{ data.index + 1 }}
            </template>
            <template #cell(area)="data"> {{ data.item.area }} m<sup>2</sup> </template>
            <template #cell(dealAmount)="data">
              {{ buildKoreanNum(dealAmountToNumber(data.item.dealAmount)) }} 원
            </template>
          </b-table>
        </b-col>
      </b-row>
    </b-container>

    <apt-chart-modal :apt="apt" :params="params" :show="showChartModal" @hidden="onHiddenChart" />
  </div>
</template>

<script>
import { getAptDeals } from '../../api/apt.js';
import AptChartModal from './AptChartModal.vue';

export default {
  props: ['apt', 'params'],
  components: {
    AptChartModal,
  },
  created() {
    getAptDeals(this.params.gugun, this.apt.dong, this.apt.aptName)
      .then(({ data }) => {
        this.aptDealInfo = data.map((obj) => ({
          ...obj,
          dealDate: obj.dealYear + '.' + obj.dealMonth + '.' + obj.dealDay,
        }));
      })
      .catch((err) => {
        console.error(err);
      });
  },
  mounted() {
    var roadviewContainer = document.getElementById('roadview'); //로드뷰를 표시할 div
    var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
    var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

    var position = this.apt.marker.getPosition();

    // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
    roadviewClient.getNearestPanoId(position, 50, function (panoId) {
      if (panoId) {
        roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
      } else {
        var roadviewElement = document.getElementById('roadview');
        roadviewElement.style = 'height: 120px';
        console.log(roadviewElement);
        var content = `
          <div class="text-center my-5" style="height:120px">로드뷰를 제공하지 않습니다 :(</div>
        `;
        roadviewElement.innerHTML = content;
      }
    });
  },
  data() {
    return {
      sortBy: 'dealDate',
      sortDesc: true,
      aptDealInfo: [],
      fields: [
        'No',
        { key: 'dealAmount', label: '거래금액', sortable: true },
        { key: 'dealDate', label: '거래일자', sortable: true },
        { key: 'area', label: '면적', sortable: true },
        { key: 'floor', label: '층' },
      ],
      showChartModal: false,
    };
  },
  computed: {
    recentDealAmount() {
      return {
        ...this.apt,
        dealDate: this.apt.dealYear + '.' + this.apt.dealMonth + '.' + this.apt.dealDay,
      };
    },
    maxDealAmount() {
      if (!this.aptDealInfo || this.aptDealInfo.length == 0) {
        return { dealDate: '' };
      }

      var maxDeal = 0;
      var maxDealInfo = {};
      for (var i = 0; i < this.aptDealInfo.length; i++) {
        var deal = this.dealAmountToNumber(this.aptDealInfo[i].dealAmount);
        if (maxDeal < deal) {
          maxDeal = deal;
          maxDealInfo = this.aptDealInfo[i];
        }
      }
      return maxDealInfo;
    },
    minDealAmount() {
      if (!this.aptDealInfo || this.aptDealInfo.length == 0) {
        return { dealDate: '' };
      }

      var minDeal = 0;
      var minDealInfo = {};
      for (var i = 0; i < this.aptDealInfo.length; i++) {
        var deal = this.dealAmountToNumber(this.aptDealInfo[i].dealAmount);
        if (minDeal == 0 || minDeal > deal) {
          minDeal = deal;
          minDealInfo = this.aptDealInfo[i];
        }
      }
      return minDealInfo;
    },
    avgDealAmount() {
      if (!this.aptDealInfo || this.aptDealInfo.length == 0) {
        return 0;
      }

      var total = 0;
      for (var i = 0; i < this.aptDealInfo.length; i++) {
        total += this.dealAmountToNumber(this.aptDealInfo[i].dealAmount);
      }
      return this.buildKoreanNum(parseInt(total / this.aptDealInfo.length));
    },
  },
  methods: {
    makeDateFormat(year, month, day) {
      month = month >= 10 ? month : '0' + month;
      day = day >= 10 ? day : '0' + day;
      return year + '-' + month + '-' + day;
    },
    numComma(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    },
    buildKoreanNum(num) {
      num = String(num);
      let result = '',
        man = '';

      if (num.length >= 4) {
        num = num.substring(0, num.length - 4);
      }
      if (num.length >= 4) {
        man = num.substring(num.length - 4);
        num = num.substring(0, num.length - 4);
      }
      if (Number(num) != 0) {
        result = result.concat(num, '억 ');
      }
      if (Number(man) != 0) {
        result = result.concat(man, '만');
      }

      return result;
    },
    dealAmountToNumber(val) {
      if (!val) {
        return 0;
      }

      return parseInt(val.split(',').join('') + '0000');
    },
    onClickChart() {
      this.showChartModal = true;
    },
    onHiddenChart() {
      this.showChartModal = false;
    },
  },
};
</script>

<style>
#roadview {
  width: 100%;
  height: 400px;
}
.roadview-wrapper {
  margin-bottom: 1.25rem;
}
@media (min-width: 992px) {
  #roadview {
    height: 100%;
  }
  .roadview-wrapper {
    margin-bottom: 0;
  }
}
</style>
