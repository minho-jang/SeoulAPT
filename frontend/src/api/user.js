import { instance_seoulapt } from '../util/axiosInstance';

export const login = (userid, userpwd) => {
  return instance_seoulapt.post('/user/login', { userid, userpwd });
};

export const modifyUser = (user) => {
  return instance_seoulapt.put('/user/modify', user);
};

export const join = (user) => {
  return instance_seoulapt.post('/user/join', user);
};

export const deleteUser = (userid) => {
  return instance_seoulapt.delete('/user/delete', { params: { userid } });
};

export const deleteHistory = (historyid) => {
  return instance_seoulapt.delete(`/user/history/delete?historyid=${historyid}`);
};

export const addHistory = (gugun, userid) => {
  return instance_seoulapt.post('/user/history/add', { gugun, userid });
};

export const getHistory = (userid) => {
  return instance_seoulapt.get('/user/history', { params: { userid } });
};
