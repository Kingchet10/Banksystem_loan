<template>
    <div class="repayment-reminder-page">
      <el-form :model="reminderSettings" label-width="120px" class="reminder-form">
        <el-form-item label="选择提醒时间">
          <el-select v-model="reminderSettings.timeBefore" placeholder="请选择提醒时间">
            <el-option label="1天" value="1_day"></el-option>
            <el-option label="1周" value="1_week"></el-option>
            <el-option label="1个月" value="1_month"></el-option>
            <el-option label="2个月" value="2_months"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="saveSettings">保存设置</el-button>
        </el-form-item>
      </el-form>
      <el-dialog v-model="reminderDialogVisible" title="还款提醒" width="400px">
        <p>您的贷款ID: {{ upcomingLoanId }} 将于 {{ upcomingLoanDate }} 到期，请及时归还。</p>
        <span slot="footer" class="dialog-footer">
          <el-button @click="reminderDialogVisible = false" type="primary">关闭</el-button>
        </span>
      </el-dialog>
    </div>
  </template>
  
  <script>
  import { ref, onMounted } from 'vue';
  import { ElForm, ElFormItem, ElInput, ElButton, ElSelect, ElOption, ElDialog } from 'element-plus';
  
  export default {
    components: {
      ElForm, ElFormItem, ElInput, ElButton, ElSelect, ElOption, ElDialog
    },
    setup() {
      const reminderSettings = ref({
        timeBefore: '1_day'
      });
  
      const reminderDialogVisible = ref(false);
      const upcomingLoanId = ref('');
      const upcomingLoanDate = ref('');
  
      const loans = [
        { loan_id: 1, due_date: '2024-06-15' },
        { loan_id: 2, due_date: '2024-07-01' },
        { loan_id: 3, due_date: '2024-06-25' },
      ];
  
      const checkReminders = () => {
        const now = new Date();
        const reminders = {
          '1_day': 1,
          '1_week': 7,
          '1_month': 30,
          '2_months': 60,
        };
  
        const daysBefore = reminders[reminderSettings.value.timeBefore];
  
        for (const loan of loans) {
          const dueDate = new Date(loan.due_date);
          const reminderDate = new Date(dueDate);
          reminderDate.setDate(dueDate.getDate() - daysBefore);
  
          if (now >= reminderDate && now <= dueDate) {
            upcomingLoanId.value = loan.loan_id;
            upcomingLoanDate.value = loan.due_date;
            reminderDialogVisible.value = true;
            break;
          }
        }
      };
  
      const saveSettings = () => {
        localStorage.setItem('reminderSettings', JSON.stringify(reminderSettings.value));
        checkReminders();
      };
  
      onMounted(() => {
        const savedSettings = JSON.parse(localStorage.getItem('reminderSettings'));
        if (savedSettings) {
          reminderSettings.value = savedSettings;
        }
        checkReminders();
      });
  
      return {
        reminderSettings,
        reminderDialogVisible,
        upcomingLoanId,
        upcomingLoanDate,
        saveSettings
      };
    }
  };
  </script>
  
  <style scoped>
  .repayment-reminder-page {
    padding: 20px;
    background-color: #f9f9f9;
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  
  .reminder-form {
    width: 300px;
    margin-top: 20px;
  }
  
  .dialog-footer {
    text-align: right;
  }
  </style>
  