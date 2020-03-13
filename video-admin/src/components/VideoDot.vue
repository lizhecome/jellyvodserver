<template>
  <el-dialog
    title="设置视频打点信息"
    :visible.sync="visible"
    width="50%"
    @open="open"
    @close="close">
    <div class="player-container dot-info" id="player-container" v-loading="loading"></div>
    <el-form :model="dotForm" ref="dotForm" label-width="100px" class="dot-form" :loading="formLoading">
      <el-form-item v-for="(item, index) in dotForm.list" label="打点信息">
        <el-row>
          <el-col :span="14">
            <el-input
              type="number"
              placeholder="输入时间点(类型：数字，单位: s)"
              class="dot-time"
              v-model="item.time"></el-input>          
            <el-input
              type="textarea"
              maxlength="200"
              show-word-limit
              placeholder="输入打点内容"
              v-model="item.content"></el-input>            
          </el-col>
          <el-col :span="5">
            <el-button class="delete-dot-btn" plain @click="deleteDotItem(index)">删除</el-button>            
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="addDotItem">新增打点</el-button>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取 消</el-button>
      <el-button type="primary" @click="setDot" :loading="dotLoading">确 定</el-button>
    </span>
  </el-dialog>
</template>
<script>
  import qs from 'qs'

  export default {
    props: ['vid'],
    data() {
      return {
        visible: false,
        player: null,
        loading: false,
        dotForm: {
          list: [{
            time: '',
            content: ''
          }]
        },
        dotLoading: false,
        formLoading: false,
      }
    },
    methods: {
      setDot() {
        let dotList = this.dotForm.list
        if (dotList.some(item => item.time === '')) {
          this.$message.warning('打点时间点不能为空!')
          return
        }

        this.dotLoading = true
        let dotParams = dotList.map(item => ({
          videoId: this.vid,
          time: item.time,
          content: item.content
        }))
        let params = {
          parms: JSON.stringify({dotList: dotParams}),
          videoId: this.vid
        }

        this.axios.post("/console/longVideo/setLongVideoDot", qs.stringify(params))
        .then(({data}) => {
          this.$message.success('设置打点信息成功!')
          this.visible = false
        })
        .catch(this.serviceError)
        .finally(() => {
          this.dotLoading = false
        })
      },
      addDotItem() {
        this.dotForm.list.push({
          time: '',
          content: ''
        })
        
      },
      deleteDotItem(index) {
        this.dotForm.list.splice(index, 1)
      },
      open() {
        this.formLoading = true
        let paramsDot = {
          videoId: this.vid
        }
        this.axios.get('/console/longVideo/getDotListById', { params: paramsDot })
        .then(({data}) => {
          let list = data.data.map(item => ({
            time: item.time,
            content: item.content
          }))
          this.dotForm.list = list.length ? list : [{time: '', content: ''}]
        })
        .catch(this.serviceError)
        .finally(() => {
          this.formLoading = false
        })

        let params = { videoId: this.vid }
        this.loading = true
        this.axios.get('/vodVideo/getVideoById', { params })
          .then(({data}) => {
            let playInfoList = data.data.playInfoList
            let source = {}
            playInfoList.forEach(item => {
              let sourceItem = {}
              source[item.definition] = item.playURL
            })
            source = JSON.stringify(source)
            // let source = playInfoList.find(item => item.definition === 'OD').playURL
            this.player = this.createPlayer(source)
          })
          .catch(this.serviceError)
          .finally(() => {
            this.loading = false
          })
      },
      close() {
        if (this.player) {
          this.player.dispose()
          // this.player.pause()
        }
      },
      createPlayer (source) {
        var player = new Aliplayer({
          "id": "player-container",
          "controlBarVisibility": "always",
          "source": source,
          "width": "60%",
          "height": "250px",
          "autoplay": true,
        }, function (player) {});
        return player
      }
    }
  }
</script>
<style lang="scss">
  .player-container.dot-info {
    min-height: 250px;
    margin: 0 auto;
  }
  .dot-form {
    margin-top: 20px;
    .dot-time {
      margin-bottom: 10px;
    }
  }
  .delete-dot-btn {
    margin-left: 15px !important;
  }
</style>