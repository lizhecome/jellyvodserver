import axios from 'axios'
import qs from 'qs'
/**
 * 获取标签列表
 * type: 标签类型(（1，长视频 2，电视剧3，vip视频）)
 */
function getTags(type) {
  return axios.get('/console/longVideo/getTagsListByType', {params: {
    type
  }})
}

export {
  getTags
}