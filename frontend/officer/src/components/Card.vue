<template>
  <el-scrollbar height="100%" style="width: 100%;">
    <!-- 标题和搜索框 -->
    <div style="margin-top: 20px; margin-left: 40px; font-size: 2em; font-weight: bold; ">借书证管理和借还书
      <el-input v-model="toSearch" :prefix-icon="Search"
                style=" width: 15vw;min-width: 150px; margin-left: 30px; margin-right: 30px; float: right;" clearable />
    </div>

    <!-- 借书证卡片显示区 -->
    <div style="display: flex;flex-wrap: wrap; justify-content: start;">

      <!-- 借书证卡片 -->
      <div class="cardBox" v-for="card in cards" v-show="card.name.includes(toSearch)" :key="card.cardId">
        <div>
          <!-- 卡片标题 -->
          <div style="font-size: 25px; font-weight: bold;">No. {{ card.cardId }}</div>

          <el-divider />

          <!-- 卡片内容 -->
          <div style="margin-left: 10px; text-align: start; font-size: 16px;">
            <p style="padding: 2.5px;"><span style="font-weight: bold;">姓名：</span>{{ card.name }}</p>
            <p style="padding: 2.5px;"><span style="font-weight: bold;">部门：</span>{{ card.department }}</p>
            <p style="padding: 2.5px;"><span style="font-weight: bold;">类型：</span>{{ card.type }}</p>
          </div>

          <el-divider />

          <!-- 卡片操作 -->
          <div style="margin-top: 10px;">
            <el-button type="primary" :icon="Edit" @click="this.toModifyInfo.cardId = card.cardId, this.toModifyInfo.name = card.name,
                this.toModifyInfo.department = card.department, this.toModifyInfo.type = card.type,
                this.modifyCardVisible = true" circle />
            <el-button type="danger" :icon="Delete" circle
                       @click="this.toRemove = card.cardId, this.removeCardVisible = true"
                       style="margin-left: 10px;" />
            <el-button type="danger" :icon="Info" circle
                       @click="this.cardId = card.cardId, this.borrowvisible = true"
                       style="margin-left: 10px;">
              借书
            </el-button>
            <el-button type="danger" :icon="Info" circle
                       @click="this.cardId = card.cardId, this.returnvisible = true"
                       style="margin-left: 10px;">
              还书
            </el-button>
          </div>

        </div>
      </div>

      <!-- 新建借书证卡片 -->
      <el-button class="newCardBox"
                 @click="newCardInfo.name = '', newCardInfo.department = '', newCardInfo.type = '学生', newCardVisible = true">
        <el-icon style="height: 50px; width: 50px;">
          <Plus style="height: 100%; width: 100%;" />
        </el-icon>
      </el-button>

    </div>


    <!-- 新建借书证对话框 -->
    <el-dialog v-model="newCardVisible" title="新建借书证" width="30%" align-center>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        姓名：
        <el-input v-model="newCardInfo.name" style="width: 12.5vw;" clearable />
      </div>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        部门：
        <el-input v-model="newCardInfo.department" style="width: 12.5vw;" clearable />
      </div>
      <div style="margin-left: 2vw;   font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        类型：
        <el-select v-model="newCardInfo.type" size="large" style="width: 12.5vw;">
          <el-option v-for="type in types" :key="type.value" :label="type.label" :value="type.value" />
        </el-select>
      </div>

      <template #footer>
                <span>
                    <el-button @click="newCardVisible = false">取消</el-button>
                    <el-button type="primary" @click="ConfirmNewCard"
                               :disabled="newCardInfo.name.length === 0 || newCardInfo.department.length === 0">确定</el-button>
                </span>
      </template>
    </el-dialog>


    <!-- 修改信息对话框 -->
    <el-dialog v-model="modifyCardVisible" :title="'修改信息(借书证cardId: ' + this.toModifyInfo.cardId + ')'" width="30%"
               align-center>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        姓名：
        <el-input v-model="toModifyInfo.name" style="width: 12.5vw;" clearable />
      </div>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        部门：
        <el-input v-model="toModifyInfo.department" style="width: 12.5vw;" clearable />
      </div>
      <div style="margin-left: 2vw;   font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        类型：
        <el-select v-model="toModifyInfo.type" size="middle" style="width: 12.5vw;">
          <el-option v-for="type in types" :key="type.value" :label="type.label" :value="type.value" />
        </el-select>
      </div>

      <template #footer>
                <span class="dialog-footer">
                    <el-button @click="modifyCardVisible = false">取消</el-button>
                    <el-button type="primary" @click="ConfirmModifyCard"
                               :disabled="toModifyInfo.name.length === 0 || toModifyInfo.department.length === 0">确定</el-button>
                </span>
      </template>
    </el-dialog>

    <!-- 删除借书证对话框 -->
    <el-dialog v-model="removeCardVisible" title="删除借书证" width="30%">
      <span>确定删除<span style="font-weight: bold;">{{ toRemove }}号借书证</span>吗？</span>

      <template #footer>
                <span class="dialog-footer">
                    <el-button @click="removeCardVisible = false">取消</el-button>
                    <el-button type="danger" @click="ConfirmRemoveCard">
                        删除
                    </el-button>
                </span>
      </template>
    </el-dialog>


    <!-- 借书对话框 -->
    <el-dialog v-model="borrowvisible" title="借书" width="30%">
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        要借的书的id
        <el-input v-model="bookId" style="width: 12.5vw;" clearable />
      </div>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        借书时间，注意时间格式
        <el-input v-model="borrowtime" style="width: 12.5vw;" clearable />
      </div>

      <template #footer>
                <span class="dialog-footer">
                    <el-button @click="borrowvisible = false">取消</el-button>
                    <el-button type="danger" @click="ConfirmBorrow">
                        Borrow
                    </el-button>
                </span>
      </template>
    </el-dialog>

    <!-- 还书对话框 -->
    <el-dialog v-model="returnvisible" title="还书" width="30%">
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        要还的书的id
        <el-input v-model="bookId" style="width: 12.5vw;" clearable />
      </div>
      <div style="margin-left: 2vw; font-weight: bold; font-size: 1rem; margin-top: 20px; ">
        还书时间，注意时间格式
        <el-input v-model="returntime" style="width: 12.5vw;" clearable />
      </div>

      <template #footer>
                <span class="dialog-footer">
                    <el-button @click="returnvisible = false">取消</el-button>
                    <el-button type="danger" @click="ConfirmReturn">
                        return
                    </el-button>
                </span>
      </template>
    </el-dialog>

  </el-scrollbar>
</template>

<script>
import { Delete, Edit, Search } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import axios from 'axios'

export default {
  data() {
    return {
      cards: [{ // 借书证列表
        cardId: 1,
        name: '小明',
        department: '计算机学院',
        type: '学生'
      }, {
        cardId: 2,
        name: '王老师',
        department: '计算机学院',
        type: '教师'
      }
      ],
      Delete,
      Edit,
      Search,
      toSearch: '', // 搜索内容
      types: [ // 借书证类型
        {
          value: '教师',
          label: '教师',
        },
        {
          value: '学生',
          label: '学生',
        }
      ],
      newCardVisible: false, // 新建借书证对话框可见性
      removeCardVisible: false, // 删除借书证对话框可见性

      borrowvisible: false,
      returnvisible: false,
      cardId:0,
      bookId:0,
      borrowtime:202401011000,
      returntime:202401011100,

      toRemove: 0, // 待删除借书证号
      newCardInfo: { // 待新建借书证信息
        name: '',
        department: '',
        type: '学生'
      },
      modifyCardVisible: false, // 修改信息对话框可见性
      toModifyInfo: { // 待修改借书证信息
        cardId: 0,
        name: '',
        department: '',
        type: '学生'
      },
    }
  },
  methods: {
    ConfirmNewCard() {
      // 发出POST请求
      let response =axios.post("/card2",
          { // 请求体
            name: this.newCardInfo.name,
            department: this.newCardInfo.department,
            type: this.newCardInfo.type
          })
          .then(response => {
            let back=response.data;
            console.log(back);
            if(back=="1")
            {
              ElMessage.success("借书证新建成功") // 显示消息提醒
              this.newCardVisible = false // 将对话框设置为不可见
              this.QueryCards() // 重新查询借书证以刷新页面
            }
            else
            {
              alert("You have registered the same card before");
            }
          }).catch(error => {
            // 处理错误
            console.error("An error occurred:", error);
            alert("An error occurred");
          });
    },

    ConfirmModifyCard() {
      // TODO: YOUR CODE HERE
    },
    ConfirmRemoveCard() {
      // 发出POST请求删除借书证
      axios.post("/removeCard", {
        cardId: this.toRemove
      })
          .then(response => {
            let result = response.data;
            console.log(result);
            if (result == "1") {
              ElMessage.success("借书证删除成功");
              this.removeCardVisible = false;
              this.QueryCards();
            } else {
              ElMessage.error("删除失败");
            }
          })
          .catch(error => {
            // 处理错误
            console.error("An error occurred:", error);
            ElMessage.error("操作时发生错误");
          });
    },
    QueryCards() {
      this.cards = [] // 清空列表
      let response = axios.get('/card1') // 向/card发出GET请求
          .then(response => {
            let cards = response.data // 接收响应负载
            cards.forEach(card => { // 对于每个借书证
              this.cards.push(card) // 将其加入到列表中
              //ElMessage.success("借书证查询成功") // 显示消息提醒

            })
          })
          .catch(error => {
            // 处理错误
            console.error("An error occurred:", error);
            alert("An error occurred");
          });
    },
    ConfirmBorrow(){
      axios.post("/borrow1", {
        cardID: this.cardId,
        bookID: this.bookId,
        borrowTime: this.borrowtime,
        returnTime: 0
      })
          .then(response => {
            let result = response.data;
            console.log(result);
            if (result == "1") {
              ElMessage.success("借书成功");
              this.removeCardVisible = false;
              this.QueryCards();
            } else {
              ElMessage.error("借书失败");
            }
          })
          .catch(error => {
            // 处理错误
            console.error("An error occurred:", error);
            ElMessage.error("操作时发生错误");
          });
    },
    ConfirmReturn()
    {
      axios.post("/return1", {
        cardID: this.cardId,
        bookID: this.bookId,
        borrowTime: this.borrowtime,
        returnTime: this.returntime
      })
          .then(response => {
            let result = response.data;
            console.log(result);
            if (result == "1") {
              ElMessage.success("还书成功");
              this.removeCardVisible = false;
              this.QueryCards();
            } else {
              ElMessage.error("还书失败");
            }
          })
          .catch(error => {
            // 处理错误
            console.error("An error occurred:", error);
            ElMessage.error("操作时发生错误");
          });
    }
  },

  mounted() { // 当页面被渲染时
    this.QueryCards() // 查询借书
  }
}

</script>


<style scoped>
.cardBox {
  height: 300px;
  width: 200px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  text-align: center;
  margin-top: 40px;
  margin-left: 27.5px;
  margin-right: 10px;
  padding: 7.5px;
  padding-right: 10px;
  padding-top: 15px;
}

.newCardBox {
  height: 300px;
  width: 200px;
  margin-top: 40px;
  margin-left: 27.5px;
  margin-right: 10px;
  padding: 7.5px;
  padding-right: 10px;
  padding-top: 15px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  text-align: center;
}
</style>