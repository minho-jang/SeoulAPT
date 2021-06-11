import { instance_seoulapt } from '../util/axiosInstance';

export const getQnaList = () => {
  return instance_seoulapt.get('/qna/list');
};

export const getQnaDetail = (id) => {
  return instance_seoulapt.get(`/qna/list/${id}`);
};

export const writeQna = (qna) => {
  return instance_seoulapt.post('/qna/write', qna);
};

export const deleteQna = (id) => {
  return instance_seoulapt.delete('/qna/delete', { params: { id } });
};

export const modifyQna = (qna) => {
  return instance_seoulapt.put('/qna/modify', qna);
};
