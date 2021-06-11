<template>
  <div class="wrapper mt-5">
    <!-- <img src="@/assets/About.jpg" alt="about-page-image" width="100%" style="position:relative" /> -->
    <b-container>
      <img src="@/assets/seoulapt_logo.png" alt="amsg" width="40%" id="titleImg" />
      <div class="box1">
        <h5>페이지소개</h5>
        <br />
        <img src="@/assets/house.jpg" alt="amsg" width="40%" id="box1img" />
        <div style="display: inline-block; width: 60%; margin-top: 20px">
          <h5 style="text-align: center">Seoul APT</h5>
          <br />
          아파트 매매 공공 데이터 API와 카카오 지도 API 사용를 사용해<br />
          우리나라의 아파트 매매 정보를 한 눈에 알아볼 수 있습니다.<br /><br />
          이용자들 간에 QnA 게시판을 통해 매매정보에 대해 궁금한 사항 묻고 답할 수 있습니다.<br />
          <br />
        </div>
      </div>
      <div class="box2">
        <h5>팀원 소개 및 역할</h5>
        <br />
        <div>
          <img src="@/assets/kwon.jpg" alt="amsg" width="30%" id="box2img_1" />
          <div>
            권영린(팀장) <br />
            Back-end
          </div>
        </div>
        <div>
          <img src="@/assets/jang.jpg" alt="amsg" width="30%" id="box2img_2" />
          <div>
            장민호<br />
            Front-end
          </div>
        </div>
      </div>
      <div class="box3">
        <h5>사용 기술</h5>
        <br />
        <img src="@/assets/structure_3.png" alt="amsg" width="100%" id="box3img" />
      </div>
    </b-container>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.container div {
  width: 80%;
  margin: 30px auto;
  background-color: whitesmoke;
  border-radius: 10px;
  padding: 20px;
  margin-bottom: 0px;
  vertical-align: top;
}

#box1img {
  margin-top: 10px;
}

#titleImg {
  display: block;
  margin: 0 auto;
}

.box2 img {
  border-radius: 70%;
  display: inline-block;
}

.box2 div {
  display: inline-block;
  width: 50%;
}

.box2 div div {
  display: inline-block;
  width: 70%;
  margin: 0 auto;
}

h5 {
  font-weight: bold;
}

.wrapper {
  font-weight: 600;
  margin-bottom: 100px;
}
</style>
