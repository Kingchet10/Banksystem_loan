<template>
  <div class="loan-query-page">
    <div class="filter-container">
      <el-select v-model="sortKey" placeholder="选择排序方式" class="sort-select">
        <el-option label="贷款时间" value="date_applied"></el-option>
        <el-option label="贷款金额" value="amount"></el-option>
        <el-option label="贷款状态" value="status"></el-option>
      </el-select>
      <el-button type="primary" @click="sortLoans" class="sort-button">排序</el-button>
    </div>
    <div class="loan-list-container">
      <el-table :data="loans" style="width: 100%" class="loan-table">
        <el-table-column prop="loan_id" label="贷款编号" width="180" align="center"></el-table-column>
        <el-table-column prop="amount" label="贷款金额" width="180" align="center"></el-table-column>
        <el-table-column prop="date_applied" label="申请日期" width="180" align="center"></el-table-column>
        <el-table-column prop="status" label="贷款状态" width="180" align="center">
          <template #default="scope">
            <el-tag :type="getStatusType(scope.row.status)">
              {{ getStatusText(scope.row.status) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="150" align="center">
          <template #default="scope">
            <el-button type="primary" size="small" @click="viewLoanDetails(scope.row)" class="view-button">查看详情</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-dialog v-model="dialogVisible" title="贷款详情" width="600px" class="loan-dialog">
      <el-form :model="selectedLoan" label-width="120px" class="loan-form">
        <el-form-item label="贷款编号">
          <el-input v-model="selectedLoan.loan_id" disabled></el-input>
        </el-form-item>
        <el-form-item label="借款人编号">
          <el-input v-model="selectedLoan.borrow_id" disabled></el-input>
        </el-form-item>
        <el-form-item label="转入卡编号">
          <el-input v-model="selectedLoan.card_id" disabled></el-input>
        </el-form-item>
        <el-form-item label="审查员编号">
          <el-input v-model="selectedLoan.officer_id" disabled></el-input>
        </el-form-item>
        <el-form-item label="贷款金额">
          <el-input v-model="selectedLoan.amount" disabled></el-input>
        </el-form-item>
        <el-form-item label="贷款利率">
          <el-input v-model="selectedLoan.rate" disabled></el-input>
        </el-form-item>
        <el-form-item label="贷款期限">
          <el-input v-model="selectedLoan.term" disabled></el-input>
        </el-form-item>
        <el-form-item label="贷款状态">
          <el-input v-model="selectedLoan.status" disabled></el-input>
        </el-form-item>
        <el-form-item label="申请日期">
          <el-input v-model="selectedLoan.date_applied" disabled></el-input>
        </el-form-item>
        <el-form-item label="审批日期">
          <el-input v-model="selectedLoan.date_approved" disabled></el-input>
        </el-form-item>
        <el-form-item label="表单编号">
          <el-input v-model="selectedLoan.form_id" disabled></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" type="primary">关闭</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { ref } from 'vue';
import { ElTable, ElTableColumn, ElDialog, ElButton, ElSelect, ElOption, ElForm, ElFormItem, ElInput, ElTag } from 'element-plus';

export default {
  components: {
    ElTable, ElTableColumn, ElDialog, ElButton, ElSelect, ElOption, ElForm, ElFormItem, ElInput, ElTag
  },
  setup() {
    const loans = ref([
      {
        loan_id: 1,
        borrow_id: 101,
        card_id: 1001,
        officer_id: 201,
        amount: 50000,
        rate: 3.5,
        term: 36,
        status: 'application',
        date_applied: '2023-01-01',
        date_approved: '2023-01-05',
        form_id: 301
      },
      {
        loan_id: 2,
        borrow_id: 102,
        card_id: 1002,
        officer_id: 202,
        amount: 75000,
        rate: 4.0,
        term: 48,
        status: 'repayment',
        date_applied: '2023-02-15',
        date_approved: '2023-02-20',
        form_id: 302
      },
      {
        loan_id: 3,
        borrow_id: 103,
        card_id: 1003,
        officer_id: 203,
        amount: 30000,
        rate: 2.8,
        term: 24,
        status: 'settled',
        date_applied: '2023-03-10',
        date_approved: '2023-03-15',
        form_id: 303
      }
    ]);

    const sortKey = ref('');
    const dialogVisible = ref(false);
    const selectedLoan = ref({});

    const sortLoans = () => {
      if (sortKey.value) {
        loans.value.sort((a, b) => {
          if (sortKey.value === 'amount') {
            return a[sortKey.value] - b[sortKey.value];
          } else if (sortKey.value === 'date_applied') {
            return new Date(a[sortKey.value]) - new Date(b[sortKey.value]);
          } else {
            return a[sortKey.value].localeCompare(b[sortKey.value]);
          }
        });
      }
    };

    const viewLoanDetails = (row) => {
      selectedLoan.value = { ...row };
      dialogVisible.value = true;
    };

    const getStatusType = (status) => {
      switch (status) {
        case 'application':
          return 'info';
        case 'repayment':
          return 'success';
        case 'settled':
          return 'warning';
        case 'overdue':
          return 'danger';
        default:
          return 'default';
      }
    };

    const getStatusText = (status) => {
      switch (status) {
        case 'application':
          return '申请中';
        case 'repayment':
          return '还款中';
        case 'settled':
          return '已结清';
        case 'overdue':
          return '逾期';
        default:
          return status;
      }
    };

    return {
      loans,
      sortKey,
      dialogVisible,
      selectedLoan,
      sortLoans,
      viewLoanDetails,
      getStatusType,
      getStatusText
    };
  }
};
</script>

<style scoped>
.loan-query-page {
  padding: 20px;
  background-color: #f9f9f9;
}

.filter-container {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.sort-select {
  margin-right: 10px;
}

.sort-button {
  background-color: #409eff;
  border-color: #409eff;
  color: #fff;
}

.loan-list-container {
  max-height: calc(100vh - 150px);
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
}

.loan-table .el-table__cell--center {
  text-align: center;
}

.view-button {
  background-color: #67c23a;
  border-color: #67c23a;
  color: #fff;
}

.loan-dialog .el-dialog__header {
  background-color: #409eff;
  color: #fff;
}

.loan-dialog .el-dialog__body {
  padding: 20px;
}

.loan-dialog .el-dialog__footer {
  background-color: #f9f9f9;
  text-align: right;
  padding: 10px 20px;
}

.dialog-footer {
  text-align: right;
}

.loan-form .el-form-item__label {
  color: #409eff;
}
</style>
