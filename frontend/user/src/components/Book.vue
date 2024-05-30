<template>
  <div class="page-container">
    <div class="submit-button-container">
      <el-button type="primary" @click="submitForm">提交（Submit）</el-button>
    </div>
    <div class="loan-application-form">
      <el-form ref="loanForm" :model="loanData" label-position="top">
        <el-form-item label="选择银行卡">
          <el-select v-model="loanData.selectedCard" placeholder="请选择银行卡">
            <el-option
              v-for="card in bankCards"
              :key="card.id"
              :label="card.cardNumber + ' - ' + card.holderName + ' - ' + card.bank"
              :value="card.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="姓名(Name)">
              <el-input v-model="loanData.name" />
            </el-form-item>
            <el-form-item label="身份证号(ID Number)">
              <el-input v-model="loanData.idNumber" />
            </el-form-item>
            <el-form-item label="出生日期(Date of Birth)">
              <el-date-picker v-model="loanData.dob" type="date" placeholder="选择日期" />
            </el-form-item>
            <el-form-item label="性别(Gender)">
              <el-select v-model="loanData.gender" placeholder="请选择性别">
                <el-option label="男" value="male"></el-option>
                <el-option label="女" value="female"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="婚姻情况(Marital Status)">
              <el-select v-model="loanData.maritalStatus" placeholder="请选择婚姻情况">
                <el-option label="已婚" value="married"></el-option>
                <el-option label="未婚" value="unmarried"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="职业(Occupation)">
              <el-input v-model="loanData.occupation" />
            </el-form-item>
            <el-form-item label="年收入(Annual Income)">
              <el-input v-model="loanData.annualIncome" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="住宅地址(Residential Address)">
              <el-input v-model="loanData.residentialAddress" />
            </el-form-item>
            <el-form-item label="电话号码(Phone Number)">
              <el-input v-model="loanData.phoneNumber" />
            </el-form-item>
            <el-form-item label="电子邮件地址(Email Address)">
              <el-input v-model="loanData.email" />
            </el-form-item>
            <el-form-item label="教育背景(Education Background)">
              <el-input v-model="loanData.educationBackground" />
            </el-form-item>
            <el-form-item label="贷款用途(Loan Purpose)">
              <el-input v-model="loanData.loanPurpose" />
            </el-form-item>
            <el-form-item label="贷款金额(Loan Amount)">
              <el-input v-model="loanData.loanAmount" />
            </el-form-item>
            <el-form-item label="贷款期限(Loan Term)">
              <el-input v-model="loanData.loanTerm" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="个人情况声明(Personal Statement)">
          <el-input type="textarea" :autosize="{ minRows: 7, maxRows: 14 }" v-model="loanData.personalStatement" />
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { ElForm, ElFormItem, ElInput, ElButton, ElRow, ElCol, ElDatePicker, ElSelect, ElOption, ElMessage } from 'element-plus';

export default {
  components: {
    ElForm, ElFormItem, ElInput, ElButton, ElRow, ElCol, ElDatePicker, ElSelect, ElOption, ElMessage
  },
  data() {
    return {
      loanData: {
        name: '',
        idNumber: '',
        dob: '',
        gender: '',
        maritalStatus: '',
        occupation: '',
        annualIncome: '',
        residentialAddress: '',
        phoneNumber: '',
        email: '',
        educationBackground: '',
        loanPurpose: '',
        loanAmount: '',
        loanTerm: '',
        personalStatement: '',
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
    async submitForm() {
      try {
        const response = await this.$axios.post('/submit-loan-application', this.loanData);
        this.$message.success('贷款申请已提交成功！');
      } catch (error) {
        console.error('提交贷款申请时发生错误:', error);
        this.$message.error('提交失败。');
      }
    }
  }
}
</script>

<style scoped>
.page-container {
  position: relative;
  max-width: 1000px;
  margin: 20px auto;
  padding: 20px;
  background-color: #fff;
  border: 1px solid #ccc;
  border-radius: 15px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
  box-sizing: border-box;
}

.loan-application-form {
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
