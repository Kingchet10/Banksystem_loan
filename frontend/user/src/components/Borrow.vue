<template>
    <div class="page-container">
      <div class="submit-button-container">
        <el-button type="primary" @click="confirmRepayment">确认还款（Submit）</el-button>
      </div>
      <div class="repayment-form">
        <el-form ref="repaymentForm" :model="repaymentData" label-position="top">
          <el-form-item label="贷款ID号(Loan ID)">
            <el-input v-model="repaymentData.loanId" placeholder="请输入贷款ID号" />
          </el-form-item>
          <el-form-item label="选择还款银行卡(Repayment Bank Card)">
            <el-select v-model="repaymentData.selectedCard" placeholder="请选择还款银行卡">
              <el-option
                v-for="card in bankCards"
                :key="card.id"
                :label="card.cardNumber + ' - ' + card.holderName + ' - ' + card.bank"
                :value="card.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </template>
  
  <script>
  import { ElForm, ElFormItem, ElInput, ElButton, ElSelect, ElOption, ElMessage } from 'element-plus';
  
  export default {
    components: {
      ElForm, ElFormItem, ElInput, ElButton, ElSelect, ElOption, ElMessage
    },
    data() {
      return {
        repaymentData: {
          loanId: '',
          selectedCard: ''
        },
        bankCards: [
          { id: 'card1', cardNumber: '**** **** **** 1234', holderName: '张三', bank: '建设银行' },
          { id: 'card2', cardNumber: '**** **** **** 5678', holderName: '李四', bank: '工商银行' },
          { id: 'card3', cardNumber: '**** **** **** 9012', holderName: '王五', bank: '农业银行' }
        ]
      };
    },
    methods: {
      async confirmRepayment() {
        try {
          const response = await this.$axios.post('/confirm-repayment', this.repaymentData);
          this.$message.success('还款申请已提交成功！');
        } catch (error) {
          console.error('提交还款申请时发生错误:', error);
          this.$message.error('提交失败。');
        }
      }
    }
  }
  </script>
  
  <style scoped>
  .page-container {
    position: relative;
    max-width: 600px;
    margin: 20px auto;
    padding: 20px;
    background-color: #fff;
    border: 1px solid #ccc;
    border-radius: 15px;
    box-shadow: 0 2px 8px rgba(0,0,0,0.1);
    box-sizing: border-box;
  }
  
  .repayment-form {
    padding: 20px;
    max-height: calc(100vh - 100px); /* 设置最大高度 */
    overflow-y: auto; /* 添加垂直滚动条 */
  }
  
  .submit-button-container {
    position: fixed;
    top: 20px;
    right: 20px;
    z-index: 1000;
  }
  
  .el-form-item {
    margin-bottom: 30px;
  }
  
  .el-input {
    width: 100%;
  }
  
  .el-button {
    width: 150px;
  }
  </style>
  