<script>
  export default {
    props: ['id', 'name', 'array'],
    data() {
      return {
        file: '',
        upload: '',
        minhamesa: `http://minhamesarpg.com/users/img/mapas/`,
      };
    },
    methods: {
      handleAvatarSuccess(res, file) {
        this.upload = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload(file) {
        this.array.name = file.name;
        this.array.type = file.type;
        this.array.url = this.minhamesa + file.name;
        this.array.file = file;

        const isLt2M = file.size / 1024 / 1024 < 2;
        this.array.size = isLt2M;
        console.log(this.array);
        if (!isLt2M) {
          this.$message.error('Avatar picture size can not exceed 2MB!');
        }
        return isLt2M;
      }
    }
  }
</script>
<template>
  <div>
    <el-upload
      :action="minhamesa"
      class="avatar-uploader"
      :data="array"
      :on-success="handleAvatarSuccess"
      :before-upload="beforeAvatarUpload">
      <img v-if="array.name" :src="array.url" class="avatar">
      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>
  </div>
</template>
<style>
  el-upload{
    border: dotted;
  }
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }

  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
