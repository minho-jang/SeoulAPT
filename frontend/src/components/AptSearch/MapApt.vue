<template>
  <b-overlay :show="isLoading" class="h-100"><div id="map" ref="mapContainer"></div></b-overlay>
</template>

<script>
import { mapState, mapGetters, mapMutations } from 'vuex';

export default {
  data() {
    return {
      map: null,
      clusterer: null,
      onMark: null,
      isLoading: false,
    };
  },
  props: ['params'],
  mounted() {
    this.isLoading = true;
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement('script');
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = `http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAO_API_JS_KEY}&libraries=services,clusterer`;
      document.head.appendChild(script);
    }
    this.isLoading = false;
  },
  computed: {
    ...mapState(['apts', 'clickedApt', 'markers']),
    ...mapGetters(['getMarkers']),
  },
  watch: {
    clickedApt(val) {
      this.map.setCenter(val.marker.getPosition());
      this.map.setLevel(3);
      if (this.onMark) {
        this.markerOff(this.onMark);
        this.markerOn(val);
      } else {
        this.markerOn(val);
      }
      this.onMark = val;
    },
    apts() {
      this.markAptToMap();
    },
    markers() {
      this.makeMarkerClusterer();
    },
  },
  methods: {
    ...mapMutations(['SET_SELECTED_APT_DETAIL', 'PUSH_MARKER', 'CLEAR_MARKERS']),
    initMap() {
      const container = document.getElementById('map');
      const options = {
        center: new kakao.maps.LatLng(37.555153, 126.970765),
        level: 7,
      };
      this.map = new kakao.maps.Map(container, options);

      // 마커 클러스터러를 생성합니다
      this.CLEAR_MARKERS();
      this.clusterer = new kakao.maps.MarkerClusterer({
        map: this.map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 5, // 클러스터 할 최소 지도 레벨
      });
    },
    markAptToMap() {
      // if (!window.kakao) {
      //   console.log('no kakao');
      //   return;
      // }

      // 주소-좌표 변환 객체를 생성합니다
      const geocoder = new kakao.maps.services.Geocoder();

      this.apts.forEach((item, index) => {
        const address =
          this.params.sido + ' ' + this.params.gugun + ' ' + item.dong + ' ' + item.jibun;

        // 주소로 좌표를 검색합니다
        geocoder.addressSearch(address, (result, status) => {
          // 정상적으로 검색이 완료됐으면
          if (status === kakao.maps.services.Status.OK) {
            const coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            if (index == 0) {
              // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
              this.map.setCenter(coords);
            }

            // 결과값으로 받은 위치를 마커로 표시합니다
            const marker = new kakao.maps.Marker({
              // map: this.map,
              position: coords,
              opacity: 0.5,
              clickable: true,
            });

            // 인포윈도우로 장소에 대한 설명을 표시합니다
            const infowindow = new kakao.maps.InfoWindow({
              content: `<div style="width:150px;text-align:center;padding:6px 0;">${item.aptName}</div>`,
            });

            item.marker = marker;
            item.infowindow = infowindow;
            this.PUSH_MARKER(marker);

            kakao.maps.event.addListener(
              marker,
              'mouseover',
              this.makeOverListener(marker, infowindow)
            );
            kakao.maps.event.addListener(
              marker,
              'mouseout',
              this.makeOutListener(marker, infowindow)
            );
            kakao.maps.event.addListener(marker, 'click', this.makeClickListner(item));
          }
        });
      });
    },
    makeOverListener(marker, infowindow) {
      return () => this.markerOn({ marker, infowindow });
    },
    makeOutListener(marker, infowindow) {
      return () => this.markerOff({ marker, infowindow });
    },
    markerOn({ marker, infowindow }) {
      marker.setOpacity(1);
      infowindow.open(this.map, marker);
    },
    markerOff({ marker, infowindow }) {
      marker.setOpacity(0.5);
      infowindow.close();
    },
    makeClickListner(apt) {
      return () => {
        this.$emit('clickMarker', apt);
      };
    },
    makeMarkerClusterer() {
      // 클러스터러에 마커들을 추가합니다
      this.clusterer.addMarkers(this.markers);
    },
  },
};
</script>

<style>
#map {
  width: 100%;
  height: 100%;
}
</style>
