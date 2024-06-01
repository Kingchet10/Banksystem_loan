<template>
    <div class="user-loan-history-page">
      <el-form :model="searchForm" label-width="120px" class="search-form">
        <el-form-item label="身份证号">
          <el-input v-model="searchForm.idNumber" placeholder="请输入身份证号"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="searchLoans(searchForm.idNumber)">查询</el-button>
        </el-form-item>
      </el-form>
      <div class="loan-list-container" v-if="loans.length > 0">
        <el-table :data="loans" style="width: 100%" class="loan-table" header-align="center" align="center">
          <el-table-column prop="loan_id" label="贷款编号" width="180"></el-table-column>
          <el-table-column prop="borrow_id" label="借款人编号" width="180"></el-table-column>
          <el-table-column prop="card_id" label="转入卡编号" width="180"></el-table-column>
          <el-table-column prop="officer_id" label="审查员编号" width="180"></el-table-column>
          <el-table-column prop="amount" label="贷款金额" width="180"></el-table-column>
          <el-table-column prop="rate" label="贷款利率" width="180"></el-table-column>
          <el-table-column prop="term" label="贷款期限" width="180"></el-table-column>
          <el-table-column prop="status" label="贷款状态" width="180"></el-table-column>
          <el-table-column prop="date_applied" label="申请日期" width="180"></el-table-column>
          <el-table-column prop="date_approved" label="审批日期" width="180"></el-table-column>
          <el-table-column prop="form_id" label="表单编号" width="180"></el-table-column>
        </el-table>
      </div>
    </div>
  </template>
  
  <script>
  import { ref } from 'vue';
  import { ElTable, ElTableColumn, ElForm, ElFormItem, ElInput, ElButton, ElMessage } from 'element-plus';
  
  export default {
    components: {
      ElTable, ElTableColumn, ElForm, ElFormItem, ElInput, ElButton, ElMessage
    },
    setup() {
      const searchForm = ref({ idNumber: '' });
      const loans = ref([]);
  
      const searchLoans = () => {
        // 模拟贷款数据
        const allLoans = [
          { loan_id: 1, borrow_id: 101, card_id: 1001, officer_id: 201, amount: 50000, rate: 3.5, term: 36, status: 'application', date_applied: '2023-01-01', date_approved: '2023-01-05', form_id: 301, idNumber: '123456789012345678' },
          { loan_id: 2, borrow_id: 102, card_id: 1002, officer_id: 202, amount: 75000, rate: 4.0, term: 48, status: 'repayment', date_applied: '2023-02-15', date_approved: '2023-02-20', form_id: 302, idNumber: '123456789012345679' },
          { loan_id: 3, borrow_id: 103, card_id: 1003, officer_id: 203, amount: 30000, rate: 2.8, term: 24, status: 'settled', date_applied: '2023-03-10', date_approved: '2023-03-15', form_id: 303, idNumber: '123456789012345680' }
        ];
  
        loans.value = allLoans.filter(loan => loan.idNumber === searchForm.value.idNumber);
  
        if (loans.value.length === 0) {
          ElMessage.error('没有找到相关贷款记录');
        }
      };
  
      return {
        searchForm,
        loans,
        searchLoans
      };
    },
    data() {
      return {
        loanHistory: [],
        totalLoans: 0,
        pageSize: 10,
        currentPage: 1
      };
    },
    methods:{
      async searchLoans(id_number){
        try {
          const response = await this.$axios.get(`/search-forms/${id_number}`, {
            params: {
              page: this.currentPage,
              pageSize: this.pageSize
            }
          });
          this.loanHistory = response.data.records;
          this.totalLoans = response.data.total;
          // Log the total number of loans
          console.log('Total Loans:', this.totalApprovals, this.loanHistory);
          if(this.totalLoans == 0){  //0输出
            ElMessage.error('没有找到相关贷款记录');
          }
        } catch (error) {
          console.error('获取用户贷款历史时发生错误:', error);
          ElMessage.error('无法加载用户贷款历史。');
        }
      }
    }
  };
  </script>
  
  <style scoped>
  .user-loan-history-page {
    padding: 20px;
    background-color: #f9f9f9;
  }
  
  .search-form {
    margin-bottom: 20px;
  }
  
  .loan-list-container {
    max-height: calc(100vh - 200px);
    overflow-y: auto;
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  }
  
  .loan-table .el-table__header-wrapper {
    background-color: #409eff;
    color: #fff;
    font-weight: bold;
    font-size: 16px;
  }
  
  .loan-table .el-table__body-wrapper .el-table__row {
    font-size: 18px;
    font-weight: bold;
    height: 60px;
    border-bottom: 1px solid #ebeef5;
  }
  
  .loan-table .el-table__body-wrapper .el-table__row:hover {
    background-color: #f2f6fc;
  }
  
  .loan-table .el-table__cell {
    padding: 10px;
    font-weight: bold;
    color: #303133;
    text-align: center; /* 确保内容居中 */
  }
  
  .loan-table .el-table__header {
    text-align: center; /* 确保表头居中 */
  }
  </style>
  