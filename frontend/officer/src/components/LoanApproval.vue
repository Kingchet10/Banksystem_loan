<template>
  <div class="loan-query-page">
    <div class="loan-list-container">
      <el-table :data="loans" style="width: 100%" class="loan-table" header-align="center" align="center">
        <el-table-column prop="loan_id" label="贷款编号" width="180"></el-table-column>
        <el-table-column prop="amount" label="贷款金额" width="180"></el-table-column>
        <el-table-column prop="name" label="贷款人" width="180"></el-table-column>
        <el-table-column prop="date_applied" label="贷款时间" width="180"></el-table-column>
        <el-table-column label="操作" width="450">
          <template #default="scope">
            <el-button type="primary" size="small" @click="viewLoanDetails(scope.row.loan_id)" class="view-button">查看详情</el-button>
            <el-button type="success" size="small" @click="openApprovalDialog(scope.row)" class="approve-button">审批</el-button>
            <el-button type="info" size="small" @click="viewCreditReport(scope.row.loan_id)" class="credit-button">查看征信</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination background
                     layout="prev, pager, next, total"
                     :total="totalLoans"
                     :page-size="pageSize"
                     @current-change="handlePageChange"
                     :current-page="currentPage">
      </el-pagination>
      <el-pagination background
                     layout="prev, pager, next, total"
                     :total="totalloans"
                     :page-size="pageSize"
                     @current-change="handlePageChange"
                     :current-page="currentPage">
      </el-pagination>
    </div>
    <el-dialog v-model="dialogVisible" title="贷款详情" width="600px" :modal-append-to-body="false">
      <div class="dialog-content">
        <el-form :model="selectedLoan" label-position="top" label-width="120px" class="loan-form">
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="姓名(Name)">
                <el-input v-model="selectedLoan.name" disabled />
              </el-form-item>
              <el-form-item label="身份证号(ID Number)">
                <el-input v-model="selectedLoan.idNumber" disabled />
              </el-form-item>
              <el-form-item label="出生日期(Date of Birth)">
                <el-date-picker v-model="selectedLoan.dob" type="date" placeholder="选择日期" disabled />
              </el-form-item>
              <el-form-item label="性别(Gender)">
                <el-select v-model="selectedLoan.gender" placeholder="请选择性别" disabled>
                  <el-option label="男" value="male"></el-option>
                  <el-option label="女" value="female"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="婚姻情况(Marital Status)">
                <el-select v-model="selectedLoan.maritalStatus" placeholder="请选择婚姻情况" disabled>
                  <el-option label="已婚" value="married"></el-option>
                  <el-option label="未婚" value="unmarried"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="职业(Occupation)">
                <el-input v-model="selectedLoan.occupation" disabled />
              </el-form-item>
              <el-form-item label="年收入(Annual Income)">
                <el-input v-model="selectedLoan.annualIncome" disabled />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="住宅地址(Residential Address)">
                <el-input v-model="selectedLoan.residentialAddress" disabled />
              </el-form-item>
              <el-form-item label="电话号码(Phone Number)">
                <el-input v-model="selectedLoan.phoneNumber" disabled />
              </el-form-item>
              <el-form-item label="电子邮件地址(Email Address)">
                <el-input v-model="selectedLoan.email" disabled />
              </el-form-item>
              <el-form-item label="教育背景(Education Background)">
                <el-input v-model="selectedLoan.educationBackground" disabled />
              </el-form-item>
              <el-form-item label="贷款用途(Loan Purpose)">
                <el-input v-model="selectedLoan.loanPurpose" disabled />
              </el-form-item>
              <el-form-item label="贷款金额(Loan Amount)">
                <el-input v-model="selectedLoan.loanAmount" disabled />
              </el-form-item>
              <el-form-item label="贷款期限(Loan Term)">
                <el-input v-model="selectedLoan.loanTerm" disabled />
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item label="个人情况声明(Personal Statement)">
            <el-input type="textarea" :autosize="{ minRows: 7, maxRows: 14 }" v-model="selectedLoan.personalStatement" disabled />
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">关闭</el-button>
      </span>
    </el-dialog>
    <el-dialog v-model="approvalDialogVisible" title="贷款审批" width="400px" :modal-append-to-body="false">
      <p>是否同意此贷款申请？</p>
      <span slot="footer" class="dialog-footer">
        <el-button type="success" @click="approveLoan">同意</el-button>
        <el-button type="danger" @click="rejectLoan">拒绝</el-button>
        <el-button @click="approvalDialogVisible = false">关闭</el-button>
      </span>
    </el-dialog>
    <el-dialog v-model="creditDialogVisible" title="征信情况" width="600px" :modal-append-to-body="false">
      <div class="dialog-content">
        <el-form :model="selectedCredit" label-position="top" label-width="120px" class="credit-form">
          <el-form-item label="姓名(Name)">
            <el-input v-model="selectedCredit.name" disabled />
          </el-form-item>
          <el-form-item label="信用评分(Credit Score)">
            <el-input v-model="selectedCredit.creditScore" disabled />
          </el-form-item>
          <el-form-item label="信用额度(Credit limit)">
            <el-input v-model="selectedCredit.creditlimit"  disabled />
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="creditDialogVisible = false">关闭</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { ref } from 'vue';
import { ElTable, ElTableColumn, ElDialog, ElButton, ElForm, ElFormItem, ElInput, ElRow, ElCol, ElDatePicker, ElSelect, ElOption, ElMessage } from 'element-plus';

export default {
  components: {
    ElTable, ElTableColumn, ElDialog, ElButton, ElForm, ElFormItem, ElInput, ElRow, ElCol, ElDatePicker, ElSelect, ElOption, ElMessage
  },

  setup() {
    const loans = ref([
      { loan_id: 1, borrow_id: 101, card_id: 1001, officer_id: 201, amount: 50000, rate: 3.5, term: 36, status: 'application', date_applied: '2023-01-01', date_approved: '2023-01-05', form_id: 301, name: '张三', idNumber: '123456789012345678', dob: '1990-01-01', gender: 'male', maritalStatus: 'married', occupation: '工程师', annualIncome: '100000', residentialAddress: '北京市朝阳区', phoneNumber: '12345678901', email: 'zhangsan@example.com', educationBackground: '本科', loanPurpose: '购房', loanAmount: '500000', loanTerm: '36', personalStatement: '无' },
      { loan_id: 2, borrow_id: 102, card_id: 1002, officer_id: 202, amount: 75000, rate: 4.0, term: 48, status: 'repayment', date_applied: '2023-02-15', date_approved: '2023-02-20', form_id: 302, name: '李四', idNumber: '123456789012345679', dob: '1991-02-01', gender: 'female', maritalStatus: 'unmarried', occupation: '医生', annualIncome: '200000', residentialAddress: '上海市浦东新区', phoneNumber: '12345678902', email: 'lisi@example.com', educationBackground: '硕士', loanPurpose: '购车', loanAmount: '750000', loanTerm: '48', personalStatement: '无' },
      { loan_id: 3, borrow_id: 103, card_id: 1003, officer_id: 203, amount: 30000, rate: 2.8, term: 24, status: 'settled', date_applied: '2023-03-10', date_approved: '2023-03-15', form_id: 303, name: '王五', idNumber: '123456789012345680', dob: '1992-03-01', gender: 'male', maritalStatus: 'married', occupation: '教师', annualIncome: '150000', residentialAddress: '广州市天河区', phoneNumber: '12345678903', email: 'wangwu@example.com', educationBackground: '博士', loanPurpose: '旅游', loanAmount: '300000', loanTerm: '24', personalStatement: '无' }
    ]);

    const dialogVisible = ref(false);
    const approvalDialogVisible = ref(false);
    const creditDialogVisible = ref(false);
    const selectedLoan = ref({});
    const selectedCredit = ref({});

    const viewLoanDetails = (loan) => {
      selectedLoan.value = loan;
      dialogVisible.value = true;
    };

    const openApprovalDialog = (loan) => {
      selectedLoan.value = loan;
      approvalDialogVisible.value = true;
    };

    const viewCreditReport = (loan) => {
      // 模拟获取征信报告
      selectedCredit.value = {
        name: loan.name,
        creditScore: Math.floor(Math.random() * 300) + 600, // 随机生成一个信用评分
        creditlimit: 10000,// 模拟信用历史信息
      };
      creditDialogVisible.value = true;
    };

    return {
      loans,
      dialogVisible,
      approvalDialogVisible,
      creditDialogVisible,
      selectedLoan,
      selectedCredit,
      viewLoanDetails,
      openApprovalDialog,
      viewCreditReport,
    };
  },

  data() {
    return {
      loansData: [],
      totalLoans: 0,
      pageSize: 10,
      currentPage: 1
    };
  },
  mounted() {
    this.fetchloans();
  },
  methods: {
    async fetchloans() {
      try {
        const loanResponse = await this.$axios.get('/get-loans', {
          params: {
            page: this.currentPage,
            pageSize: this.pageSize
          }
        });
        const formResponse = await this.$axios.get(`/get-forms/${loanResponse.records.form_id}`);
        this.loans = loanResponse.data.records + formResponse.data.records;
        this.totalLoans = loanResponse.data.total;
        // Log the total number of loans
        console.log('Total Loans:', this.totalLoans, this.loansData);
      } catch (error) {
        console.error('获取贷款申请时发生错误:', error);
        ElMessage.error('无法加载贷款申请。');
      }
    },
    async approveLoan(id) {
      try{
        const response = await this.$axios.put(`/approve-loan/${id}`);
        this.fetchloans();
        ElMessage.success('贷款申请已同意。');
      }catch (error) {
        if (error !== 'cancel') {
          console.error('审批时发生错误:', error);
          ElMessage.error('审批失败。');
        }
      }
    },
    async rejectLoan(id){
      try{
        const response = await this.$axios.put(`/reject-loan/${id}`);
        this.fetchloans();
        ElMessage.success('贷款申请已拒绝。');
      } catch (error) {
        if(error !== 'cancel'){
          console.error('审批时发生错误:',error);
          ElMessage.error(('审批失败。'));
        }
      }
    },
    async viewLoanDetails(id) {
      try {
        const response = await this.$axios.get(`/get-detailed-loan/${id}`);
        dialogVisible.value = true;
      }catch (error) {

      }
    },
    async viewCreditReport(){

    },
    handlePageChange(page) {
      this.currentPage = page;
      this.fetchOfficers();
    }
  },
};
</script>

<style scoped>
.loan-query-page {
  padding: 20px;
  background-color: #f9f9f9;
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
  text-align: center; /* 确保内容居中 */
}

.loan-table .el-table__header {
  text-align: center; /* 确保表头居中 */
}

.view-button {
  background-color: #67c23a;
  border-color: #67c23a;
  color: #fff;
}

.approve-button {
  margin-left: 10px;
  background-color: #13ce66;
  border-color: #13ce66;
  color: #fff;
}

.credit-button {
  margin-left: 10px;
  background-color: #409eff;
  border-color: #409eff;
  color: #fff;
}

.loan-dialog .el-dialog__header {
  background-color: #409eff;
  color: #fff;
}

.loan-dialog .dialog-content {
  max-height: 400px;
  overflow-y: auto;
}

.loan-dialog .el-dialog__body {
  padding: 0 20px;
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

.credit-dialog .el-dialog__header {
  background-color: #409eff;
  color: #fff;
}

.credit-dialog .dialog-content {
  max-height: 400px;
  overflow-y: auto;
}

.credit-dialog .el-dialog__body {
  padding: 0 20px;
}

.credit-dialog .el-dialog__footer {
  background-color: #f9f9f9;
  text-align: right;
  padding: 10px 20px;
}

.credit-form .el-form-item__label {
  color: #409eff;
}
</style>
