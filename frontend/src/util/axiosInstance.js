import axios from 'axios';

export const instance_seoulapt = axios.create({
  baseURL: 'http://localhost',
});

// request 보내기 전 처리
instance_seoulapt.interceptors.request.use(
  (config) => {
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// response 받기 전 처리
instance_seoulapt.interceptors.response.use(
  (response) => {
    return response;
  },

  (error) => {
    return Promise.reject(error);
  }
);
