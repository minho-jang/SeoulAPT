import { instance_seoulapt } from '../util/axiosInstance';

export const getDongCode = (address) => {
  return instance_seoulapt.get('/house/addrToCode', { params: { address } });
};
