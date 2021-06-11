import { instance_seoulapt } from '../util/axiosInstance';

export const getApts = (gugun) => {
  return instance_seoulapt.get('/house/gugun', { params: { gugun } });
};

export const getAptDeals = (gugun, dong, aptName) => {
  return instance_seoulapt.get('/house/gugun/apt', { params: { gugun, dong, aptName } });
};

export const getGugunAptDeals = (gugun, dong, aptName) => {
  return instance_seoulapt.get('/house/chart/gugun/price', { params: { gugun, dong, aptName } });
};

export const getGugunAptArea = (gugun, dong, aptName) => {
  return instance_seoulapt.get('/house/chart/gugun/area', { params: { gugun, dong, aptName } });
};

export const getAptPriceTrend = (gugun, dong, aptName) => {
  return instance_seoulapt.get('/house/chart/apt/price', { params: { gugun, dong, aptName } });
};
