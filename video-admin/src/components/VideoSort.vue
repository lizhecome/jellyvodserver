<template>
  <el-dialog title="设置剧集" :visible.sync="visible" width="50%" @close="close" @open="open">
    <el-form label-width="130px" :model="videoForm" ref="videoForm">
      <el-form-item
        label="电视剧"
        prop="tvId"
        :rules="[{required: true, message: '电视剧不能为空'}]">
        <el-select
          v-model="videoForm.tvId"
          remote
          filterable
          placeholder="请输入电视剧名称"
          :remote-method="loadTv"
          @change="selectTvChange"
          :loading="searchLoading">
          <el-option
            v-for="item in options"
            :key="item.tvId"
            :label="item.title"
            :value="item.tvId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="当前电视剧剧集数">
        <el-input type="" v-model="videoForm.total" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item
        prop="sort"
        label="设置剧集号"
      :rules="[{required: true, message: '剧集号不能为空'}, {type: 'number', message: '剧集号必须为数字'}, {type: 'number', min: 1, message: '剧集号不能小于1'}]">
        <el-input type="number" v-model.number="videoForm.sort" placeholder="剧集号不能大于当前电视剧剧集数"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取 消</el-button>
      <el-button type="primary" @click="confirm" :loading="confirmLoading">确 定</el-button>
    </span>
  </el-dialog>
</template>
<script>
  import qs from 'qs'

  export default {
    props: ['videoId', 'tvId', 'sort', 'tvName'],
    data() {
      return {
        visible: false,
        searchLoading: false,
        confirmLoading: false,
        options: [],
        videoForm: {
          tvName: '',
          tvId: '',
          sort: null,
          total: ''
        }        
      }
    },
    methods: {
      confirm() {
        this.$refs.videoForm.validate((valid) => {
          if (valid) {
            let { sort, total, tvId, tvName } = this.videoForm
            if (sort > total) {
              this.$message.warning('剧集号不能大于当前电视剧剧集数')
              return
            }
            let params = {
              videoId: this.videoId,
              tvId: tvId,
              sort: sort,
              tvName: tvName
            }
            this.confirmLoading = true
            this.axios.post('/console/longVideo/setLongVideoSort', qs.stringify(params))
            .then(({data}) => {
              this.visible = false
              if (data.code == '200') {
                this.$message.success('设置成功!')              
              } else {
                this.$message.error(data.message)
              }
              this.$emit('reload')
            })
            .catch(this.serviceError)
            .finally(() => {
              this.confirmLoading = false
            })
          } else {
            this.$message.warning('请填写表单!')
          }
        })
      },
      getTvInfo(tvId) {
        return this.axios.get('/console/longVideo/getTvPlayList', {
          params: { tvId, pageIndex: 1, pageSize: 5 }
        })
      },
      selectTvChange(tvId) {
        console.log(this.options)
        let item = this.options.find(g => g.tvId === tvId)
        this.videoForm.tvName = item.tvName
        if(tvId == this.tvId){
          this.videoForm.total = item.total
        }else {
          this.videoForm.total = item.total + 1
        }
      },
      loadTv(query) {
        if (query !== '') {
          let params = {
            pageIndex: 1,
            pageSize: 50,
            title: query
          }
          this.searchLoading = true
          this.axios.get('/console/longVideo/getTvPlayList', {params})
          .then(({data}) => {
            this.options = data.data.tvPlayList.map(item => {
              return {
                tvId: item.tvId,
                title: item.title,
                total: item.total ? Number(item.total) : 0,
                tvName: item.tvName
              }
            })
          })
          .finally(() => {
            this.searchLoading = false
          })

        } else {
          this.options = []
        }
      },
      close() {
        this.videoForm.tvId = ''
        this.videoForm.tvName = ''
        this.videoForm.tvItem = ''
        this.videoForm.sort = ''
        this.videoForm.total = ''
        this.options = []
      },
      open() {
        // if (this.tvId) {
        //   this.getTvInfo(this.tvId)
        //   .then(({ data }) => {
        //     console.log(data)
        //     let tvList = data.data.tvPlayList
        //     if (tvList.length) {
        //       this.videoForm.total = tvList[0].total ? Number(tvList[0].total) : 1
        //       this.options = [{
        //         tvId: this.tvId,
        //         title: tvList[0].title,
        //       }]
        //     }
        //   })
        //   .catch(this.serviceError)
        //   this.videoForm.sort = Number(this.sort)
        //   this.videoForm.tvId = this.tvId
        //   this.videoForm.tvName = this.tvName
        // }
      }
    }
  }
</script>