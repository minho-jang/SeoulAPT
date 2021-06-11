import { instance_seoulapt } from '../util/axiosInstance';

export const getCommentList = (qnaboardid) => {
  return instance_seoulapt.get('/qna/comment/list', { params: { qnaboardid } });
};

export const writeComment = (comment) => {
  return instance_seoulapt.post('/qna/comment/write', comment);
};

export const deleteComment = (qnacommentid) => {
  return instance_seoulapt.delete('/qna/comment/delete', { params: { qnacommentid } });
};

export const modifyComment = (comment) => {
  return instance_seoulapt.put('/qna/comment/modify', comment);
};
