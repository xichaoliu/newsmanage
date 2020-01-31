<template>
  <div>
    <input type="file" @change="handleFileChange">
    <el-button @click="handleUpload">上传</el-button>
  </div>
</template>
<script>
// import {getUserList} from '../../api/api';
import ApiService from '../../api/api';

const LENGTH = 10 // 切片数量
export default {
  data: () => ({
    container: {
      file: null
    }
  }),
  mounted() {
  },
  methods: {
    handleFileChange(e) {
      const [file] = e.target.files;
      if (!file) return;
      this.container.file = file;
    },
   
    // 生成切片文件
    createFileChunk(file, length = LENGTH) {
      const fileChunkList = [];
      const chunkSize = Math.ceil(file.size/length);
      let cur = 0;
      while(cur < file.size) {
        fileChunkList.push({
          file: file.slice(cur, cur+chunkSize)
        });
        cur += chunkSize;
      }
      console.log("filechunklist", fileChunkList);
      return fileChunkList;
    },
    // 上传切片
    async uploadChunks(fileList) {
        const requestList = fileList
        .map(({chunk,hash}) => {
          const formData= new FormData();
          formData.append("chunk", chunk);
          formData.append("hash", hash);
          formData.append("filename", this.container.file.name);
          return {formData};
        })
        .map(async ({formData}) => ApiService.uploadFile(formData))
        console.log(requestList);
        await Promise.all(requestList).catch((e) => {
          console.log("error", e)
        });
    },
     async handleUpload() {
       if (!this.container.file) return;
       const fileChunkList = this.createFileChunk(this.container.file);
       const fileList = fileChunkList.map(({file}, index) => ({
         chunk: file,
         hash:this.container.file.name + "-" + index // 文件名 + 数组下标
       }))
       await this.uploadChunks(fileList);
     },
  }
}
</script>
<style lang="scss" scoped>

</style>